package com.company;

import java.text.Collator;
import java.util.ArrayList;

public class Main {
    public static final int USER_SCROLLS = 1;
    public static final int PAGE_NUMBER = 1000;

    public static void main(String[] args) {
        final ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        System.out.println("generating list");
        for(int i = 0; i < USER_SCROLLS; i++){
            ArrayList<String> stringArrayList = new ArrayList<String>();
                for(int j = 0; j < PAGE_NUMBER; j++) {
                stringArrayList.add(generateRandomString());
            }
            arrayLists.add(stringArrayList);
        }

        System.out.println("performing first sort");
        final SortedArrayList<String> sortedForSortOnSingleInsertion = new SortedArrayList(Collator.getInstance());
        long timeForFirstSolution = performActionAndGetTime(new OnActionListener() {
            @Override
            public void performMe() {
                for(ArrayList<String> arrayList: arrayLists){
                    for(String str: arrayList){
                        sortedForSortOnSingleInsertion.add(str);
                    }
                }
            }
        });

        System.out.println("performing second sort");
        final SortedArrayList<String> sortedForSortOnGroupInsertion = new SortedArrayList(Collator.getInstance());
        long timeForSecondSolution = performActionAndGetTime(new OnActionListener() {
            @Override
            public void performMe() {
                for(ArrayList<String> arrayList: arrayLists){
                   sortedForSortOnGroupInsertion.addAll(arrayList);
                }
            }
        });


        System.out.println("time for single insertion solution=" + timeForFirstSolution);
        System.out.println("time for group insertion solution="+ timeForSecondSolution);
    }

    private static String generateRandomString() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    public static long performActionAndGetTime(OnActionListener onActionListener){
        long startTime = System.currentTimeMillis();
        onActionListener.performMe();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}