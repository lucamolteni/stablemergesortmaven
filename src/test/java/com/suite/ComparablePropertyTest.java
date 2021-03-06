package com.suite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.java.lang.Encoded;
import com.pholser.junit.quickcheck.generator.java.lang.Encoded.InCharset;
import com.redhat.InternalKnowledgePackage;

import static java.lang.Integer.signum;
import static org.junit.Assert.*;

public abstract class ComparablePropertyTest {

    protected abstract Comparator<InternalKnowledgePackage> getComparingMethod();

    @Property(trials = 1000)
    /*
    The implementor must ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. (This implies that x.compareTo(y) must throw an exception iff y.compareTo(x) throws an exception.)
     */
    public void negatedSignComparingNamesAndRules(@From(Encoded.class) @InCharset("ascii") String a,
                                                  @From(Encoded.class) @InCharset("ascii") String b,
                                                  List<@From(Encoded.class) @InCharset("ascii") String> rulesA,
                                                  List<@From(Encoded.class) @InCharset("ascii") String> rulesB
    ) throws Exception {

        Comparator<InternalKnowledgePackage> func = getComparingMethod();

        InternalKnowledgePackage ia = new InternalKnowledgePackage(a, rulesA);
        InternalKnowledgePackage ib = new InternalKnowledgePackage(b, rulesB);

        final int compareAB = func.compare(ia, ib);
        final int compareBA = func.compare(ib, ia);

        assertEquals(signum(compareAB), -signum(compareBA));
    }

    @Property(trials = 1000)
    /*
    The implementor must also ensure that the relation is transitive: (x.compareTo(y)>0 && y.compareTo(z)>0) implies x.compareTo(z)>0.
     */
    public void transitiveProperty(@From(Encoded.class) @InCharset("ascii") String a,
                                   @From(Encoded.class) @InCharset("ascii") String b,
                                   @From(Encoded.class) @InCharset("ascii") String c,
                                   @InRange(min = "0", max = "100") Integer rulesA,
                                   @InRange(min = "0", max = "100") Integer rulesB,
                                   @InRange(min = "0", max = "100") Integer rulesC
    ) throws Exception {

        Comparator<InternalKnowledgePackage> func = getComparingMethod();

        InternalKnowledgePackage ia = new InternalKnowledgePackage(a, collectionOfSize(rulesA));
        InternalKnowledgePackage ib = new InternalKnowledgePackage(b, collectionOfSize(rulesB));
        InternalKnowledgePackage ic = new InternalKnowledgePackage(c, collectionOfSize(rulesC));

        if ((func.compare(ia, ib)) > 0 && (func.compare(ib, ic) > 0)) {

//            System.out.println("ia = " + ia);
//            System.out.println("ib = " + ib);
//            System.out.println("ic = " + ic);
            assertTrue(func.compare(ia, ic) > 0);
        }

        assertTrue(true);
    }

    @Property(trials = 1000)
    /*
    Finally, the implementor must ensure that x.compareTo(y)==0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
     */
    public void equalsSignComparingEqualsElements(@From(SimplePackageNameGenerator.class) @SimplePackageName String a,
                                                  @From(SimplePackageNameGenerator.class) @SimplePackageName String b,
                                                  @From(SimplePackageNameGenerator.class) @SimplePackageName String c,
                                                  @InRange(min = "0", max = "5") Integer rulesA,
                                                  @InRange(min = "0", max = "8") Integer rulesB,
                                                  @InRange(min = "0", max = "2") Integer rulesC
    ) throws Exception {

        Comparator<InternalKnowledgePackage> func = getComparingMethod();

        InternalKnowledgePackage ia = new InternalKnowledgePackage(a, collectionOfSize(rulesA));
        InternalKnowledgePackage ib = new InternalKnowledgePackage(b, collectionOfSize(rulesB));
        InternalKnowledgePackage ic = new InternalKnowledgePackage(c, collectionOfSize(rulesC));

        if ((func.compare(ia, ib)) == 0) {

            final int signIAIC = signum(func.compare(ia, ic));
            final int signIBIC = signum(func.compare(ib, ic));
            if (signIAIC != signIBIC) {
                System.out.println("ia = " + ia);
                System.out.println("ib = " + ib);
                System.out.println("ic = " + ic);
                System.out.println("signIAIC = " + signIAIC);
                System.out.println("signIBIC = " + signIBIC);
            }
            assertEquals(signIAIC, signIBIC);
        }

        assertTrue(true);
    }

    private Collection<String> collectionOfSize(Integer i) {
        final List<String> coll = new ArrayList<>();

        for (int k = 0; k < i; k++) {
            coll.add("randomRule" + k);
        }

        return coll;
    }
}