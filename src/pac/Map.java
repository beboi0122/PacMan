package pac;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *	Ez az osztály rajzolja ki a pályát, valamint PacMan-t és a szellemeket.
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
     * Map osztály konstruktora.
     * Fájlból beolvassa a pályát amit egy listában tárol el.
     * @param path A fájl elérése amiben a pálya van.
     * @param game A JFrame amiben ez a Map osztály benne van.
     * @param color A falaknak a színe.
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
     * A JPanel egyik fügvénye.
     * Kirajzolja a pályát, a pelleteket, a szellemeket és pacmant.
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
     * Megszámolja és eltárolja egy integerben, hogy hány pellet van összesen a pályán.
     * @param map   A string lista ami a pályát tartalamzza.
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
     * A pályára visszatölti a pelleteket, hogy egy újabb kõr kezdõdhessen.
     * @param map   A string lista ami a pályát tartalamzza.
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
     * Ellenörzi, hogy ütközött-e PacMan egy szellemel.
     * @return  true- ha ütközött, false- ha nem ütközött.
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
     * Ez a föggvény akkor hívódik meg, ha pacman ütközött egy szellemel.
     * Ellenörzi, hogy van-e meg pacmannak élete, ha nincs meghívja a GameOver függvényt és a játéknak vége,
     *        egyébként csak  levon egyett PacMan életébõl.
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
     * Ez a fügvény hívódik meg ha a játéknak vége.
     * Vagy mert PacMan összes élete elfogyott, vagy mert a játékos leállítótta a játékot.
     * Jelzi a szellem illetve a PacMan objektumnak, hogy a játéknak vége,
     *      valamint megnyit egy új ablakot (EndOdGame) ahol a játékos beíthatja a nevét.
     *      Ezután meghívja a Game objektumnak a close függvényét ami leállítja a játékot.
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
