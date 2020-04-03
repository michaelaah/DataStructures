/**
 * The HashTable class below implements a Hash Table via the external chaining
 * method. Aside from the HashTable functions listed below he HashTable class
 * also contains a nested Mapping class, which is a tuple of type (String s, int i).
 * The Mapping class tuple relationship of type (String s, int i) represents the
 * tuple (key, value) relationship.
 */

import java.util.NoSuchElementException;
import java.util.Objects;

public class HashTable {

  /**
   * The Mapping class describes the attributes and behavior of a Mapping object
   * that is to be stored within a HashTable object.
   */
  public class Mapping{

    // Mapping class variables
    private Mapping next;
    private String key;
    private int value;

    /**
     * The Mapping constructor returns an instance of a Mapping object.
     * @param key The key value to store in the Mapping object
     * @param value The value attribute to store in the Mapping object
     */
    public Mapping(String key, int value){
      this.key = key;
      this.value = value;
      this.next = null;
    }
    
  } // End of Mapping class

  // HashTable class variables
  private int size;
  private int count;
  private Object[] bucketArray;

  /**
   * The HashTable constructor returns an instance of a HashTable object and
   * initializes the HashTable's instance variables.
   */
  public HashTable(){
    this.count = 0;
    this.size = 19;     // Assigning instance variable to prime number nineteen.
    this.bucketArray = new Object[this.size];
  }

  /**
   * The size method returns the size of the HashTable object, that is, its
   * capacity.
   * @return the capacity of the HashTable object
   */
  public int size(){

    return this.size;
  }

  /**
   * The put method adds a Mapping object to the bucketArray variable. Before
   * and after adding the Mapping object, the put method test whether the
   * bucketArray needs to be rehashed or not via testing the overLoad method.
   * @param key The key associated with the value
   * @param value The value to store in the hashTable object
   */
  public void put(String key, int value) {
    if (overLoad()){
      reHash();
    }
    int index = hash(key);
    Mapping elem = new Mapping(key, value);
    if (bucketArray[index] == null) {
      bucketArray[index] = elem;
    } else {
      Mapping temp = (Mapping) bucketArray[index];
      if (temp.next == null){
        temp.next = elem;
      } else {
        while (temp.next != null){
          temp = temp.next;
        }
        temp.next = elem;
      }
    }
    count++;
    if (overLoad()){
      reHash();
    }
  }

  /**
   * The remove method searches the HashTable object for the object with the
   * corresponding key and removes it from the HashTable.
   * @param key The key associated with the Mapping object to remove.
   */
  public void remove(String key) {
    Mapping elem = (Mapping) bucketArray[hash(key)];
    if (elem.key.equals(key)) {
      if (elem.next == null) {
        bucketArray[hash(key)] = null;
      } else {
        bucketArray[hash(key)] = elem.next;
      }
    } else {
      if (elem.next.key.equals(key)){
        elem.next = elem.next.next;
      }
      while (elem.next != null) {
        if (elem.next.key.equals(key)){
          if (elem.next.next != null){
            elem.next = elem.next.next;
          } else {
            elem.next = null;
          }
        } else {
          elem = elem.next;
        }
      }
    }
    count--;
  }

  /**
   * The get method returns the value of a given key, if the key is found
   * within the HashTable object, else a no such element exception is thrown.
   * @param key The key to get the value of
   * @return the value associated with the key parameter
   */
  public int get(String key){
    Mapping elem = (Mapping) bucketArray[hash(key)];
    if (elem.key.equals(key)){
      return elem.value;
    } else {
      if (elem.next == null){
        throw new NoSuchElementException();
      }
      while (elem.next != null){
        elem = elem.next;
        if (elem.key.equals(key)){
          return elem.value;
        }
      }
      throw new NoSuchElementException();
    }
  }

  /**
   * The overLoad function determines if the current HashTable object is over
   * a specific threshold. If so, true is returned, else false is returned.
   * @return True if the HashTable object is over a specific threshold,
   * else false is returned
   */
  private boolean overLoad(){
    double nElems = (double) this.count;
    double nCapacity = (double) this.size;

    if (nElems / nCapacity > .85) {

      return true;
    } else {

      return false;
    }
  }

  /**
   * The reHash method creates a new bucketArray variable and shifts all
   * elements in the old bucketArray into the new array and updates both the
   * size and bucketArray variable references.
   */
  private void reHash(){
    Object[] oldArray = this.bucketArray;
    int newSize = findNextPrime(this.size);
    bucketArray = new Object[newSize];

    for (Object item: oldArray){
      Mapping node = (Mapping) item;
      while (node != null){
        put(node.key,node.value);
        count--;
        node = node.next;
      }
    }
    this.size = newSize;
  }

  /**
   * The hash function takes a String parameter variable and returns an int that
   * is less than the size of the HashTable object.
   * @param key The key to get the hash integer of
   * @return An int variable that is less than the size of the HashTable object.
   */
  private int hash(String key){
    int hashNumber = hashCode(key);
    
    return hashNumber % this.size;
  }

  /**
   * The hashCode function takes a String parameter variable and returns an int.
   * @param key The to get the hash integer of
   * @return An int variable
   */
  private int hashCode(String key) {
    int result = Objects.hashCode(key);
    result = Math.abs(31 * result);

    return result;
  }

  /**
   * The findNextPrime method takes an int, doubles it, then searches for and
   * returns the next largest prime number.
   * @param n An int variable to double and then find the next prime number of.
   * @return A prime number that is a little more than double the parameter
   * variable.
   */
  private int findNextPrime(int n){
    int newPrime = n * 2;
    if (isPrime(newPrime)){
      return newPrime;
    } else {
      while (!(isPrime(newPrime))){
        newPrime++;
      }
    }

    return newPrime;
  }

  /**
   * The isPrime method determines if the given parameter variable is a prime
   * number or not, it returns true if it is prime, otherwise false is returned.
   * @param number The number to test if it is a prime number
   * @return true if the parameter variable is prime, else false is returned
   */
  private boolean isPrime(int number){
    for (int i = 2; i < number; i++){
      if (number % i == 0){

        return false;
      }
    }

    return true;
  }

  /**
   * The toString method returns a String representation of the HashTable object.
   * @return A String representation of the HashTable object.
   */
  public String toString(){
    String result = "";
    for (Object o: this.bucketArray){
      Mapping item = (Mapping) o;
      if (item != null){
        result += "Key: " + item.key + " | Value: " + item.value + "\n";
        if (item.next != null){
          while (item.next != null){
            item = item.next;
            result += "Key: " + item.key + " | Value: " + item.value + "\n";
          }
        }
      }
    }

    return result;
  }
