/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhashmap;

/**
 *
 * @author timmermank1
 */
public class MyHashTable<T> {

    private final int HASH_TABLE_SIZE; //Capacity of the table
    private Record<T>[] hashMap;
    private int currentSize; //Number of values in the table
    public int collisionsForThisInsert; //This variable is strictly for analysis purposes

    public MyHashTable(int size) {
        this.HASH_TABLE_SIZE = size;
        hashMap = new Record[HASH_TABLE_SIZE];
        currentSize = 0;
        //Fills hashMap array with empty records
        for (int i = 0; i < HASH_TABLE_SIZE; i++) {
            Record<T> r = new Record<T>();
            hashMap[i] = r;
        }
    }

    /*Finds an element with a certain key and stores it in the value passed*/
    public boolean find(int key, T value) {
        return false;
    }

    /*Inserts the key/value into the hashtable*/
    public boolean insert(int key, T value) {
        int homeIndex = hashFunction(key);
        int collisions = 0;
        if (hashMap[homeIndex].isNormal() && hashMap[homeIndex].getKey() != key) {
            int nextIndex = probeFunction(homeIndex, collisions++);
            while (hashMap[nextIndex].isNormal()&& collisions<HASH_TABLE_SIZE) {
                if (hashMap[nextIndex].getKey() == key) {
                    System.out.println("Duplicate Key");
                    return false;
                }
                nextIndex = probeFunction(homeIndex, collisions++);
            }
            Record<T> insertThis = new Record<T>(key, value);
            hashMap[nextIndex] = insertThis;
            currentSize++;
        } else if (hashMap[homeIndex].getKey() == key) {
            System.out.println("Duplicate Key");
            return false;
        } else {
            Record<T> insertThis = new Record<T>(key, value);
            hashMap[homeIndex] = insertThis;
            currentSize++;
        }
        return true;
    }

    /*Kills a table key and returns the associated value*/
public T remove(int key) {

        return null;
    }

    /*Returns the load factor for the hash*/
    public double alpha() {
        return this.currentSize / this.HASH_TABLE_SIZE;
    }

    /*Hash function for finding the home position*/
    private int hashFunction(int key) {
        return key % HASH_TABLE_SIZE;
    }

    /*The result of probing is returned with the new slot's position*/
    private int probeFunction(int homeIndex, int collisions) {
        int newIndex = homeIndex + collisions;
        if( newIndex >= HASH_TABLE_SIZE){
            newIndex = 0;
        }
        return newIndex;

    }

    public String toString() {
        String table = "";
        for (int i = 0; i < this.HASH_TABLE_SIZE; i++) {
            table += i + ". " + hashMap[i].toString() + "\n";
        }
        return table;
    }

}
