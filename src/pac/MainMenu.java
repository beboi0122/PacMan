package pac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.CENTER;

/**
 * Ez az osztály hozza létre azt az ablakot, ahol a játék fõmenüét láthatjuk.
 */
public class MainMenu extends JFrame {

    /**
     * A Main Menu konstruktora
     * Létrehoz egy ablakot három gombbal.
     */
    public MainMenu(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PacMan");
        setResizable(false);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1));
        getContentPane().setBackground(Color.black);
        setIconImage(new ImageIcon("pics/icon.png").getImage());

        ImageIcon title = new ImageIcon(new ImageIcon("pics/title.png").getImage().getScaledInstance(178*3, 41*3, Image.SCALE_DEFAULT));
        JLabel titlepic = new JLabel(title);

        JPanel cim = new JPanel();
        cim.setBackground(Color.BLACK);
        cim.add(titlepic, CENTER);
        add(cim);

        ImageIcon load = new ImageIcon(new ImageIcon("pics/load.gif").getImage().getScaledInstance(636, 79, Image.SCALE_DEFAULT));
        JLabel loadpic = new JLabel(load);

        JPanel animacio = new JPanel();
        animacio.setBackground(Color.BLACK);
        animacio.add(loadpic, CENTER);
        add(animacio);

        JPanel g_p1 = new JPanel();
        g_p1.setBackground(Color.BLACK);
        JButton p1  = new JButton("Start");
        p1.setPreferredSize(new Dimension(300, 80));
        p1.setBackground(new Color(12, 12, 83));
        p1.setFont(new Font("Arial Black", Font.PLAIN, 40));
        p1.setForeground(Color.GRAY);
        g_p1.add(p1);
        add(g_p1);
        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseMap cm = new ChooseMap();
                cm.setVisible(true);
                close();
            }
        });

        JPanel g_p2 = new JPanel();
        g_p2.setBackground(Color.BLACK);
        JButton p2  = new JButton("HighScores");
        p2.setPreferredSize(new Dimension(300, 80));
        p2.setBackground(new Color(12, 12, 83));
        p2.setFont(new Font("Arial Black", Font.PLAIN, 40));
        p2.setForeground(Color.GRAY);
        g_p2.add(p2);
        add(g_p2);
        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameScores GamSco = new GameScores();
                GamSco.setVisible(true);
            }
        });


        JPanel g_p3 = new JPanel();
        g_p3.setBackground(Color.BLACK);
        JButton p3  = new JButton("Exit");
        p3.setPreferredSize(new Dimension(300, 80));
        p3.setBackground(new Color(12, 12, 83));
        p3.setFont(new Font("Arial Black", Font.PLAIN, 40));
        p3.setForeground(Color.GRAY);
        g_p3.add(p3);
        add(g_p3);
        p3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        add(animacio);
    }

    /**
     * Ha elindítjuk a játékot ezt a fügvényt hívja meg a program ami elrejti a fõmenüt.
     */
    public void close(){
        this.setVisible(false);
    }

    public MainMenu getMM(){
        return this;
    }


    private class ChooseMap extends JFrame{

        /**
         * MainMenun belüli osztály, ChooseMap konstruktora.
         * Egy ablakot hoz létre három gombbal. Ezekkel a gombokkal tudjuk kiválasztani,
         *      hogy melyik pályán szeretnénk játszani.
         */
        public ChooseMap(){
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setTitle("PacMan");
            setResizable(false);
            setSize(500, 150);
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.black);
            setIconImage(new ImageIcon("pics/icon.png").getImage());

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Game g = new Game(getMM(), Color.BLUE, "maps/pac1.txt" );
                    g.setVisible(true);
                    dispose();
                }
            });

            JPanel buttons = new JPanel();
            buttons.setLayout(new FlowLayout());
            buttons.setBackground(Color.BLACK);

            JButton jb1 = new JButton("Original");
            jb1.setBackground(new Color(12, 12, 83));
            jb1.setFont(new Font("Arial Black", Font.PLAIN, 20));
            jb1.setForeground(Color.white);
            jb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game g = new Game(getMM(), Color.BLUE, "maps/pac1.txt" );
                    g.setVisible(true);
                    dispose();
                }
            });

            JButton jb2 = new JButton("Ms.Pac-Man");
            jb2.setBackground(new Color(12, 12, 83));
            jb2.setFont(new Font("Arial Black", Font.PLAIN, 20));
            jb2.setForeground(Color.white);
            jb2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game g = new Game(getMM(), Color.PINK, "maps/pac2.txt" );
                    g.setVisible(true);
                    dispose();
                }
            });

            JButton jb3 = new JButton("Cookie-Man");
            jb3.setBackground(new Color(12, 12, 83));
            jb3.setFont(new Font("Arial Black", Font.PLAIN, 20));
            jb3.setForeground(Color.white);
            jb3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game g = new Game(getMM(), new Color(53, 156, 156), "maps/pac3.txt" );
                    g.setVisible(true);
                    dispose();
                }
            });

            JPanel textpanel = new JPanel(new FlowLayout());
            textpanel.setBackground(Color.BLACK);
            JLabel tx = new JLabel("Choose a Map!");
            tx.setFont(new Font("Arial Black", Font.PLAIN, 30));
            tx.setForeground(Color.white);

            buttons.add(jb1);
            buttons.add(jb2);
            buttons.add(jb3);

            textpanel.add(tx);

            add(textpanel, BorderLayout.NORTH);
            add(buttons, BorderLayout.CENTER);

        }
    }


}
