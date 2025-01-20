package siku;

import siku.domain.Invoice;
import siku.domain.Performance;
import siku.domain.Play;

import java.util.HashMap;
import java.util.List;

public class Statement {

    public String run(List<Invoice> invoices, HashMap<String, Play> plays){
        String result = "";
        int totalAmount = 0;
        int volumeCredits = 0;
        for (Invoice invoice : invoices) {
            List<Performance> performances = invoice.getPerformances();
            result += "청구 내역 (고객명: " + invoice.getCustomer() + ")\n";
            for (Performance performance : performances) {
                {
                    Play play = plays.get(performance.getPlayID());
                    int thisAmount = 0;
                    switch (play.getType()) {
                        case "tragedy":
                            thisAmount = 40000;
                            if (performance.getAudience() > 30) {
                                thisAmount += 1000 * (performance.getAudience() - 30);
                            }
                            break;
                        case "comedy":
                            thisAmount = 30000;
                            if (performance.getAudience() > 20) {
                                thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                            }
                            thisAmount += 300 * performance.getAudience();
                            break;
                        default:
                            throw new IllegalArgumentException("알 수 없는 장르: " + play.getType());
                    }
                    // 포인트를 적립한다.
                    volumeCredits += Math.max(performance.getAudience() - 30, 0);
                    // 희극 관객 5명마다 추가 포인트를 적립한다.
                    if ("comedy".equals(play.getType())) {
                        volumeCredits += Math.floorDiv(performance.getAudience(), 5);
                    }
                    // 청구 내역을 출력한다.
                    result += "  " + play.getName() + ": $" + String.format("%.2f", thisAmount / 100.0) + " (" + performance.getAudience() + "석)\n";
                    totalAmount += thisAmount;
                }
            }
            result += "총액: $" + String.format("%.2f", totalAmount / 100.0) + "\n";
            result += "적립 포인트: " + volumeCredits + "점\n";
        }
        return result;
    }
}
