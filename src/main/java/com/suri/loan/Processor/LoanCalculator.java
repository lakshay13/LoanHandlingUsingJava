package com.suri.loan.Processor;

import com.suri.loan.Model.LoanBorrower;
import com.suri.loan.Model.LoanInformation;
import com.suri.loan.Model.LoanLender;
import java.lang.Math;
import java.util.logging.Logger;

/**
 * @author lakshay13@gmail.com
 */
public class LoanCalculator {

    private static final Logger LOGGER = Logger.getLogger(LoanCalculator.class.toString());
    private static final int numberOfYears = 3; // 36 months correspond to 3 years

    /**
     * Method that sets the computed repayment loan values in the LoanInformation Model
     * @param loanLender object containing information of selected Loan Lender
     * @param loanBorrower object containing information of loan borrower
     * @return Loan Repayment Information
     * @throws Exception if there is any issue in calculating loan
     */
    public LoanInformation getLoanRepaymentInfo(LoanLender loanLender, LoanBorrower loanBorrower) throws Exception {

        if (loanLender == null || loanBorrower == null) {
            return null;
        }

        double totalLoan = getCalculatedLoan(loanLender, loanBorrower);

        // setting loan repayment info in LoanInformation object
        LoanInformation loanInformation = new LoanInformation();
        loanInformation.setTotalRepayment(Math.floor(totalLoan * 100) / 100.0); // storing with 2 decimals
        loanInformation.setMonthlyRepayment(Math.floor((totalLoan/36) * 100) / 100.0); // storing with 2 decimals
        loanInformation.setRateOfInterest(loanLender.getRateOfInterest());
        loanInformation.setRequestedAmount(loanBorrower.getRequestedLoanAmount());

        return loanInformation;
    }

    /**
     * Method that computes the loan repayment values
     * @param loanLender object containing information of selected Loan Lender
     * @param loanBorrower object containing information of loan borrower
     * @return Loan Repayment Information
     * @throws Exception if there is any issue in calculating loan
     */
    private double getCalculatedLoan(LoanLender loanLender, LoanBorrower loanBorrower) throws Exception {
        double loan;
        double roi = loanLender.getRateOfInterest();
        int amt = loanBorrower.getRequestedLoanAmount();
        // monthly compounding interest
        try {
            double base = 1+(roi/(100*12));
            double power = 12 * numberOfYears;
            double factor = Math.pow(base, power);
            loan = amt * factor;
        } catch (Exception e) {
            LOGGER.warning("Exception occured during calculation of loan" + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return loan;
    }
}
