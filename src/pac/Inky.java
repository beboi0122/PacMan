package pac;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A kék szellem (Inky) osztálya.
 */
public class Inky extends Ghosts{
    private int DirX_BeforStop;
    private int DirY_BeforStop;
    private Blinky blinky;

    /**
     * Inky osztály konstriktora.
     * Betölti a hozzá tartozó képeket és beállítja a szellem kezdõpozicióját.
     * Inky Blinkihez képset mondja meg a saját célpontját.
     * @param map   Egy string lista ami az aktuália pályát tartalmazza.
     * @param pac   A játékban lévõ PacMan objektum.
     * @param blinky    A játékban lévõ Blinky szellem.
     */
    public Inky(ArrayList<String> map, PacMan pac, Blinky blinky){
        this.blinky = blinky;
        setMap(map);
        setPac(pac);
        StartPosition(getMap(), 'b');
        setFirst_startX(getStart_x() * 30);
        setFirst_startY(getStart_y() * 34);
        setX(getStart_x() * 30);
        setY(getStart_y() * 34);
        StartPosition(getMap(), 'i');
        setDelya_time(4000);

        setLeft(new ImageIcon("pics/inky/left1.png").getImage(), new ImageIcon("pics/inky/left2.png").getImage());

        setUp(new ImageIcon("pics/inky/up1.png").getImage(), new ImageIcon("pics/inky/up2.png").getImage());

        setDown(new ImageIcon("pics/inky/down1.png").getImage(), new ImageIcon("pics/inky/down2.png").getImage());

        setRight(new ImageIcon("pics/inky/right1.png").getImage(), new ImageIcon("pics/inky/right2.png").getImage());

        setImg(getUp(0));
    }

    @Override
    public void SetTarget(int PacManX, int PacManY, int X, int Y){
        int dX;
        int dY;
        X = blinky.getX();
        Y = blinky.getY();
        if(getPac().getDir_x() == 0 && getPac().getDir_Y() == 0){
            dX = DirX_BeforStop;
            dY = DirY_BeforStop;
        }else{
            dX = getPac().getDir_x();
            dY =  getPac().getDir_Y();
            DirX_BeforStop = getPac().getDir_x();
            DirY_BeforStop =  getPac().getDir_Y();
        }
        setTargetX(PacManX+(dX*30*2) - (X-(PacManX+(dX*30*2))));
        setTargetY(PacManY+(dY*34*2) - (Y-(PacManY+(dY*34*2))));
    }

}
