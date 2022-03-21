/***
 * Contains the convertToPostFix and evaluatePostfix algorithms
 */
public class Calc {

    /***
     * checks the priority of the arithmetic operation
     * @param operation an arithmetic operation
     * @return an integer showing the precedence of the arithmetic operation
     */
    public static int precedence(char operation){
        switch (operation){
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    /***
     * Converts an infix expression to a post-fix expression
     * @param infix string that will be converted to a post-fix expression
     * @return string with post-fix expression
     */
    public static String convertToPostFix(String infix)
    {
        StackInterface operatorStack = new LinkedStack();
        String postfix = "";
        char nextCharacter;
        char topOperator;

        for( int i =0; i < infix.length(); i++)
        {
            nextCharacter = infix.charAt(i);

            switch (nextCharacter){
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-' : case '*' : case '/' :
                    while(!operatorStack.isEmpty() && (precedence(nextCharacter) <= precedence((Character) operatorStack.peek())))
                    {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')':
                    topOperator = (char) operatorStack.pop();
                    while (topOperator != '(')
                    {
                        postfix += topOperator;
                        topOperator = (char) operatorStack.pop();
                    }
                    break;
                default:
                    postfix += nextCharacter;
                    break;
            }
        }
        while (!operatorStack.isEmpty())
        {
            topOperator = (char) operatorStack.pop();
            postfix += topOperator;
        }
        return postfix;
    }

    /***
     * Evaluates a postfix expression
     * @param postfix a string to be evaluated
     * @return an integer with the answer of the postfix expression
     */
    public static int evaluatePostfix(String postfix)
    {
        //A space is required in between each variable and operand. Ex: 2 2 +
        char[] c = postfix.toCharArray();
        StackInterface valueStack = new ResizableArrayStack(c.length);
        int operandOne;
        int operandTwo;
        int result;

        for(int i = 0; i < postfix.length(); i++)
        {
            char nextCharacter = postfix.charAt(i);

            if (Character.isDigit(nextCharacter))
            {
                int x = 0;

                while(Character.isDigit(nextCharacter))
                {
                    x = x * 10 + Integer.parseInt(String.valueOf(nextCharacter));
                    i++;
                    nextCharacter = postfix.charAt(i);
                }

                valueStack.push(x);
            }

            else
            {
                switch (nextCharacter)
                {
                    case '+':
                        operandTwo = Integer.parseInt(String.valueOf(valueStack.pop()));
                        operandOne = Integer.parseInt(String.valueOf(valueStack.pop()));
                        result = operandOne + operandTwo;
                        valueStack.push(result);
                        break;
                    case '-':
                        operandTwo = Integer.parseInt(String.valueOf(valueStack.pop()));
                        operandOne = Integer.parseInt(String.valueOf(valueStack.pop()));
                        result = operandOne - operandTwo;
                        valueStack.push(result);
                        break;
                    case '*':
                        operandTwo = Integer.parseInt(String.valueOf(valueStack.pop()));
                        operandOne = Integer.parseInt(String.valueOf(valueStack.pop()));
                        result = operandOne * operandTwo;
                        valueStack.push(result);
                        break;
                    case '/':
                        operandTwo = Integer.parseInt(String.valueOf(valueStack.pop()));
                        operandOne = Integer.parseInt(String.valueOf(valueStack.pop()));
                        result = operandOne / operandTwo;
                        valueStack.push(result);
                        break;
                    case '^':
                        operandTwo = Integer.parseInt(String.valueOf(valueStack.pop()));
                        operandOne = Integer.parseInt(String.valueOf(valueStack.pop()));
                        result = (int) Math.pow(operandOne, operandTwo);
                        valueStack.push(result);
                        break;
                    default:
                        break;
                }
            }

        }
        return (int) valueStack.peek();
    }

    /***
     * Main method
     * @param args
     */
    public static void main(String args[])
    {
        String exp = "a*b/(c-a)+d*e";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + Calc.convertToPostFix(exp));
        String exp2 = "2 3 * 4 2 - / 5 6 * +";
        System.out.println("Evaluated Postfix Expression: " + Calc.evaluatePostfix(exp2));
    }

}
