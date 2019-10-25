package com.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class ImmutableQueueTest {

    @Test
    public void createEmptyQueue() {
        Queue<Integer> q = new ImmutableQueue();
        Assert.assertEquals(0, q.size());
    }

    @Test
    public void createQueue() {
        Queue<Integer> q = ImmutableQueue.of(1,2,3);
        Assert.assertEquals(3, q.size());
        Assert.assertEquals(1, (int) q.head());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createQueueWithSomeNulls() {
        Queue<Integer> q = ImmutableQueue.of(1,null,3);
    }

    @Test
    public void addToQueue() {
        Queue<Integer> q = new ImmutableQueue();
        Queue<Integer> q2 = q.enQueque(3);

        Assert.assertEquals(0, q.size());
        Assert.assertEquals(1, q2.size());
        Assert.assertNotEquals(q, q2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullElementToQueue() {
        Queue<Integer> q = new ImmutableQueue();
        Queue<Integer> q2 = q.enQueque(null);
    }

    @Test
    public void removeFromQueueWithOneElement() {
        Queue<Integer> q = ImmutableQueue.of(3);
        Queue<Integer> q2 = q.deQueque();

        Assert.assertEquals(1, q.size());
        Assert.assertEquals(0, q2.size());

        Assert.assertEquals(3, (int) q.head());
        Assert.assertNotEquals(q, q2);
    }

    @Test
    public void removeFromQueueWithMultipleElements() {
        Queue<Integer> q = ImmutableQueue.of(3,2,4);
        Queue<Integer> q2 = q.deQueque();

        Assert.assertEquals(3, q.size());
        Assert.assertEquals(2, q2.size());

        Assert.assertEquals(3, (int) q.head());
        Assert.assertEquals(2, (int) q2.head());
        Assert.assertNotEquals(q, q2);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFromEmptyQueue() {
        ImmutableQueue<Integer> q = new ImmutableQueue();
        q.deQueque();
    }

    @Test(expected = NoSuchElementException.class)
    public void headFromEmptyQueue() {
        ImmutableQueue<Integer> q = new ImmutableQueue();
        q.head();
    }

    @Test
    public void testCombinedOperations() {
        Queue<Integer> q = ImmutableQueue.of(3,2);

        Queue<Integer> q2 = q.deQueque();
        Queue<Integer> q3 = q.enQueque(4);
        Queue<Integer> q4 = q2.deQueque();

        Assert.assertEquals(2, q.size());
        Assert.assertEquals(1, q2.size());
        Assert.assertEquals(3, q3.size());
        Assert.assertEquals(0, q4.size());

        Assert.assertEquals(3, (int) q.head());
        Assert.assertEquals(2, (int) q2.head());
        Assert.assertEquals(3, (int) q3.head());

        Queue<Integer> q5 = q4.enQueque(9);
        Assert.assertEquals(1, q5.size());
        Assert.assertEquals(9, (int) q5.head());

        Assert.assertNotEquals(q, q2);
        Assert.assertNotEquals(q, q3);
        Assert.assertNotEquals(q, q4);
        Assert.assertNotEquals(q2, q3);
        Assert.assertNotEquals(q4, q3);
    }
}
