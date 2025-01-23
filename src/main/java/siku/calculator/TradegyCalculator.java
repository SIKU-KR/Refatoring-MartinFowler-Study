package siku.calculator;

import siku.domain.EnrichPerformance;

public class TradegyCalculator extends PerformanceCalculator{

    public TradegyCalculator(EnrichPerformance performance) {
        super(performance);
    }

    @Override
    public int amountFor() {
        int result;
        result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }
}
