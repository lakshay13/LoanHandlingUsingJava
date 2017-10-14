package com.suri.loan;

import com.suri.loan.Model.LoanBorrower;
import com.suri.loan.Model.LoanInformation;
import com.suri.loan.Model.LoanLender;
import com.suri.loan.Processor.LoanCalculator;
import com.suri.loan.Processor.LoanLenderProcessor;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.suri.loan.util.ReadCsv.getLoanLenderDataFromFile;

/**
 * @author lakshay13@gmail.com
 */
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.toString());

    public static void main(String[] args) {

        // Reading the amount requested by user.
        int amtRequested;
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the amount of loan you want: ");
        try {
            amtRequested = reader.nextInt();
        } catch (Exception e) {
            LOGGER.info("Please enter a proper value");
            return;
        } finally {
            reader.close();
        }

        // Reading the lender's data
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<LoanLender> loanLenderList = getLoanLenderDataFromFile(classLoader.getResource("file1.csv").getPath());

        LoanBorrower loanBorrower = new LoanBorrower(amtRequested);
        LoanLenderProcessor loanLenderProcessor = new LoanLenderProcessor();
        LoanCalculator loanCalculator = new LoanCalculator();
        LoanLender loanLender;
        LoanInformation loanInformation = null;

        // validate the loan amount requested
        if (loanLenderProcessor.isLoanAmountValidated(loanBorrower)) {
            // get possible list of loan lenders
            List<LoanLender> possibleLenders = loanLenderProcessor.getPossibleLenders(loanLenderList, loanBorrower);
            // get the selected loan lender
            loanLender = loanLenderProcessor.getLender(possibleLenders);
            if (loanLender != null) {
                try {
                    // calculate the loan repayment amt
                    loanInformation = loanCalculator.getLoanRepaymentInfo(loanLender, loanBorrower);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (loanInformation != null) {
                    // display the result
                    System.out.println("Requested Amount: £" + loanInformation.getRequestedAmount());
                    System.out.println("Rate: " + loanInformation.getRateOfInterest()*100 + "%");
                    System.out.println("Monthly Repayment: £" + loanInformation.getMonthlyRepayment());
                    System.out.println("Total Payment: £" + loanInformation.getTotalRepayment());
                }
            } else {
                System.out.println("Sorry, a quote can not be provided at this time");
            }
        } else {
            System.out.println("Sorry, loan amount requested is not within 100£ incremental range of 1000£ to 15000£ (inclusive)");
        }
    }
}
