package siku;

public class Main {
    public static void main(String[] args) {
        IO io = new IO("src/data/");
        Statement statement = new Statement(io.readInvoices(), io.readPlays());
        System.out.println(statement.run());
    }
}