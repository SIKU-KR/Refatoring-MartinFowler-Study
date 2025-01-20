package siku;

public class Main {
    public static void main(String[] args) {
        IO io = new IO("src/data/");
        Statement statement = new Statement();
        System.out.println(statement.run(io.readInvoices(), io.readPlays()));
    }
}