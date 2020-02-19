// This class implements a BinarySearchTree data structure.
// Note: this tree does not support self balancing.

public class BinarySearchTree {

  // BinarySearchTree class instance variables
  private BinarySearchTree parent;
  private BinarySearchTree left;
  private BinarySearchTree right;
  private int value;

  /**
   * The BinarySearchTree no argument constructor instantiates an instance of a
   * BinarySearchTree object while setting the parent, children, and value
   * fields to null and zero.
   */
  public BinarySearchTree(){
    this.left = null;
    this.right = null;
    this.parent = null;
    this.value = 0;
  }

  /**
   * This BinarySearchTree constructor allows the client to instantiate a
   * BinarySearchTree object while passing a parameter value to the value
   * instance variable.
   * @param e the vale to store in this BinarySearchTree object
   */
  public BinarySearchTree(int e){
    this.left = null;
    this.right = null;
    this.parent = null;
    this.value = e;
  }

  /**
   * The getParent method gets the parent reference of this BinarySearchTree
   * object.
   * @return The parent reference
   */
  public BinarySearchTree getParent() {
    return parent;
  }

  /**
   * The getLeft method gets the left reference of this BinarySearchTree
   * object.
   * @return The left reference
   */
  public BinarySearchTree getLeft() {
    if (left == null){
      throw new NullPointerException();
    }
    return left;
  }

  /**
   * The getRight method gets the right reference of this BinarySearchTree
   * object.
   * @return The right reference
   */
  public BinarySearchTree getRight() {
    if (right == null){
      throw new NullPointerException();
    }
    return right;
  }

  /**
   * The getValue method gets the value instance variable of this
   * BinarySearchTree object.
   * @return The value instance variable
   */
  public int getValue() {
    return value;
  }

  /**
   * The setParent method sets the parent instance variable of this
   * BinarySearchTree object to the parameter reference variable.
   * @param parent The reference to set this BinarySearchTree object's parent
   * reference to
   */
  public void setParent(BinarySearchTree parent) {
    this.parent = parent;
  }

  /**
   * The setLeft method sets the left instance variable of this
   * BinarySearchTree object to the parameter reference variable.
   * @param left The reference to set this BinarySearchTree object's left
   * reference to
   */
  public void setLeft(BinarySearchTree left) {
    this.left = left;
  }

  /**
   * The setRight method sets the right instance variable of this
   * BinarySearchTree object to the parameter reference variable.
   * @param right The reference to set this BinarySearchTree object's right
   * reference to
   */
  public void setRight(BinarySearchTree right) {
    this.right = right;
  }

  /**
   * The setValue method sets the value instance variable of this
   * BinarySearchTree object to the parameter variable.
   * @param value The value to set this BinarySearchTree object's value class
   * variable to
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * The contains method searches the BinarySearchTree for the parameter
   * variable.
   * @param e The value to look for in the tree
   * @return true if the value is in the tree, false otherwise
   */
  public boolean contains(int e){
    BinarySearchTree n = findNode(this, e);
    return n != null;
  }

  /**
   * THe insert method inserts a BinarySearchTree into a tree, if the tree
   * exists, or makes the parameter variable the root of a new BinarySearchTree.
   * @param e the value to be inserted into the BinarySearchTree
   */
  public void insert(int e){
    BinarySearchTree t = new BinarySearchTree();
    t.value = e;
    if (this == null){
      t.parent = this;
    } else {
      insertNode(this, t);
    }
  }

  /**
   * The remove method removes a BinarySearchTree object from the
   * BinarySearchTree and finds an appropriate replacement for that object if
   * the BinarySearchTree object to e removed has one or more child nodes.
   * @param e the value of the element to remove from the BinarySearchTree
   */
  public void remove(int e){
    BinarySearchTree n = new BinarySearchTree();
    n.value = Integer.MIN_VALUE;
    n.right = this;
    this.parent = n;
    BinarySearchTree r = findNode(n,e);
    if (r != null){
      removeNode(r);
    }
    this.parent = n.right;
    this.parent = null;
  }

  /**
   * The getSize method counts the number of nodes in a BinarySearchTree object.
   * @return the integer count fo the number of nodes in a given
   * BinarySearchTree
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
   * The isBalanced method returns true if the BinarySearchTree object has a
   * difference in leaf nodes in each left and right subtree no greater than 1.
   * Otherwise, this method returns false.
   * @param t the BinarySearchTree object to test if it is balanced
   * @return true if the BinarySearchTree object is balanced, false otherwise
   */
  public boolean isBalanced(BinarySearchTree t){
    return isBalancedHelper(t) >= 0;
  }

