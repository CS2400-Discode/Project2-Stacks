/***
 * Tests the convertToPostFix and evaluatePostfix algorithms
 */
public class CalculatorTest {

    /***
     * Main method
     * @param args
     */
    public static void main(String args[])
    {
        String exp = "a*b/(c-a)+d*e";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + Calc.convertToPostFix(exp));
        String exp2 = "23*42-/56*+";
        System.out.println("Evaluated Postfix Expression: " + Calc.evaluatePostfix(exp2));
    }
}
