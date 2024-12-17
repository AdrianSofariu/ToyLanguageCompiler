package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.IType;
import model.values.IValue;

public interface IExpression {
    IValue eval(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws ADTException, ExpressionException;
    IExpression deepCopy();
    IType typeCheck(MyIDictionary<String, IType> typeEnv) throws ExpressionException, ADTException;
}
