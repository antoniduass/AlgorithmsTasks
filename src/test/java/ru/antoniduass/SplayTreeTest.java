package ru.antoniduass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SplayTreeTest {
    @Test
    void testPrint1() {
        SplayTree test1 = new SplayTree();

        test1.print();

        Assertions.assertEquals("_", test1.print());
    }
    @Test
    void testPrint2() {
        SplayTree test2 = new SplayTree();

        SplayTree.Entry min = test2.min();

        Assertions.assertEquals("_", test2.print());
    }
    @Test
    void testPrint3() {
        SplayTree test3 = new SplayTree();

        test3.add(95, "95");
        test3.add(12, "12");
        test3.add(88, "88");
        test3.add(62, "62");

        Assertions.assertEquals("[62 62]\n[12 12 62] [88 88 62]\n_ _ _ [95 95 88]", test3.print());
    }
    @Test
    void testPrint4() {
        SplayTree test4 = new SplayTree();

        test4.search(1);

        Assertions.assertEquals("_", test4.print());
    }
    @Test
    void testPrint5() {
        SplayTree test5 = new SplayTree();

        test5.delete(8);

        Assertions.assertEquals("_", test5.print());
    }
    @Test
    void testPrint6() {
        SplayTree test6 = new SplayTree();

        test6.add(1,"0");
        test6.add(10,"0");
        test6.add(5,"0");
        test6.add(43,"0");
        test6.add(2,"0");
        test6.delete(0);
        test6.search(1);
        test6.search(99);
        test6.add(12,"0");
        test6.add(4,"0");
        test6.set(5, "1");
        test6.add(11,"0");
        test6.max();
        test6.add(6,"0");

        Assertions.assertEquals("[6 0]\n[5 1 6] [43 0 6]\n[4 0 5] _ [11 0 43] _\n[1 0 4] _ _ _ [10 0 11] [12 0 11] _ _\n_ [2 0 1] _ _ _ _ _ _ _ _ _ _ _ _ _ _", test6.print());
    }
    @Test
    void testPrint7() {
        SplayTree test7 = new SplayTree();

        test7.add(1,"0");
        test7.add(1,"0");
        test7.add(2,"0");
        test7.add(5,"0");

        Assertions.assertEquals("[5 0]\n[2 0 5] _\n[1 0 2] _ _ _\n[1 0 1] _ _ _ _ _ _ _", test7.print());
    }
    @Test
    void testPrint8() {
        SplayTree test8 = new SplayTree();

        test8.add(2,"0");
        test8.add(2,"0");
        test8.add(1,"0");
        test8.add(5,"0");
        test8.delete(2);

        Assertions.assertEquals("[2 0]\n[1 0 2] [5 0 2]", test8.print());
    }
    @Test
    void testMin() {
        SplayTree test9 = new SplayTree();
        SplayTree.Entry treeMin;

        test9.add(2, "0");
        test9.add(43, "0");
        test9.add(55, "0");
        test9.add(25, "0");
        test9.add(15, "0");
        treeMin = test9.min();
        test9.print();
        Assertions.assertEquals("[2 0]\n_ [15 0 2]\n_ _ _ [25 0 15]\n_ _ _ _ _ _ _ [55 0 25]\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ [43 0 55] _", test9.print());
    }
}