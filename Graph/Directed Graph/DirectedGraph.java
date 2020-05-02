/**
 * The DirectedGraph class implements the behavior of a directed graph.
 * Note: This DirectedGraph class uses Vertex and Edge objects.
 */
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class DirectedGraph {

  // Class Variables
  private HashMap<Vertex, LinkedList<Edge>> diGraph;
  private int size;

  /**
   * The DirectedGraph constructor takes no arguments and initializes each
   * class variable.
   */
  public DirectedGraph(){
    this.diGraph = new HashMap<Vertex, LinkedList<Edge>>();
    this.size = 0;
  }

  /**
   * The getSize method tracks the size of the DirectedGraph object.
   * @return an int representing the size (number of vertices) in this object
   */
  public int getSize() {
    return size;
  }

  /**
   * The add method adds a Vertex object to the DirectedGraph object.
   * @param v The Vertex object to add to the DirectedGraph
   */
  public void add(Vertex v){
    if(!(this.diGraph.containsKey(v))){
      this.diGraph.put(v, new LinkedList<Edge>());
      this.size++;
    } else {
      
      throw new KeyAlreadyExistsException();
    }

  }

  /**
   * The remove method remove a specific Vertex object from the DirectedGraph
   * object.
   * @param v The Vertex object to remove
   */
  public void remove(Vertex v){
    if (this.diGraph.containsKey(v)) {
      Set<Vertex> vertexList = this.diGraph.keySet();
      for (Vertex x : vertexList) {
        LinkedList<Edge> edgeList = this.diGraph.get(x);
        for (Edge e : edgeList) {
          Vertex elem = e.getVertex();
          if (elem.equals(v)) {
            edgeList.remove(e);
          }
        }
      }
      this.diGraph.remove(v);
      this.size--;
    } else {

      throw new NoSuchElementException();
    }
  }

  /**
   * The getVertices method returns a list of all Vertex objects within the
   * DirectedGraph object.
   * @return An ArrayList of all Vertex objects in this DirectedGraph
   */
  public ArrayList<Vertex> getVertices(){
    Set<Vertex> keySet = this.diGraph.keySet();
    ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
    vertexList.addAll(keySet);

    return vertexList;
  }

  /**
   * The getEdges method returns a list of all Vertex objects that are adjacent
   * to the Vertex parameter object.
   * @param v The Vertex object to get a list of adjacencies of
   * @return An ArrayList of Vertex objects adjacent to the parameter Vertex
   */
  public ArrayList<Vertex> getEdges(Vertex v){
    ArrayList<Vertex> adjList = new ArrayList<Vertex>();
    LinkedList<Edge> edgeList = this.diGraph.get(v);

    for (Edge edge: edgeList){
      adjList.add(edge.getVertex());
    }

    return adjList;
  }

  /**
   * The getAdjacent edges method returns a list of all Edge objects that are
   * adjacent to the Vertex parameter object.
   * @param v The Vertex object to get a list of adjacencies of
   * @return An ArrayList of Edge objects adjacent to the parameter Vertex
   */
  private ArrayList<Edge> getAdjacentEdges(Vertex v){
    LinkedList<Edge> eList = this.diGraph.get(v);
    ArrayList<Edge> edgeList = new ArrayList<Edge>();
    edgeList.addAll(eList);

    return edgeList;
  }

  /**
   * The addEdge method adds an edge between two Vertex objects in this
   * DirectedGraph object.
   * @param source The Vertex where the edge will com from
   * @param destination The Vertex object where the edge will go to
   * @param cost The cost or weight of this edge between the two Vertexes
   */
  public void addEdge(Vertex source, Vertex destination, int cost){
    if (!(this.diGraph.containsKey(source)) ||
            !(this.diGraph.containsKey(destination))){
      throw new NoSuchElementException();
    } else {
      LinkedList<Edge> edgeList = this.diGraph.get(source);
      edgeList.add(new Edge(destination,cost));
      if(!(this.diGraph.containsKey(destination))){
        this.add(destination);
      }
    }

  }

  /**
   * The removeEdge method removes an edge between two Vertex objects.
   * @param source The Vertex object where the edge comes out from
   * @param destination The Vertex object where the edge goes to
   */
  public void removeEdge(Vertex source, Vertex destination){
    LinkedList<Edge> edgeList = this.diGraph.get(source);
    for (Edge e: edgeList){
      if (e.equals(destination)){
        edgeList.remove(e);
      }
    }

  }

  /**
   * The isStronglyConnected method determines if the DirectedGraph is strongly
   * connected or not.
   * @return True if the DirectedGraph object is strongly connected
   */
  public boolean isStronglyConnected(){
    if (this.size < 2){
      return true;
    }
    for (Vertex x: this.getVertices()){
      int count = countVertices(x);
      System.out.println("Count " + count + " on " + x.getName());
      if (count == this.size){
        resetVisitedField();

        return true;
      }
      resetVisitedField();
    }

    return false;
  }

  /**
   * The countVertices method counts recursively traverses and counts the
   * Vertex objects in the DirectedGraph object.
   * @param v The Vertex object to begin the traversal from
   * @return The integer count of the number of Vertex objects visited
   */
  private int countVertices( Vertex v){
    int count = 0;
    if(!(v.isVisited())){
      v.setVisited(true);
      count = 1;
      for (Edge c: this.diGraph.get(v)){
        count += countVertices(c.getVertex());
      }
    }

    return count;
  }

  /**
   * The sourceVertex method returns the Vertex object from the graph where
   * each other Vertex object can be traversed to.
   * @return The source Vertex, or null if no such Vertex object exists
   */
  public Vertex sourceVertex(){
    for (Vertex x: this.getVertices()){
      int count = countVertices(x);
      if (count == this.size){
        resetVisitedField();

        return x;
      }
      resetVisitedField();
    }

    return null;
  }

  /**
   * This method returns true if there is a cycle in the DirectedGraph object.
   * @return True if a cycle exists
   */
  public boolean hasCycle(){
    for (Vertex vertex: this.getVertices()) {
      resetVisitedField();
      if (hasCycleDFS(vertex, vertex)) {

        return true;
      }
    }

    return false;
  }

  /**
   * The private hasCycleDFS helper method recursively searches the DirectedGraph
   * object for a cycle and returns true of one is found.
   * @param source The Vertex to begin the search from
   * @param destination The target Vertex if there is a cycle
   * @return True if a cycle exists
   */
  private boolean hasCycleDFS(Vertex source, Vertex destination){
    if (source.isVisited()){
      return false;
    }
    source.setVisited(true);

    for (Edge adjacent: this.diGraph.get(source)){
      if (adjacent.equals(destination)){
        return true;
      }
      if (hasCycleDFS(adjacent.getVertex(),destination)){
        return true;
      }
    }
    resetVisitedField();

    return false;
  }

  /**
   * This method creates a String representation of the DirectedGraph object.
   * @return a String representation of the DirectedGraph object
   */
  public String toString(){
    String result = "";
    Set<Vertex> vertexList = this.diGraph.keySet();
    int iter = vertexList.size() - 1;
    for (Vertex v: vertexList){
      result += "Vertex " + v.getName();
      LinkedList<Edge> edgeList = this.diGraph.get(v);

      if (edgeList.size() > 0){
        int i = edgeList.size() - 1;
        result += " - Edges:";
        for (Edge e: edgeList){
          result += " " + e;
          if (i > 0){
            result += ",";
          }
          i--;
        }
      }
      if (iter > 0){
        result += "\n";
      }
      iter--;
    }

    return result;
  }

  /**
   * The resetVisitedField method sets the visited class variable of all Vertex
   * objects within the DirectedGraph to false.
   */
  private void resetVisitedField(){
    for (Vertex x: this.diGraph.keySet()){
      x.setVisited(false);
    }
  }

  /**
   * The clear method empties the entire DirectedGraph object and sets the
   * size field variable to zero.
   */
  public void clear(){
    this.diGraph.clear();
    this.size = 0;
  }

  /**
   * Dijkstra's algorithm is used to find the shorted path between two Vertex
   * objects in this DirectedGraph object.
   * @param source The Vertex object to begin the traversal from
   * @param destination The Vertex object to end the traversal at
   * @return An ArrayList of the Vertex objects in the shortest path from
   * the source Vertex to the destination Vertex
   */
  public ArrayList<Vertex> dijkstra(Vertex source, Vertex destination){
    if (!(this.diGraph.containsKey(source)) || !this.diGraph.containsKey(destination)){
      throw new NoSuchElementException();
    } else {

      LinkedList<Vertex> list = new LinkedList<Vertex>();
      ArrayList<Vertex> pathList = new ArrayList<Vertex>();
      buildTree(source);

      while (destination != null) {
        list.addFirst(destination);
        destination = destination.getParent();
      }
      pathList.addAll(list);
      resetParentLinks();
      resetVisitedField();

      return pathList;
    }
  }

  /**
   * The buildTree helper method contains the algorithm associated with Dijkstra's
   * algorithm. This method sets the parent class variable for each Vertex object
   * found to be in the shortest path from the source Vertex.
   * @param source The Vertex to build the path to
   */
  private void buildTree(Vertex source) {
    for (Vertex vertex : this.getVertices()) {
      vertex.setDistance(Integer.MAX_VALUE);
    }

    PriorityQueue<Vertex> priorityQ = new
            PriorityQueue<Vertex>(Comparator.comparing(Vertex::getDistance));
    source.setDistance(0);
    priorityQ.add(source);

    while (priorityQ.size() > 0){
      Vertex node = priorityQ.poll();
      if(node.isVisited()){
        continue;
      }
      node.setVisited(true);
      
      for (Edge adjacent: this.diGraph.get(node)){
        Vertex elem = adjacent.getVertex();
        int dist = node.getDistance() + this.getWeight(node,elem);
        if (elem.getDistance() > dist){
          elem.setDistance(dist);
          elem.setParent(node);
          priorityQ.add(elem);
        }
      }

    }

  }

  /**
   * The private getWeight method returns the cost of of the edge between
   * two Vertex objects.
   * @param source The Vertex object to traveling from
   * @param destination The Vertex object the source is connected to
   * @return the cost of the edge between two Vertex objects, else the minimum
   * integer value is returned
   */
  private int getWeight(Vertex source, Vertex destination){
    ArrayList<Edge> edgeList = this.getAdjacentEdges(source);
    for (Edge edge: edgeList){
      if (destination.equals(edge.getVertex())){

        return edge.getCost();
      }
    }

    return Integer.MIN_VALUE;
  }
  
  /**
   * The resetParentLinks method assigns each Vertex object's, within the DirectedGraph
   * object, parent class variable to null.
   */
  private void resetParentLinks(){
    for (Vertex k: this.getVertices()){
      k.setParent(null);
    }
  }
  

}
