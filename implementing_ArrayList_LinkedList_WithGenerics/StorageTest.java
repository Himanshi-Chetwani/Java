/*
 * Classname:StorageTest.java
 *
 * Version information:1
 *
 * Date:02/17/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */


/**
 * To test the working of both array list and linked list
 */
@SuppressWarnings("unchecked")
public class StorageTest<T extends Comparable>
{
    /**
     * To test the inner workings of each function of both array list and
     * linked list
     * @param is specifies type of storage to be tested
     */
    private void individualTest(StorageInterface<T> is)
    {
        System.out.println("Testing " + is.getClass());
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.add((T)(Comparable)1);
        is.addAll((T[])new Comparable[]{2,3,4});
        System.out.println("Should be\n\t4\n\t" + is.size());
        System.out.println("Should be\n\t{1, 2, 3, 4}\n\t" + is);
        is.addAll(0, ((T[])new Comparable[]{5, 6, 7}));
        System.out.println("Should be\n\t{5, 6, 7, 1, 2, 3, 4}\n\t" + is);
        is.sort();
        is.clear();
        is.addAll((T[])new Comparable[]{1,2,3,4,5,6,7});
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + is);
        is.add(1, (T)(Comparable)3);
        System.out.println("Should be\n\t{1, 3, 2, 3, 4, 5, 6, 7}\n\t" + is);
        System.out.println("Should be\n\t1\n\t" + is.indexOf((T)(Comparable)3));
        System.out.println("Should be\n\t3\n\t" + is.lastIndexOf(
                (T)(Comparable)3));
        is.remove((T)(Comparable)(3));
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6, 7}\n\t" + is);
        is.remove(6);
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6}\n\t" + is);
        System.out.println("Should be\n\tfalse\n\t" + is.contains((T)(Comparable)7));
        System.out.println("Should be\n\ttrue\n\t" + is.contains((T)(Comparable)4));
        System.out.println("Should be\n\tfalse\n\t" + is.containsAll(
                (T[])new Comparable[]{4, 5, 6, 7}));
        System.out.println("Should be\n\ttrue\n\t" + is.containsAll(
                (T[])new Comparable[]{4
                , 5, 6}));
        System.out.println("Should be\n\t21\n\t" + is.hashCode());
        System.out.println("Should be\n\tfalse\n\t" + is.removeAll
                ((T[])new Comparable[]{7, 8, 9}));
        System.out.println("Should be\n\t{1, 2, 3, 4, 5, 6}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll
                ((T[])new Comparable[]{6, 7, 8}));
        System.out.println("Should be\n\t{1, 2, 3, 4, 5}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll
                ((T[])new Comparable[]{4, 5}));
        System.out.println("Should be\n\t{1, 2, 3}\n\t" + is);
        is.clear();
        System.out.println("Should be\n\tfalse\n\t"+is.contains(null));
        System.out.println("should be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should be\n\t0\n\t" + is.hashCode());
        is.add(null);
        System.out.println("Should be\n\t{null}\n\t"+is);
        System.out.println("Should be\n\ttrue\n\t"+is.contains(null));
    }
    private void individualTestString(StorageInterface<T> is)
    {
        System.out.println("Testing " + is.getClass());
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.add((T)"a");
        is.addAll((T[])new Comparable[]{"b","c","d"});
        System.out.println("Should be\n\t4\n\t" + is.size());
        System.out.println("Should be\n\t{a, b, c, d}\n\t" + is);
        is.addAll(0, ((T[])new Comparable[]{"e", "f", "g"}));
        System.out.println("Should be\n\t{e,f,g,a,b,c,d}\n\t" + is);
        is.sort();
        System.out.println("Should be\n\t{a,b,c,d,e,f,g}\n\t"+is);
        is.clear();
        is.addAll((T[])new Comparable[]{"a","b","c","d","e","f","g"});
        System.out.println("Should be\n\t{a,b,c,d,e,f,g}\n\t" + is);
        is.add(1, (T)"c");
        System.out.println("Should be\n\t{a,c,b,c,d,e,f,g}\n\t" + is);
        System.out.println("Should be\n\t1\n\t" + is.indexOf((T)
                "c"));
        System.out.println("Should be\n\t3\n\t" + is.lastIndexOf((T)"c"));
        is.remove((T) "c");
        System.out.println("Should be\n\t{a,b,c,d,e,f,g}\n\t" + is);
        is.remove(6);
        System.out.println("Should be\n\t{a,b,c,d,e,f}\n\t" + is);
        System.out.println("Should be\n\tfalse\n\t" + is.contains((T)"g"));
        System.out.println("Should be\n\ttrue\n\t" + is.contains((T)"d"));
        System.out.println("Should be\n\tfalse\n\t" + is.containsAll
                ((T[])new Comparable[]{"d","e","f","g"}));
        System.out.println("Should be\n\ttrue\n\t" + is.containsAll
                ((T[])new Comparable[]{"d","e","f"}));
        System.out.println("Should be\n\t597\n\t" + is.hashCode());
        System.out.println("Should be\n\tfalse\n\t" + is.removeAll
                ((T[])new Comparable[]{"g","h","i"}));
        System.out.println("Should be\n\t{a,b,c,d,e,f}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll
                ((T[])new Comparable[]{"f","g","h"}));
        System.out.println("Should be\n\t{a,b,c,d,e}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll
                ((T[])new Comparable[]{"d","e"}));
        System.out.println("Should be\n\t{a,b,c}\n\t" + is);
        is.clear();
        System.out.println("Should be\n\tfalse\n\t"+is.contains(null));
        System.out.println("should be\n\tempty\n\t" + is.isEmpty() + " : "
                + is);
        System.out.println("Should be\n\t0\n\t" + is.hashCode());
        is.add(null);
        System.out.println("Should be\n\t{null}\n\t"+is);
        System.out.println("Should be\n\ttrue\n\t"+is.contains(null));
    }

    /**
     * To cast the two linked list and array list to storage type and call
     * the individual testing function
     * @param args STDIN
     */
    public static void main(String[] args)
    {

        StorageTest obj = new StorageTest();
        System.out.println("Testing on provided cases: ");
        System.out.println("----------------------------");
        StorageAbstract<Integer> ill = new LinkedList<>();
        obj.individualTest(ill);
        StorageAbstract<Integer> ial = new ArrayList<>();
        obj.individualTest(ial);
        StorageAbstract<String> iel = new LinkedList<>();
        obj.individualTest(iel);
        StorageAbstract<String> icl = new ArrayList<>();
        obj.individualTest(icl);
        System.out.println("----------------------------");
        System.out.println("Testing on self made cases: ");
        StorageAbstract<String> iol = new LinkedList<>();
        obj.individualTestString(iol);
        System.out.println("----------------------------");
        StorageAbstract<String> iul = new ArrayList<>();
        obj.individualTestString(iul);
        System.out.println("----------------------------");
        System.out.println("Should be\n\ttrue\n\t" + ill.contentEquals(ial));
        ial.add(1);
        System.out.println("----------------------------");
        System.out.println("Should be\n\tfalse\n\t" + ill.contentEquals(ial));
        System.out.println("----------------------------");
        ill.add(1);
        System.out.println("Should be\n\ttrue\n\t" + ill.contentEquals(ial));


    }


}