package pac;

import javax.swing.*;
import java.util.ArrayList;

/**
 *A narancss�rga szellem (Clyde) oszt�lya.
 */
public class Clyde extends Ghosts {

    /**
     * Clyde oszt�ly konstriktora.
     * Bet�lti a hozz� tartoz� k�peket �s be�ll�tja a szellem kezd�pozici�j�t.
     * @param map   Egy string lista ami az aktu�lia p�ly�t tartalmazza.
     * @param pac   A j�t�kban l�v� PacMan objektum.
     */
    public Clyde(ArrayList<String> map, PacMan pac){
        setMap(map);
        setPac(pac);
        StartPosition(getMap(), 'b');
        setFirst_startX(getStart_x() * 30);
        setFirst_startY(getStart_y() * 34);
        setX(getStart_x() * 30);
        setY(getStart_y() * 34);
        StartPosition(getMap(), 'c');
        setDelya_time(6000);

        setLeft(new ImageIcon("pics/clyde/left1.png").getImage(), new ImageIcon("pics/clyde/left2.png").getImage());
        setUp(new ImageIcon("pics/clyde/up1.png").getImage(), new ImageIcon("pics/clyde/up2.png").getImage());
        setDown(new ImageIcon("pics/clyde/down1.png").getImage(), new ImageIcon("pics/clyde/down2.png").getImage());
        setRight(new ImageIcon("pics/clyde/right1.png").getImage(), new ImageIcon("pics/clyde/right2.png").getImage());

        setImg(getUp(0));
    }


    @Override
    public void SetTarget(int PacManX, int PacManY, int X, int Y){
        int x = Math.abs(PacManX/30 - X/30);
        int y = Math.abs(PacManY/34 - Y/34);
        if(Math.sqrt(x*x + y*y) <= 8){
            setTargetX(0);
            setTargetY(21*34);
        }else{
            setTargetX(PacManX);
            setTargetY(PacManY);
        }
    }

}
