package siku;

import siku.domain.EnrichPerformance;

public class PerformanceCalculator {
    EnrichPerformance performance;

    public PerformanceCalculator(EnrichPerformance performance) {
        this.performance = performance;
    }

    public int amountFor() {
        int result;
        switch (performance.getPlay().getType()) {
            case "tragedy":
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + performance.getPlay().getType());
        }
        return result;
    }

    public int volumeCreditsFor() {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        if ("comedy".equals(performance.getPlay().getType())) {
            result += Math.floorDiv(performance.getAudience(), 5);
        }
        return result;
    }

}
