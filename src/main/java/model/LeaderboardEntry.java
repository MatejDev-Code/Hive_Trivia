package model;

/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 5/4/2026
 */
public class LeaderboardEntry {
    private User user;
    private int score;
    private int rank;
    public LeaderboardEntry(int rank, User user, int score){
        this.rank = rank;
        this.user = user;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }

    public int getRank() {
        return rank;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
