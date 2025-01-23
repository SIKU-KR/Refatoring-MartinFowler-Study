package siku;

import siku.domain.*;

import java.util.HashMap;
import java.util.List;

public class Statement {

    public String statement(Invoice invoice, HashMap<String, Play> plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    private StatementData createStatementData(Invoice invoice, HashMap<String, Play> plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances(plays));
        statementData.setTotalAmount(totalAmount(statementData.getPerformances()));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData.getPerformances()));
        return statementData;
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

    private int totalAmount(List<EnrichPerformance> performances) {
        int result = 0;
        for (EnrichPerformance performance : performances) {
            result += performance.getAmount();
        }
        return result;
    }

    private int totalVolumeCredits(List<EnrichPerformance> performances) {
        int result = 0;
        for (EnrichPerformance performance : performances) {
            result += performance.getVolumeCredits();
        }
        return result;
    }

    private String usd(double amount) {
        return String.format("%.2f", amount / 100.0);
    }
}
