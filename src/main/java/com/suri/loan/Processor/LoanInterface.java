package com.suri.loan.Processor;

import com.suri.loan.Model.LoanBorrower;
import com.suri.loan.Model.LoanLender;

import java.util.List;

/**
 * @author lakshay13@gmail.com
 */
public interface LoanInterface {

    boolean isLoanAmountValidated(LoanBorrower loanBorrower);
    List<LoanLender> getPossibleLenders(List<LoanLender> loanLenders, LoanBorrower loanBorrower);
    LoanLender getLender(List<LoanLender> possibleLender);
}
