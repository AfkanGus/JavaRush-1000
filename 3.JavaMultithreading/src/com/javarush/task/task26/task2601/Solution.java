package com.javarush.task.task26.task2601;

import java.lang.reflect.Array;
import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        Integer median;
        if (array.length % 2 == 0){
            median = ((array[array.length / 2] + array[array.length / 2 - 1]) / 2);
        }else {
        median = array[array.length/2];}
        List<Integer> li= Arrays.asList(array);
        Comparator<Integer> compareByMedian = Comparator.comparingInt(o -> Math.abs(median - o));
        li.sort(compareByMedian);
        return array;
    }
}
