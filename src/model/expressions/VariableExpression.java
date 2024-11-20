package model.expressions;

import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.values.IValue;

public class VariableExpression implements IExpression {
    private String var;

    public VariableExpression(String var) {
        this.var = var;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap){
        return symTable.get(var);
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(this.var);
    }

    @Override
    public String toString() {
        return var;
    }
}
