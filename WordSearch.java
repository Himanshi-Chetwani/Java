/*
 * Classname:WordSearch.java
 *
 * Version information:1
 *
 * Date:03/02/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */

import java.io.File; //File IO
import java.util.Scanner; //Scanner class fnctions
import java.util.regex.Matcher; //Reg ex matchinf
import java.util.regex.Pattern; //Reg ex pattern compilation

/**
 * Usinng Reg ex to solve a word uzzle which finds words, horizontally,
 * vertically, diagonaaly both forwards and backwards
 */
public class WordSearch {
    public static int row_count = 0;//Total size of n*n puzlle where n=rowcount
    public static boolean vertf=false; // To decide whether to display o/p as
    // row or column

    /**
     * Loops diagonlly below primary diagonal to find out the string
     * @param myArray
     * @return : New array with diagonally concatenated strings
     */
    public static String[] loopDiagonally1(String[] myArray){
        String [] diagonalString = new  String[row_count];
        for (int i = myArray.length - 1; i >= 0; i--) {
            String temp = "";
            for (int j = 0, x = i; x <= myArray.length - 1; j++, x++) {
                temp = temp+myArray[x].charAt(j);
            }
            if(temp!=null) {
                diagonalString[i] = temp;
            }
        }

        return diagonalString;
    }
    /**
     * Loops diagonlly above primary diagonal to find out the string
     * @param myArray
     * @return : New array with diagonally concatenated strings
     */
    public static String[] loopDiagonally2(String[] myArray){
        String [] diagonalString2 = new  String[row_count];
        for (int i = myArray.length - 1; i >= 0; i--) {
            String temp = "";
            for (int j = 0, x = i; x <= myArray.length - 1; j++, x++) {
                temp = temp + myArray[j].charAt(x);
            }
            if (temp!=null) {
                diagonalString2[i] = temp;
            }
        }

        return diagonalString2;
    }
    /**
     * Loops diagonlly below primary diagonal to find out the string in the
     * other direction
     * @param myArray
     * @return : New array with diagonally concatenated strings
     */
    public static String[] loopDiagonally3(String[] myArray){
        String [] diagonalString3 = new  String[row_count];
        for (int i = myArray.length - 1; i >= 0; i--) {
            String temp = "";
            for (int j = myArray.length-1, x = i; x <=myArray.length-1; j--
                    , x++) {
                temp = temp+myArray[x].charAt(j);
            }
            if(temp!=null) {
                diagonalString3[i] = temp;
            }
        }

        return diagonalString3;
    }
    /**
     * Loops diagonlly above primary diagonal to find out the string in the
     * other direction
     * @param myArray
     * @return : New array with diagonally concatenated strings
     */
    public static String[] loopDiagonally4(String[] myArray){
        String [] diagonalString4 = new  String[row_count];
        for (int i =0; i < myArray.length; i++) {
            String temp = "";
            for (int j = 0, x = i; x >=0; j++
                    , x--) {
                temp = temp+myArray[j].charAt(x);
            }
            if(temp!=null) {
                diagonalString4[i] = temp;
            }
        }

        return diagonalString4;
    }
    /**
     * Loops vertically  to find out the string vertically
     * @param myArray
     * @return : New array with vrtically concatenated strings
     */
    public static String[] VerticalStrings(String[] myArray){
        String [] verticalString = new String[row_count];
        for (int index=0;index<myArray.length;index++){
            String temp="";
            for(int index1=0;index1<myArray.length;index1++)
            {
                temp=
                        temp+myArray[index1].charAt(index);
                verticalString[index]=temp;

            }

        }
        return verticalString;
    }

    /**
     * To check the word in reverse
     * @param word
     * @return reverse word
     */
    public static  String reverse_word( String word){
        String reverse="";
        for(int index=word.length()-1;index>=0;index--)
        {
            reverse=reverse+word.charAt(index);
        }
        return reverse;

    }

    /**
     * To check if the pattern is present in the array
     * @param regex
     * @param line
     * @return String matches reg ex or not
     */
    public static  Boolean testThePattern (String regex, String line){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(line);
        Boolean found = matcher.find();
        return found;
    }

