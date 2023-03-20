package pl.wiktorkrupa.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;

    public CreditCard(String cardNumber) {
    }
    public int limitAssigned= 0;
    public int numberOfWithdrawals =0;

    public void assignCredit(BigDecimal creditAmount) {
        if (limitAssigned == 1){
            throw new LimitAlreadyAssignedException();
        }
        if (isBelowCreditTreshold(creditAmount)) {
            throw new CreditBelowThresholdException();
        }
        this.balance = creditAmount;
        limitAssigned = 1;
    }

    private static boolean isBelowCreditTreshold(BigDecimal creditAmount) {
        return creditAmount.compareTo(BigDecimal.valueOf(100)) < 0;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amountWithdrawn) {

       if (numberOfWithdrawals<10) {
           this.balance = balance.subtract(amountWithdrawn);
           numberOfWithdrawals += 1;
       }
       else throw new tooMuchWithdrawals();
    }

    public void reassignCredit(BigDecimal newBalance) {
        if (isBelowCreditTreshold(newBalance)){
            throw new CreditBelowThresholdException();
        }
        this.balance = newBalance;

    }
}
