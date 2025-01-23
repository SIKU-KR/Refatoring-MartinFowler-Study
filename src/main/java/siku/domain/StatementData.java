package siku.domain;

import java.util.ArrayList;
import java.util.List;

public class StatementData {

    String customer;
    List<EnrichPerformance> performances;

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
}
