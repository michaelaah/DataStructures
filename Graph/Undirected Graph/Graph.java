/**
 * The Graph class implements the behavior of a Graph data structure.
 * This Graph implements an adjacency list structure and uses Vertex objects.
 * Note: This class implements an undirected Graph structure.
 */
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class Graph {

  // Class Variables
  /*
   * This class uses a HashMap data structure to implement a Graph where a
   * given Vertex object, the key, is associated with it's own adjacency list,
   * (a LinkedList data structure) the value.
   */
  private HashMap<Vertex, LinkedList<Vertex>> adjacencyList;
  private int size;             // Declaring a private int size class variable.

  /*
   * Graph constructor.
   */
  public Graph(){
    /*
     * Instantiating the HashMap object class variable and assigning the
     * size class variable to zero.
     */
    this.adjacencyList = new HashMap<Vertex, LinkedList<Vertex>>();
    this.size = 0;
  }

  /**
   * The getSize method returns the number of Vertex objects to the client.
   * @return the integer number of Vertex objects held by the Graph object
   */
  public int getSize() {

    return size;           // Returning the size class variable to the client.
  }

  /**
   * The addVertex method adds a given Vertex object to the Graph object if
   * it has not already been added before. Else, the method throws a
   * keyAlreadyExistsException.
   * @param v The Vertex object to add to the Graph
   */
  public void addVertex(Vertex v){
    /*
     * If the Vertex object parameter variable has not been added to the Graph
     * yet, put the Vertex object into the HashMap, create its own
     * LinkedList associated value, and increment the size class variable.
     */
    if (!(this.adjacencyList.containsKey(v))){
      this.adjacencyList.put(v, new LinkedList<Vertex>());
      this.size++;

    } else {
      /*
       * Else, this Vertex object has already been added, throw the
       * KeyAlreadyExistsException.
       */
      throw new KeyAlreadyExistsException();
    }

  }

  /**
   * The getVertices method returns an ArrayList of all the Vertex objects
   * in the Graph.
   * @return An ArrayList of all the Vertex objects in the Graph
   */
  public ArrayList<Vertex> getVertices(){
    /*
     * Declare and assign a Set data structure to the HashMap object's ket set
     * via the keySet() HashMap method.
     */
    Set<Vertex> keySet = this.adjacencyList.keySet();

    // Instantiate an ArrayList object to be returned to the client.
    ArrayList<Vertex> vertexList = new ArrayList<Vertex>();

    // Add all elements from the keySet to the ArrayList of Vertex objects.
    vertexList.addAll(keySet);

    return vertexList;
  }

  /**
   * The addEdge method adds an edge connecting two Vertex objects to each
   * other - specifically the two Vertex objects passed as parameter variables
   * into this method.
   * @param x The first Vertex object to add an edge with parameter variable y
   * @param y The second Vertex object to add an edge with parameter variable x
   */
  public void addEdge(Vertex x, Vertex y){
     // If the HaspMap contains both the x and y parameter variables as keys.
    if (this.adjacencyList.containsKey(x) &&
            this.adjacencyList.containsKey(y)) {
      /*
       * Get the adjacency lists of both the x and y keys and add y to x's
       * adjacency list and add x to y's adjacency list.
       */
      LinkedList<Vertex> TempList1 = this.adjacencyList.get(x);
      TempList1.add(y);
      LinkedList<Vertex> TempList2 = this.adjacencyList.get(y);
      TempList2.add(x);

    } else {
      /*
       * Else the x and y keys have not been added to the HashMap yet so a
       * NoSuchElementException is thrown.
       */
      throw new NoSuchElementException();
    }

  }

  /**
   * The removeVertex method removes a Vertex object, the Vertex passed as this
   * method's parameter variable, from the Graph and from each adjacency list
   * it is on.
   * @param x the Vertex object to remove from the Graph object
   */
  public void removeVertex(Vertex x){
    // Get a set of all the keys (Vertex objects) in the HaspMap.
    Set<Vertex> vertexSet = this.adjacencyList.keySet();

    // For each loop to traverse the set of keys.
    for (Vertex v: vertexSet){
      /*
       * Retrieve the adjacencyList value for each key in the HashMap and
       * remove the x parameter variable from each list.
       */
      LinkedList<Vertex> vertexList = this.adjacencyList.get(v);
      vertexList.remove(x);
    }
    /*
     * Remove the x parameter variable from the HashMap itself and decrement
     * the size class variable.
     */
    this.adjacencyList.remove(x);
    this.size--;
  }

  /**
   * The removeEdge method removes the edge that connects one Vertex object to
   * another Vertex object.
   * @param x The first Vertex object to remove the edge from
   * @param y The second Vertex object to remove the edge from
   */
  public void removeEdge(Vertex x, Vertex y){
    // If both the x and y parameter variables are keys in the HashMap
    if (this.adjacencyList.containsKey(x) &&
            this.adjacencyList.containsKey(y)){
      /*
       * Retrieve the x adjacency list, the value in the HashMap, and remove
       * y from the adjacency list. Then perform the same operations vice versa
       * on the other Vertex object.
       */
      LinkedList<Vertex> xAdjList = this.adjacencyList.get(x);
      xAdjList.remove(y);
      LinkedList<Vertex> yAdjList = this.adjacencyList.get(y);
      yAdjList.remove(x);

    } else {
      // Else, throw a NoSuchElementException.
      throw new NoSuchElementException();
    }

  }

  /**
   * The getAdjacencies method returns an adjacency list of the Vertex object
   * passed into this method as a parameter variable.
   * @param x The Vertex object to get the adjacency list of
   * @return A linkedList of the Vertex object's adjacencies
   */
  public LinkedList<Vertex> getAdjacencies(Vertex x){
    // If the HashMap data structure contains the parameter variable as a key.
    if (this.adjacencyList.containsKey(x)) {

      // Return the key's associated value adjacency list to the client.
      return this.adjacencyList.get(x);

    } else {
      // Else, a NoSuchElementException is thrown.
      throw new NoSuchElementException();
    }

  }

  /**
   * The isConnected method takes a graph as input and determines if
   * all Vertex objects are connected to the Graph object. Meaning,
   * @param graph The Graph object to test if it is completely connected
   * @return True if all Vertex objects are connected within the Graph
   */
  public boolean isConnected(){
    ArrayList<Vertex> vertexList = this.getVertices();
    // If the size of the arrayList is less than 2 then return true.
    if (vertexList.size() < 2){
      return true;
    }

    // Retrieve the first element of the vertexList ArrayList.
    Vertex vertex = vertexList.get(0);
    // Call countVertices method and store return value.
    int count = countVertices(vertex);
    // Reset all Vertex object's visited filed to false.
    setVerticesToNotVisited();

    return count == this.size;
  }

  /**
   * The countVertices helper method takes a Vertex object as a parameter and
   * counts the number of Vertex objects it has an adjacency with.
   * @param vertex the Vertex object to count the adjacencies of
   * @return the integer of the number of adjacencies, including the Vertex
   * object parameter if it has not been visited
   */
  private int countVertices(Vertex vertex){
    // Declare and assign the count variable to zero.
    int count = 0;
    if (!(vertex.isVisited())){   // If the vertex has not been visited.

       // Set the vertex visited variable to true, increment count variable.
      vertex.setVisited(true);
      count = 1;

      // For each Vertex object in the vertex variable adjacency list.
      for (Vertex connections: this.getAdjacencies(vertex)){
        /*
         * Increment the count local variable by the value returned from the
         * recursive call to the countVertices method when passing the
         * connections variable as the parameter.
         */
        count += countVertices(connections);
      }

    }

    return count;
  }

  /**
   * The notConnected method returns a String representation of all the Vertex
   * objects within the Graph object that are not connected to the other
   * Vertex objects in the Graph data structure.
   * @param vertex the Vertex to test from if it is connected to other Vertex
   * objects in the Graph
   * @return A String representation containing the names of all the junctions
   *
   */
  public String notConnected(Vertex vertex){

    visitAdjacencies(vertex);
    /*
     * Declare a String ArrayList and retrieve a list of Vertex objects from
     * the Graph data structure.
     */
    ArrayList<String> nameList = new ArrayList<String>();
    ArrayList<Vertex> vertexList = this.getVertices();

    // For each loop to iterate through the vertexList object.
    for (Vertex vertice: vertexList){
      if (!(vertice.isVisited())){         // If not visited.
        nameList.add(vertice.getName());   // Add Vertex object's name to list.
      }

    }

    setVerticesToNotVisited();    // Reset all Vertex object's visited filed to false.
    Collections.sort(nameList);   // Sort the nameList variable alphabetically.
    String result = "";           // An empty String variable to append to.

    // For each loop to iterate over each element in the nameList.
    for (String name: nameList){
      // Each name is then appended to the result String variable.
      result += " " + name;
    }

    return result;
  }

  /**
   * The visitAdjacencies method visits the adjacent Vertex objects of the
   * Vertex object passed into this method as a parameter variable and
   * recursively visits all the  adjacent Vertex objects of the parameter
   * Vertex object adjacent Vertexes if they have not been visited before.
   * @param vertex The Vertex object of which the adjacent Vertex objects
   * should be visited
   */
  private void visitAdjacencies(Vertex vertex){
    if (!(vertex.isVisited())) {  // If the Vertex object has not been visited.
      // Set the visited Vertex object's class variable to true.
      vertex.setVisited(true);
      /*
       * For each adjacent Vertex object within the parameter Vertex object's
       * adjacency list, recursively call the visitAdjacancies method.
       */
      for (Vertex connections : this.getAdjacencies(vertex)) {
        visitAdjacencies(connections);
      }
    }

  }
  
   /**
   * The numConnectedComponents determines how many distinct connected
   * components there are in the Graph. If all Vertex objects are connected
   * so that all Vertex objects can be visited from any one Vertex, then this
   * method would return 1.
   * @return The number of distinct connected components within the Graph
   */
  public int numConnectedComponents(){
    int numOfComponents = 0;
    ArrayList<Vertex> vertexList = this.getVertices();
    for (Vertex v: vertexList){
      if (!v.isVisited()){
        numOfComponents++;
        depthFirstSearch(v);
      }
    }
    // Reset all Vertex object's visited filed to false.
    setVerticesToNotVisited();

    return numOfComponents;
  }

  /**
   * Method to perform a Depth First Search traversal of the Graph.
   * @param v The Vertex to begin the Depth First Search from
   */
  private void depthFirstSearch(Vertex v){
    if (!v.isVisited()){
      v.setVisited(true);
      for (Vertex x: this.getAdjacencies(v)){
        depthFirstSearch(x);
      }
    }

  }

  /**
   * The getComponents method returns a string representation of all of the
   * distinct components presented in a list format.
   * @return A String representation of each of the components within the Graph
   */
  public String getComponents(){
    int number = this.numConnectedComponents();
    ArrayList<String> componentList = new ArrayList<String>(number);
    for (Vertex v: this.getVertices()){
      if (!v.isVisited()){
        /*
         * Adding the String returned from the nameDepthSearch method to the
         *  componentList when passing Vertex variable v as the parameter.
         */
        componentList.add(nameDepthSearch(v));
      }

    }
    String result = "";         // Instantiating an empty String to append to.
    for (int i = 0; i < componentList.size(); i++) {
      result += (i + 1) + ": " + componentList.get(i) + "\n";
    }

    // Reset all Vertex object's visited filed to false.
    setVerticesToNotVisited();

    return result;
  }

  /**
   * The nameDepthSearch method returns a String representation of a depth first
   * search performed on the parameter vertex.
   * @param x The Vertex object to perform the search on
   * @return A String representation of the depth first search on the parameter
   * variable
   */
  private String nameDepthSearch(Vertex x){
    String s = "";
    if(!x.isVisited()){
      x.setVisited(true);
      s += x.getName() + " ";
      for (Vertex v: this.getAdjacencies(x)){
        s += nameDepthSearch(v);
      }
    }

    return s;
  }
  
  /**
   * The setVerticesToNotVisited sets the visited class variable of all Vertex
   * objects within the Graph to false.
   */
  private void setVerticesToNotVisited(){
    for (Vertex v: this.getVertices()){
      v.setVisited(false);
    }
    
  }


}
