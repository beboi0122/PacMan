package pac;

import javax.swing.*;
import java.awt.*;

/**
 * Ez az oszt�ly hozza l�tre azt a panelt ami a j�t�k k�zben jobbra l�that�,
 * 		�s a j�t�kkal kapcsolatos adatokat jelen�ti meg.
 */
public class GamePanel extends JPanel{
    private int Pacman_points;
    private JLabel points_label;
    private JPanel life;
    private JLabel tim;

    /**
     * GamePane konstruktora
     * Ez a panel l�that� j�t�k k�zben, a j�t�k mellet jobbra. Ezen k�vethet�, hogy
     *      mennyi �lete maradt a PacMan-nek, valamint hogy mennyi ideje j�tszik a j�t�kos.
     */
    public GamePanel(){
        setBackground(Color.BLACK);
        BorderLayout Blay = new BorderLayout();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(378, 735));
        ImageIcon title = new ImageIcon(new ImageIcon("pics/title.png").getImage().getScaledInstance(178*2, 41*2, Image.SCALE_DEFAULT));
        JLabel titlepic = new JLabel(title);

        JPanel center = new JPanel();
        center.setBackground(Color.BLACK);
        center.setLayout(new GridLayout(3,1));
        points_label = new JLabel(Pacman_points + " Points", SwingConstants.CENTER);
        points_label.setFont(new Font("Arial Black", Font.PLAIN, 40));
        points_label.setForeground(Color.white);
        center.add(points_label);


        life = new JPanel();
        life.setBackground(Color.BLACK);
        ImageIcon lf = new ImageIcon(new ImageIcon("pics/pacman/right1.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        life.setLayout(new FlowLayout());
        life.add(new JLabel(lf));
        life.add(new JLabel(lf));
        life.add(new JLabel(lf));
        center.add(life);


        JPanel time= new JPanel();
        time.setBackground(Color.BLACK);
        tim = new JLabel(0 + " : " + 0);
        tim.setFont(new Font("Arial Black", Font.PLAIN, 40));
        tim.setForeground(Color.white);
        time.add(tim);
        center.add(time);

        JPanel presq = new JPanel();
        presq.setBackground(Color.black);
        JLabel presq_l = new JLabel("Press 'q' to end the game!");
        presq_l.setFont(new Font("Arial Black", Font.PLAIN, 20));
        presq_l.setForeground(Color.gray);
        presq.add(presq_l);
        add(presq, BorderLayout.SOUTH);




        add(center, BorderLayout.CENTER);
        add(titlepic, BorderLayout.NORTH);
    }

    /**
     * Be�ll�tja a k�perny�n l�that� pontsz�mot.
     * @param pacman_points Az aktu�lis pontsz�m.
     */
    public void setPacman_points(int pacman_points) {
        Pacman_points = pacman_points;
        points_label.setText(pacman_points + " Points");
    }

    /**
     * Be�ll�tja a k�perny�n l�that� �letek sz�m�t.
     * @param x PacMan �leteinek sz�ma.
     */
    public void PacLife(int x){
        if(x == 2){
            life.remove(2);
            ImageIcon lf = new ImageIcon(new ImageIcon("pics/die.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            life.add(new JLabel(lf));
            points_label.setText(Pacman_points + " Points ");
        }
        if(x == 1){
            life.remove(1);
            ImageIcon lf = new ImageIcon(new ImageIcon("pics/die.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            life.add(new JLabel(lf));
            points_label.setText(Pacman_points + " Points " );
        }
        if(x == 0){
            life.remove(0);
            ImageIcon lf = new ImageIcon(new ImageIcon("pics/die.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            life.add(new JLabel(lf));
            points_label.setText(Pacman_points + " Points ");
        }
    }

    /**
     * Be�ll�tja a k�perny�n l�that� id�t, ami azt mutatja mennyi ideje j�tszik a j�t�kos.
     * @param tm    A j�t�kkal t�lt�tt id� ms-ben;
     */
    public void setTime(int tm){
        tm = tm/1000 ;
        int sec = tm%60;
        tm = (tm-tm%60)/60;
        int min = tm;

        tim.setText(String.format("%02d", min) + " : " + String.format("%02d", sec));
    }
}
