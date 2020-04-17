/**
 * The Vertex class implements the structure of a Vertex object. A Vertex object
 * has a String name and an integer value for data storage.
 */
public class Vertex {

  // Class Variables
  private String name;
  private int data;
  private boolean visited;  // Declaring a visited boolean class variable.

  /**
   * The Vertex constructor returns a Vertex object. THis constructor takes a
   * String and an integer as parameter variables.
   * @param name The name to store as this Vertex object's class variable
   * @param data The data to store in this Vertex object's class variable
   */
  public Vertex(String name, int data){
    this.name = name;
    this.data = data;
    this.visited = false;
  }

  /**
   * The no argument Vertex constructor allows a Vertex object to be created
   * without providing a String or integer parameter variable. The name and
   * data class variables of this Vertex object will therefore be set to null
   * and zero.
   */
  public Vertex(){

    // Assigning null, zero, and false to each class variable, based on type.
    this.name = null;
    this.data = 0;
    this.visited = false;
  }

  public String getName() {

    return name;            // Return name instance variable.
  }

  public void setName(String name) {

    this.name = name;
  }

  public int getData() {

    return data;          // Return data instance variable.
  }

  public void setData(int data) {

    this.data = data;
  }

  /**
   * The isVisited method returns true if this Vertex object's visited variable
   * is true, else false is returned.
   * @return True if the visited class variable is true, else false is returned.
   */
  public boolean isVisited() {

    return visited;        // Returning the value if the visited class variable.
  }

  /**
   * The setVisited method allows the client to set the value of this Vertex
   * object's visited class variable.
   * @param visited The value to change the Vertex object's visited class
   * variable to
   */
  public void setVisited(boolean visited) {

    this.visited = visited;     // Assigning parameter value to class variable.
  }

  /**
   * The equals method returns true of the name instance variable of this
   * Vertex object is equal to the name instance variable of the parameter
   * Vertex object.
   * @param v The Vertex parameter object to test for equality
   * @return True if the name instance variable of this and the parameter
   * Vertex objects have the same value, else false is returned
   */
  public boolean equals(Vertex v){

    return this.name.equals(v.name);
  }

  /**
   * The toString method returns a String representation of the Vertex object
   * to the client.
   * @return A String, the name instance variable
   */
  public String toString(){

    return this.name;
  }

}
