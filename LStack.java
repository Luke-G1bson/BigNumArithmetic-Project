// Linked stack implementation
class LStack implements Stack {
    private Link top;               // Pointer to first element
    private int size;               // Number of elements

    // Constructors
    LStack() {
        top = null;
        size = 0;
    }


    // Put "it" on stack
    public boolean push(Object it) {
        top = new Link(it, top);
        size++;
        return true;
    }

    // Remove "it" from stack
    public Object pop() {
        if (top == null) return null;
        Object it = top.element();
        top = top.next();
        size--;
        return it;
    }



    // Check if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }
}