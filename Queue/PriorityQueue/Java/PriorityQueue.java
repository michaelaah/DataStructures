/**
 * This class implements a priority queue via an ArrayList. Note: 1) this 
 * priority queue class supports queue element priority updates. More 
 * specifically, if an object is added to the queue that is already enqueued, 
 * the priority will be updated to the new priority from the most recent 
 * addition. 2) This priority queue class uses objects from the nested 
 * Element class.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PriorityQueue {

  // Class Variables
  private ArrayList<Element> priorityQueue;

  // Constructor
  public PriorityQueue() {
    this.priorityQueue = new ArrayList<Element>();
  }

  public int size(){
    return this.priorityQueue.size();
  }

  public void add(Object obj, int priority){
    if (contains(obj)){
      int index = getIndex(obj);
      Element elem = this.priorityQueue.get(index);
      elem.priority = priority;
    } else {
      Element element = new Element(obj,priority);
      this.priorityQueue.add(element);
    }
  }

  public Object remove(){
    Element elem = findPriority();
    this.priorityQueue.remove(elem);
    return elem.obj;
  }

  public Object peek(){
    if (this.priorityQueue.isEmpty()){
      return null;
    } else {
      Element elem = findPriority();
      return elem.obj;
    }
  }

  private Element findPriority(){
    Element elem = null;
    int low = Integer.MAX_VALUE;
    for (int i = 0; i < this.priorityQueue.size(); i++) {
      if (this.priorityQueue.get(i).priority < low){
        low = this.priorityQueue.get(i).priority;
        elem = this.priorityQueue.get(i);
      }
    }

    return elem;
  }

  public boolean contains(Object object){
    for (int i = 0; i < this.priorityQueue.size(); i++) {
      Object elem = this.priorityQueue.get(i).obj;
      if (elem.equals(object)){
        return true;
      }
    }

    return false;
  }

  public void clear(){
    this.priorityQueue.clear();
  }

  private int getIndex(Object obj){
    for (int i = 0; i < this.priorityQueue.size(); i++) {
      if (obj.equals(this.priorityQueue.get(i).obj)){
        return i;
      }
    }
    return -1;
  }

  public String toString(){
    Collections.sort(this.priorityQueue);
    String result = "";
    for (int i = 0; i < this.priorityQueue.size(); i++) {
      if (i == 0) {
        result += "[";
      }
      result += this.priorityQueue.get(i).obj;
      if (i != this.priorityQueue.size() - 1) {
        result += ", ";
      } else {
        result += "]";
      }
    }
    return result;
  }


  // Nested Element Class
  private class Element implements Comparator<Element>, Comparable<Element> {

    private Object obj;
    private int priority;

    private Element(Object obj, int priority){
      this.obj = obj;
      this.priority = priority;
    }

    public int compare(Element e1, Element e2){
      if (e1.priority < e2.priority){
        return -1;
      }
      if (e1.priority > e2.priority){
        return 1;
      }
      return 0;
    }

    public int compareTo(Element elem){
      Integer e = this.priority;
      return e.compareTo(elem.priority);
    }
    
    
  }
  
  
}
