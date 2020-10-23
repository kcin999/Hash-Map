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
    int[] psuedoRandomSequence;

    public MyHashTable(int size) {
        this.HASH_TABLE_SIZE = size;
        hashMap = new Record[HASH_TABLE_SIZE];
        currentSize = 0;
        //Fills hashMap array with empty records
        for (int i = 0; i < HASH_TABLE_SIZE; i++) {
            Record<T> r = new Record<T>();
            hashMap[i] = r;
        }
        //Pseudo Random Probe sequence
        this.psuedoRandomSequence = new int[HASH_TABLE_SIZE];
        for (int i = 0; i < this.psuedoRandomSequence.length; i++) {
            this.psuedoRandomSequence[i] = (int) (Math.random() * HASH_TABLE_SIZE);
        }
        String temp = "[";
        for (int i = 0; i < this.psuedoRandomSequence.length; i++) {
            temp = temp+ " " + this.psuedoRandomSequence[i]+ ",";
        }
        System.out.println("Random Sequence: " + temp);
    }

    /*Finds an element with a certain key and stores it in the value passed*/
    public boolean find(int key, T value) {
        int homeIndex = hashFunction(key);
        int checkIndex = homeIndex;
        int collisions = 0;
        while (this.hashMap[checkIndex].getKey() != key && collisions < this.HASH_TABLE_SIZE) {
            checkIndex = probeFunction(homeIndex, ++collisions);
        }
        if (collisions >= this.HASH_TABLE_SIZE || !this.hashMap[checkIndex].isNormal()) {
            return false;
        }
        value = this.hashMap[checkIndex].getValue();
        return true;
    }

    /*Inserts the key/value into the hashtable*/
    public boolean insert(int key, T value) {
        int homeIndex = hashFunction(key);
        int checkIndex = homeIndex;
        collisionsForThisInsert = 0;

        while (this.hashMap[checkIndex].isNormal() && collisionsForThisInsert < this.HASH_TABLE_SIZE) {
            checkIndex = probeFunction(homeIndex, ++collisionsForThisInsert);
        }
        if (collisionsForThisInsert >= this.HASH_TABLE_SIZE && this.hashMap[checkIndex].getKey() == key) {
            return false;
        }
        Record<T> insertThis = new Record<T>(key, value);
        this.hashMap[checkIndex] = insertThis;
        this.currentSize++;
        return true;
    }

    /*Kills a table key and returns the associated value*/
    public T remove(int key) {
        int homeIndex = hashFunction(key);
        int checkIndex = homeIndex;
        int collisions = 0;
        while (this.hashMap[checkIndex].getKey() != key && collisions < this.HASH_TABLE_SIZE) {
            checkIndex = probeFunction(homeIndex, ++collisions);
        }
        if (collisions >= this.HASH_TABLE_SIZE) {
            return null;
        }
        this.hashMap[checkIndex].deleteRecord();
        this.currentSize--;
        return this.hashMap[checkIndex].getValue();
    }

    /*Returns the load factor for the hash*/
    public double alpha() {
        return this.currentSize / this.HASH_TABLE_SIZE;
    }

    /*Hash function for finding the home position*/
    private int hashFunction(int key) {
        return (int) (HASH_TABLE_SIZE * (.3657 * key % 1)); //Multiplication Key
        //return key % this.HASH_TABLE_SIZE; // Modular Key
    }

    /*The result of probing is returned with the new slot's position*/
    private int probeFunction(int homeIndex, int collisions) {
        return (homeIndex + (collisions*this.psuedoRandomSequence[homeIndex])) % this.HASH_TABLE_SIZE;//Psuedorandom Probe
        //  return (homeIndex + collisions)%this.HASH_TABLE_SIZE ;//Linear Probe

    }
  

    public String toString() {
        String table = "";
        for (int i = 0; i < this.HASH_TABLE_SIZE; i++) {
            table += i + ". " + hashMap[i].toString() + "\n";
        }
        return table;
    }

}
