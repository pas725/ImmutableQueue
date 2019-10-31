package com.test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ImmutableQueue<T> implements Queue<T> {

    private ArrayList<T> list;
    private static ArrayList<?> EMPTY = new ArrayList<>();

    public ImmutableQueue() {
        list = (ArrayList<T>) EMPTY;
    }

    private ImmutableQueue(ArrayList<T> t) {
        list = t;
    }

    public static <T> Queue<T> of(T first, T ...others) {
        checkForNullElement(first);
        ArrayList<T> newList = new ArrayList<T>(others.length + 1);
        newList.add(first);
        for(T t: others) {
            checkForNullElement(t);
            newList.add(t);
        }
        return construct(newList);
    }

    @Override
    public Queue<T> enQueque(T t) {
        checkForNullElement(t);
        return construct(addToList(t));
    }

    @Override
    public Queue<T> deQueque() {
        checkForEmptyQueue();
        return construct(removeHeadFromList());
    }

    @Override
    public T head() {
        checkForEmptyQueue();
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list == null || list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    private ArrayList<T> addToList(T t) {
        ArrayList<T> newList = cloneList();
        newList.add(t);
        return newList;
    }

    private ArrayList<T> removeHeadFromList() {
        ArrayList<T> newList = cloneList();
        newList.remove(0);
        return newList;
    }

    private ArrayList<T> cloneList() {
        return (ArrayList<T>) list.clone();
    }

    private static <T> Queue<T> construct(ArrayList<T> t) {
        return new ImmutableQueue<T>(t);
    }

    private void checkForEmptyQueue() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Queue.");
    }

    private static <T> void checkForNullElement(T t) {
        if (t == null)
            throw new IllegalArgumentException("Queue doesn't allow NULL elements.");
    }
}
