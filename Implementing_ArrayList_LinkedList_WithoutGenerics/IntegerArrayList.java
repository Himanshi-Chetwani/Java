
/*
 * Classname:IntegerArrayList.java
 *
 * Version information:1
 *
 * Date:02/17/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */

/**
 * This class is used to implement the array list using the concepts of
 * inheritence
 */
public class IntegerArrayList extends IntegerStorageImplementation{

    public int[] storage;
    private int capacity=0;
    private int initialCapacity=0;

    /**
     * Function used to increase capacity
     * @param array_capacity
     * @param index
     * @param element
     * @return The increased array
     */
    public int increaseCapacity(int array_capacity,int index,int element){
        int [] temp;
        int [] temp1=new int[capacity];
        temp=storage;
        capacity=array_capacity;
        storage=new int[capacity];
        int loop;
        if(temp==null|| size == 0){
            storage[0]=element;
            capacity=1;
        }
        else if(index<(capacity-1)){
            for (int loop2 = 0; loop2 < temp.length ; loop2++) {
                temp1[loop2] = temp[loop2];
            }
            temp1[index]=element;
            for(int loop1=index;loop1<capacity-1;loop1++){
                temp1[loop1+1]=temp[loop1];
            }
            storage=temp1;
        }
        else {
            for (loop = 0; loop < temp.length ; loop++) {
                storage[loop] = temp[loop];
            }
        }
        return capacity;
    }

    /**
     * To add a new element to the array list
     * @param o
     * @return true if added
     */
    @Override
    public boolean add(int o) {
        initialCapacity = size;
        capacity=initialCapacity+1;
        capacity=increaseCapacity(capacity,capacity,o);
        storage[capacity-1]=o;
        size++;
        return true;
    }

    /**
     * To add element to array list at given index
     * @param index
     * @param element
     */
    @Override
    public void add(int index, int element) {
        int temp;
        initialCapacity=size;
        capacity=size+1;
        capacity=increaseCapacity(capacity,index,element);
        size++;
    }

    /**
     * To clear the array list and make size 0
     */
    @Override
    public void clear() {
        size=0;
        storage=new int[size];

    }

    /**
     * To check if the element is contained in the array
     * @param o
     * @return true if contained; else false
     */
    @Override
    public boolean contains(int o) {
        boolean flag=false;
        if(storage==null)
            flag=false;
        else {
            for (int loop = 0; loop < storage.length; loop++)
                if (storage[loop] == o) {
                    flag = true;
                    break;
                }
                else
                    flag = false;
        }
        return flag;
    }

    /**
     * To return element at given index
     * @param index
     * @return element at index
     */
    @Override
    public int get(int index) {
       return storage[index];
    }

    /**
     * First occurence of integer
     * @param o
     * @return index of First occurence of integer
     */
    @Override
    public int indexOf(int o) {
        int index=-1;
        for(int loop=0;loop<storage.length-1;loop++){
            if(storage[loop]==o){
                index=loop;
                break;
            }
        }
        return index;
    }

    /**
     * Last occurence of integer
     * @param o
     * @return index of First occurence of integer
     */
    @Override
    public int lastIndexOf(int o) {
        int index=-1;
        for(int loop=storage.length-1;loop>=0;loop--){
            if(storage[loop]==o){
                index=loop;
                break;
            }
        }
        return index;
    }

    /**
     * To reduce capacity when removing element
     * @param array_capacity
     * @param index
     */
    public void reduceCapacity(int array_capacity,int index) {
        int temp[];
        temp = storage;
        int[] temp1 = new int[array_capacity];
        int[] temp2 = new int[array_capacity+1];

        if (index == array_capacity ) {
            for (int loop2 = 0; loop2 < temp1.length; loop2++) {
                temp1[loop2] = temp[loop2];
            }
            storage=temp1;
        } else if (index == 0) {
            for (int loop2 = 1; loop2 < temp.length; loop2++) {
                temp1[loop2-1] = temp[loop2];
            }
            storage=temp1;

        } else {
            for (int loop = 0; loop <temp.length -1 ; loop++) {
                temp1[loop ] = temp[loop];
            }
            for (int loop = 0; loop <temp.length  ; loop++) {
                temp2[loop ] = temp[loop];
            }
            for (int loop = index + 1; loop <temp.length  ; loop++) {
                temp1[loop - 1] = temp2[loop];
            }

            storage=temp1;
        }




    }

    /**
     * To remove element at index
     * @param index
     * @return element removed
     */
    @Override
    public int remove(int index) {
        capacity=size-1;
        int element=storage[index];
        reduceCapacity( capacity, index);
        size--;
        return element;
    }

    /**
     * To remove element
     * @param o
     * @return true if removed else false
     */
    @Override
    public boolean remove(Integer o) {
        int index=-1;
        boolean flag=false;
        for(int loop=0;loop<storage.length;loop++){
            if(storage[loop]==o){
                index=loop;
                flag=true;
                break;
            }
        }
        if(flag)
            remove(index);
        return flag;
    }

    /**
     * To assign an element at given index
     * @param index
     * @param element
     * @return older element at said position
     */
    @Override
    public int set(int index, int element) {
        int prev=storage[index];
        storage[index]=element;
        return prev;
    }


}
