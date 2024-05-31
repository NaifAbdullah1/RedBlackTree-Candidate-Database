package rbt;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order traversal of the
 * tree.
 * 
 * @author Naif Abdullah
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always maintained.
   */
  protected static class Node<T> {

    public T data; // Data type T is a generic data type, takes any data type
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public boolean isBlack; // true = node is black - false = node is red.

    public Node(T data) {
      this.data = data;
      isBlack = false; // So that every newly created node is red by default
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     * return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level order traversal. The
     * toString of the RedBlackTree class below produces an inorder traversal of the nodes / values
     * of the tree. This method will be helpful as a helper for the debugging and testing of your
     * rotation implementation.
     *
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() {
      String output = "[";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null) {
          q.add(next.leftChild);
        }
        if (next.rightChild != null) {
          q.add(next.rightChild);
        }
        output += next.data.toString();
        if (!q.isEmpty()) {
          output += ", ";
        }
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   *
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references
   */
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null) {
      throw new NullPointerException(
          "This RedBlackTree cannot store null references.");
    }

    Node<T> newNode = new Node<>(data);
    if (root == null) { // if root is null, then the tree is empty, then we'll just set the root
      root = newNode;
      size++;
      root.isBlack = true; // Since we're inserting the root, we should make it black.
      return true;
    } 
    else {
      boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
      if (returnValue) {
        size++;
      } else {
        throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");
      }
      /*A valid red-black tree must always have a black root, thus we have this
      statement so that this rule is never broken.*/
      root.isBlack = true;
      return returnValue;
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   *
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   * as a descenedent beneath
   * @return true is the value was inserted in subtree, false if not
   */
  private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0) {
      return false;
    } // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode); // Checking for violations
        return true;
        // otherwise continue recursive search for location to insert
      } else {
        return insertHelper(newNode, subtree.leftChild);
      }
    } // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode); // Checking for violations
        return true;
        // otherwise continue recursive search for location to insert
      } else {
        return insertHelper(newNode, subtree.rightChild);
      }
    }
  }

  /**
   * This method resolves any red property violations that may occur from inserting a new node into
   * a red-black tree. It also preserved all other red-black tree properties.
   *
   * @param newNode the node that was newly created and inserted into the tree (before the
   * enforcement of the properties)
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    /*If the parent node of the newly inserted node (newNode) is black, then
      no red-on-red violations were made. Therefore we can exit the method */
    if (newNode.parent != null && newNode.parent.isBlack) {
      return;
    }

    /* 
      Note the following: 
      -In the following cases description note that a newly inserted node is 
      red by default. And in all the cases, the newly inserted node AND the 
      parent are red. (i.e. all of the 3 cases are red-on-red violations
      
      -"Uncle" = Parent's sibling
      
      -When we say "parent" or "grandparent". We're talking in the 
      perspective of the newly inserted node */
    
    /* To shorten the code, it's possible that saving the parent's and grandparent's references
      in a variable of type Node<T> */
    
    /*Notice the conditional statement. By DeMorgan's law, the conditional 
      statement is equal to !(newNode.parent == null || newNode.parent.isBlack) */
    if (newNode.parent != null && !newNode.parent.isBlack) { // checking for red on red violation
      // Once we confirm we have a red-on-red violation. Then we can look at the 3 cases:
    
      /*
      Note that in the if statement of the first and second case, we shortened the if-statement 
      by replacing [(newNode.parent.leftChild != null && newNode.parent.leftChild.equals(newNode)]
      with [newNode.isLeftChild()]
      We also replaced [(newNode.parent.rightChild != null && newNode.parent.rightChild.equals(newNode)]
      with [!newNode.isLeftChild()]
      */
      
      /* Case 1: newNode's uncle is black and newNode is on the opposite side (the farther 
      side away from the uncle). Then we'll rotate the parent and grandparent. 
      Then color swap the parent and grandparent*/
      if ((newNode.isLeftChild() 
          && (newNode.parent.parent.rightChild == null || newNode.parent.parent.rightChild.isBlack)) 
          || (!newNode.isLeftChild() //i.e. is it the right child? If it's not the left, then it's the right
          && (newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.isBlack))) {
        rotate(newNode.parent, newNode.parent.parent);

        // Swapping parent's color
        /* We have to check if the parent/grandparent is null or not first. 
          This is to prevent NullPointerException from being thrown*/
        if (newNode.parent != null) {
          newNode.parent.isBlack = !newNode.parent.isBlack;
        }

        // Swapping grandparent's color
        if (newNode.parent.rightChild.isBlack) {
          newNode.parent.rightChild.isBlack = !newNode.parent.rightChild.isBlack;
        } else if (newNode.parent.leftChild.isBlack) {
          newNode.parent.leftChild.isBlack = !newNode.parent.leftChild.isBlack;
        }
        // We need to make sure that the root doesn't have a parent.
        root.parent = null;
        // Once we're done, we will exit the method so that other cases are not triggered.
        return;
      }

      /*Case 2: newNode's uncle is black and newNode is on the same side (the side closer to the 
    uncle). Then rotate the red nodes, the ones that are involved in the red-on-red violations, 
    then apply case 1
       */
      if ((newNode.isLeftChild()
          && (newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.isBlack))
          || (!newNode.isLeftChild()
          && (newNode.parent.parent.rightChild == null || newNode.parent.parent.rightChild.isBlack))) {
        rotate(newNode, newNode.parent);
        // To apply case 1, we just recurse this method. Case 1 block will run
        enforceRBTreePropertiesAfterInsert((newNode.leftChild == null) ? 
            newNode.rightChild : newNode.leftChild);
        return;
      }

      /*Case 3: newNode's uncle is red REGARDLESS OF THE SIDE newNode IS ON. 
      In this case, we just swap the colors of the parent, grandparent, and uncle.
      Finally, just set the root node (NOT THE PARENT OR GRANDPARENT), to black
       */
 /*First, do a null check before every comparison and then check if it's a red-on-red violation 
    before we proceed
       */
      if ((newNode.parent.parent.leftChild != null && !newNode.parent.parent.leftChild.isBlack)
          && (newNode.parent.parent.rightChild != null && !newNode.parent.parent.rightChild.isBlack)) {

        // Swapping the parent's color
        newNode.parent.isBlack = !newNode.parent.isBlack;

        //Swapping grandparent's color
        newNode.parent.parent.isBlack = !newNode.parent.parent.isBlack;

        //Swapping uncle's color
        if (newNode.parent.parent.rightChild.equals(newNode.parent)) {
          //if the uncle is on the right side and the node in question is on the opposite side
          // Swap grandparent leftchild's color
          newNode.parent.parent.leftChild.isBlack = !newNode.parent.parent.leftChild.isBlack;
        } else if (newNode.parent.parent.leftChild.equals(newNode.parent)) {
          //if the uncle is on the right side and the node in question is on the opposite side
          newNode.parent.parent.rightChild.isBlack = !newNode.parent.parent.rightChild.isBlack;
        }

        /* Because the violation may have been pushed up the tree, check the grandparent
          Since the tree might be small, do a null check in case if there was no grandparent*/
        if (newNode.parent.parent != null) {
          enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
        }
        // Finally, we need to ensure that the root is black.
        root.isBlack = true;
      }
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation. When the
   * provided child is a rightChild of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   *
   * @param child is the node being rotated from child to parent position (between these two node
   * arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   * arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   * initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    // Null check: if both children are null, then it's a tree with 1 node, no ration
    if (parent.leftChild == null && parent.rightChild == null) {
      return;
    }

    if ((parent.leftChild != null && !parent.leftChild.equals(child)) && (parent.rightChild != null && !parent.rightChild.equals(child))) {
      throw new IllegalArgumentException("Given parent and child are not related");
    }
    
    /*
    In both right and left rotations, one problem that may occur is that the nodes are rotated, 
    but their references would be incorrect. For example, a root node that was demoted to be a 
    child node may still have a parent reference of null. and the NEW root may be pointing to the 
    former root as its parent node. Therefore, we'll set the references after every shift in 
    position. 
    */

    if (parent.leftChild != null && parent.leftChild.equals(child)) { // rotate RIGHT
      Node<T> leftNodeOld = parent.leftChild;
      parent.leftChild = leftNodeOld.rightChild;

      if (parent.parent == null) { // if there was no parent above the given parent, the left child becomes the root
        root = leftNodeOld;
        if (leftNodeOld != null) { // Setting the parent's reference
          leftNodeOld.parent = null;
        }

      } else if (parent.parent.leftChild != null && parent.parent.leftChild.equals(parent)) {
        if (leftNodeOld != null) { // Setting the reference
          leftNodeOld.parent = parent.parent;
        }
        parent.parent.leftChild = leftNodeOld;
      } else {
        if (leftNodeOld != null) { // Setting the reference
          leftNodeOld.parent = parent.parent;
        }
        parent.parent.rightChild = leftNodeOld;
      }

      if (parent != null) { // Setting the reference
        parent.parent = leftNodeOld;
      }
      leftNodeOld.rightChild = parent;
    } else if (parent.rightChild != null && parent.rightChild.equals(child)) { //Rotate left

      Node<T> rightNodeOld = parent.rightChild;

      if (rightNodeOld.leftChild != null) { // Setting the reference
        rightNodeOld.leftChild.parent = parent.rightChild;
      }

      parent.rightChild = rightNodeOld.leftChild;

      if (parent.parent == null) { // Setting the reference
        root = rightNodeOld;

      } else if (parent.parent.leftChild != null && parent.parent.leftChild.equals(parent)) {
        if (rightNodeOld != null) { // Setting the reference
          rightNodeOld.parent = parent.parent;
        }

        parent.parent.leftChild = rightNodeOld;
      } else {
        if (rightNodeOld != null) { // Setting the reference
          rightNodeOld.parent = parent.parent;
        }

        parent.parent.rightChild = rightNodeOld;
      }

      if (parent != null) {// Setting the reference
        parent.parent = rightNodeOld;
      }
      rightNodeOld.leftChild = parent;
    }
  }

  /**
   * Get the size of the tree (its number of nodes).
   *
   * @return the number of nodes in the tree
   */
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   *
   * @return true of this.size() return 0, false if this.size() > 0
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Checks whether the tree contains the value *data*.
   *
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null) {
      throw new NullPointerException(
          "This RedBlackTree cannot store null references.");
    }
    return this.containsHelper(data, root);
  }

  /**
   * Recursive helper method that recurses through the tree and looks for the value *data*.
   *
   * @param data the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private boolean containsHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return false;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return containsHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return containsHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return true;
      }
    }
  }

  /**
   * Returns an iterator over the values in in-order (sorted) order.
   *
   * @return iterator object that traverses the tree in in-order sequence
   */
  public Iterator<T> iterator() {
    // use an anonymous class here that implements the Iterator interface
    // we create a new on-off object of this class everytime the iterator
    // method is called
    return new Iterator<T>() {
      // a stack and current reference store the progress of the traversal
      // so that we can return one value at a time with the Iterator
      Stack<Node<T>> stack = null;
      Node<T> current = root;

      /**
       * The next method is called for each value in the traversal sequence. It returns one value at
       * a time.
       *
       * @return next value in the sequence of the traversal
       * @throws NoSuchElementException if there is no more elements in the sequence
       */
      public T next() {
        // if stack == null, we need to initialize the stack and current element
        if (stack == null) {
          stack = new Stack<Node<T>>();
          current = root;
        }
        // go left as far as possible in the sub tree we are in until we hit a null
        // leaf (current is null), pushing all the nodes we fund on our way onto the
        // stack to process later
        while (current != null) {
          stack.push(current);
          current = current.leftChild;
        }
        // as long as the stack is not empty, we haven't finished the traversal yet;
        // take the next element from the stack and return it, then start to step down
        // its right subtree (set its right sub tree to current)
        if (!stack.isEmpty()) {
          Node<T> processedNode = stack.pop();
          current = processedNode.rightChild;
          return processedNode.data;
        } else {
          // if the stack is empty, we are done with our traversal
          throw new NoSuchElementException("There are no more elements in the tree");
        }

      }

      /**
       * Returns a boolean that indicates if the iterator has more elements (true), or if the
       * traversal has finished (false)
       *
       * @return boolean indicating whether there are more elements / steps for the traversal
       */
      public boolean hasNext() {
        // return true if we either still have a current reference, or the stack
        // is not empty yet
        return !(current == null && (stack == null || stack.isEmpty()));
      }

    };
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
   * that this RedBlackTree class implementation of toString generates an inorder traversal. The
   * toString of the Node class class above produces a level order traversal of the nodes / values
   * of the tree.
   *
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  @Override
  public String toString() {
    // use the inorder Iterator that we get by calling the iterator method above
    // to generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    Iterator<T> treeNodeIterator = this.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (treeNodeIterator.hasNext()) {
      sb.append(treeNodeIterator.next());
    }
    while (treeNodeIterator.hasNext()) {
      T data = treeNodeIterator.next();
      sb.append(", ");
      sb.append(data.toString());
    }
    sb.append(" ]");
    return sb.toString();
  }
}
