package siku.domain;

import java.util.Arrays;
import java.util.List;

public class Invoice {
    private String customer;
    private List<Performance> performances;

    // 기본 생성자
    public Invoice() {}

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
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

    @Override
    public String toString() {
        return "Invoice{" +
                "customer='" + customer + '\'' +
                ", performances=" + performances +
                '}';
    }
}
