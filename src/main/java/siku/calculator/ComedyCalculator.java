package siku.calculator;

import siku.domain.EnrichPerformance;

public class ComedyCalculator extends PerformanceCalculator{

    public ComedyCalculator(EnrichPerformance performance) {
        super(performance);
    }

    @Override
    public int amountFor() {
        int result;
        result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    public int volumeCreditsFor() {
        int result = super.volumeCreditsFor();
        result += Math.floorDiv(performance.getAudience(), 5);
        return result;
    }
}
