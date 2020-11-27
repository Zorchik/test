package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParseFile implements Runnable {
    private final String fileName;
    private final Map<String, Set<String>> results;
    private final WorkWithFiles workWithFiles;

    public ParseFile(String fileName, Map<String, Set<String>> results) {
        this.fileName = fileName;
        this.results = results;
        workWithFiles = new WorkWithFiles();
    }

    public void parse() {
        ArrayList<String> buff = workWithFiles.readFile(fileName);
        if (buff != null && buff.size() != 0) {
            String[] namesOfValues = buff.get(0).split(";");
            for (int i = 1; i < buff.size(); i++) {
                if (!buff.get(i).equals("")) {
                    String[] values = buff.get(i).split(";");
                    for (int j = 0; j < namesOfValues.length; j++) {
                        Set<String> interValues = new HashSet<>();
                        if (results.containsKey(namesOfValues[j])) {
                            interValues = results.get(namesOfValues[j]);
                        }
                        interValues.add(values[j]);
                        results.put(namesOfValues[j], interValues);
                    }
                }
            }
        } else {
            System.out.println("File " + fileName + " is empty!");
        }
        System.out.println();
    }

    @Override
    public void run() {
        parse();
    }
}
