package siku;

import siku.domain.EnrichPerformance;
import siku.domain.Invoice;
import siku.domain.Play;
import siku.domain.StatementData;

import java.util.HashMap;
import java.util.List;

public class StatementDataFactory {

    public static StatementData createStatementData(Invoice invoice, HashMap<String, Play> plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances(plays));
        statementData.setTotalAmount(totalAmount(statementData.getPerformances()));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData.getPerformances()));
        return statementData;
    }

    private static int totalAmount(List<EnrichPerformance> performances) {
        int result = 0;
        for (EnrichPerformance performance : performances) {
            result += performance.getAmount();
        }
        return result;
    }

    private static int totalVolumeCredits(List<EnrichPerformance> performances) {
        int result = 0;
        for (EnrichPerformance performance : performances) {
            result += performance.getVolumeCredits();
        }
        return result;
    }

}
