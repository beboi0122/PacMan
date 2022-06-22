package pac;

import java.io.Serializable;

/**
 * Egy j�t�kos eredm�nyeit t�rol� oszt�ly.
 */
public class Score implements Serializable {
    private String NickName;
    private int Score;
    private int Play_Time;

    /**
     * Score oszt�ly konstruktora
     * @param nickname  A j�t�kos j�t�kbeli neve
     * @param score A j�t�kos �ltal el�rt pont
     * @param play_time A j�t�kos j�t�kkal elt�lt�tt ideje.
     */
    public Score(String nickname, int score, int play_time){
        NickName = nickname;
        Score = score;
        Play_Time = play_time;
    }

    //Getters
    public String getNickName() {
        return NickName;
    }
    public int getScore() {
        return Score;
    }

    public int getPlay_Time() {
        return Play_Time;
    }

    /**
     * A j�t�kid�tadja vissza stringben
     * @return A j�t�kid� stringben
     */
    public String getPlay_TimeAsString() {
        int tm = getPlay_Time()/1000 ;
        int sec = tm%60;
        tm = (tm-tm%60)/60;
        int min = tm;

        return String.format("%02d", min) + " : " + String.format("%02d", sec);
    }

    //Settesr
    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setPlay_Time(int play_Time) {
        Play_Time = play_Time;
    }
}

