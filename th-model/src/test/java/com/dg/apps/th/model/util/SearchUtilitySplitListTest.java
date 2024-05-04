package com.dg.apps.th.model.util;

import com.dg.apps.th.model.testonly.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Test {@link ListUtility#splitList(List, int)}.
 */
public class SearchUtilitySplitListTest extends TestBase {
    private final BiFunction<List<Integer>, Integer, List<List<Integer>>> functionUnderTest = ListUtility::splitList;

    @Test
    public void testOne() {
        final List<List<Integer>> result = functionUnderTest.apply(scenario(), 1);

        Assert.assertNotNull(result);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(9, result.get(0).size());
    }

    @Test
    public final void testTwo() {
        final List<List<Integer>> result = functionUnderTest.apply(scenario(), 2);

        Assert.assertNotNull(result);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(5, result.get(0).size());
        Assert.assertEquals(4, result.get(1).size());
    }

    @Test
    public final void testThree() {
        final List<List<Integer>> result = functionUnderTest.apply(scenario(), 3);

        Assert.assertNotNull(result);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(3, result.get(0).size());
        Assert.assertEquals(3, result.get(1).size());
        Assert.assertEquals(3, result.get(2).size());
    }

    @Test
    public final void testNine() {
        final List<List<Integer>> result = functionUnderTest.apply(scenario(), 9);

        Assert.assertNotNull(result);

        Assert.assertEquals(9, result.size());
        result.forEach(list -> Assert.assertEquals(1, list.size()));
    }

    @Test
    public final void testTen() {
        final List<List<Integer>> result = functionUnderTest.apply(scenario(), 10);

        Assert.assertNotNull(result);

        Assert.assertEquals(10, result.size());
        Assert.assertEquals(1, result.get(0).size());
        Assert.assertEquals(1, result.get(1).size());
        Assert.assertEquals(1, result.get(2).size());
        Assert.assertEquals(1, result.get(3).size());
        Assert.assertEquals(1, result.get(4).size());
        Assert.assertEquals(1, result.get(5).size());
        Assert.assertEquals(1, result.get(6).size());
        Assert.assertEquals(1, result.get(7).size());
        Assert.assertEquals(1, result.get(8).size());
        Assert.assertEquals(0, result.get(9).size());
    }

    private List<Integer> scenario() {
        return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
