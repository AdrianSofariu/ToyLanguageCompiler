package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.IType;
import model.values.IValue;

public class VariableExpression implements IExpression {
    private String var;

    public VariableExpression(String var) {
        this.var = var;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws ADTException {
        return symTable.get(var);
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(this.var);
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws KeyNotFoundException {
        return typeEnv.get(var);
    }

    @Override
    public String toString() {
        return var;
    }
}
