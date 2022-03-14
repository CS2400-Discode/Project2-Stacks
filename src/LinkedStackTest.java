/***
 * Tests the convertToPostFix algorithm
 */
public class LinkedStackTest {


    public static void main(String args[])
    {
    String exp = "a*b/(c-a)+d*e";
    System.out.println("Infix Expression: " + exp);
    System.out.println("Postfix Expression: " + LinkedStack.convertToPostFix(exp));

    }

}