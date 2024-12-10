package model.garbageCollector;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.values.IValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IGarbageCollector {
    Map<Integer, IValue> collect(List<MyIDictionary<String, IValue>> symTables, Map<Integer, IValue> heap);
}
