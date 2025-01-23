package siku;

import siku.domain.EnrichPerformance;
import siku.domain.Invoice;
import siku.domain.Play;
import siku.domain.StatementData;

import java.util.HashMap;

public class Statement {

    public String statement(Invoice invoice, HashMap<String, Play> plays) {
        return renderPlainText(StatementDataFactory.createStatementData(invoice, plays));
    }

    private String renderPlainText(StatementData statementData) {
        String result = "청구 내역 (고객명: " + statementData.getCustomer() + ")\n";
        for (EnrichPerformance performance : statementData.getPerformances()) {
            result += "  " + performance.getPlay().getName() + ": $" + usd(performance.getAmount()) + " (" + performance.getAudience() + "석)\n";
        }
        result += "총액: $" + usd(statementData.getTotalAmount()) + "\n";
        result += "적립 포인트: " + statementData.getTotalVolumeCredits() + "점\n";
        return result;
    }

    private String usd(double amount) {
        return String.format("%.2f", amount / 100.0);
    }
}
