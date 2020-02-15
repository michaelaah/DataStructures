// This class implements a stack data structure.

public class MyStack {

    private MyLinkedList theStack;

    public MyStack(){
        this.theStack = new MyLinkedList();
    }

    public void push(Object obj){
        this.theStack.addFirst(obj);
    }

    public Object pop(){
       return this.theStack.removeFirst();
    }

    public Object peek(){
        return this.theStack.getHeadData();
    }

    public boolean isEmpty(){
        return this.theStack.isEmpty();
    }

    public String toString(){
        String result = "";
        if(!(this.theStack.isEmpty())){
          result += this.theStack.toString();
        }
        return result;
    }
    
    public void clear(){
        this.theStack.clear();
    }

}
