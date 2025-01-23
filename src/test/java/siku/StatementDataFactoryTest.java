package siku;

import org.junit.jupiter.api.Test;
import siku.domain.Invoice;
import siku.domain.Play;
import siku.domain.StatementData;

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