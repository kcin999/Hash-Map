/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingandwritingexample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author timmermank1
 */
public class ReadingAndWritingExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        readFromAFile();
        WriteToAFile();
    }

    private static void readFromAFile() throws FileNotFoundException {
        /** To read from a file, the file must exist.
         * On the Right hand side, Under the files tab, if
         * you look in the project folder you will see
         * a file called myInputFile.csv
         * Note: if the Files tab isn't available 
         *  in the menus bar click Window->Files
        */
        
        //Find the file.
        File myFile = new File("myInputFile.csv");
        //Connect to the file
        Scanner input = new Scanner(myFile);
        
        
        //type input.  to see all the options. Here is a breif example.
        // I only care about the ratings
        ArrayList<Double> ratings = new ArrayList();
        input.nextLine(); //throw away the headers line.
        while(input.hasNext()){
            //Read in the next line and split it into an array based on commas
            String [] line = input.nextLine().split(",");
            //Grab the item I care about (index 3), remove whitespaces (trim),
            // then change it into the data type I want
            double indRating = Double.parseDouble(line[3].trim());
            //add it to my arrayList
            ratings.add(indRating);
        }
        System.out.println(ratings);
       input.close(); //disconnect so others can use it
    }

    private static void WriteToAFile() throws FileNotFoundException {
        //To write to a file, we can simply create the file on the fly.
        //Notice that under the files tab there is no myOutFile.txt
        //Note: if there is you can delete it. If you don't delete it, 
        // it will be overwritten.
        // If you are confused about files tab see the method readingFromAFile
        PrintWriter outFile = new PrintWriter("myOutFile.txt");
        
        //now we can simply replace System.out with outFile.
        
        //Basic Example
        for (int i = 0; i < 10; i++) {
            double value = Math.random()*12 +4;
            outFile.print(i+ ". ");
            outFile.printf("%.2f\n", value);
        }
        outFile.close();//Forces the write of whatever is in the buffer
        
    }
    
}
