import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class ResizableArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty stack with a default capacity. */
    public ResizableArrayStack() //default constructor
    {
        this(DEFAULT_CAPACITY);
    }

    /** Creates an empty stack with a desired capacity.
     @param initialCapacity The desired capacity. */
    public ResizableArrayStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    /** Checks to see if capacity is too large.
     * @param capacity Capacity to be checked. */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a stack over the capacity of " + MAX_CAPACITY);
    }

    /** Pushes a new entry onto the top of the stack.
     * @param newEntry  An object to be added to the stack. */
    public void push(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    /** Checks capacity of the stack, and doubles it if full. */
    private void ensureCapacity()
    {
        if (topIndex >= stack.length - 1) // If array is full, double its size
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    /** Tests to see if object is initialized.
     * Throws an exception is object is not initialized. */
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("ArrayStack object is corrupt.");
    }

    /** Removes the top entry of the stack.
     * @return The entry removed if successful, or an exception. */
    public T pop()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    /** Shows the top entry of the stack.
     * @return The top entry if successful, or an exception. */
    public T peek()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    /** Tests to see if stack is empty.
     @return True if stack is empty, false if not. */
    public boolean isEmpty()
    {
        return topIndex < 0;
    }

    /** Removes all entries in a stack. */
    public void clear()
    {
        checkIntegrity();
        while (topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        }
    }
    
    public static int evaluatePostfix(String postfix)
    {
        //Evaluates a postifx expresssion.
        char[] c = postfix.toCharArray();
        StackInterface valueStack = new ResizableArrayStack(c.length);
        int operandOne;
        int operandTwo;
        int result;

        for(int i = 0; i < postfix.length(); i++)
        {
            char nextCharacter = postfix.charAt(i);
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
                    result = operandOne ^ operandTwo;
                    valueStack.push(result);
                    break;
                default:
                    valueStack.push(nextCharacter); 
            }
        }
        return (int) valueStack.peek();
    }
}
