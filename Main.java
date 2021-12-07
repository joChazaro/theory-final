package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        File text = new File("C:/Users/roberto/IdeaProjects/TheoryFinalPart1/src/com/company/example_dfa.txt");
        Scanner line = new Scanner(text);
        int count = 0;
        ArrayList<String> santa = new ArrayList<>();
        ArrayList<String> claus = new ArrayList<>();
        while(line.hasNextLine()) { // adds all rows from the file into an array list
            santa.add(line.nextLine());
            count++;
        }
        System.out.println("Input List: " + santa);
        for (int i = 1; i < count; i++) { // gets rid of repeating and only 1 F states
            String x = santa.get(i).substring(0, santa.get(i).indexOf(" ")); // get current line, then first token from that line

            for (int j = 1; j < count; j++) {
                String y = santa.get(j).substring(0, santa.get(j).indexOf(" "));

                String jingle = y + "-" + x;
                String bells = x + "-" + y;
                if (!x.equalsIgnoreCase(y)) {
                    if (y.contains("F") && x.contains("F") && !claus.contains(jingle) && !claus.contains(bells)) {
                        claus.add(bells);
                    } else if (!claus.contains(jingle) && !claus.contains(bells) && !bells.contains("F")) {
                        claus.add(bells);
                    }
                }
            }
        }
        ArrayList<String> removeList = new ArrayList<>();
        for (String dasher : claus) { // gets rid of all states that lead to a single finish state. If both states
            String[] holder = dasher.split("-"); // lead to only finish states, then keep
            String temp1 = holder[0];
            String temp2 = holder[1];

            for (int j = 1; j < santa.size(); j++) {
                String[] santaHolder = santa.get(j).split(" ");
                if (santaHolder[0].contains(temp1)) { // if first one matches
                    if (!(santaHolder[1].contains("F") && santaHolder[2].contains("F"))) {
                        if (santaHolder[1].contains("F") || santaHolder[2].contains("F")) {
                            removeList.add(dasher);
                        }
                    }
                } else if (santaHolder[0].contains(temp2)) { // if first one matches
                    if (!(santaHolder[1].contains("F") && santaHolder[2].contains("F"))) {
                        if (santaHolder[1].contains("F") || santaHolder[2].contains("F")) {
                            removeList.add(dasher);
                        }
                    }
                }
            }
        }
        for (String item : removeList) {
            claus.remove(item);
        }
        System.out.println("Shrunken DFA: " + claus);

    }
}
