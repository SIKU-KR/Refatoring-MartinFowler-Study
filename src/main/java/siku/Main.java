package siku;

public class Main {
    public static void main(String[] args) {
        JSONReader JSONReader = new JSONReader("src/data/");
        Statement statement = new Statement();
        System.out.println(statement.statement(JSONReader.readInvoices().get(0), JSONReader.readPlays()));
        System.out.println(statement.htmlStatement(JSONReader.readInvoices().get(0), JSONReader.readPlays()));
    }
}