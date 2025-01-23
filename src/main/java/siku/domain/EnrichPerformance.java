package siku.domain;

import siku.CalculatorFactory;
import siku.calculator.PerformanceCalculator;

import java.util.HashMap;

public class EnrichPerformance extends Performance{

    Play play;
    int amount;
    int volumeCredits;

    public EnrichPerformance(Performance performance, HashMap<String, Play> plays) {
        this.setPlayID(performance.getPlayID());
        this.setAudience(performance.getAudience());
        this.play = this.playFor(plays);
        PerformanceCalculator calculator = CalculatorFactory.createPerformanceCalculator(this);
        this.amount = calculator.amountFor();
        this.volumeCredits = calculator.volumeCreditsFor();
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


}
