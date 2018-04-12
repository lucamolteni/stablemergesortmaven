package com;

import java.util.Comparator;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.redhat.InternalKnowledgePackage;
import com.suite.ComparablePropertyTest;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class ByNameComparable extends ComparablePropertyTest {

    @Override
    protected Comparator<InternalKnowledgePackage> getComparingMethod() {
        return Comparator.comparing(InternalKnowledgePackage::getName);
    }
}
