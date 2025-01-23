package siku.domain;

import java.util.ArrayList;
import java.util.List;

public class StatementData {

    String customer;
    List<EnrichPerformance> performances;
    int totalAmount;
    int totalVolumeCredits;

    public StatementData() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<EnrichPerformance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<EnrichPerformance> performances) {
        this.performances = performances;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalVolumeCredits() {
        return totalVolumeCredits;
    }

    public void setTotalVolumeCredits(int totalVolumeCredits) {
        this.totalVolumeCredits = totalVolumeCredits;
    }
}
