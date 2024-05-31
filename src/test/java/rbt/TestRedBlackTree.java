package rbt;

/** * JUnit imports ** */
// The Assertions class that we import from here includes assertion methods like assertEquals()
// which we will used in the test methods.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// JUnit imports end

/**
 * This class contains JUnit tests as it is mainly used for testing the RedBlackTree class
 *
 * @author Naif Abdullah
 */
public class TestRedBlackTree {

  //The @Test annotation allows JUnit to recognize its following method as a test method
  /**
   * In this test method, we'll focus on simulating a case 1 violation with a null uncle node.
   */
  @Test
  public void testCase1A() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    rbt.insert(1);
    rbt.insert(2);
    rbt.insert(3); // Case 1 with null parent sibling

    String expectedOutput = "[2, 1, 3]";
    assertEquals(rbt.root.toString(), expectedOutput);
  }

  /**
   * In this test method, we'll focus on simulating a case 1 violation with a non-null uncle node.
   */
  @Test
  public void testCase1B() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    rbt.insert(1);
    rbt.insert(2);
    rbt.insert(3);
    rbt.insert(4);
    rbt.insert(5); // Case 1 with non-null parent sibling

    String expectedOutput = "[2, 1, 4, 3, 5]";
    assertEquals(rbt.root.toString(), expectedOutput);
  }

  /**
   * In this test method, we'll focus on simulating a case 2 violation
   */
  @Test
  public void testCase2() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    rbt.insert(10);
    rbt.insert(20);
    rbt.insert(30);
    rbt.insert(25);
    rbt.insert(26); // Case 2 on the right half of the tree
    rbt.insert(5);
    rbt.insert(6);

    String expectedOutput = "[20, 6, 26, 5, 10, 25, 30]";
    assertEquals(rbt.root.toString(), expectedOutput);
  }

  /**
   * In this test method, we'll focus on simulating a case 3 violation on both sides of the RBT.
   */
  @Test
  public void testCase3() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    // Doing a case 3 test on the RIGHT side of the whole tree
    rbt.insert(10);
    rbt.insert(20);
    rbt.insert(30);
    rbt.insert(35);

    // Doing a case 3 test on the LEFT side of the whole tree
    rbt.insert(5);
    rbt.insert(15);
    rbt.insert(4);

    String expectedOutput = "[20, 10, 30, 5, 15, 35, 4]";
    assertEquals(rbt.root.toString(), expectedOutput);
  }

  /**
   * In this test method, we'll be going through all 3 red-on-red violation cases.
   */
  @Test
  public void testAll() {
    RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();

    rbt.insert(100);
    rbt.insert(99);
    rbt.insert(98); // Case 1
    rbt.insert(80); // Case 3
    rbt.insert(70); // Case 1
    rbt.insert(60); // Case 3
    rbt.insert(50); // Case 1
    rbt.insert(40); // Case 3 pushed and then Case 1
    rbt.insert(30); // Case 1
    rbt.insert(20); // Case 3
    rbt.insert(10); // Case 3 pushed up
    rbt.insert(15);  // Case 2
    rbt.insert(14);  // Case 2

    String expectedOutput = "[80, 40, 99, 20, 60, 98, 100, 14, 30, 50, 70, 10, 15]";
    assertEquals(rbt.root.toString(), expectedOutput);
  }
}
