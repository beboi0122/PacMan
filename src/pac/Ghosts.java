package pac;

import java.awt.*;

/**
 * A szellemek közös õsosztálya.
 */
public abstract class Ghosts extends Specie{
    private PacMan pac;

    private Image[] left = new Image[2];
    private Image[] up = new Image[2];
    private Image[] down = new Image[2];
    private Image[] right = new Image[2];
    private Image[] actual = new Image[2];
    private int dir_x_befor;
    private int dir_y_befor;
    private boolean delya;
    private int TargetX;
    private int TargetY;
    private int first_startX;
    private int first_startY;
    private int delya_time;

    /**
     * egy Integer négyzetét adja vissza.
     * @param x A szám aminek a négyzetét keressük
     * @return  A szám négyzete
     */
    public int Square(int x){
        return x*x;
    }

    /**
     * Ez a függvény állítja ba hogy merre forduljon a szellem.
     * Ez a függvény mindig csak egy keresztezõdésben van megívva.
     * Megnézi hogy a keresztezõdésben melyik irányba lépjen hogy közelebb kerüljön a célpnthoz.
     * A szellemek nem fordlhatnak visszafelé, valamit van két sáv ahol nem fordulhatnak felfelé.
     * @param target_x  A célpotn (ahova el akar jutni a szellem) x koordinátája
     * @param target_y  A célpotn (ahova el akar jutni a szellem) y koordinátája
     * @param ghost_x   A szellem x koordinátája
     * @param ghost_y   A szellem y koordinátája
     */
    public void searchPath(int target_x, int target_y, int ghost_x, int ghost_y) {
        target_x = target_x / 30;
        target_y = target_y / 34;
        ghost_x = ghost_x / 30;
        ghost_y = ghost_y / 34;
        int upi;
        int downi;
        int lefti;
        int righti;


        //up
        if (getDir_y_befor() == 1 || IsAWall(ghost_x, ghost_y - 1)) {
            upi = 99999;
        } else {
            upi = Square(target_x - ghost_x) + Square(target_y - (ghost_y - 1));
        }

        //down
        if (getDir_y_befor() == -1 || IsAWall(ghost_x, ghost_y + 1)) {
            downi = 99999;
        } else {
            downi = Square(target_x - ghost_x) + Square(target_y - (ghost_y + 1));
        }

        //left
        if (getDir_x_befor() == 1 || IsAWall(ghost_x - 1, ghost_y)) {
            lefti = 99999;
        } else {
            lefti = Square(target_x - (ghost_x - 1)) + Square(target_y - ghost_y);
        }

        //right
        if (getDir_x_befor() == -1 || IsAWall(ghost_x + 1, ghost_y)) {
            righti = 99999;
        } else {
            righti = Square(target_x - (ghost_x + 1)) + Square(target_y - ghost_y);
        }
        //special cases
        if(ghost_x >=8 && ghost_x <= 12 && ghost_y == 15){
            upi = 99999;
        }
        if(ghost_x >=8 && ghost_x <= 12 && ghost_y == 7){
            upi = 99999;
        }

        if (upi <= downi && upi <= righti && upi <= lefti) {
            setDir_x(0);
            setDir_x_befor(0);
            setDir_Y(-1);
            setDir_y_befor(-1);
            setActual(getUp(0), getUp(1));
            return;
        }
        if (lefti < upi && lefti <= downi && lefti <= righti) {
            setDir_x(-1);
            setDir_x_befor(-1);
            setDir_Y(0);
            setDir_y_befor(0);
            setActual(getLeft(0), getLeft(1));
            return;
        }
        if (downi < upi && downi < lefti && downi <= righti) {
            setDir_x(0);
            setDir_x_befor(0);
            setDir_Y(1);
            setDir_y_befor(1);
            setActual(getDown(0), getDown(1));
            return;
        }else {
            setDir_x(1);
            setDir_x_befor(1);
            setDir_Y(0);
            setDir_y_befor(0);
            setActual(getRight(0), getRight(1));
            return;
        }
    }

    /**
     * Beállítja a szellemet egy új kör kezdetéhez.
     */
    public void NewRound(){
        setX(getStart_x()*30);
        setY(getStart_y()*34);
        setDir_x(-1);
        setDir_x_befor(-1);
        setDelya(true);
    }

