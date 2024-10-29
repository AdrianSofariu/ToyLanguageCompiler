package model.adt;

import exceptions.EmptyStackException;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{

    private final Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public void push(T element) {
        stack.push(element);
    }

    @Override
    public T pop() throws EmptyStackException {
        if(stack.isEmpty())
            throw new EmptyStackException("Stack is empty");
        return stack.pop();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        //iterate from top to bottom
        for (int i = stack.size() - 1; i >= 0; i--) {
            str.append(stack.get(i).toString()).append("\n");
        }
        return str.toString();
    }
}
