package com.suri.loan.Model;

/**
 * @author lakshay13@gmail.com
 */
public class LoanInformation {

    private int requestedAmount;
    private double rateOfInterest;
    private double monthlyRepayment;
    private double totalRepayment;

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }
}



