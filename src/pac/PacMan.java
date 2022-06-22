package pac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * A karakternek akit a j�t�kos ir�nyit (PacMan) oszt�lya.
 */
public class PacMan extends Specie implements KeyListener {
    private Image full;
    private Image[] left = new Image[2];
    private Image[] up = new Image[2];
    private Image[] down = new Image[2];
    private Image[] right = new Image[2];
    private Image[] actual = new Image[2];
    private Image[] tmp = new Image[2];
    private int tempDX;
    private int tempDY;
    private int dots;
    private int points;
    private int Life;
    private boolean EndGame;

    /**
     * PacMan oszt�ly konstruktora.
     * Bet�lti a hozz� tartoz� k�peket �s be�ll�tja PacMan kezd�pozici�j�t.
     * @param map Egy string lista ami az aktu�lia p�ly�t tartalmazza.
     */
    public PacMan(ArrayList<String> map) {
        setMap(map);
        StartPosition(map, 'P');
        setX(getStart_x() * 30);
        setY(getStart_y() * 34);
        Life = 3;

        ImageIcon fl = new ImageIcon("pics/pacman/closed.png");
        full = fl.getImage();

        ImageIcon lf = new ImageIcon("pics/pacman/left1.png");
        left[0] = lf.getImage();
        lf = new ImageIcon("pics/pacman/left2.png");
        left[1] = lf.getImage();


        ImageIcon u = new ImageIcon("pics/pacman/up1.png");
        up[0] = u.getImage();
        u = new ImageIcon("pics/pacman/up2.png");
        up[1] = u.getImage();


        ImageIcon d = new ImageIcon("pics/pacman/down1.png");
        down[0] = d.getImage();
        d = new ImageIcon("pics/pacman/down2.png");
        down[1] = d.getImage();


        ImageIcon rg = new ImageIcon("pics/pacman/right1.png");
        right[0] = rg.getImage();
        rg = new ImageIcon("pics/pacman/right2.png");
        right[1] = rg.getImage();


        setImg(full);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                tmp = left;
                tempDX = -1;
                tempDY = 0;
                break;
            case KeyEvent.VK_UP:
                tmp = up;
                tempDX = 0;
                tempDY = -1;
                break;
            case KeyEvent.VK_RIGHT:
                tmp = right;
                tempDX = 1;
                tempDY = 0;
                break;
            case KeyEvent.VK_DOWN:
                tmp = down;
                tempDX = 0;
                tempDY = 1;
                break;
            case KeyEvent.VK_Q:
                setEndGame(true);
                break;


        }
    }

    /**
     * Ez a f�gv�ny valos�tja meg pacman ir�nyv�lt�s�t.
     * Csak akkor hajtja v�gre az ir�nyv�lt�st ha PacMan
     *      keresztez�d�sben van �s abban az ir�nyben nincs fal
     */
    public void Turn() {
        if (((getX() % 30 == 0 || getX() % 30 > 28) && (getY() % 34 == 0 || getY() % 34 > 32)) && (getX() > -10 && getX() < 620 && getY() > 0 && getY() < 714) && !wallwillbe(getX(), getY(), tempDX, tempDY, getMap())) {
            actual = tmp;
            setDir_x(tempDX);
            setDir_Y(tempDY);
        }
    }

    /**
     * Ez a f�gv�ny felel�s az�rt, hogy ha PacMan egy pellethez �r
     *      megegye azt, azaz elt�nj�n a p�j�rol �s pacman pontot kapjon.
     * @param map   Egy string lista ami az aktu�lia p�ly�t tartalmazza.
     * @param x PacMan x koordin�t�ja.
     * @param y PacMan y koordin�t�ja.
     */
    public void Eat(ArrayList<String> map, int x, int y) {
        if ((y + 17) / 34 >= 0 && (x + 15) / 30 >= 0 && (y + 17) / 34 < 21 && (x + 15) / 30 < 21) {
            char c = map.get((y + 17) / 34).charAt((x + 15) / 30);
            if (c != '#' && c != '0' && c != '-' && c != '/') {
                StringBuilder tmp = new StringBuilder(map.get((y + 17) / 34));
                tmp.setCharAt((x + 15) / 30, '0');
                map.set((y + 17) / 34, tmp.toString());
                dots++;
                points = points + 10;
            }
        }
    }

    /**
     * Ez a f�ggv�ny felel�s PacMan mozg�s��rt.
     * Ha falnak �tk�zne PacMan meg�ll.
     */
    public void move() {
        if (wallwillbe(getX(), getY(), getDir_x(), getDir_Y(), getMap())) {
            setDir_Y(0);
            setDir_x(0);
        } else {
            setX(getX() + getDir_x());
            setY(getY() + getDir_Y());
            if (getX() > 630) {
                setX(-25);
            }
            if (getX() < -25) {
                setX(630);
            }
            if (getY() > 783) {
                setY(-20);
            }
            if (getY() < -20) {
                setY(783);
            }
        }

    }

    /**
     * Be�ll�tja pacman egy �j k�r kezdet�hez.
     * Be�ll�tja a kezd� pozici�j�t �s azt, hogy ne mozogjon.
     */
    public void newRoud() {
        setX(getStart_x() * 30);
        setY(getStart_y() * 34);
        setDir_x(0);
        tempDY = 0;
        tempDX = 0;
        setDir_Y(0);
    }

    /**
     * Ez a f�gv�ny felel az�rt hogy az aktu�lis k�p PacMan-r�l megfeleljen
     *      annak amerre PacMan halad.
     * Egy blokkon bel�l n�gyszer v�ltozik a k�p, hogy Pacman ki �s becsukja a sz�j�t.
     * @param x PacMan x koordin�t�ja.
     * @param y PacMan y koordin�t�ja.
     * @param dirY  PacMan mozg�s�nak fel-le ir�nya, ez alapjn mondja meg a f�ggv�ny,
     *              hogy PacMan fel le, vagy jobbra balra halad.
     * @return  A k�p amit aktu�lisan ki kell rajzolni.
     */
    public Image Anim(int x, int y, int dirY) {
        if (dirY == 0) {
            switch ((x / 15) % 4) {
                case 0:
                    return full;
                case 1:
                    return actual[0];
                case 2:
                    return actual[1];
                default:
                    return actual[0];
            }
        } else {
            switch ((y / 15) % 4) {
                case 0:
                    return full;
                case 1:
                    return actual[0];
                case 2:
                    return actual[1];
                default:
                    return actual[0];
            }
        }

    }

    @Override
    public void run(){
        while (true){
            if(isNoDotsLeft()){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newRoud();
                dots = 0;
                setNoDotsLeft(false);
            }
            Turn();
            move();
            Eat(getMap(), getX(), getY());
            setImg(Anim(getX(), getY(), getDir_Y()));
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(isEnd()){
                break;
            }
        }
    }



    //setters
    public void setPoints(int points) {
        this.points = points;
    }
    public void setLife(int x){
        Life = x;
    }
    public void setEndGame(boolean endGame) {
        EndGame = endGame;
    }
    //getters
    public boolean isEndGame() {
        return EndGame;
    }
    public int getLife(){
        return Life;
    }
    public PacMan getPacMan(){
        return this;
    }
    public int getDots() {
        return dots;
    }
    public int getPoints() {
        return points;
    }

}
