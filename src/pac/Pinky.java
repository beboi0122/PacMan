package pac;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A r�zsasz�n szellem (Pinky) oszt�lya.
 */
public class Pinky extends Ghosts {
    private int DirX_BeforStop;
    private int DirY_BeforStop;

    /**
     * Pinky oszt�ly konstriktora.
     * Bet�lti a hozz� tartoz� k�peket �s be�ll�tja a szellem kezd�pozici�j�t.
     * @param map   Egy string lista ami az aktu�lia p�ly�t tartalmazza.
     * @param pac   A j�t�kban l�v� PacMan objektum.
     */
    public Pinky(ArrayList<String> map, PacMan pac){
        setMap(map);
        setPac(pac);
        StartPosition(getMap(), 'b');
        setFirst_startX(getStart_x() * 30);
        setFirst_startY(getStart_y() * 34);
        setX(getStart_x() * 30);
        setY(getStart_y() * 34);
        StartPosition(getMap(), 'p');
        setDelya_time(2000);



        setLeft(new ImageIcon("pics/pinky/left1.png").getImage(), new ImageIcon("pics/pinky/left2.png").getImage());

        setUp(new ImageIcon("pics/pinky/up1.png").getImage(), new ImageIcon("pics/pinky/up2.png").getImage());

        setDown(new ImageIcon("pics/pinky/down1.png").getImage(), new ImageIcon("pics/pinky/down2.png").getImage());

        setRight(new ImageIcon("pics/pinky/right1.png").getImage(), new ImageIcon("pics/pinky/right2.png").getImage());

        setImg(getDown(0));
    }

    /**
     * Be�ll�tja a Pinky c�lpontj�t.
     * @param PacManX   Pacman x koordin�t�ja
     * @param PacManY   PacMan y koordin�t�ja
     */
    @Override
    public void SetTarget(int PacManX, int PacManY, int X, int Y){
        int dX;
        int dY;
        if(getPac().getDir_x() == 0 && getPac().getDir_Y() == 0){
            dX = DirX_BeforStop;
            dY = DirY_BeforStop;
        }else{
            dX = getPac().getDir_x();
            dY = getPac().getDir_Y();
            DirX_BeforStop = getPac().getDir_x();
            DirY_BeforStop = getPac().getDir_Y();
        }
        if(dY == -1){
            setTargetX(PacManX-(30*4));
            setTargetY(PacManY-(34*4));
        }
        else{
            setTargetX(PacManX+(dX*30*4));
            setTargetY(PacManY+(dY*34*4));
        }
    }




}
