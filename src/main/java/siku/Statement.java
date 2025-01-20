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

    public String run(){
        String result = "";
        int totalAmount = 0;
        int volumeCredits = 0;
        for (Invoice invoice : invoices) {
            List<Performance> performances = invoice.getPerformances();
            result += "청구 내역 (고객명: " + invoice.getCustomer() + ")\n";
            for (Performance performance : performances) {
                {
                    int thisAmount = amountFor(performance);
                    // 포인트를 적립한다.
                    volumeCredits += Math.max(performance.getAudience() - 30, 0);
                    // 희극 관객 5명마다 추가 포인트를 적립한다.
                    if ("comedy".equals(playFor(performance).getType())) {
                        volumeCredits += Math.floorDiv(performance.getAudience(), 5);
                    }
                    // 청구 내역을 출력한다.
                    result += "  " + playFor(performance).getName() + ": $" + String.format("%.2f", thisAmount / 100.0) + " (" + performance.getAudience() + "석)\n";
                    totalAmount += thisAmount;
                }
            }
            result += "총액: $" + String.format("%.2f", totalAmount / 100.0) + "\n";
            result += "적립 포인트: " + volumeCredits + "점\n";
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
}
