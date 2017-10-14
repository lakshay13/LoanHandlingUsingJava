package com.suri.loan.Processor;

import com.suri.loan.Model.LoanBorrower;
import com.suri.loan.Model.LoanLender;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author lakshay13@gmail.com
 */
public class LoanLenderProcessor implements LoanInterface {

    private static final Logger LOGGER = Logger.getLogger(LoanLenderProcessor.class.toString());
    private static final int MIN_BORROW_AMT = 1000;
    private static final int MAX_BORROW_AMT = 1500;
    private static final int FACTOR = 100;

    /**
     * Method is used to validate the loan amount if it is within the limits as specified by the requirement
     * @param loanBorrower loan borrower object
     * @return true if borrower amount satisfies conditions
     */
    public boolean isLoanAmountValidated(LoanBorrower loanBorrower) {

        int amt = loanBorrower.getRequestedLoanAmount();
        if ((amt < MIN_BORROW_AMT || amt > MAX_BORROW_AMT) || (amt % FACTOR !=0)) {
            LOGGER.warning("Loan amount requested does not meet the requirement");
            return false;
        }
        return true;
    }

    /**
     * Method that returns possible lenders with the money requested by borrower
     * @param loanLenders entire list of loan lenders
     * @param loanBorrower loan borrower object
     * @return possible lenders list with the money requested by borrowers
     */
    public List<LoanLender> getPossibleLenders(List<LoanLender> loanLenders, LoanBorrower loanBorrower) {

        if (loanLenders.size() == 0 || loanBorrower == null) {
            return null;
        }

        List<LoanLender> possibleLenders = new LinkedList<LoanLender>();
        int amt = loanBorrower.getRequestedLoanAmount();

        for (LoanLender loanContender: loanLenders) {
            if (loanContender.getAvailable() >= amt) {
                possibleLenders.add(loanContender);
            }
        }
        return possibleLenders;
    }

    /**
     * Method returns the lender with minimum interest rate that has the money to lend to borrower
     * @param possibleLenders input list of possible lenders
     * @return suggested lender
     */
    public LoanLender getLender(List<LoanLender> possibleLenders) {

        if (possibleLenders.size() == 0) {
            return null;
        }

        LoanLender suggestedLender = possibleLenders.get(0);
        double minRateOfInterest = possibleLenders.get(0).getRateOfInterest();

        for (LoanLender l : possibleLenders) {
            if (l.getRateOfInterest() < minRateOfInterest) {
                minRateOfInterest = l.getRateOfInterest();
                suggestedLender = l;
            }
        }
        return suggestedLender;
    }
}
