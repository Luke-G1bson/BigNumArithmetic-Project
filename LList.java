import java.util.NoSuchElementException;

// Linked list implementation
class LList implements List {
    private Link head;         // Pointer to list header
    private Link tail;         // Pointer to last element
    private Link curr;         // Access to current element
    private int listSize;      // Size of list

    // Constructor
    LList() { clear(); }

    // Remove all elements
    public void clear() {
        curr = tail = new Link(null); // Create trailer
        head = new Link(tail);        // Create header
        listSize = 0;
    }



    // Append "it" to list
    public boolean append(Object it) {
        tail.setNext(new Link(null));
        tail.setElement(it);
        tail = tail.next();
        listSize++;
        return true;
    }



    public void moveToStart() { curr = head.next(); } // Set curr at list start



    // Move curr one step right; no change if now at end
    public void next() { if (curr != tail) curr = curr.next(); }

    public int length() { return listSize; } // Return list length


    // Return current element value.
    public Object getValue() throws NoSuchElementException {
        if (curr == tail) // No current element
            throw new NoSuchElementException("getvalue() in LList has current of " + curr + " and size of "
                    + listSize + " that is not a a valid element");
        return curr.element();
    }




}
