package pac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Az az osztály ami létrehozza azt az ablakot, ahol maga a játék lesz látható.
 */
public class Game extends JFrame{
    private Map map;
    private Thread map_t;

    private PacMan pacman;
    private Thread pacman_t;

    private Blinky blinky;
    private Thread blinky_t;

    private Pinky pinky;
    private Thread pinky_t;

    private Inky inky;
    private Thread inky_t;

    private Clyde clyde;
    private Thread clyde_t;

    private MainMenu mainmenu;

    private Color color;
    private String MapPath;
    private boolean MapChosen;

    /**
     * Game osztály konstruktora.
     * Létrehoz egy ablakot, ahol maga a játék lesz látható.
     * @param mainmenu  A MainMenu egy objektuma, ahol létre lett hozzva ennek az osztályna egy objektuma
     * @param color A falaknak a színe.
     * @param MapPath   A fájl elérése amiben a pálya van.
     */
    public Game(MainMenu mainmenu, Color color, String MapPath){
        this.color = color;
        this.MapPath = MapPath;
        this.mainmenu = mainmenu;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainmenu.setVisible(true);
                pacman.setEnd(true);
                map.setEnd(true);
                blinky.setEnd(true);
                pinky.setEnd(true);
                inky.setEnd(true);
                clyde.setEnd(true);
            }
        });
        setTitle("PacMan");
        setResizable(false);
        setSize(1024, 758);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);
        setIconImage(new ImageIcon("pics/icon.png").getImage());



        map = new Map(MapPath, this, color);
        map_t = new Thread(map);

        pacman= new PacMan(map.getMap());
        pacman_t = new Thread(pacman);
        addKeyListener(pacman);
        map.setPacMan(pacman);

        blinky = new Blinky(map.getMap(), pacman);
        blinky_t = new Thread(blinky);

        pinky = new Pinky(map.getMap(), pacman);
        pinky_t = new Thread(pinky);

        inky = new Inky(map.getMap(), pacman, blinky);
        inky_t = new Thread(inky);

        clyde = new Clyde(map.getMap(), pacman);
        clyde_t = new Thread(clyde);

        ArrayList<Ghosts> ghosts = new ArrayList<Ghosts>();
        ghosts.add(blinky);
        ghosts.add(pinky);
        ghosts.add(inky);
        ghosts.add(clyde);

        ArrayList<Thread> threads = new ArrayList<Thread>();
        threads.add(pacman_t);
        threads.add(blinky_t);
        threads.add(pinky_t);
        threads.add(inky_t);
        threads.add(clyde_t);

        map.setGhosts(ghosts);
        map.setThreads(threads);

        map.setBackground(Color.BLACK);

        GamePanel jp1 = new GamePanel();
        add(map, BorderLayout.CENTER);
        add(jp1, BorderLayout.EAST);
        map.setGamPan(jp1);

        map_t.start();
    }

    /**
     * Jelzi a szellemeknek, a PacMan-nek és a map objektumnak, hogy vége a játéknak,
     *      majd bezárja ezt az ablakot és láthatóvá teszi a fõmenüt.
     */
    public void Close(){
        mainmenu.setVisible(true);
        pacman.setEnd(true);
        map.setEnd(true);
        blinky.setEnd(true);
        pinky.setEnd(true);
        inky.setEnd(true);
        clyde.setEnd(true);
        this.dispose();
    }

}
