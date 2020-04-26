/**
 * The Edge class below implements the behavior of an Edge object.
 * Note: The Edge class uses Vertex objects.
 */
public class Edge {

  // Class variables
  private Vertex vertex;
  private int cost;

  // Constructor with arguments
  public Edge(Vertex destination, int cost){
    this.vertex = destination;
    this.cost = cost;
  }

  // Class Setter and Getter methods
  public void setCost(int cost){
    this.cost = cost;
  }

  public int getCost(){
    return this.cost;
  }

  public void setVertex(Vertex destination){
    this.vertex = destination;
  }

  public Vertex getVertex(){
    return this.vertex;
  }

  /**
   * An equals method that tests equality of other Edge objects with this Edge
   * object.
   * @param e The Edge to compare to this one
   * @return True if the Vertex class fields are equal
   */
  public boolean equals(Edge e){
    if (e.vertex.equals(this.vertex)){
      return true;
    } else {
      return false;
    }
  }

  /**
   * An equals method that tests equality of other Vertex objects compared with
   * this Edge object.
   * @param v The Vertex object to compare to this Edge object
   * @return True if the Vertex object and the Vertex class field are the same
   */
  public boolean equals(Vertex v){
    if (v.equals(this.vertex)){
      return true;
    } else {
      return false;
    }
  }

  /**
   * The toString method returns a String representation of an Edge object.
   * @return A String representation of an Edge object, listing the vertex
   * field's name variable and the class field
   */
  public String toString(){
    String result = "";
    result += "Name: " + this.vertex.getName() + " | Cost: " + this.cost;
    return result;
  }

}
