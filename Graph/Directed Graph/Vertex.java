/**
 * The Vertex class below describes the behavior of a Vertex object.
 */
import java.util.Comparator;

public class Vertex implements Comparator<Vertex> {

  // Class Variables
  private String name;
  private int value;
  private Vertex parent;
  private boolean visited;
  private int distance;

  // Constructor with arguments
  public Vertex(String name, int value){
    this.name = name;
    this.value = value;
    this.parent = null;
    this.visited = false;
  }

  // Constructor with only name field as an argument
  public Vertex(String name){
    this.name = name;
    this.value = 0;
    this.parent = null;
    this.visited = false;
  }

  // Constructor without arguments
  public Vertex(){
    this.name = null;
    this.value = 0;
    this.parent = null;
    this.visited = false;
  }

  // Class Setter and Getter methods
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public Vertex getParent() {
    return parent;
  }

  public void setParent(Vertex parent) {
    this.parent = parent;
  }

  protected boolean isVisited() {
    return visited;
  }

  protected void setVisited(boolean visited) {
    this.visited = visited;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }

  /**
   * An equals method to determine equality between Vertex objects.
   * @param v The vertex object to compare this one to
   * @return True of the name fields are equal
   */
  public boolean equals(Vertex v){
    if (v.name.equals(this.name) && v.value == this.value){
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns a String representation of this Vertex object.
   * @return a String with the filed name and value listed
   */
  public String toString(){
    return "Name: " + this.name + " | Value: " + this.value;
  }

  /**
   * The compare method compares two Vertex objects based on their distance
   * class variable.
   * @param obj1 The first Vertex object to compare
   * @param obj2 The second Vertex object to compare
   * @return An integer value denoting whether the first Vertex object is
   * greater than, equal to, or less than the second Vertex object
   */
  @Override
  public int compare(Vertex obj1, Vertex obj2){
    if (obj1.distance < obj2.distance){
      return 1;
    }
    if (obj1.distance > obj2.distance){
      return -1;
    }
    return 0;
  }

  
}
