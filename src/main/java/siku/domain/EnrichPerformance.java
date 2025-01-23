package siku.domain;

import java.util.HashMap;

public class EnrichPerformance extends Performance{

    Play play;
    int amount;
    int volumeCredits;

    public EnrichPerformance(Performance performance, HashMap<String, Play> plays) {
        this.setPlayID(performance.getPlayID());
        this.setAudience(performance.getAudience());
        this.play = this.playFor(plays);
        this.amount = this.amountFor();
        this.volumeCredits = this.volumeCreditsFor();
    }

    public Play getPlay(){
        return this.play;
    }

    public int getAmount(){
        return this.amount;
    }

    public int getVolumeCredits(){
        return this.volumeCredits;
    }

    private Play playFor(HashMap<String, Play> plays) {
        return plays.get(getPlayID());
    }

    private int amountFor() {
        int result;
        switch (getPlay().getType()) {
            case "tragedy":
                result = 40000;
                if (getAudience() > 30) {
                    result += 1000 * (getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (getAudience() > 20) {
                    result += 10000 + 500 * (getAudience() - 20);
                }
                result += 300 * getAudience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + this.play.getType());
        }
        return result;
    }

    private int volumeCreditsFor() {
        int result = 0;
        result += Math.max(getAudience() - 30, 0);
        if ("comedy".equals(play.getType())) {
            result += Math.floorDiv(getAudience(), 5);
        }
        return result;
    }
}
