package siku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatementTest {

    @Test
    void testRun(){
        // given
        IO io = new IO("src/data/");
        Statement statement = new Statement();
        // when
        String result = statement.run(io.readInvoices(), io.readPlays());
        // then
        assertTrue(result.contains("청구 내역 (고객명: BigCo)"));
        assertTrue(result.contains("Hamlet: $650.00 (55석)"));
        assertTrue(result.contains("As You Like It: $580.00 (35석)"));
        assertTrue(result.contains("Othello: $500.00 (40석)"));
        assertTrue(result.contains("총액: $1730.00"));
        assertTrue(result.contains("적립 포인트: 47점"));
    }

}