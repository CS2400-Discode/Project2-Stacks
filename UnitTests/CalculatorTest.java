import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/***
 * Uses JUnit to test different scenarios for the convertToPostFix and evaluatePostfix methods in Calc.java
 */
class CalculatorTest {
    /**
     * Tests to see if the infix expression being empty affects the postfix expression.
     */
    @Test
    void nullInfix() {
        String exp = "";
        String postFix = Calc.convertToPostFix(exp);
        assertEquals("", postFix);
    }

    /**
     * Tests to see if the infix expression having no operands affects the postfix expression.
     */
    @Test
    void nullOperandInfix() {
        String exp = "abcdefg";
        String postFix = Calc.convertToPostFix(exp);
        assertEquals("abcdefg", postFix);
    }

    /**
     * Tests to see if the infix expression having no variables affects the postfix expression.
     */
    @Test
    void nullVariableInfix() {
        String exp = "*/+-";
        String postFix = Calc.convertToPostFix(exp);
        assertEquals("*/+-", postFix);
    }
}