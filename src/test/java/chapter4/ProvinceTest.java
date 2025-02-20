package chapter4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvinceTest {

    Province asia;

    @BeforeEach
    void setUp() {
        asia = new Province(ProvinceData.sampleProvinceData());
    }

    @Test
    void shortfall(){
        assertEquals(asia.getShortfall(), 5);
    }

    @Test
    void profit(){
        assertEquals(asia.getProfit(), 230);
    }

    @Test
    void changeProduction(){
        asia.producers.get(1).setProduction(20);
        assertEquals(asia.getShortfall(), -6);
        assertEquals(asia.getProfit(), 292);
    }
}