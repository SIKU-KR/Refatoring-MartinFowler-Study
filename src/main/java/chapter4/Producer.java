package chapter4;

public class Producer {
    String name;
    int cost;
    int production;
    Province province;

    public Producer(Data data) {
        this.name = data.name;
        this.cost = data.cost;
        this.production = data.production;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getProduction() {
        return production;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setProduction(int newProduction) {
        this.province.totalProduction += newProduction - this.production;
        this.production = newProduction;
    }
}
