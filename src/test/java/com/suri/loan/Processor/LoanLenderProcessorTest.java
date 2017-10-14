package com.suri.loan.Processor;

import com.suri.loan.Model.LoanBorrower;
import com.suri.loan.Model.LoanLender;
import org.junit.Test;

import java.util.List;

import static com.suri.loan.util.ReadCsv.getLoanLenderDataFromFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author lakshay13@gmail.com
 */
public class LoanLenderProcessorTest {

    @Test
    public void testAmountValidationWithIncorrectBorrowingAmt() {

        //given: when loan borrowing amount is too less
        LoanBorrower loanBorrower = new LoanBorrower(100);

        //when: validation method is invoked
        LoanLenderProcessor loanLenderProcessor = new LoanLenderProcessor();
        boolean loanAmtValidation = loanLenderProcessor.isLoanAmountValidated(loanBorrower);

        //then: loan amount validation should be false
        assertFalse(loanAmtValidation);
    }

    @Test
    public void testAmountValidationWithProperBorrowingAmt() {

        //given: when loan borrowing amount is within range
        LoanBorrower loanBorrower = new LoanBorrower(1500);

        //when: validation method is invoked
        LoanLenderProcessor loanLenderProcessor = new LoanLenderProcessor();
        boolean loanAmtValidation = loanLenderProcessor.isLoanAmountValidated(loanBorrower);

        //then: loan amount validation should be true
        assertTrue(loanAmtValidation);
    }

    @Test
    public void testGetPossibleLender() {

        //given: list of lenders and a borrower
        List<LoanLender> loanLenderList = getLoanLenderDataFromFile("/Users/lakshay/Desktop/Projects/LoanProject/src/test/resources/file1.csv");
        LoanBorrower loanBorrower = new LoanBorrower(1000);

        //when:  method to get possible lender's is invoked
        LoanLenderProcessor loanLenderProcessor = new LoanLenderProcessor();
        List<LoanLender> possibleLoanLenderList = loanLenderProcessor.getPossibleLenders(loanLenderList, loanBorrower);

        //then: possible lender list should have size 1 as there is only 1 lender in list which can afford 1000 pounds
        assertEquals(1, possibleLoanLenderList.size());
    }

    @Test
    public void testNoLenderWhenBorrowingAmountIsTooHigh() {

        //given: list of lenders and a borrower
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<LoanLender> loanLenderList = getLoanLenderDataFromFile(classLoader.getResource("file1.csv").getPath());
        LoanBorrower loanBorrower = new LoanBorrower(1500);

        //when:  method to get possible lender's is invoked
        LoanLenderProcessor loanLenderProcessor = new LoanLenderProcessor();
        List<LoanLender> possibleLoanLenderList = loanLenderProcessor.getPossibleLenders(loanLenderList, loanBorrower);

        //then: possible lender list should have size 0 as there is only NO lender in list which can afford 1500 pounds
        assertEquals(0, possibleLoanLenderList.size());
    }

    @Test
    public void testGetLenderWithLeastInterestRate() {

        //given: list of lenders and a borrower
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<LoanLender> loanLenderList = getLoanLenderDataFromFile(classLoader.getResource("file1.csv").getPath());
        LoanBorrower loanBorrower = new LoanBorrower(1000);

        //when:  possible lender's info is obtained with which the lender info method is invoked
        LoanLenderProcessor loanLenderProcessor = new LoanLenderProcessor();
        List<LoanLender> possibleLoanLenderList = loanLenderProcessor.getPossibleLenders(loanLenderList, loanBorrower);
        LoanLender loanLender = loanLenderProcessor.getLender(possibleLoanLenderList);

        //then: loan lender obtained should be the one with minimum rate of interest which can give the requested amount.
        assertEquals(0.071, loanLender.getRateOfInterest(), 0.1);
    }
}
