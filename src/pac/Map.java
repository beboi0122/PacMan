package pac;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *	Ez az oszt�ly rajzolja ki a p�ly�t, valamint PacMan-t �s a szellemeket.
 */
public class Map extends JPanel implements Runnable{
    private ArrayList<String> map = new ArrayList<String>();
    private int start_time;
    private GamePanel GamPan;
    private int points;
    private Game game;
    private boolean End;
    private Color color;

    private PacMan Pacman;
    
    private EndOfGame end;

    /** 0-Blinky, 1-Pinky, 2-Inky, 3-Clyde **/
    ArrayList<Ghosts> ghosts;

    /** 0-PacMan, 1-Blinky, 2-Pinky, 3-Inky, 4-Clyde **/
    ArrayList<Thread> threads;

    /**
     * Map oszt�ly konstruktora.
     * F�jlb�l beolvassa a p�ly�t amit egy list�ban t�rol el.
     * @param path A f�jl el�r�se amiben a p�lya van.
     * @param game A JFrame amiben ez a Map oszt�ly benne van.
     * @param color A falaknak a sz�ne.
     */
    public Map(String path, Game game, Color color){
        this.color = color;
        this.game = game;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) break;
            map.add(line);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * A JPanel egyik f�gv�nye.
     * Kirajzolja a p�ly�t, a pelleteket, a szellemeket �s pacmant.
     **/
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i =0; i < map.size(); i++) {
            for(int j = 0; j < map.get(0).length(); ++j) {
                if(map.get(i).charAt(j)== '#') {
                    g.setColor(color);
                    g.fillRect(30*j, 34*i, 30, 34);
                    g.setColor(Color.BLACK);
                    g.drawRect(30*j, 34*i, 30, 34);
                }
                if(map.get(i).charAt(j) == ' ' || map.get(i).charAt(j) == 'P' || map.get(i).charAt(j) == 'b' ){
                    g.setColor(new Color(255, 174, 51));
                    g.fillRoundRect(30*j+12, 34*i+13, 8, 8, 10, 10);
                }
                if(map.get(i).charAt(j) == '-'){
                    g.setColor(Color.WHITE);
                    g.fillRect(30*j, 34*i+10, 30, 10);
                    g.setColor(Color.BLACK);
                    g.drawRect(30*j, 34*i, 30, 34);
                }
            }
        }
        //pacman
        g.drawImage(getPacman().getImg(), Pacman.getX(), Pacman.getY(), 30, 34, null);
        //ghosts
        try{
            for(int i = 0; i < ghosts.size(); ++i){
                g.drawImage(ghosts.get(i).getImg(), ghosts.get(i).getX(), ghosts.get(i).getY(), 30, 34, null);
            };
        }catch (NullPointerException e){}
    }

    /**
     * Megsz�molja �s elt�rolja egy integerben, hogy h�ny pellet van �sszesen a p�ly�n.
     * @param map   A string lista ami a p�ly�t tartalamzza.
     */
    public void pointsset(ArrayList<String> map){
        for(int i =0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).length(); ++j) {
                if(map.get(i).charAt(j) == ' ' || map.get(i).charAt(j) == 'P' || map.get(i).charAt(j) == 'b' ){
                    ++points;
                }
            }
        }
    }

    /**
     * A p�ly�ra visszat�lti a pelleteket, hogy egy �jabb k�r kezd�dhessen.
     * @param map   A string lista ami a p�ly�t tartalamzza.
     */
    public void NewRound(ArrayList<String> map){
        for(int i =0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).length(); ++j) {
                if(map.get(i).charAt(j) == '0'){
                    StringBuilder tmp = new StringBuilder(map.get(i));
                    tmp.setCharAt(j, ' ');
                    map.set(i, tmp.toString());
                }
            }
        }
    }

    /**
     * Ellen�rzi, hogy �tk�z�tt-e PacMan egy szellemel.
     * @return  true- ha �tk�z�tt, false- ha nem �tk�z�tt.
     */
    public boolean isCollided(){
        try {
            for (int i = 0; i < ghosts.size(); ++i) {
                if ((Pacman.getX() + 15) / 30 == (ghosts.get(i).getX() + 15) / 30 && (Pacman.getY() + 17) / 34 == (ghosts.get(i).getY() + 17) / 34) {
                    return true;
                }
            }
        }catch (NullPointerException e){
            e.getMessage();
        }
        return false;
    }

    /**
     * Ez a f�ggv�ny akkor h�v�dik meg, ha pacman �tk�z�tt egy szellemel.
     * Ellen�rzi, hogy van-e meg pacmannak �lete, ha nincs megh�vja a GameOver f�ggv�nyt �s a j�t�knak v�ge,
     *        egy�bk�nt csak  levon egyett PacMan �let�b�l.
     */
    public void Collide() {
        if(Pacman.getLife() == 1){
            GameOver();
        }
        if (Pacman.getLife() >= 0) {
            Pacman.setLife(Pacman.getLife() - 1);
            if(GamPan != null) {
            	GamPan.PacLife(Pacman.getLife());
            }
        }
    }

    /**
     * Ez a f�gv�ny h�v�dik meg ha a j�t�knak v�ge.
     * Vagy mert PacMan �sszes �lete elfogyott, vagy mert a j�t�kos le�ll�t�tta a j�t�kot.
     * Jelzi a szellem illetve a PacMan objektumnak, hogy a j�t�knak v�ge,
     *      valamint megnyit egy �j ablakot (EndOdGame) ahol a j�t�kos be�thatja a nev�t.
     *      Ezut�n megh�vja a Game objektumnak a close f�ggv�ny�t ami le�ll�tja a j�t�kot.
     */
    public void GameOver(){
        Pacman.setEnd(true);
        try {
            for(int i = 0; i < ghosts.size(); ++i){
                ghosts.get(i).setEnd(true);
            }
        }catch (NullPointerException e){}

        int time = (int)System.currentTimeMillis() -start_time;
        end = new EndOfGame(Pacman.getPoints(), time);
        end.setVisible(true);
        while(!end.isDone()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end.dispose();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.Close();
    }

    @Override
    public void run() {
        for(int i = 0; i < threads.size(); ++i){
            threads.get(i).start();
        }
        pointsset(map);
        start_time = (int)System.currentTimeMillis();
        while(true) {
            if(Pacman.isEndGame()){
                GameOver();
            }
            if(GamPan != null) {
            	GamPan.setTime((int)System.currentTimeMillis() - start_time);
            }
            repaint();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Pacman.getDots() >= points){
                Pacman.setNoDotsLeft(true);
                NewRound(map);
            }
            try {
                GamPan.setPacman_points(Pacman.getPoints());
            }catch (NullPointerException e){
                e.getMessage();
            }

            if(isCollided()){
                Collide();
                Pacman.newRoud();
                try {
                    for(int i = 0; i < ghosts.size(); ++i){
                        ghosts.get(i).NewRound();
                    }
                }catch (NullPointerException e){}

            }
            if(isEnd()){
                break;
            }
        }
    }

    //setters
    public void setGamPan(GamePanel gamPan) {
        GamPan = gamPan;
    }
    public void setEnd(boolean end) {
        End = end;
    }
    public void setGhosts(ArrayList<Ghosts> ghosts) {
        this.ghosts = ghosts;
    }
    public void setThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }
    public void setPacMan(PacMan psm){
        Pacman = psm;
    }
    public void setPoint(int point) {
    	points = point;
    }


    //getters
    public ArrayList<String> getMap(){
        return map;
    }
    public boolean isEnd() {
        return End;
    }
    public int getStart_time() {
        return start_time;
    }
    public Game getGame() {
        return game;
    }
    public GamePanel getGamPan() {
        return GamPan;
    }
    public int getPoints() {
        return points;
    }
    public Color getColor() {
        return color;
    }
    public PacMan getPacman() {
        return Pacman;
    }
    public ArrayList<Ghosts> getGhosts() {
    	return ghosts;
    }
    public EndOfGame getEnd(){
    	return end;
    }
}
