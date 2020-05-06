// This class implements a queue data structure.

public class MyQueue {

    private MyLinkedList theQueue;

    public MyQueue(){
        this.theQueue = new MyLinkedList();
    }

    public void add(Object obj){
        this.theQueue.addFirst(obj);
    }

    public Object remove(){
        return this.theQueue.removeLast();
    }

    public Object peek(){
        if(this.theQueue.isEmpty()){
            return null;
        }
        return this.theQueue.getTailData();
    }
    
    public boolean contains(Object obj){
        return this.theQueue.contains(obj);
    }

    public String toString(){
        String result = "";
        if(!(this.theQueue.isEmpty())){
            result += this.theQueue.toString();
        }
        return result;
    }
    
    public void clear(){
        this.theQueue.clear();
    }
}
