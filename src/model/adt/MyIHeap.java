package model.adt;

import model.values.IValue;

import java.util.Map;

public interface MyIHeap {

    int allocate(IValue value);
    IValue getValue(int address);
    boolean contains(int address);
    void set(int address, IValue value);
    Map<Integer, IValue> getContent();
    void setContent(Map<Integer, IValue> newContent);
}
