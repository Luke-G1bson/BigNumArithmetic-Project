// List class ADT. Generalize by using "Object" for the element type.
import java.util.NoSuchElementException;
public interface List { // List class ADT
    // Remove all contents from the list, so it is once again empty
    public void clear();

    // Append "it" at the end of the list
    // The client must ensure that the list's capacity is not exceeded
    public boolean append(Object it);


    // Set the current position to the start of the list
    public void moveToStart();



    // Move the current position one step right, no change if already at end
    public void next();

    // Return the number of elements in the list
    public int length();


    // Return the current element
    public Object getValue() throws NoSuchElementException;

}
