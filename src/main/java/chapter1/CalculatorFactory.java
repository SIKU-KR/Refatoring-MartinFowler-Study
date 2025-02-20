package chapter1;

import chapter1.calculator.ComedyCalculator;
import chapter1.calculator.PerformanceCalculator;
import chapter1.calculator.TradegyCalculator;
import chapter1.domain.EnrichPerformance;

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