  /**
   * The isBalancedHelper method is a recursive helper method for the isBalanced
   * method. THis method takes a BinarySearchTree object and returns an int
   * representing the difference between the left and right subtrees.
   * @param t a BinarySearchTree object
   * @return the difference between the left and right subtrees
   */
  protected int isBalancedHelper(BinarySearchTree t){
    if (t == null){
      return 0;
    }
    int lSide = isBalancedHelper(t.left);
    int rSide = isBalancedHelper(t.right);
    int difference = Math.abs(lSide - rSide);
    if (lSide < 0 || rSide < 0 || difference > 1){
      return -1;
    }
    return lSide + rSide + 1;
  }

  /**
   * The getDepth method returns the depth of a given BinarySearchTree node
   * within a BinarySearchTree data structure.
   * @return the integer depth of a given BinarySearchTree node
   */
  public int getDepth(){
    if (this.parent == null){
      return 0;
    }
    int count = 0;
    BinarySearchTree temp = this.parent;
    while (temp != null){
      count ++;
      temp = temp.parent;
    }

    return count;
  }

  /**
   * The getHeight method returns the height (or maximum depth) of a
   * BinarySearchTree object.
   * @return the integer height of a BinarySearchTree object
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
   * The isBST method returns a boolean value to the client, true if the BST
   * is in sorted order, meaning that the left subtree is < the parent and that
   * the right subtree is > the parent, else false is returned.
   * @return true if the data elements are in the tree in sorted order, false
   * otherwise
   */
  public boolean isBST(){
    int low = Integer.MIN_VALUE;
    int high = Integer.MAX_VALUE;

    return isBSTHelper(this, low, high);
  }

  /**
   * The isBSTHelper method implements the logic described in the isBST method
   * but takes a node, a maximum, and a minimum value as parameters.
   * @param T The node to test if it is a BST or not
   * @param low the lowest possible value
   * @param high the highest possible value
   * @returntrue if the data elements are in the tree in sorted order, false
   *    * otherwise
   */
  public boolean isBSTHelper(BinarySearchTree T, int low, int high){
    if (T == null){
      return true;
    }
    if (T.value < low || T.value > high){
      System.out.println("Failed here");
      return false;
    }

      return isBSTHelper(T.left, low, T.value) && isBSTHelper(T.right, T.value, high);
  }

  /**
   * The findNode helper method searches the BinarySearchTree for the value
   * given in the parameter and returns either null or the node that holds
   * the value being searched for.
   * @param T The BinarySearchTree to search in
   * @param target The value to look for
   * @return The node if the value was found or null if not found
   */
  private BinarySearchTree findNode(BinarySearchTree T, int target){
    if (T == null || T.value == target){
      return T;
    }
    else if (T.value > target){
      return findNode(T.left, target);
    }
    else
      return findNode(T.right, target);
  }

  /**
   * The insertNode helper method helps insert a BinarySearchTree object
   * within a tree while maintaining the order of the tree.
   * @param T The BinarySearchTree to insert the node into
   * @param n The BinarySearchTree object to add to an existing tree
   */
  private void insertNode(BinarySearchTree T, BinarySearchTree n){
    if (T.value >= n.value){
      if (T.left == null){
        T.left = n;
        n.parent = T;

      } else insertNode(T.left,n);

    } else {
      if (T.right == null){
        T.right = n;
        n.parent = T;
      } else insertNode(T.right,n);

    }

  }

  private void removeNode(BinarySearchTree r){
    BinarySearchTree n = new BinarySearchTree();
    if (r.left == null){
      n = r.right;

    } else if (r.right == null){
        n = r.left;
      } else {
      n = findReplace(r);
      n.left = r.right;
      n.right = r.right;
    }

    if (r.parent.left == r){
      r.parent.left = n;
    } else {
      r.parent.right = n;
    }

    if (n != null){
      n.parent = r.parent;
    }

  }

  private BinarySearchTree findReplace(BinarySearchTree n){
    n = n.right;
    while (n.left != null) {
      n = n.left;
    }
    n.parent.left = n.right;
    if (n.right != null){
      n.right.parent = n.parent;
    }
    return n;
  }

  /**
   * The toString method returns a string representation of an the
   * BinarySearchTree object. Note: this method performs Inorder tree traversal.
   * @return a string representation of a BinarySearchTree object
   */
  public String toString(){
    String s = "";
    if (this.left != null){
      s += this.left.toString();
    }
    if (this.value != 0){
      s += this.value + " ";
    }
    if (this.right != null){
      s += this.right.toString();
    }

    return s;
  }

}
