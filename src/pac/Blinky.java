package pac;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A piros szellem (Blinky) oszt�lya. 
 */
public class Blinky extends Ghosts {

    /**
     * Blinky oszt�ly konstriktora.
     * Bet�lti a hozz� tartoz� k�peket �s be�ll�tja a szellem kezd�pozici�j�t.
     * @param map   Egy string lista ami az aktu�lia p�ly�t tartalmazza.
     * @param pac   A j�t�kban l�v� PacMan objektum.
     */
    public Blinky(ArrayList<String> map, PacMan pac) {
        setMap(map);
        setPac(pac);
        StartPosition(getMap(), 'b');
        setFirst_startX(getStart_x() * 30);
        setFirst_startY(getStart_y() * 34);
        setX(getStart_x() * 30);
        setY(getStart_y() * 34);
        setDelya_time(500);

        setLeft(new ImageIcon("pics/blinky/left1.png").getImage(), new ImageIcon("pics/blinky/left2.png").getImage());
        setUp(new ImageIcon("pics/blinky/up1.png").getImage(), new ImageIcon("pics/blinky/up2.png").getImage());
        setDown(new ImageIcon("pics/blinky/down1.png").getImage(), new ImageIcon("pics/blinky/down2.png").getImage());
        setRight(new ImageIcon("pics/blinky/right1.png").getImage(), new ImageIcon("pics/blinky/right2.png").getImage());

        setImg(getLeft(0));

    }
    @Override
    public void SetTarget(int PacManX, int PacManY, int X, int Y){
        setTargetX(PacManX);
        setTargetY(PacManY);
    }

}
