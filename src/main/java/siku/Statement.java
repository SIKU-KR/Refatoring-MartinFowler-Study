package siku;

import siku.domain.Invoice;
import siku.domain.Performance;
import siku.domain.Play;
import siku.domain.StatementData;

import java.util.HashMap;
import java.util.List;

public class Statement {

    public String statement(Invoice invoice, HashMap<String, Play> plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances());
        return renderPlainText(statementData, plays);
    }

    private String renderPlainText(StatementData statementData, HashMap<String, Play> plays) {
        String result = "청구 내역 (고객명: " + statementData.getCustomer() + ")\n";
        for (Performance performance : statementData.getPerformances()) {
            {
                result += "  " + playFor(performance, plays).getName() + ": $" + usd(amountFor(performance, plays)) + " (" + performance.getAudience() + "석)\n";
            }
        }
        result += "총액: $" + usd(totalAmount(statementData.getPerformances(), plays)) + "\n";
        result += "적립 포인트: " + totalVolumeCredits(statementData.getPerformances(), plays) + "점\n";
        return result;
    }

    private int totalAmount(List<Performance> performances, HashMap<String, Play> plays) {
        int result = 0;
        for (Performance performance : performances) {
            result += amountFor(performance, plays);
        }
        return result;
    }

    private int totalVolumeCredits(List<Performance> performances, HashMap<String, Play> plays) {
        int result = 0;
        for (Performance performance : performances) {
            result += volumeCreditsFor(performance, plays);
        }
        return result;
    }

    private int amountFor(Performance aPerformance, HashMap<String, Play> plays) {
        int result;
        switch (playFor(aPerformance, plays).getType()) {
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
                throw new IllegalArgumentException("알 수 없는 장르: " + playFor(aPerformance, plays).getType());
        }
        return result;
    }

    private Play playFor(Performance aPerformance, HashMap<String, Play> plays) {
        return plays.get(aPerformance.getPlayID());
    }

    private int volumeCreditsFor(Performance aPerformance, HashMap<String, Play> plays) {
        int result = 0;
        result += Math.max(aPerformance.getAudience() - 30, 0);
        if ("comedy".equals(playFor(aPerformance, plays).getType())) {
            result += Math.floorDiv(aPerformance.getAudience(), 5);
        }
        return result;
    }

    private String usd(double amount) {
        return String.format("%.2f", amount / 100.0);
    }
}
