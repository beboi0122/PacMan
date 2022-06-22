package pac;

import java.awt.*;
import java.util.ArrayList;

/**
 * A szellemek és pacman közös õsosztálya
 */
public abstract class Specie implements Runnable{
    private int x;
    private int y;
    private int start_x;
    private int start_y;
    private int dir_x = 0;
    private int dir_Y = 0;
    private ArrayList<String> map;
    private boolean NoDotsLeft;
    private boolean PacManDie;
    private Image img;
    private boolean end;

    /**
     * Megmondja hogy ha PacMan vagy egy szellem a megadott irányba halad tovább,
     *      fal lesz-e vagy sem.
     * @param x PacMan vagy egy szellem x koordinátája.
     * @param y PacMan vagy egy szellem y koordinátája.
     * @param d_x PacMan vagy egy szellem haladási irányanak x koordinátája.
     * @param d_y PacMan vagy egy szellem haladási irányanak y koordinátája.
     * @param map Egy string lista ami az aktuália pályát tartalmazza.
     * @return true- ha fal lesz, false- ha nem lesz fal.
     */
    public boolean wallwillbe(int x, int y, int d_x, int d_y, ArrayList<String> map) {
        if(((y+d_y + 17)+(17*d_y))/34 >= 21 || ((x+d_x + 15)+(15*d_x))/30 >= 21 || ((y+d_y + 17)+(17*d_y))/34 < 0 || ((x+d_x + 15)+(15*d_x))/30 <0){
            return false;
        }
        if (map.get(((y+d_y + 17)+(17*d_y)) / 34).charAt(((x+d_x + 15)+(15*d_x)) / 30) == '#' || map.get(((y+d_y + 17)+(17*d_y)) / 34).charAt(((x+d_x + 15)+(15*d_x)) / 30) == '-') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Beállítja PacMan vagy egy szellem kezdõpontját.
     * @param map   Egy string lista ami az aktuália pályát tartalmazza.
     * @param c A karakter ami a map stinglistában a PacMan vagy  szellem helyét jelzi.
     */
    public void StartPosition(ArrayList<String> map, char c){
        for(int i =0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).length(); ++j) {
                if(map.get(i).charAt(j)== c){
                    setStart_x(j);
                    setStart_y(i);
                    return;
                }
            }
        }
    }

    /**
     * Megadja hogy egy adott négyzetrács a pályán fal-e vagy sem.
     * @param x A pályának egy x koordinátája
     * @param y A pályának egy y koordinátája
     * @return true- ha fal, false- ha nem fal.
     */
    public boolean IsAWall(int x, int y){
        if(x>=0 && x< 21 && y >= 0 && y < 21){
            if(map.get(y).charAt(x) == '#' || map.get(y).charAt(x) == '-') {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    @Override
    public abstract void run();

    //setters
    public void setNoDotsLeft(boolean noDotsLeft) {
        NoDotsLeft = noDotsLeft;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setDir_x(int dir_x) {
        this.dir_x = dir_x;
    }
    public void setDir_Y(int dir_Y) {
        this.dir_Y = dir_Y;
    }
    public void setMap(ArrayList<String> map) {
        this.map = map;
    }
    public void setImg(Image img) {
        this.img = img;
    }
    public void setStart_x(int x){
        start_x= x;
    }
    public void setEnd(boolean end) {
        this.end = end;
    }
    public int getStart_y() {
        return start_y;
    }
    public void setStart_y(int start_y) {
        this.start_y = start_y;
    }


    //getters
    public int getStart_x() {
        return start_x;
    }
    public boolean isNoDotsLeft() {
        return NoDotsLeft;
    }
    public int getX() {
        return x;
    }
    public Image getImg(){
        return img;
    }
    public int getY() {
        return y;
    }
    public int getDir_x() {
        return dir_x;
    }
    public int getDir_Y() {
        return dir_Y;
    }
    public boolean isEnd() {
        return end;
    }
    public ArrayList<String> getMap() {
        return map;
    }
}
