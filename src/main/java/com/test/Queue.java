package com.test;

/*
 * @author : Created by piyush_sagar on 10/25/19.
 */
public interface Queue<T> {
    Queue<T> enQueque(T t);
    Queue<T> deQueque();
    T head();
    boolean isEmpty();
    int size();
}
