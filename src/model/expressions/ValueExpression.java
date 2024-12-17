package model.expressions;

import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.IType;
import model.values.IValue;

public class ValueExpression implements IExpression {
    private IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap){
        return value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(this.value);
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws ExpressionException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }


}
