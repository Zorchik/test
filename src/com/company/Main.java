package com.company;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Map<String, Set<String>> results = Collections.synchronizedMap(new HashMap<>());
        WorkWithFiles workWithFiles = new WorkWithFiles();

        System.out.println("Enter file names separated by ';' ");
        ArrayList<String> files = new ArrayList<>(Arrays.asList(new Scanner(System.in).nextLine().split(";")));
        try {
            for (String file : files) {
                executorService.submit(new ParseFile(file, results));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        workWithFiles.writeToFiles(results);
    }
}
