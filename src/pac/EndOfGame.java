package pac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.List;

/**
 * Ez az osztály hozza látre azt az ablakot ahol a játék végen a játékos beírja a nevét.
 */
public class EndOfGame extends JFrame {
    private boolean done;
    private ScoreData data;
    private int point;
    private int playtime;


    /**
     * EndOfGame osztály konstruktora.
     * Megnyit egy ablakot, ahol a játékos be tudja írni a nevét és kimenteni egy fájla a
     *      pontszámával, és a játszott idejével együtt.
     * @param score A játékos pontszáma.
     * @param playtime  A játékban töltött idõ.
     */
    public EndOfGame(int score, int playtime){
        point = score;
        this.playtime = playtime;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("PacMan");
        setResizable(false);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("pics/icon.png").getImage());
        setLayout(new GridLayout(3, 1));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                done = true;
            }
        });

        JTextField jtf = new JTextField(20);
        JButton jb = new JButton("Done!");
        JLabel jl =  new JLabel("Enter your name and press 'Done!'");
        JPanel text = new JPanel(new FlowLayout());
        JPanel butt = new JPanel(new FlowLayout());
        JPanel label = new JPanel(new FlowLayout());
        label.add(jl);
        text.add(jtf);
        butt.add(jb);
        add(label);
        add(text);
        add(butt);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addNewScore(jtf.getText(), point ,playtime );
                try {
                    ObjectOutputStream OjOutStr = new ObjectOutputStream(new FileOutputStream("scores.txt"));
                    OjOutStr.writeObject(data.scores);
                    OjOutStr.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                done = true;
            }
        });

        try {
            data = new ScoreData();
            ObjectInputStream OjInSt = new ObjectInputStream(new FileInputStream("scores.txt"));
            data.scores = (List<Score>)OjInSt.readObject();
            OjInSt.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    //setters
    public void setData(ScoreData data) {
        this.data = data;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }
    public void setPoint(int point) {
        this.point = point;
    }

    //getters
    public boolean isDone(){
        return done;
    }
    public int getPoint() {
        return point;
    }
    public int getPlaytime() {
        return playtime;
    }
    public ScoreData getData() {
        return data;
    }
}
