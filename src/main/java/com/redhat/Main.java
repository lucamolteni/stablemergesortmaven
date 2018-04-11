package com.redhat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // write your code here

        final List<InternalKnowledgePackage> clonedPkgs = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            final List<String> randomRules = new ArrayList<>();

            Random randomGenerator = new Random();

            int randomInt = randomGenerator.nextInt(10);

            System.out.println("randomInt = " + randomInt);
            for (int k = 0; k < randomInt; k++) {
                randomRules.add("randomRule" + k);
            }

            clonedPkgs.add(new InternalKnowledgePackage("rule " + i, randomRules));
        }

        clonedPkgs.add(new InternalKnowledgePackage("prova1", Collections.emptyList()));
        clonedPkgs.add(new InternalKnowledgePackage("prova2", Collections.emptyList()));

        final Comparator<InternalKnowledgePackage> comparator = (p1, p2) -> p1.getRules().isEmpty() || p2.getRules().isEmpty() ? 0 : p1.getName().compareTo(p2.getName());

        clonedPkgs.sort(comparator);
    }
}
