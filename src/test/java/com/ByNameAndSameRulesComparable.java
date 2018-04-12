package com;

import java.util.Comparator;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.redhat.InternalKnowledgePackage;
import com.suite.ComparablePropertyTest;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class ByNameAndSameRulesComparable extends ComparablePropertyTest {

    @Override
    protected Comparator<InternalKnowledgePackage> getComparingMethod() {
        return (p1, p2) -> p1.getRules().isEmpty() || p2.getRules().isEmpty() ? 0 : p1.getName().compareTo(p2.getName());
    }
}
