// This program is written to test the MyQueue class - implemented via the MyLinkedList class.

public class Main {

    public static void main(String[] args) {

        // This section of code is written to instantiate the MyQueue class object and create testing variables.
        MyQueue theQueue = new MyQueue();
        int first = 5;
        int second = 6;
        int third = 7;

        // Add method testing
        theQueue.add(first);
        System.out.println(theQueue);
        theQueue.add(second);
        System.out.println(theQueue);
        theQueue.add(third);
        System.out.println(theQueue);

        // Peek and Remove method testing
        System.out.println(theQueue.peek());
        theQueue.remove();
        System.out.println(theQueue);

        System.out.println(theQueue.peek());
        theQueue.remove();
        System.out.println(theQueue);

        System.out.println(theQueue.peek());
   }
}
