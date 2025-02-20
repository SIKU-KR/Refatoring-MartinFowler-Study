package chapter4;

import java.util.Comparator;
import java.util.List;

public class Province {
    String name;
    List<Producer> producers;
    int totalProduction;
    int demand;
    int price;

    public Province(ProvinceData data) {
        this.name = data.name;
        this.producers = data.producers;
        this.demand = data.demand;
        this.price = data.price;
        this.totalProduction = this.producers.stream()
                .mapToInt(p -> p.production)
                .sum();
        for (Producer p : this.producers) {
            p.province = this;
        }
    }

    public String getName() {
        return name;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public int getTotalProduction() {
        return totalProduction;
    }

    public int getDemand() {
        return demand;
    }

    public int getPrice() {
        return price;
    }

    public void setTotalProduction(int totalProduction) {
        this.totalProduction = totalProduction;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int getShortfall() {
        return this.demand - this.totalProduction;
    }

    int getProfit(){
        return this.demandValue() - this.demandCost();
    }

    int demandValue(){
        return this.satisfiedDemand() * this.price;
    }

    int satisfiedDemand(){
        return Math.min(this.demand, this.totalProduction);
    }

    public int demandCost() {
        int[] remainingDemand = { this.demand };

        return this.producers.stream()
                .sorted(Comparator.comparingInt(a -> a.cost))
                .mapToInt(p -> {
                    int contribution = Math.min(remainingDemand[0], p.production);
                    remainingDemand[0] -= contribution;
                    return contribution * p.cost;
                })
                .sum();
    }
}
