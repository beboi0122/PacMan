package pac;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.List;
/**
 *Ez az osztály hozza létre azt az ablakot ahol a korábbi játékosokat, pontszámait és játékidejüket láthatjuk.
 */
public class GameScores extends JFrame {
    private ScoreData data;

    /**
     * GameScores konstruktora.
     * A korábbi játékosok adatait egy fájlbol olvassuk be szerializásás segítségével.
     */
    public GameScores() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("PacMan");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("pics/icon.png").getImage());

        try {
            data = new ScoreData();
            ObjectInputStream OjInSt = new ObjectInputStream(new FileInputStream("scores.txt"));
            data.scores = (List<Score>)OjInSt.readObject();
            OjInSt.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream OjOutStr = new ObjectOutputStream(new FileOutputStream("scores.txt"));
                    OjOutStr.writeObject(data.scores);
                    OjOutStr.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        JTable JTab = new JTable(data);
        JTab.setRowHeight(JTab.getRowHeight()+15);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        JTab.setDefaultRenderer(String.class, centerRenderer);
        JTab.setFont(new Font("Arial Black", Font.PLAIN, 15));
        JScrollPane JScoPan = new JScrollPane(JTab);

        TableRowSorter<ScoreData> sort = new TableRowSorter<ScoreData>(data);
        JTab.setRowSorter(sort);
        JTab.getRowSorter().toggleSortOrder(1);
        JTab.getRowSorter().toggleSortOrder(1);

        add(JScoPan, BorderLayout.CENTER);

        JLabel higsco = new JLabel("HighScores");
        higsco.setFont(new Font("Arial Black", Font.PLAIN, 20));
        JPanel hisco = new JPanel(new FlowLayout());
        hisco.add(higsco);
        add(hisco, BorderLayout.NORTH);

    }


}
