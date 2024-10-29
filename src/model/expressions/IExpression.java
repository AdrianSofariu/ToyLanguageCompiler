package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.values.IValue;

public interface IExpression {
    IValue eval(MyIDictionary<String, IValue> symTable) throws ADTException, ExpressionException;
    IExpression deepCopy();
}
