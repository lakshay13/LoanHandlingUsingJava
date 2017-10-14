package com.suri.loan.Model;

/**
 * @author lakshay13@gmail.com
 */
public class LoanBorrower {

    private int requestedLoanAmount;

    public LoanBorrower(int requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public int getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(int requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanBorrower that = (LoanBorrower) o;

        return requestedLoanAmount == that.requestedLoanAmount;

    }

    @Override
    public int hashCode() {
        return requestedLoanAmount;
    }

    @Override
    public String toString() {
        return "LoanBorrower{" +
                "requestedLoanAmount=" + requestedLoanAmount +
                '}';
    }
}
