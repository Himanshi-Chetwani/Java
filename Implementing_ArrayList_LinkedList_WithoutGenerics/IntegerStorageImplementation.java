/*
 * Name:
 *  IntegerStorageImplementation.java
 *
 * Version:
 *  1.0
 *
 * Revision:
 *  None
 */

/**
 * Class to implement storage methods common to ArrayLists and LinkedLists
 */
public abstract class IntegerStorageImplementation implements IntegerStorage {
    int size;

    /**
     * Add all elements of input array to the system
     * @param os input array to be added to the list
     * @return true
     */
    @Override
    public boolean addAll(int[] os) {
        for (int value = 0; value < os.length; value++) {
            add(os[value]);
        }
        return true;
    }
    /**
     * Add all elements of the input array to a specific position in the system
     * @param index position at which elements should be inserted
     * @param os array of elements to be added
     * @return true
     */
    @Override
    public boolean addAll(int index, int[] os) {
        for (int value = os.length - 1; value >= 0; value--) {
            add(index, os[value]);
        }
        return true;
    }

    /**
     * Check if system contains all elements of input array
     * @param os input array whose elements are to be checked
     * @return false if element is not present else true
     */
    @Override
    public boolean containsAll(int[] os) {
        for(int element: os){
            if(!(contains(element))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two objects contain the same elements in the same order
     * @param o object whose elements are to be compared with current instance
     * @return true if all elements are of same value and in same order
     */

    @Override
    public boolean contentEquals(IntegerStorage o) {
        boolean flag=false;
	if(isEmpty() && o.isEmpty()){
		return true;
	}
        for(int loop=0;loop<size();loop++){
            if(get(loop)==o.get(loop))
                flag=true;
            else
                flag=false;
        }
        return flag;
    }

    /**
     * Checks if the list is empty
     * @return true is list is empty
     */
    @Override
    public  boolean isEmpty(){
        if(size()==0)
            return true;
        else
            return false;
    }

    /**
     * Remove from the list the elements in the specified array
     * @param os input array whose elements are to be removed from the list
     * @return true if elements are removed from the list
     */
    @Override
    public boolean removeAll(int[] os) {
	boolean flag = false;
        for(int loopVariable: os){
	    if(contains(loopVariable)){
            	remove((Integer)loopVariable);
		flag = true;
        	}
	}
        return flag;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Sort the elements in ascending order
     */
    @Override
    public void sort() {
     int temp,temp2;
        for (int loop =0;loop<size-1;loop++){
            for (int loop2=0;loop2<size-loop-1;loop2++){
                if(get(loop2)>get(loop2+1)){
                    temp=get(loop2);
                    temp2=get(loop2+1);
                    set(loop2,temp2);
                    set(loop2+1,temp);
                }
            }
        }

    }

    /**
     * Calculates hash code of the list.
     * @return Sum of all elements
     */
    @Override
    public int	hashCode(){
        int sum=0;
        for(int loop=0;loop<size;loop++){
            sum+=get(loop);
        }
        return sum;
    }

    /**
     * To generate a string representation of the list
     * @return string representation
     */
    public String toString(){
        String result="{ ";
        for(int loop=0;loop<size;loop++){
            result+=get(loop);
            result+=" ";
        }
        result+="}";
        return result;


    }

    /**
     * Checks if two objects hold the same memory reference
     * @param o object to be compared against
     * @return true if both objects hold same memory reference
     */
    public boolean equals(Object o){
        boolean flag=false;
        if(this==o)
            return true;
        else
            return false;
    }

}
