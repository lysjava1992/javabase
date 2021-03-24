package com.handbook.java.jvm;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

public class MyStack<T> {
    private T[] elements;
    private int size = 0;
    private static final int INIT_CAPACITY = 16;

    public MyStack() {
        elements = (T[]) new Object[INIT_CAPACITY];
    }

    public void push(T elem) {
        ensureCapacity();
        elements[size++] = elem;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

}
