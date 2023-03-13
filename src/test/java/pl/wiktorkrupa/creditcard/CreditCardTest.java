package pl.wiktorkrupa.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        //Act
        card.assignLimit(BigDecimal.valueOf(1000));
        //Assert
        assert card.getBalance().equals(BigDecimal.valueOf(1100));
    }
    @Test
    void itAllowsToAssignDifferentCreditLimit() {
        //Arrange
        CreditCard card = new CreditCard("1234-5678");
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5679");
        //Act
        card1.assignLimit(BigDecimal.valueOf(1000));
        card2.assignLimit(BigDecimal.valueOf(1100));
        //Assert
        assertEquals(BigDecimal.valueOf(1000), card.getBalance());
        assert card1.getBalance().equals(BigDecimal.valueOf(1000));
        assert card2.getBalance().equals(BigDecimal.valueOf(1100));
    }

    @Test
    void testDoubleAndFloats() {
       /* double x1 = 0.03;
        double x2 = 0.01;
        double result = x1 - x2;

        System.out.println(result);*/
    }
    @Test
    void itCantAssignLimitBelow100(){
        CreditCard card = new CreditCard("1234-5678");
        try{
            card.assignLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        }catch (CreditBelowThresholdExeption e) {
            assertTrue(true);
        }
        assertThrows(
                CreditBelowThresholdExeption.class,
                () -> card.assignLimit(BigDecimal.valueOf(10)));
        assertThrows(
                CreditBelowThresholdExeption.class,
                () -> card.assignLimit(BigDecimal.valueOf(99)));

    }

}
