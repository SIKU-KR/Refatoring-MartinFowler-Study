package siku;

import siku.domain.Invoice;
import siku.domain.Performance;
import siku.domain.Play;
import siku.domain.StatementData;

import java.util.HashMap;
import java.util.List;

public class Statement {

    HashMap<String, Play> plays;

    public Statement(HashMap<String, Play> plays) {
        this.plays = plays;
    }

    public String run(Invoice invoice) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances());
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) {
        String result = "청구 내역 (고객명: " + statementData.getCustomer() + ")\n";
        for (Performance performance : statementData.getPerformances()) {
            {
                result += "  " + playFor(performance).getName() + ": $" + usd(amountFor(performance)) + " (" + performance.getAudience() + "석)\n";
            }
        }
        result += "총액: $" + usd(totalAmount(statementData.getPerformances())) + "\n";
        result += "적립 포인트: " + totalVolumeCredits(statementData.getPerformances()) + "점\n";
        return result;
    }

    private int totalAmount(List<Performance> performances) {
        int result = 0;
        for (Performance performance : performances) {
            result += amountFor(performance);
        }
        return result;
    }

    private int totalVolumeCredits(List<Performance> performances) {
        int result = 0;
        for (Performance performance : performances) {
            result += volumeCreditsFor(performance);
        }
        return result;
    }

    private int amountFor(Performance aPerformance) {
        int result;
        switch (playFor(aPerformance).getType()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.getAudience() > 20) {
                    result += 10000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 장르: " + playFor(aPerformance).getType());
        }
        return result;
    }

    private Play playFor(Performance aPerformance) {
        return this.plays.get(aPerformance.getPlayID());
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result = 0;
        result += Math.max(aPerformance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(aPerformance).getType())) {
            result += Math.floorDiv(aPerformance.getAudience(), 5);
        }
        return result;
    }

    private String usd(double amount) {
        return String.format("%.2f", amount / 100.0);
    }
}