    /**
     * Ez a függvény felelõs a szellem mozgásáért.
     */
    public void move() {
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

    /**
     * Ez a fügvény felel azért hogy az aktuális kép a szellemrõl megfeleljen
     *      annak amerre a szellem megy.
     * Egy blokkon belül négyszer változik a kép, hogy a szellem mozgása ne legyen egyhangú.
     * @param x A szellem x koordinátája
     * @param y A szellem y koordinátája
     * @param dirY  A szellem mozgásának fel-le iránya, ez alapjn mondja meg a függvény,
     *              hogy a szellem fel le, vagy jobbra balra halad
     * @return  A kép amit aktuálisan ki kell rajzolni.
     */
    public Image Anim(int x, int y, int dirY) {
        if (getDir_Y() == 0) {
            switch ((getX() / 15) % 4) {
                case 0:
                    return getActual(1);
                case 1:
                    return getActual(0);
                case 2:
                    return getActual(1);
                default:
                    return getActual(0);
            }
        } else {
            switch ((getY() / 15) % 4) {
                case 0:
                    return getActual(1);
                case 1:
                    return getActual(0);
                case 2:
                    return getActual(1);
                default:
                    return getActual(0);
            }

        }
    }

    /**
     * Beállítja a szellemek célpontját.
     * @param PacManX  Pacman x koordinátája
     * @param PacManY  PacMan y koordinátája
     * @param X Clyde x koordinátája
     * @param Y Clyde y koordinátája
     */
    public abstract void SetTarget(int PacManX, int PacManY, int X, int Y);

    @Override
    public void run() {
        NewRound();
        while(true) {
            if(isDelya()){
                try {
                    Thread.sleep(getDelya_time());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setX(getFirst_startX());
                setY(getFirst_startY());
                setDir_x_befor(-1);
                setImg(getLeft(0));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setDelya(false);
            }
            setImg(Anim(getX(), getY(), getDir_Y()));
            if(getPac().isNoDotsLeft()){
                NewRound();
            }
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getX() % 30 == 0 && getY() % 34 == 0 && getX() / 30 >= 0 && getX() / 30 < 21 && getY() / 34 >= 0 && getY() / 34 < 21) {
                SetTarget(getPac().getX(), getPac().getY(), getX(), getY());
                searchPath(getTargetX(), getTargetY(), getX(), getY());
            }
            move();
            if(isEnd()){
                break;
            }
        }
    }

    //setters
    public void setDir_x_befor(int dir_x_befor) {
        this.dir_x_befor = dir_x_befor;
    }
    public void setDir_y_befor(int dir_y_befor) {
        this.dir_y_befor = dir_y_befor;
    }
    public void setPac(PacMan pac) {
        this.pac = pac;
    }
    public void setDown(Image down1, Image down2 ) {
        down[0] = down1;
        down[1] = down2;
    }
    public void setLeft(Image left1, Image left2) {
        left[0] = left1;
        left[1] = left2;
    }
    public void setUp(Image up1, Image up2) {
        up[0] = up1;
        up[1] = up2;
    }
    public void setRight(Image right1, Image right2) {
        right[0] = right1;
        right[1] = right2;
    }
    public void setActual(Image actual1, Image actual2) {
        actual[0] = actual1;
        actual[1] = actual2;
    }
    public void setDelya(boolean delya) {
        this.delya = delya;
    }
    public void setTargetX(int targetX) {
        TargetX = targetX;
    }
    public void setTargetY(int targetY) {
        TargetY = targetY;
    }
    public void setFirst_startX(int first_startX) {
        this.first_startX = first_startX;
    }
    public void setFirst_startY(int first_startY) {
        this.first_startY = first_startY;
    }
    public void setDelya_time(int delya_time) {
        this.delya_time = delya_time;
    }

    //getters
    public PacMan getPac() {
        return pac;
    }
    public int getDir_x_befor() {
        return dir_x_befor;
    }
    public int getDir_y_befor() {
        return dir_y_befor;
    }
    public Image getDown(int x){
        return down[x];
    }
    public Image getUp(int x){
        return up[x];
    }
    public Image getRight(int x){
        return right[x];
    }
    public Image getLeft(int x){
        return left[x];
    }
    public Image getActual(int x){
        return actual[x];
    }
    public boolean isDelya() {
        return delya;
    }
    public int getTargetX() {
        return TargetX;
    }
    public int getTargetY() {
        return TargetY;
    }
    public int getFirst_startX() {
        return first_startX;
    }
    public int getFirst_startY() {
        return first_startY;
    }
    public int getDelya_time() {
        return delya_time;
    }
}
