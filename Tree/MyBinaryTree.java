/**
 * This class implements a BinaryTree.
 * Note: this class does not support removal or order specific insertion.
 */
public class MyBinaryTree {

  // BinaryTree class instance variables
  private MyBinaryTree parent;
  private MyBinaryTree left;
  private MyBinaryTree right;
  private Object value;

  /**
   * The BinaryTree no argument constructor instantiates an instance of a
   * BinaryTree object while setting the parent, children, and value fields to
   * null.
   */
  public MyBinaryTree(){
    this.left = null;
    this.right = null;
    this.parent = null;
    this.value = null;
  }

  /**
   * The BinaryTree constructor instantiates an instance of a BinaryTree object
   * while setting the parent and children references to null and setting the
   * value fields to the parameter variable value.
   */
  public MyBinaryTree(Object o){
    this.left = null;
    this.right = null;
    this.parent = null;
    this.value = o;
  }

  /**
   * Sets the BinaryTree's value field to the parameter variable.
   * @param o the value to store in a specific BinaryTree object
   */
  public void setValue(Object o){
    this.value = o;
  }

  /**
   * The getValue method retrieves the value of a given BinaryTree object node.
   * @return the specific BinaryTree object's value field
   */
  public Object getValue(){

    return this.value;
  }

  /**
   * The getLeft method returns the BinaryTree object's left child reference.
   * @return left child reference of a BinaryTree object
   */
  public BinaryTree getLeft() {

    return left;
  }

  /**
   * The getRight method returns the BinaryTree object's right child reference.
   * @return right child reference of a BinaryTree object
   */
  public BinaryTree getRight() {

    return right;
  }

  /**
   * The getParent method returns the BinaryTree object's parent reference.
   * @return parent reference of a BinaryTree object
   */
  public BinaryTree getParent(){

    return this.parent;
  }

  /**
   * The insertLeft method inserts a BinaryTree object as the left child of
   * this BinaryTree object.
   * @param t the BinaryTree object to be the left child
   */
  public void insertLeft(MyBinaryTree t){
    if (this.left == null){
      this.left = t;
      t.parent = this;
    }
  }

  /**
   * The insertRight method inserts a BinaryTree object as the right child of
   * this BinaryTree object.
   * @param t the BinaryTree object to be the right child
   */
  public void insertRight(MyBinaryTree t){
    if (this.right == null){
      this.right = t;
      t.parent = this;
    }
  }

  /**
   * The getSize method counts the number of nodes in a BinaryTree object.
   * @return the integer count fo the number of nodes in a given BinaryTree
   */
  public int getSize() {
    if (this.left != null && this.right == null){
      return 1 + this.left.getSize();
    }
    if (this.left == null && this.right != null){
      return 1 + this.right.getSize();
    }
    if (this.left == null && this.right == null){
      return 1;
    }

    return 1 + this.right.getSize() + this.left.getSize();

  }

  /**
   * The getDepth method returns the depth of a given BinaryTree node within a
   * BinaryTree data structure.
   * @return the integer depth of a given BinaryTree node
   */
  public int getDepth(){
    if (this.parent == null){
      return 0;
    }
    int count = 0;
    BinaryTree temp = this.parent;
    while (temp != null){
      count ++;
      temp = temp.parent;
    }

    return count;
  }

  /**
   * The getHeight method returns the height (or maximum depth) of a BinaryTree
   * object.
   * @return the integer height of a BinaryTree object
   */
  public int getHeight(){
    if (this.left != null && this.right == null){
      return 1 + Math.max(this.left.getHeight(),0);
    }
    if (this.left == null && this.right != null){
      return 1 + Math.max(this.right.getHeight(),0);
    }
    if (this.left != null && this.right != null) {
      return 1 + Math.max(this.left.getHeight(), this.right.getHeight());
    }

    return 0;
  }
  
  /**
   * The clear method assigns null values to all the BinaryTree instance
   * variables.
   */
  public void clear(){
    this.left = null;
    this.right = null;
    this.parent = null;
    this.value = null;
  }

  /**
   * The isEmpty method tests if the value field of a given BinaryTree object
   * is empty or not.
   * @return a boolean value - true if the BinaryTree value field is empty,
   * false otherwise
   */
  public boolean isEmpty(){
    if (this.value == null){
      return true;
    }

    return false;
  }

  /**
   * The isRoot method tests if the given  BinaryTree object is the root or not.
   * @return a boolean value - true if the BinaryTree object is the root,
   * false otherwise
   */
  public boolean isRoot(){
    if (this.parent == null){
      return true;
    }

    return false;
  }
  
  /**
   * The equals method determines if two Binary tree nodes are equal if
   * their value class variables are equal.
   * @param t the BinaryTree object to test for equality..
   * @return True if the value instance variables of the two BinaryTree object's
   * are equal false otherwise
   */
  public boolean equals(BinaryTree t){
    if (this.value.equals(t.value)){
      return true;
    }

    return false;
  }

  /**
   * The toString method returns a string representation of an the BinaryTree
   * object. Note: this method performs Inorder tree traversal.
   * @return a string representation of a BinaryTree object
   */
  public String toString(){
    String s = "";
    if (this.left != null){
      s += this.left.toString();
    }
    if (this.value != null){
      s += this.value.toString() + " ";
    }
    if (this.right != null){
      s += this.right.toString();
    }

    return s;
  }

}
