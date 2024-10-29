package model.adt;

import exceptions.EmptyStackException;

public interface MyIStack<T> {

    void push(T element);
    T pop() throws EmptyStackException;
    int size();
    boolean isEmpty();
}
