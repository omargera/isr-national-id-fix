package com.omar.nationalid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NationalIdFixer {

    public static final int NATIONAL_ID_SIZE = 9;
    public static final List<Integer> VALIDATION_VECTOR = Arrays.asList(1,2,1,2,1,2,1,2,1);

    /**
     * used to reverse the calculation of specialMulti
     */
    private static Map<Integer,Integer> translateMap;
    static {
        translateMap = new HashMap<>();
        translateMap.put(0,0);
        translateMap.put(2,1);
        translateMap.put(4,2);
        translateMap.put(6,3);
        translateMap.put(8,4);
        translateMap.put(1,5);
        translateMap.put(3,6);
        translateMap.put(5,7);
        translateMap.put(7,8);
        translateMap.put(9,9);
    }


    /**
     * this function tries to fix the id that contains 9 digits,
     * it can do so if only one digit is missing
     *
     * @return the fixed id number
     */
    static List<Integer> fixId(List<Integer> integerList) {
        //validating the length of the id
        assert integerList.size() == NATIONAL_ID_SIZE;
        boolean onlyOnedigitIsMissing = validateOnlyOneDigitIsmissing(integerList);
        if (onlyOnedigitIsMissing){
            int missingIndex = locateMissingDigit(integerList);
            Integer missingDigit = calculateMissingDigit(integerList, missingIndex);
            integerList.set(missingIndex, missingDigit);
            assert validateNationalId(integerList);
        }

        return integerList;
    }


    public static boolean validateNationalId(List<Integer> integerList) {
        int totalSum = getTotalSum(integerList);
        return totalSum%10 == 0;
    }

    private static int getTotalSum(List<Integer> integerList) {
        int totalSum = 0;
        for(int i = 0; i<NATIONAL_ID_SIZE ;i++){
            totalSum += specialMulti(VALIDATION_VECTOR.get(i), integerList.get(i));
        }
        return totalSum;
    }

    private static int specialMulti(Integer integer1, Integer integer2) {
        int sum = 0;
        if (integer1 != null && integer2 != null) {
            int mul = integer1 * integer2;
            while (mul != 0)
            {
                sum = sum + mul % 10;
                mul = mul/10;
            }
        }
        return sum;
    }

    private static Integer calculateMissingDigit(List<Integer> integerList, int missingIndex) {
        int multiplier = VALIDATION_VECTOR.get(missingIndex);
        int totalSum = getTotalSum(integerList);
        int missingDig;
        if (multiplier == 1) {
            missingDig = (10 - (totalSum % 10))%10;
        } else {
            missingDig = (10 - (totalSum % 10))%10;
            missingDig = translateMap.get(missingDig);
        }
        System.out.println("integer list "+integerList);
        System.out.println("missing index " + missingIndex);
        System.out.println("mul "+multiplier);
        System.out.println("totalSum "+totalSum);
        System.out.println("missing dig "+missingDig);
        return missingDig;
    }

    /**
     * @return the index of the missing digit if one digit is missing(0-8), -1 otherwise
     */
    private static int locateMissingDigit(List<Integer> integerList) {
        for (int i = 0 ; i < integerList.size(); i++) {
            if (integerList.get(i)==null){
                return i;
            }
        }
        return -1;
    }

    private static boolean validateOnlyOneDigitIsmissing(List<Integer> integerList) {
        int countMissing = 0;
        for (Integer integer: integerList) {
            if (integer==null){
                countMissing++;
            }
        }
        return countMissing == 1;
    }

    public boolean someLibraryMethod() {
        return true;
    }
}
