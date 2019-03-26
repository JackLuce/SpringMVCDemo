package com.util;

public  class ConvertUtil {
    /**
     * 字符串数组转int数组
     * @param strArr
     * @return
     */
    public static int[] stringArrToIntArr(String [] strArr){
        int [] intArr = new int[strArr.length];
        for (int i = 0;i<strArr.length;i++){
            intArr [i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    /**
     * 字符串数组转double数组
     * @param strArr
     * @return
     */
    public static Double[] stringArrToDoubleArr(String [] strArr){
        Double [] doubleArr = new Double[strArr.length];
        for (int i = 0;i<doubleArr.length;i++){
            doubleArr [i] = Double.parseDouble(strArr[i]);
        }
        return doubleArr;
    }

}