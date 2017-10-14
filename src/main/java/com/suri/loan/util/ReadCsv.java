package com.suri.loan.util;

import com.suri.loan.Model.LoanLender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lakshay13@gmail.com
 */
public class ReadCsv {

    /**
     * method to read the csv file provided in the resources section of the project
     * @param filePath: path where the file is
     * @return entire list of loan lenders
     */
    public static List<LoanLender> getLoanLenderDataFromFile(String filePath) {

        LoanLender loanLender;
        List<LoanLender> list = new LinkedList<>();
        String line = "";
        String csvSplitIndicator = ",";


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // skipping the first line as it is the name of the columns
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] lineSplit = line.split(csvSplitIndicator);
                loanLender = new LoanLender(lineSplit[0], Double.parseDouble(lineSplit[1]),
                        Integer.parseInt(lineSplit[2]));
                list.add(loanLender);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
