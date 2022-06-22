package pac;

import java.io.Serializable;

/**
 * Egy játékos eredményeit tároló osztály.
 */
public class Score implements Serializable {
    private String NickName;
    private int Score;
    private int Play_Time;

    /**
     * Score osztály konstruktora
     * @param nickname  A játékos játékbeli neve
     * @param score A játékos által elért pont
     * @param play_time A játékos játékkal eltöltött ideje.
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
     * A játékidõtadja vissza stringben
     * @return A játékidõ stringben
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

