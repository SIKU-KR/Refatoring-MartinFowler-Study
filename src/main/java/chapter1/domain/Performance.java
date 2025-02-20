package chapter1.domain;

public class Performance {
    private String playID;
    private int audience;

    // 기본 생성자
    public Performance() {}

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "playID='" + playID + '\'' +
                ", audience=" + audience +
                '}';
    }
}
