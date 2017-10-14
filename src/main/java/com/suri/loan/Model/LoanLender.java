package com.suri.loan.Model;

/**
 * @author lakshay13@gmail.com
 */
public class LoanLender {

    private String lenderName;
    private double rateOfInterest;
    private int available;

    public LoanLender(String lenderName, double rateOfInterest, int available) {
        this.lenderName = lenderName;
        this.rateOfInterest = rateOfInterest;
        this.available = available;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(float rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }


}
