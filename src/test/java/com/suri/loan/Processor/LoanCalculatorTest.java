package com.suri.loan.Processor;

import com.suri.loan.Model.LoanBorrower;
import com.suri.loan.Model.LoanInformation;
import com.suri.loan.Model.LoanLender;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author lakshay13@gmail.com
 */
public class LoanCalculatorTest {

    private LoanInformation loanInformation;

    @Test
    public void testLoanRepaymentWithNoLoanLender() {

        // given: loan borrower but no loan lender
        LoanBorrower loanBorrower = new LoanBorrower(200);

        // when: loan repayment is calculated
        try {
            LoanCalculator loanCalculator = new LoanCalculator();
            loanInformation = loanCalculator.getLoanRepaymentInfo(null, loanBorrower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // then: loan information should be null
        assertNull(loanInformation);
    }

    @Test
    public void testLoanRepaymentWithNoBorrowerInformation() {

        // given: loan lender but no borrower
        LoanLender loanLender = new LoanLender("Alex", 7.5, 200);

        // when: loan repayment is calculated
        try {
            LoanCalculator loanCalculator = new LoanCalculator();
            loanInformation = loanCalculator.getLoanRepaymentInfo(loanLender, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // then: loan information should be null
        assertNull(loanInformation);
    }

    @Test
    public void testLoanMonthlyRepayment() {

        // given: loan lender & borrower
        LoanLender loanLender = new LoanLender("Andrea", 4.0, 500);
        LoanBorrower loanBorrower = new LoanBorrower(400);

        // when: loan repayment is calculated
        try {
            LoanCalculator loanCalculator = new LoanCalculator();
            loanInformation = loanCalculator.getLoanRepaymentInfo(loanLender, loanBorrower);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then: monthly repayment value should exist as calculated below with a precision of 0.2
        double actualMonthlyRepayment = loanInformation.getMonthlyRepayment();
        double expectedMonthlyRepayment = 12.52; // calculated manually
        assertEquals(expectedMonthlyRepayment, actualMonthlyRepayment, 0.2);
    }

    @Test
    public void testLoanTotalRepayment() {

        // given: loan lender & borrower
        LoanLender loanLender = new LoanLender("Andrea", 4.0, 500);
        LoanBorrower loanBorrower = new LoanBorrower(400);

        // when: loan repayment is calcualated
        try {
            LoanCalculator loanCalculator = new LoanCalculator();
            loanInformation = loanCalculator.getLoanRepaymentInfo(loanLender, loanBorrower);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then: total repayment value should exist as calculated below with a precision of 0.2
        double actualTotalRepayment = loanInformation.getTotalRepayment();
        double expectedTotalRepayment = 450.90; // calculated manually
        assertEquals(expectedTotalRepayment, actualTotalRepayment, 0.2);
    }
}
