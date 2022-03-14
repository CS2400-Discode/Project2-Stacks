import java.util.EmptyStackException;

/***
 * Contains methods for a linked stack
 * Contains the convertToPostFix algorithm
 * @param <T> A generic data type
 */
public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode; //first node in the chain

    /** Creates an empty stack with a default capacity. */
    public LinkedStack() //default constructor
    {
        topNode = null;
    }

    /** Pushes a new entry onto the top of the stack.
     * @param newEntry  An object to be added to the stack. */
    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    /** Removes the top entry of the stack.
     * @return The entry removed if successful, or an exception. */
    public T pop()
    {
        T top = peek();
        topNode = topNode.getNextNode();
        return top;
    }

    /** Shows the top entry of the stack.
     * @return The top entry if successful, or an exception. */
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    }

    /** Tests to see if stack is empty.
     @return True if stack is empty, false if not. */
    public boolean isEmpty()
    {
        return topNode == null;
    }

    /** Removes all entries in a stack. */
    public void clear()
    {
        topNode = null;
    }


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


    /** Creates nodes that can be used in other classes */
    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private T getData()
        {
            return data;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }

}
