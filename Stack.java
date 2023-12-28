public interface Stack { // Stack class ADT

    // Push "it" onto the top of the stack
    public boolean push(Object it);

    // Remove and return the element at the top of the stack
    public Object pop();

    // Return true if the stack is empty
    public boolean isEmpty();
}
