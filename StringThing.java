/*
 * Classname:GravityCalculator.java
 *
 * Version information:1
 *
 * Date:26/01/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */

/**
 * Differnt possible reasons as to when two strings match and do not match
 * along with their explanations.
 */
public class StringThing
{
    /**
     *
     * @param a
     * @param b
     * @return
     */

    /**
     * Method equalityTest1 is testing to check if String a and String b lie in
     * the same
     * memory intern pool.
     * @param a
     * @param b
     * @return
     */
    private static boolean equalityTest1(String a, String b)
    {
        return a == b;
    }

    /**
     * Method equalityTest2 is testing to check if String a and String b are
     * equal character-by character. They may or may not be in the same
     * intern pool, but are the same string when checked one character at a
     * time.
     * @param a
     * @param b
     * @return
     */
    private static boolean equalityTest2(String a, String b)
    {
        return a.equals(b);
    }

    /**
     * In method equalityTest3 we use the System.identityHashCode. Now System
     * .identityHashCode returns the same hash value for strings in the same
     * intern pool and will return a different hash value, even if the string
     * might be the same, but is stored in a different intern pool.
     * @param a
     * @param b
     * @return
     */
    private static boolean equalityTest3(String a, String b)
    {
        return System.identityHashCode(a) == System.identityHashCode(b);
    }

