/**
  Represent a list, implemented in a chain of nodes
 */

public class List_doublyLinked{
    private Node headSentinel;
     
    /**
      Construct an empty list
     */
    public List_doublyLinked() {
        headSentinel = new Node( null,null);
    }

    /**
      @return the number of elements in this list
     */
    public int size() {
        // recursive approach seems more perspicuous
        return size( headSentinel);
    }

    // recursively-called helper
    private int size( Node startingAt) {
        Node next = startingAt.getNextNode();
        if( next == null) return 0;
        else return 1+ size( next);
    }


     /**
       @return a string representation of this list,
       format:
           # elements [element0,element1,element2,]
      */
    public String toString() {
        String stringRep = size() + " elements [";

        for( Node node = headSentinel.getNextNode()
           ; node != null
           ; node = node.getNextNode() )
            stringRep += node.getCargo() + ",";
        return stringRep + "]";
    }

    /*public String toString() {
        String stringRep = size() + " elements [";

        for( Node node = getLastNode()
           ; node != headSentinel
           ; node = node.getPrevNode() ){
          stringRep += node.getCargo() + ",";


        }
        return stringRep + "]";
    }*/


    /**
      Append @value to the head of this list.

      @return true, in keeping with conventions yet to be discussed
     */
     public boolean addAsHead( Object val) {
        Node afterHead = headSentinel.getNextNode();
        Node newNode = new Node( val, headSentinel.getNextNode(),headSentinel);
        headSentinel.setNextNode( newNode );
        if(afterHead != null)afterHead.setPrevNode( newNode );
        return true;
     }


    /**
      @return a reference to the node before
              the node at @index
     */
    private Node getNodeBefore( int index) {
        /* iterate through the chain, up to the node
           that holds a reference to the desired node 
           
        Node node;
        int upTo;  // comma operator precludes declaration in FOR
        for( upTo = 0   , node = headSentinel
           ; upTo < index
           ; upTo++     , node = node.getNextNode()
           )
           ;  // null loop body since all the action is in the FOR
        return node;
    }*/

    private Node getNodeBefore(int index){
      if(index < 0) return headSentinel;
      return getNode(index).getPrevNode();
    }

    /**
      @return a reference to the node @index
     */
    private Node getNode( int index) {
        Node node;
        int upTo;  // comma operator precludes declaration in FOR
        for( upTo = 0   , node = headSentinel.getNextNode()
           ; upTo < index
           ; upTo++     , node = node.getNextNode()
           )
           ;  // null loop body since all the action is in the FOR
        return node;
    }

    private Node getLastNode() {
      if(size() <= 0) return headSentinel;

      Node node = headSentinel.getNextNode();
      while(node.getNextNode() != null) node = node.getNextNode();

      return node;
    }

    // accessors
    /**
      @return element @index from this list
      precondition: @index is within the bounds of the array.
          (Having warned the user about this precondition,
           you should NOT complicate your code to check
           whether user violated the condition.)
     */
    public Object get( int index ) {
        return getNode( index).getCargo();
    }


    /**
      Set value at @index to @newValue

      @return old value at @index
      @precondition: @index is within the bounds of this list.
     */
    public Object set( int index, Object newValue ) {
        return getNode( index).setCargo( newValue);
    }


    /**
      Insert @value at position @index in this list.

      Shift the element currently at that position (if any)
      and any subsequent elements to the right
      (that is, increase the index associated with each).
     */
    public boolean add( int index, Object value) {
        if(index == 0){
          addAsHead(value);
          return true;
        }
        
        Node newNode = new Node( value);
        Node prevNode = getNodeBefore(index);

        System.out.println(prevNode.getCargo());

        if(prevNode.getNextNode() != null) {
          prevNode.getNextNode().setPrevNode(newNode);
        }
        newNode.setNextNode(prevNode.getNextNode());

        newNode.setPrevNode(prevNode);
        prevNode.setNextNode(newNode);

        return true;
    }


     /**
      Remove the element at position @index in this list.

      Shift any subsequent elements to the left (that is,
      decrease the index associated with each).

      @return the value that was removed from the list
     */
    public Object remove( int index) {
        Node ax = getNode(index);
        Node before = ax.getPrevNode();
        Node next = ax.getNextNode();

        if(next != null){
          next.setPrevNode(before);
        }
        before.setNextNode( next );

        Object saveForReturn = ax.getCargo();
        return saveForReturn;
    }
}