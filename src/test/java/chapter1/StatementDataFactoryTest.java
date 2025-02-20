package chapter1;

import org.junit.jupiter.api.Test;
import chapter1.domain.Invoice;
import chapter1.domain.Play;
import chapter1.domain.StatementData;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StatementDataFactoryTest {

    @Test
    public void createStatementDataTest(){
        // Given
        JSONReader JSONReader = new JSONReader("src/data/");
        Invoice invoice = JSONReader.readInvoices().get(0);
        HashMap<String, Play> plays = JSONReader.readPlays();
        // When
        StatementData statementData = StatementDataFactory.createStatementData(invoice, plays);
        // Then
        assertEquals("BigCo", statementData.getCustomer());
        assertEquals(3, statementData.getPerformances().size());
        assertEquals(173000, statementData.getTotalAmount());
        assertEquals(47, statementData.getTotalVolumeCredits());
    }

}