    /**
     * In Method equalityTest4
     * Check 1: ( Stopping Condition for recursion)If the length of both strings
     * is equal to 0; return True as
     * both the strings do not exist and by default are equal.
     * Check 2: ( Stopping Condition for recursion) If the length of both isn't
     * 0, but one string is empty then again by default they are NOT EQUAL
     * and hance we return False.
     * Check 3: We check if the first character of either string is equal. If
     * the first character of either of the strings is equal we recursivley
     * run the method equalityTest4. The substring is taken from 1 upto the
     * end , but the substring is geneated at run time.
     *
     * This would give true if two string literals were equal as internally it
     * is testing character by character and is similar to the implementation
     * f the .equals function.
     * @param a
     * @param b
     * @return
     */
    private static boolean equalityTest4(String a, String b)
    {
        if(a.length() == 0 && b.length() == 0)
        {
            return true;
        }
        else
        {
            if(a.length() == 0 || b.length() == 0)
            {
                return false;
            }
            if(a.charAt(0) == b.charAt(0))
            {
                return equalityTest4(a.substring(1), b.substring(1));
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * In the method equalityTest5 even though is a funtion of class Object
     * and is run during runtime, interally hashCode uses .equals function
     * and two equal strings will return equal hashCodes.
     * @param a
     * @param b
     * @return
     */
    private static boolean equalityTest5(String a, String b)
    {
        return a.hashCode() == b.hashCode();
    }

    public static void main(String[] args)
    {
        StringThing obj = new StringThing();
        String abcV1 = "abc";
        String abcV2 = "a" + "b" + "c";
        String abcV3 = "abcd".substring(0, abcV1.length());
        String abcV4 = "" + abcV2;
        String abcV5 = "a" + (char)98 + 99;
        String abcV6 = new String("abc");
        String abcV7 = abcV3.intern();
        String abcv8 = abcV6;

        // using abcV1 as first parameter ...
        // all possible trues for equalityTest1
        System.out.println("Test 1");
        // Result : TRUE
        // Reason : When we compare "abc" with  "a"+"b"+"c", concatanation of
        // string literals happens during compile time. Thus giving us the
        // result true. It sees that during compile time abcV2 is "abc" and
        // checks the intern pool of the string to know that another
        // reference to the string "abc" exists and since we can have only one
        // reference inside the intern pool it puts abcV2 in the same intern
        // pool and thus giving us TRUE.
        System.out.println("2."+obj.equalityTest1(abcV1,abcV2));
        /*
        Result : False
        Reason : The substring function is a function of the object class.
        This function is evaluated during run time. Thus even though length
        of abcv1 is  3 and substring of "abcd" from(0,3) results in abc (a=0,
        b=1,c=2) we will not evaluate to true, rather evaluate to FALSE.
         */
        System.out.println("3."+obj.equalityTest1(abcV1,abcV3));
        /*
        Result = False
        Reason = The concatination of a string variable happens during
        runtime, contrary to concatination of string literals that happen
        during compile time. Thus we can ensure that during runtime a
        different memory location is assigned to it. When looked the two
        strings are not found in the same intern pool giving the result False.
         */
        System.out.println("4."+obj.equalityTest1(abcV1,abcV4));
        /*
        Result = False
        Reason : Here the string formed is ab99 which means that char was not
         abc hence giving False
         */
        System.out.println("5"+obj.equalityTest1(abcV1,abcV5));
        /*
        Result : False
        Reason : A new object is created and when a new object is created a
        new memory refernece is created and is thus placed in a different
        memory location. Since == checks for memory location it gives False
        as both strings are present in different memory locations.
         */
        System.out.println("6"+obj.equalityTest1(abcV1,abcV6));
        /*
        Result = True
        Reason = abcV3.inter() returns the string present in the intern pool
        of abcV3, so abcV7 has the string abc. When checked the string exists
         so abcV7's string "abc" is placed in the existing intern pool
         (abcV1). Thus giving us TRUE.
         */

        System.out.println("7"+obj.equalityTest1(abcV1,abcV7));
        /*
        Result : False
        Reason : abcv8 is equated to abcv6 which is put in a different memory
         location as it is a different object. Since it is a different memory
          location = returns False
         */
        System.out.println("8"+obj.equalityTest1(abcV1,abcv8));

        // all possible trues for equalityTest2

        System.out.println("Test 2");
        /*
        Result : True
        Reason : .equals check strings character by character thus since
        abcV1 and abcV2 are equal "a""a", "b"="b", "c"="c" it gives the result
         TRUE
         */
        System.out.println("2."+obj.equalityTest2(abcV1,abcV2));
        /*
        Result = TRUE
        Reason : As we know the result of abcV3 is abc and checking character
         by character we get the result true. There is  no influence of
         compile/ run time here.

         */
        System.out.println("3"+obj.equalityTest2(abcV1,abcV3));
        /*
        Result= True
        As indicated, there is no affect of concatenating during compile
        time or run time. Thus we get the result as true as both strings are abc
         */
        System.out.println("4"+obj.equalityTest2(abcV1,abcV4));
        /*
        Result = False
        As indicated previously ab99 can never be equal to abc
         */
        System.out.println("5"+obj.equalityTest2(abcV1,abcV5));
        /*
        Result = True
        Reason = Here we are checking character by character, being in the
        same memory location or a different memory location doesn't change
        the result.
         */
        System.out.println("6"+obj.equalityTest2(abcV1,abcV6));
         /*
        Result = True
        Reason = abcV3.inter() returns the string present in the intern pool
        of abcV3, so abcV7 has the string abc. When checked the string exists
         so abcV7's string "abc" is placed in the existing intern pool
         (abcV1). Thus giving us TRUE.
         */
        System.out.println("7"+obj.equalityTest2(abcV1,abcV7));
        /*
        Result = TRUE
        Reason = As abcV6 iss true we can say even abcv8 will also be true.
         */
        System.out.println("8"+obj.equalityTest2(abcV1,abcv8));

        // all possible trues for equalityTest3
        System.out.println("Test 3");
        /*
        Result = True
        Reason = abcV1 and abcV2 are present in the same intern pool as abcV2
         has the same string as abcV1 and since the memory pool can not hold
         more than one reference abcV2 is placed there. When the memory
         reference is the same the hashcode generated is equal.
         */
        System.out.println("2"+obj.equalityTest3(abcV1,abcV2));
        /*
        Result=False
        Reason = Substring is found during runtime thus a different memory
        location is identified for it, Sysrem hashcode creates a different
        hashcode for strings in different memory locations.
         */
        System.out.println("3"+obj.equalityTest3(abcV1,abcV3));
        /*
        Result = False
        Reason = Concatination of string variables happens during runtime,
        thus assigning different memory location and inturn geneating a
        different hashcode.
         */
        System.out.println("4"+obj.equalityTest3(abcV1,abcV4));
        /*
        Result = False
        Different strings generate different hash codes
         */
        System.out.println("5"+obj.equalityTest3(abcV1,abcV5));
        /*
        Result = False
        Reason = The new operator creates a new object thus inturn creating a
         new memory refernece and this inturn generating different hashcodes.
         */
        System.out.println("6"+obj.equalityTest3(abcV1,abcV6));
         /*
        Result = True
        Reason = abcV3.inter() returns the string present in the intern pool
        of abcV3, so abcV7 has the string abc. When checked the string exists
         so abcV7's string "abc" is placed in the existing intern pool
         (abcV1). Thus giving us TRUE.
         */
        System.out.println("7"+obj.equalityTest3(abcV1,abcV7));
        /*
        Response = False
        Reason = Placed in memory location of abcV6 and thus generates
        different hash code.
         */
        System.out.println("8"+obj.equalityTest3(abcV1,abcv8));

        // all possible trues for equalityTest4
        System.out.println("Test 4");
        /*
        Result : True
        Reason :  Just like.equals we check strings character by character thus
        since
        abcV1 and abcV2 are equal "a""a", "b"="b", "c"="c" it gives the result
         TRUE

         */
        System.out.println("2"+obj.equalityTest4(abcV1,abcV2));
        /*
        Result = TRUE
        Reason : As we know the result of abcV3 is abc and checking character
         by character we get the result true. There is  no influence of
         compile/ run time here.

         */
        System.out.println("3"+obj.equalityTest4(abcV1,abcV3));
        /*
        Result= True
        As indicated, there is not affect of concatenating during compile
        time or run time. Thus we get the result as true as both strings are
        abc which when checked character by character results TRUE.
         */
        System.out.println("4"+obj.equalityTest4(abcV1,abcV4));
         /*
        Result = False
        As indicated previously ab99 can never be equal to abc when checked
        character by character. It fails in the third recursive call.
         */
        System.out.println("5"+obj.equalityTest4(abcV1,abcV5));
         /*
        Result = True
        Reason = Here we are checking character by character, being in the
        same memory location or a different memory location doesn't change
        the result.
         */
        System.out.println("6"+obj.equalityTest4(abcV1,abcV6));
         /*
        Result = True
        Reason = abcV3.inter() returns the string present in the intern pool
        of abcV3, so abcV7 has the string abc. When checked the string exists
         so abcV7's string "abc" is placed in the existing intern pool
         (abcV1). Thus giving us TRUE.
         */
        System.out.println("7"+obj.equalityTest4(abcV1,abcV7));
        /*
        Result = TRUE
        Reason = As abcV6 iss true we can say even abcv8 will also be true.
         */
        System.out.println("8"+obj.equalityTest4(abcV1,abcv8));

        // all possible trues for equalityTest5
        System.out.println("Test 5");
        /*
        Result= True
        Reason = abcV1 = abc. Hashcods of same strings are equal.
         */
        System.out.println("2"+obj.equalityTest5(abcV1,abcV2));
        /*
        Result=True
        Reason : abcV3 equates to abc and hashcodes of equal strings are equal.
         */
        System.out.println("3"+obj.equalityTest5(abcV1,abcV3));
        /*
        Result= True
        As indicated, there is not affect of concatenating during compile
        time or run time. Thus we get the result as true as both strings are
        abc. Hash codes of same strings are equal.
         */
        System.out.println("4"+obj.equalityTest5(abcV1,abcV4));
        /*
        Result = False
        As indicated previously ab99 can never be equal to abc. Two
        different strings have different hash codes.
         */
        System.out.println("5"+obj.equalityTest5(abcV1,abcV5));
           /*
        Result = True
        Reason = As mentioned the two strings are equal. Both are abc.
        As hash codes of equal strings are equal irrespective of memory
        locations and object references.
         */
        System.out.println("6"+obj.equalityTest5(abcV1,abcV6));
         /*
        Result = True
        Reason = abcV3.inter() returns the string present in the intern pool
        of abcV3, so abcV7 has the string abc. When checked the string exists
         so abcV7's string "abc" is placed in the existing intern pool
         (abcV1). Thus giving us TRUE.
         */
        System.out.println("7"+obj.equalityTest5(abcV1,abcV7));
        /*
        Result = TRUE
        Reason = As abcV6 iss true we can say even abcv8 will also be true.
        As hash codes of equal strings are equal irrespective of memory
        locations and object references.
         */
        System.out.println("8"+obj.equalityTest5(abcV1,abcv8));

    }
}