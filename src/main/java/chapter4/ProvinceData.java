package chapter4;

import java.util.List;

public class ProvinceData {
    String name;
    List<Producer> producers;
    int demand;
    int price;

    public ProvinceData(String name, List<Producer> producers, int demand, int price) {
        this.name = name;
        this.producers = producers;
        this.demand = demand;
        this.price = price;
    }

    public static ProvinceData sampleProvinceData(){
        return new ProvinceData("Asia",
                List.of(new Producer(new Data("Byzntium", 10, 9)),
                        new Producer(new Data("Attalia", 12, 10)),
                        new Producer(new Data("Sinope", 10, 6))),
                30, 20);
    }
}
