package siku;

import siku.calculator.ComedyCalculator;
import siku.calculator.PerformanceCalculator;
import siku.calculator.TradegyCalculator;
import siku.domain.EnrichPerformance;

public class CalculatorFactory {
    public static PerformanceCalculator createPerformanceCalculator(EnrichPerformance performance) {
        switch(performance.getPlay().getType()) {
            case "tragedy":
                return new TradegyCalculator(performance);
            case "comedy":
                return new ComedyCalculator(performance);
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + performance.getPlay().getType());
        }
    }
}
