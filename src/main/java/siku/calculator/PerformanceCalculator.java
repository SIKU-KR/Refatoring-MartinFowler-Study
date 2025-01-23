package siku.calculator;

import siku.domain.EnrichPerformance;

public class PerformanceCalculator {
    EnrichPerformance performance;

    public PerformanceCalculator(EnrichPerformance performance) {
        this.performance = performance;
    }

    public int amountFor() {
        throw new IllegalArgumentException("알 수 없는 장르: " + performance.getPlay().getType());
    }

    public int volumeCreditsFor() {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        return result;
    }

}
