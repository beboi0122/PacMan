package pac;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * A játékosok eredményeit tároló osztály
 */
public class ScoreData extends AbstractTableModel {
    List<Score> scores = new ArrayList<Score>();


    @Override
    public int getRowCount() {
        return scores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Score score = scores.get(rowIndex);
        switch (columnIndex){
            case 0: return score.getNickName();
            case 1: return score.getScore();
            default: return score.getPlay_TimeAsString();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Nickname";
            case 1: return "Score";
            default: return "Play Time";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0: return String.class;
            case 1: return Integer.class;
            default: return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addNewScore(String nic, int point, int time){
        scores.add(new Score(nic, point, time));
    }


}
