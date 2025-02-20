package chapter1;

import chapter1.domain.EnrichPerformance;
import chapter1.domain.Invoice;
import chapter1.domain.Play;
import chapter1.domain.StatementData;

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

    public String htmlStatement(Invoice invoice, HashMap<String, Play> plays) {
        return renderHtml(StatementDataFactory.createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData statementData) {
        String result = "<h1>청구 내역 (고객명: " + statementData.getCustomer() + ")</h1>\n";
        result += "<table>\n";
        result += "<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>\n";
        for (EnrichPerformance performance : statementData.getPerformances()) {
            result += "<tr><td>" + performance.getPlay().getName() + "</td><td>" + performance.getAudience() + "</td>";
            result += "<td>" + usd(performance.getAmount()) + "</td></tr>\n";
        }
        result += "</table>\n";
        result += "<p>총액: <em>" + usd(statementData.getTotalAmount()) + "</em></p>\n";
        result += "<p>적립 포인트: <em>" + statementData.getTotalVolumeCredits() + "</em>점</p>\n";
        return result;
    }

    private String usd(double amount) {
        return String.format("%.2f", amount / 100.0);
    }
}
