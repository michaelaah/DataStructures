// This class implements a doubly linked list data structure.

public class MyLinkedList {

    // MyLinkedList class instance variables
    private Node head;
    private Node tail;

    /**
     * No argument MyLinkedList constructor, sets the head and tail references
     * to null.
     */
    public MyLinkedList(){
        head = null;
        tail = null;
    }

    /**
     * The getHeadData returns the data stored in the head node.
     * @return the data stored in the head reference instance variable
     */
    public Object getHeadData(){
        return this.head.data;
    }

    /**
     * The getTailData returns the data stored in the tail node.
     * @return the data stored in the tail reference instance variable
     */
    public Object getTailData(){
        return this.tail.data;
    }

    /**
     * The isEmpty method returns true if both the head and tail references 
     * are null, other wise it returns false
     * @return true if class instance variables are null, otherwise false
     */
    public boolean isEmpty(){
        return (head == null && tail == null);
    }

     /**
     * The addFirst method adds a new node to the front of the LinskedList
     * object.
     * @param data The value of the new node to be stored in the LinkedList
     * object.
     * @return True if the node was successfully added, false otherwise
     */
    public boolean addFirst(Object data){
        Node newNode = new Node(data);
        if(head != null) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
            return true;
        }
        if(head == null){
            head = newNode;
            if (tail == null){
                tail = newNode;
            }
            return true;
        }
        return false;
    }

  /**
     * The addLast method adds an new node with a given parameter at the end
     * of the LinkedList object.
     * @param data The value to be added to the end of the LinkedList object
     * @return True if the value was added successfully, otherwise false
     */
    public boolean addLast(Object data){
        Node newNode = new Node(data);
        if(tail == null){
            tail = newNode;
            if(head == null){
                head = newNode;
            }
            return true;
        }
        if(tail.getNext() == null){
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = tail.getNext();
            return true;
        }
        return false;
    }

    /**
     * The removeFirst method removes a node from the front of the LinkedList
     * object and returns that node to the client.
     * @return The node at the front of the Linkedlist object
     */
    public Node removeFirst(){
        if(head == null){
            return null;
        }
        if(head == tail){
            Node temp = head;
            head = null;
            tail = null;
            return temp;
        }
        Node temp = head;
        head = head.next;
        head.previous = null;
        return temp;
    }

     /**
     * The removeLast method removes a node from the back of the LinkedList
     * object and returns that node to the client.
     * @return The node at the back of the Linkedlist object
     */
    public Node removeLast(){
        if(tail == null){
            return null;
        }
        if(tail == head){
            Node temp = tail;
            head = null;
            tail = null;
            return temp;
        }
        Node temp = tail;
        tail = tail.previous;
        temp.previous = null;
        tail.next = null;
        return temp;
    }

    /**
     * The toString method provides a String representation of the LinkedList
     * object.
     * @return A String representation of the LinkedList object
     */
    public String toString() {
        String result = "";
        if (head != null) {
            result += "[ " + head.data;
            Node temp = head.next;
            while (temp != null) {
                result += ", " + temp.data;
                temp = temp.next;
            }
            result += " ]";
        }
        return result;
    }
    
    /**
     * The clear method sets the head and tail references of the LinkedList
     * object to null and therefore, erasing clearing the entire list.
     */
    public void clear(){
        this.head = null;
        this.tail = null;
    }

    // Nested Node class
    private class Node{
        
        // Node Class Variables
        private Object data;
        private Node next;
        private Node previous;
        
        // No argument Constructor
        public Node(){
            data = null;
            next = null;
            previous = null;
        }

        // Constructor with data class variable
        public Node(Object data){
            this.data = data;
            next = null;
            previous = null;
        }

        // Class Setter and Getter methods
        public void setData(Object data){
            this.data = data;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public void setPrevious(Node previous){
            this.previous = previous;
        }
        
        public Object getData(){
            return this.data;
        }

        public Node getNext(){
            return this.next;
        }

        public Node getPrevious(){
            return this.previous;
        }

    }
}
