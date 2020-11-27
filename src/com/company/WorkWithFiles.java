package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkWithFiles {
    public WorkWithFiles() {

    }

    public ArrayList<String> readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().collect(Collectors.toCollection(ArrayList::new));
        } catch (FileNotFoundException ex) {
            System.out.println("File " + fileName + " not found! Please check the file name you entered.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToFiles(Map<String, Set<String>> results) {
        for (String name : results.keySet()) {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name, true), StandardCharsets.UTF_8))) {
                for (String value : results.get(name)) {
                    bw.write(value + ";");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
