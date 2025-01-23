package siku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatementTest {

    @Test
    void testStatement(){
        // given
        JSONReader JSONReader = new JSONReader("src/data/");
        Statement statement = new Statement();
        // when
        String result = statement.statement(JSONReader.readInvoices().get(0), JSONReader.readPlays());
        // then
        assertTrue(result.contains("청구 내역 (고객명: BigCo)"));
        assertTrue(result.contains("Hamlet: $650.00 (55석)"));
        assertTrue(result.contains("As You Like It: $580.00 (35석)"));
        assertTrue(result.contains("Othello: $500.00 (40석)"));
        assertTrue(result.contains("총액: $1730.00"));
        assertTrue(result.contains("적립 포인트: 47점"));
    }

    @Test
    void testHTMLStatement(){
        // given
        JSONReader JSONReader = new JSONReader("src/data/");
        Statement statement = new Statement();
        // when
        String result = statement.htmlStatement(JSONReader.readInvoices().get(0), JSONReader.readPlays());
        // then
        assertTrue(result.contains("<h1>청구 내역 (고객명: BigCo)</h1>"));
        assertTrue(result.contains("<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>"));
        assertTrue(result.contains("<tr><td>Hamlet</td><td>55</td><td>650.00</td></tr>"));
        assertTrue(result.contains("<tr><td>As You Like It</td><td>35</td><td>580.00</td></tr>"));
        assertTrue(result.contains("<tr><td>Othello</td><td>40</td><td>500.00</td></tr>"));
        assertTrue(result.contains("<p>총액: <em>1730.00</em></p>"));
        assertTrue(result.contains("<p>적립 포인트: <em>47</em>점</p>"));
    }

}