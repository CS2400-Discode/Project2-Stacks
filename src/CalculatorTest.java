/***
 * Tests the evaluteToPostFix algorithm
 */
public class CalculatorTest {

    /***
     * Main method
     * @param args
     */
    public static void main(String args[])
    {
        String exp = "23*42-/56*+";
        System.out.println("Postfix Expression: " + exp);
        System.out.println("Evaluated Postfix Expression: " + ResizableArrayStack.evaluatePostfix(exp));
    }
}
