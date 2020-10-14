/*
 * This is the driver class. Note that it should only ever interact with the
 * MyHashTable class  - Not with the Record class.
 */
package myhashmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Katie Timmerman CS 180 - 02 Prof: Dr Timmerman Assignment:
 */
public class MyHashMapDriver {

    public static void main(String[] args) throws FileNotFoundException {
        debuggingFunctions();
        //completeAnalysis();
        //readWriteData();

    }

    public static void debuggingFunctions() {
        MyHashTable<Integer> myTable = new MyHashTable<Integer>(7);//Can be changed to any prime number
        ArrayList<Integer> addedKeys = new ArrayList<Integer>();
        System.out.println("TESTING ADDING ELEMENTS");
        for (int i = 0; i < 5; i++) {
            int key_value = (int) (Math.random() * 1000000);
            if (myTable.insert(key_value, key_value)) {
                addedKeys.add(key_value);
            } else {
                i--; //Don't count the insert if key already in table
            }
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING ELEMENTS");//All should be found and be true.
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key, key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING REMOVING ELEMENTS");//Looking for tombstones
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " removed: " + myTable.remove(key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING REMOVED ELEMENTS");//all should be false
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key, key));
        }
        System.out.println(myTable);

        System.out.println("\nTESTING FINDING VALID ELEMENTS AFTER REMOVAL");//Want these to be true
        for (int i = 1; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key, key));
        }
        System.out.println(myTable);

    }

    public static void completeAnalysis() {
        MyHashTable<Integer> myTable = new MyHashTable<Integer>(1009);
        for (int i = 0; i < 1009; i++) {
            int key_value = (int) (Math.random() * 1000000);
            if(i == 100 || i == 201 || i == 302 || i == 403 || i == 504 || i ==605 || i == 706 || i == 807 || i == 908 || i == 1007){
                // add the item
                // collect the number of collisions.
                //remove the item just added
                //repeat 19 more times
                //average the number of collisions that occured
            }
            else if (! myTable.insert(key_value, key_value)) {
                i--; //Don't count the insert if key already in table
            }
        }

    }
    
    /**
     * This part is unnecessary for all levels other than A. It is a brief
     * example of how to read and write from a file.
     * @throws FileNotFoundException 
     */
    public static void readWriteData() throws FileNotFoundException{
        //How to print to a file.  OVERWRITES
        PrintWriter outFile = new PrintWriter("output.txt");
        //outFile == System.out
        System.out.println("Hello World");
        outFile.println("Hello World");
        outFile.close();
        
        //Reading Files
        File file = new File("output.txt");
        Scanner input = new Scanner(file);
        
        int value = input.nextInt();
        
    }

}
