// This class implements a doubly linked list data structure.

public class MyLinkedList {

    private Node head;
    private Node tail;

    public MyLinkedList(){
        head = null;
        tail = null;
    }

    public Object getHeadData(){
        return this.head.data;
    }

    public Object getTailData(){
        return this.tail.data;
    }

    public boolean isEmpty(){
        return (head == null && tail == null);
    }

    public boolean addFirst(Object data){
        if(head != null) {
            Node newNode = new Node(data);
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
            return true;
        }
        if(head == null){
            Node newNode = new Node(data);
            head = newNode;
            if (tail == null){
                tail = newNode;
            }
            return true;
        }
        return false;
    }

    public boolean addLast(Object data){
        if(tail == null){
            Node newNode = new Node(data);
            tail = newNode;
            if(head == null){
                head = newNode;
            }
            return true;
        }
        if(tail.getNext() == null){
            Node newNode = new Node(data);
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = tail.getNext();
            return true;
        }
        return false;
    }

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

    // Nested Node class
    private class Node{

        private Object data;
        private Node next;
        private Node previous;

        public Node(){
            data = null;
            next = null;
            previous = null;
        }

        public Node(Object data){
            this.data = data;
            next = null;
            previous = null;
        }

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
