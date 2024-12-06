package model.garbageCollector;

import model.values.IValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IGarbageCollector {
    Map<Integer, IValue> collect(Collection<IValue> symTableValues, Map<Integer, IValue> heap);
}
