import java.util.Iterator;

public class mainclass {
    // Solve the problem in the main method
    // The answer of n = 13,  k = 2 is
    // the 11th person in the ring (index 10)
    public static void main(String[] args){
        CircularLinkedList<Integer> l =  new CircularLinkedList<>();
        int n = 13;
        int k= 2;

       for (int i= 1; i <= n; i++){
           l.add(i);
       }

        // use the iterator to iterate around the list
        Iterator<Integer> iter = l.iterator();


        System.out.println(l);



       while(l.size != 1) {

           for (int i = 0; i < k; i++) {
               iter.next();
           }
       //    if(iter.next().equals(l.head.item)) {
            // }
               iter.remove();
               System.out.println(l);
           }

       }

    }

