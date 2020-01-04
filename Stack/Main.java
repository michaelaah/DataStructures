// This program is written to test the MyStack class - implemented via the MyLinkedList class.

public class Main {

    public static void main(String[] args) {

// This section of code is written to instantiate the MyStack class object and create testing variables.
        MyStack stk = new MyStack();
        int first = 5;
        int second = 6;
        int third = 7;

        // Push method testing
        stk.push(first);
        System.out.println(stk);
        stk.push(second);
        System.out.println(stk);
        stk.push(third);
        System.out.println(stk);

        // Peek and Pop method testing
        System.out.println(stk.peek());
        stk.pop();
        System.out.println(stk);
        System.out.println(stk.peek());
        stk.pop();
        System.out.println(stk);
        
   }
}
