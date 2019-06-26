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
public class StorageTest<T extends Comparable> {
    /**
     * To test the inner workings of each function of both array list and
     * linked list
     *
     * @param is specifies type of storage to be tested
     */
    private void individualTest(StorageInterface is) {
        System.out.println("Testing " + is.getClass());
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.add((T) (Comparable) 2);
        System.out.println("Should be \n\ttrue\n\t" + is.addAll(new
                Comparable[]{6, 4, 8}));
        System.out.println("Should be\n\t4\n\t" + is.size());
        System.out.println("Should be\n\t2, 6, 4, 8}\n\t" + is);
        System.out.println("Should be\n\tfalse, should throw exceptions " +
                "twice\n\t" +
                is.addAll(0,
                        (new Comparable[]{5, 6, 7})));
        System.out.println("Should be\n\t{2, 6, 4, 8}\n\t" + is);
        is.sort();
        System.out.println();
        is.clear();
        is.addAll(new Comparable[]{12, 14, 16, 18, 20, 22});
        System.out.println("Should be\n\t{12,14,16,18,20,22}\n\t" + is);
        is.add(1, 24);
        is.add(3, 24);
        System.out.println("Should be\n\t{12, 24, 14, 24, 16, 18, 20, " +
                "22}\n\t" + is);
        System.out.println("Should be\n\t1\n\t" + is.indexOf(24));
        System.out.println("Should be\n\t3\n\t" + is.lastIndexOf(24));
        System.out.println("Trying to remove element at index " +
                "24\nShould throw exception\n\t");
        is.remove(24);
        System.out.println("Trying to remove element at index 1");
        is.remove(1);
        System.out.println("Should be\n\t{12,14,24,16,18,20,22}\n\t" + is);
        System.out.println("Should be\n\tfalse\n\t" + is.contains(7));
        System.out.println("Should be\n\ttrue\n\t" + is.contains(14));
        System.out.println("Should be\n\tfalse\n\t" + is.containsAll(
                new Comparable[]{14, 5, 16, 18}));
        System.out.println("Should be\n\ttrue\n\t" + is.containsAll(
                new Comparable[]{14, 22, 20}));
        System.out.println("Should be\n\t126\n\t" + is.hashCode());
        System.out.println(is);
        System.out.println("Should be\n\tfalse\n\t" + is.removeAll
                (new Comparable[]{7, 18, 22}));
        is.sort();
        System.out.println("Should be\n\t{12,14,16,18,20,22,24}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.removeAll
                (new Comparable[]{16, 18, 20}));
        System.out.println("Should be\n\t{12, 14, 22, 24}\n\t" + is);
        is.clear();
        System.out.println("Should be\n\tfalse\n\t" + is.contains(null));
        System.out.println("should be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should be\n\t0\n\t" + is.hashCode());
        is.add(null);
        System.out.println("Should be\n\t{null}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.contains(null));
        System.out.println("Testing set method: should throw exception");
        is.set(5,2);
        System.out.println("Testing get method: should throw exception");
        is.get(5);
    }
    private void StringTest(StorageAbstract is){
        System.out.println("Testing " + is.getClass());
        System.out.println("Should be\n\t0\n\t" + is.size());
        is.add("ab");
        System.out.println("Should be \n\ttrue\n\t" + is.addAll(new
                Comparable[]{"defg","hijk","lmno"}));
        System.out.println("Should be\n\t4\n\t" + is.size());
        System.out.println("Should be\n\t9610791\n\t" + is.hashCode());
        System.out.println("Should be\n\tab,defg,hijk,lmno}\n\t" + is);
        System.out.println("Should be \n\tfalse\n\t" + is.addAll(new
                Comparable[]{"xyz","uvwz"}));
        System.out.println("Should be \n\tfalse\n\t" + is.addAll(2,new
                Comparable[]{"xyz","uvwz"}));
        System.out.println("Should be \n\tfalse\n\t"+is.removeAll
                (new Comparable[]{"lll,ab"}));
        System.out.println("Testing set method: should throw exception");
        is.set(6,"jkjk");
        System.out.println("Testing get method: should throw exception");
        is.get(6);
        is.clear();
        System.out.println("Should be\n\tfalse\n\t" + is.contains(null));
        System.out.println("should be\n\tempty\n\t" + is.isEmpty() + " : " + is);
        System.out.println("Should be\n\t0\n\t" + is.hashCode());
        is.add(null);
        System.out.println("Should be\n\t{null}\n\t" + is);
        System.out.println("Should be\n\ttrue\n\t" + is.contains(null));

    }
    /**
     * To cast the two linked list and array list to storage type and call
     * the individual testing function
     *
     * @param args none
     */
    public static void main(String[] args) {

        StorageTest obj = new StorageTest();
        System.out.println("Testing for Integers");
        System.out.println("----------------------------");
        StorageAbstract ill = new LinkedList<Integer>();
        obj.individualTest(ill);
        System.out.println("----------------------------");
        StorageAbstract ial = new ArrayList<Integer>();
        obj.individualTest(ial);
        System.out.println("----------------------------");
        System.out.println("Testing for Strings");
        StorageAbstract iel = new LinkedList<String>();
        obj.StringTest(iel);
        System.out.println("----------------------------");
        StorageAbstract icl = new ArrayList<String>();
        obj.StringTest(icl);
        System.out.println("----------------------------");
        System.out.println("Should be\n\ttrue\n\t" + ill.contentEquals(ial));
        ial.add(2);
        System.out.println("----------------------------");
        System.out.println("Should be\n\tfalse\n\t" + ill.contentEquals(ial));
        System.out.println("----------------------------");
        ill.add(2);
        System.out.println("Should be\n\ttrue\n\t" + ill.contentEquals(ial));

    }

}