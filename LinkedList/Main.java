// This program is written to test the MyLinkedKist class.

public class Main {

    public static void main(String[] args) {

// This section of code is written to instantiate the MyLinkedList class object and create testing variables.
        MyLinkedList theList = new MyLinkedList();
        int first = 5;
        int second = 6;
        int third = 7;

        // Add first method testing
        System.out.println("Add First method Testing");
        theList.addFirst(first);
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        theList.addFirst(second);
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        theList.addFirst(third);
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        // Remove first method testing
        System.out.println("Remove First method Testing");
        theList.removeFirst();
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        theList.removeFirst();
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        // To completely empty the MyLinkedList object, the below method is called once more.
        theList.removeFirst();                  

        // Add last method testing
        System.out.println("Add Last method Testing");
        theList.addLast(first);
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        theList.addLast(second);
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        theList.addLast(third);
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        // Remove last method testing
        System.out.println("Remove Last method Testing");
        theList.removeLast();
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());

        theList.removeLast();
        System.out.println(theList);
        System.out.println("Head: " + theList.getHeadData());
        System.out.println("Tail: " + theList.getTailData());
        */
    }
}
