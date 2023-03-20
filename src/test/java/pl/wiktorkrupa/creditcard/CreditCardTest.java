package pl.wiktorkrupa.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit(){
        //Arrange
        CreditCard card = new CreditCard("1234-5678");

        //Act
        card.assignCredit(BigDecimal.valueOf(1000));

        //Assert
        assertEquals(BigDecimal.valueOf(1000), card.getBalance());
    }

    @Test
    void itAllowsToAssignCreditLimits(){
        //Arrange
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5678");
        //Act
        card1.assignCredit(BigDecimal.valueOf(1000));
        card2.assignCredit(BigDecimal.valueOf(1100));

        //Assert
        assertEquals(BigDecimal.valueOf(1000), card1.getBalance());
    }


    @Test
    void itCantAssignLimitBelow100V2() {
        CreditCard card = new CreditCard("1234-5678");

        try {
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e) {
            assertTrue(true);
        }
    }
    @Test
    void itCantAssignLimitBelow100(){

        CreditCard card = new CreditCard("1234-5678");

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(10)));
        assertThrows(CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(99)));

         assertDoesNotThrow(() -> card.assignCredit(BigDecimal.valueOf(100)));


    }
    @Test
    void itCantAssignLimitTwice(){

        CreditCard card1 = new CreditCard("1234-5678");

        card1.assignCredit(BigDecimal.valueOf(1000));

        assertThrows(
                LimitAlreadyAssignedException.class,
                () -> card1.assignCredit(BigDecimal.valueOf(1500)));


    }
    @Test
    void itAllowsToWithdraw() {
        CreditCard card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(900), card.getBalance());

    }
    @Test
    void canOnlyWithdrawTenTimes() {
        CreditCard card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));

        for (int i=0 ; i<10 ; i++){
            card.withdraw(BigDecimal.valueOf(10));
        }

        assertThrows(
                tooMuchWithdrawals.class,
                () -> card.withdraw(BigDecimal.valueOf(100)));


    }


    @Test
    void itAllowsToReassignLimit(){
        CreditCard card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));
        card.reassignCredit(BigDecimal.valueOf(500));

        assertEquals(BigDecimal.valueOf(500), card.getBalance());
    }

    @Test
    void itCantReassignLimitBelow100() {
        CreditCard card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));
        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.reassignCredit(BigDecimal.valueOf(10)));
        assertThrows(CreditBelowThresholdException.class,
                () -> card.reassignCredit(BigDecimal.valueOf(99)));

        assertDoesNotThrow(() -> card.reassignCredit(BigDecimal.valueOf(100)));
    }


}