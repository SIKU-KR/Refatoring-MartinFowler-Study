package siku.domain;

import java.util.ArrayList;
import java.util.List;

public class StatementData {

    String customer = "";
    List<Performance> performances = new ArrayList<>();

    public StatementData() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
