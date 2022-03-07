import java.util.EmptyStackException;

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
