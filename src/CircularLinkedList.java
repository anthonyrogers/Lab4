
import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {



    // Your variables
    Node<E> head;
    Node<E> tail;
    int size;  // BE SURE TO KEEP TRACK OF THE SIZE


    public CircularLinkedList() {

        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    //  helper method
    private Node<E> getNode(int index) {

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curNode = head;

        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    // attach a node to the end of the list
    public boolean add(E item) {
        this.add(size,item);
        return true;
    }

    // Cases to handle
    // out of bounds - DONE
    // adding to empty list - DONE
    // adding to front - DONE
    // adding to "end" - DONE
    // adding anywhere else -DONE
    public void add(int index, E item){

        if(index < 0 || index > this.size){
            throw new IndexOutOfBoundsException();
        }

        Node<E> adding = new Node<>(item);

        if(size == 0){
            head = adding;
            tail = adding;
            tail.next = head;
            head.prev = tail;
        }
         else if(index==0){
          adding.next = head;
          head.prev = adding;
          head = adding;
          head.prev = tail;
          tail.next = head;
         }
         else if (index == size){
            adding.prev = tail;
            tail.next = adding;
            tail = adding;
            tail.next = head;
            head.prev = tail;


        }else{
            Node<E> before = getNode(index-1);
            adding.prev = before;
            adding.next = before.next;
            before.next = adding;
            before.next.prev = adding;


        }
        size++;

    }
    // remove must handle the following cases
    // out of bounds - DONE
    // removing the only thing in the list-done
    // removing the first thing in the list-done
    // removing the last thing
    // removing any other node
    // REMEMBER TO DECREMENT THE SIZE
    public E remove(int index) {

        if(index < 0 || index > this.size){
            if(index < 0){
                System.out.println("less then 0");
            }
            if(index >= this.size){
                System.out.println("size to large");
            }
            throw new IndexOutOfBoundsException();
        }

        E userReturn;

        if(size == 1){
         userReturn = head.item;
         head.next  = null;
         tail.next = null;

        }
        else if(index == 0){
            userReturn = head.item;
            head = head.next;
            tail.next = head;
            head.prev = tail; //to complete the circle


        }
        else if(index == size-1){
            userReturn = tail.item;
            tail = tail.prev;
            tail.next = head;
            head.prev = tail; //to complete the circle

        }
        else{
            Node<E> last = getNode(index-1);
            userReturn = last.next.item;
            last.next = last.next.next;
            last.next.prev = last;
        }
        size--;

        return userReturn;
    }


   public String toString(){
        Node<E> current = head;
        StringBuilder result = new StringBuilder();
        if(size == 0){
            return "[]";
        }
        if(size == 1) {
            return head.item.toString();

        }
        else{
            do{
                result.append(current.item);
                result.append(" ==> ");
                current = current.next;
            } while(current != head);
        }
        return result.toString();
    }


    public Iterator<E> iterator() {

        return new ListIterator<>();
    }


    private class ListIterator<E> implements Iterator<E>{

        Node<E> nextItem;
        Node<E> prev;
        int index;

        @SuppressWarnings("unchecked")
        //Creates a new iterator that starts at the head of the list
        public ListIterator(){
            nextItem = (Node<E>) head;
            index = 0;
        }

        // returns true if there is a next node
        // this is always should return true if the list has something in it
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return size != 0;
        }

        // advances the iterator to the next item
        // handles wrapping around back to the head automatically for you
        public E next() {
            // TODO Auto-generated method stub
            prev =  nextItem;
            nextItem = nextItem.next;
            index =  (index + 1) % size;
            return prev.item;

        }

        // removed the last node was visted by the .next() call
        public void remove() {
            int target;
            if(nextItem == head) {
                target = size - 1;
            } else{
                target = index - 1;
                index--;
            }
            CircularLinkedList.this.remove(target); //calls the above class
        }

    }

}