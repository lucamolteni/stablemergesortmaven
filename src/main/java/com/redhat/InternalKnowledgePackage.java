package com.redhat;

import java.util.Collection;

public class InternalKnowledgePackage {

    private final String name;
    private final Collection<String> rules;

    public InternalKnowledgePackage(String name, Collection<String> rules) {
        this.name = name;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public Collection<String> getRules() {
        return rules;
    }

    @Override
    public String toString() {
        return "InternalKnowledgePackage{" +
                "name='" + name + '\'' +
                ", rules=" + rules +
                '}';
    }
}
