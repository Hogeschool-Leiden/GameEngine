package nl.hsleiden.service.model;

public class HighScore {
    public String data;
    public String playerName;
    public int score;

    public HighScore(String data, String playerName, int score) {
        this.data = data;
        this.playerName = playerName;
        this.score = score;
    }
}
