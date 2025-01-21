package siku;

import siku.domain.Invoice;
import siku.domain.Performance;
import siku.domain.Play;

import java.util.HashMap;
import java.util.List;

public class Statement {

    List<Invoice> invoices;
    HashMap<String, Play> plays;

    public Statement(List<Invoice> invoices, HashMap<String, Play> plays) {
        this.invoices = invoices;
        this.plays = plays;
    }

    public String run() {
        String result = "";
        for (Invoice invoice : invoices) {
            result += "청구 내역 (고객명: " + invoice.getCustomer() + ")\n";
            for (Performance performance : invoice.getPerformances()) {
                {
                    result += "  " + playFor(performance).getName() + ": $" + usd(amountFor(performance)) + " (" + performance.getAudience() + "석)\n";
                }
            }
            result += "총액: $" + usd(totalAmount(invoice.getPerformances())) + "\n";
            result += "적립 포인트: " + totalVolumeCredits(invoice.getPerformances()) + "점\n";
        }
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
        int result = 0;
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
