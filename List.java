/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }
    
    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
         if (first == null) {
            return null;
        }
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
       // Create a new CharData object
        CharData cd = new CharData(chr);

        // Create a new node that points to the current first node
        Node newNode = new Node(cd, first);

        // Update first to point to the new node
        first = newNode;

        // Increase list size
        size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        String result = "(";
        Node curr = first;

        // Go through all nodes in the list
        while (curr != null) {
            result += curr.cp.toString();

            // Add a space between elements
            if (curr.next != null) {
                result += " ";
            }

            // Move to the next node
            curr = curr.next;
        }

        result += ")";
        return result;
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node curr = first;
        int index = 0;

        // Go through the list
        while (curr != null) {
            if (curr.cp.chr == chr) {
                return index;
            }
            curr = curr.next;
            index++;
        }

        // Character not found
        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        Node curr = first;

        // Search for the character in the list
        while (curr != null) {
            if (curr.cp.chr == chr) {
                curr.cp.count++;
                return;
            }
            curr = curr.next;
        }

        // Character not found, add it to the beginning
        addFirst(chr);
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        // If the list is empty
        if (first == null) {
            return false;
        }

        // If the first element matches
        if (first.cp.chr == chr) {
            first = first.next;
            size--;
            return true;
        }

        Node prev = first;
        Node curr = first.next;

        // Go through the list starting from the second node
        while (curr != null) {
            if (curr.cp.chr == chr) {
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }

        // Character not found
        return false;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        // Check for invalid index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node curr = first;
        int i = 0;

        // Move to the node at the given index
        while (i < index) {
            curr = curr.next;
            i++;
        }

        return curr.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}