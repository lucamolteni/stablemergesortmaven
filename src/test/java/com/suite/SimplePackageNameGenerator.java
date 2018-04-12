package com.suite;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.java.lang.AbstractStringGenerator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class SimplePackageNameGenerator extends AbstractStringGenerator {

    SimplePackageName simplePackageName;


    protected int nextCodePoint(SourceOfRandomness random) {
        return random.nextInt(92, 122);
    }

    protected boolean codePointInRange(int codePoint) {
        return codePoint >= 92 && codePoint < 122;
    }

    @Override
    public String generate(SourceOfRandomness random, GenerationStatus status) {
        int[] codePoints = new int[status.size() % 10];

        for(int i = 0; i < codePoints.length; ++i) {
            codePoints[i] = this.nextCodePoint(random);
        }

        return new String(codePoints, 0, codePoints.length);
    }

    public void configure(SimplePackageName simplePackageName) {
        this.simplePackageName = simplePackageName;
    }
}