    /**
     * The real operation of findinf if the string is present in the puzzle
     * or not
     * @param myArray
     * @param regular_Expression
     * @param word
     * @return : Prsent or not
     */
    public static boolean perform_operation(String[] myArray,
                                         String regular_Expression,
                                         String word){
        Boolean result = false;
        for (int index = 0; index < myArray.length; index++) {
            result = testThePattern(regular_Expression, myArray[index]);

            if (result) {
                if(!vertf) {
                   System.out.println("Found at row " + index);
                }
                else
                    System.out.println("Found at column " + index);
                result=true;
                break;
            }
        }
        return  result;
    }

    /**
     * To craete a reg ex for all conditions
     * @param myArray
     * @param word
     */
    public static void create_pattern(String[] myArray,String word) {
        String regular_Expression = "";

        regular_Expression = ".*" + word + ".*";
        String []  verticalString;
        String [] loopDiagonally1;
        String [] loopDiagonally2;
        String [] loopDiagonally3;
        String [] loopDiagonally4;
        boolean result=perform_operation(myArray,regular_Expression,word);
        if(!result) {
            regular_Expression = ".*" + reverse_word(word) + ".*";
            result=perform_operation(myArray, regular_Expression,
                    reverse_word(word));
        }
        if(!result){
            verticalString = VerticalStrings(myArray);
            vertf=true;
            regular_Expression=".*"+word+".*";
            result=perform_operation(verticalString,regular_Expression, word);
        }
        if(!result){
            verticalString = VerticalStrings(myArray);
            vertf=true;
            regular_Expression=".*"+reverse_word(word)+".*";
            result=perform_operation(verticalString,regular_Expression, word);
        }
        vertf=false;
        if(!result){
            loopDiagonally1=loopDiagonally1(myArray);
            regular_Expression=".*"+word+".*";
            result=perform_operation(loopDiagonally1,regular_Expression,word);

        }
        if(!result){
            loopDiagonally1=loopDiagonally1(myArray);
            regular_Expression=".*"+reverse_word(word)+".*";
            result=perform_operation(loopDiagonally1,regular_Expression,word);

        }
       if(!result){
            loopDiagonally2=loopDiagonally2(myArray);
            regular_Expression=".*"+word+".*";
            result=perform_operation(loopDiagonally2,regular_Expression,word);

        }
        if(!result){
            loopDiagonally2=loopDiagonally2(myArray);
            regular_Expression=".*"+reverse_word(word)+".*";
            result=perform_operation(loopDiagonally2,regular_Expression,word);

        }
        if(!result){
            loopDiagonally3=loopDiagonally3(myArray);
            regular_Expression=".*"+word+".*";
           result=perform_operation(loopDiagonally3,regular_Expression,word);

        }
        if(!result){
            loopDiagonally3=loopDiagonally3(myArray);
            regular_Expression=".*"+reverse_word(word)+".*";
            result=perform_operation(loopDiagonally3,regular_Expression,word);

        }
        if(!result){
            loopDiagonally4=loopDiagonally4(myArray);
            regular_Expression=".*"+word+".*";
            result=perform_operation(loopDiagonally4,regular_Expression,word);

        }
        if(!result){
            loopDiagonally4=loopDiagonally4(myArray);
            regular_Expression=".*"+reverse_word(word)+".*";
            result=perform_operation(loopDiagonally4,regular_Expression,word);

        }
        if(!result)
            System.out.println("Not found "+word);
    }

    /**
     * To read the file and word to be found
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = null;
        Scanner sc2= null;
        Scanner scanner = null;
        String file_path = "";
        boolean flag=false;
        do{
            try {
                System.out.println("Enter your file name");
                sc = new Scanner(System.in);
                file_path = sc.nextLine();
                if(file_path.toLowerCase().equals("exit"))
                    System.exit(1);
                sc = new Scanner(new File(file_path));
                flag= sc.hasNext();
            } catch (Exception e) {
                System.err.println("Can't find that file. Try again.");
            }
        }while (!flag);
        System.out.println("Enter the word");
        scanner = new Scanner(System.in);
        String word = scanner.nextLine().toLowerCase();
        if(word.equals("exit"))
            System.exit(2);
        System.out.println("The puzzle is:");
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
            row_count++;
        }
        String [] myArray=new String[row_count];
        try {
             sc2 = new Scanner(new File(file_path));
        }catch (Exception e) {
            System.err.println("Can't find that file. Try again.");
            System.exit(1);
        }
        for(int index=0;index<row_count;index++){
                myArray[index]=sc2.nextLine().toLowerCase();
        }
        create_pattern(myArray,word);
    }

}

