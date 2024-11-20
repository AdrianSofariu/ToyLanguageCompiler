package model.adt;

import exceptions.HeapException;
import model.values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap{

    Map<Integer, IValue> map;
    int lastAddress = 0;

    public MyHeap(){
        map = new HashMap<>();
    }

    @Override
    public int allocate(IValue value) {
        lastAddress++;
        map.put(lastAddress, value);
        return lastAddress;
    }

    @Override
    public IValue getValue(int address) {
        if(map.containsKey(address))
            return map.get(address);
        else
            throw new HeapException("Address not found");
    }

    @Override
    public boolean contains(int address) {
        return map.containsKey(address);
    }

    @Override
    public void set(int address, IValue value) {
        if(map.containsKey(address))
            map.put(address, value);
        else
            throw new HeapException("Address not found");
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, IValue> newContent) {
        map = newContent;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Integer key : map.keySet()){
            result.append(key).append(" -> ").append(map.get(key)).append("\n");
        }
        return result.toString();
    }
}
