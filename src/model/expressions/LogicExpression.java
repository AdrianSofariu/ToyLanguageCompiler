package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicExpression implements IExpression {

    private IExpression left;
    private IExpression right;
    private LogicalOperator operator;

    public LogicExpression(IExpression left, LogicalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }


    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable) throws ADTException, ExpressionException {

        //evaluate both sides of the expression
        IValue evaluatedLeft = left.eval(symTable);
        IValue evaluatedRight = right.eval(symTable);

        //check for both sides to be boolean
        if(!evaluatedLeft.getType().equals(new BoolType())){
            throw new ExpressionException("Left expression is not of bool type");
        }
        if(!evaluatedRight.getType().equals(new BoolType())){
            throw new ExpressionException("Right expression is not of bool type");
        }

        //compute the result of the logical operation depending on the operator
        switch(operator){
            case AND:
                return new BoolValue(((BoolValue)evaluatedLeft).getValue() && ((BoolValue)evaluatedRight).getValue());
            case OR:
                return new BoolValue(((BoolValue)evaluatedLeft).getValue() || ((BoolValue)evaluatedRight).getValue());
            default:
                throw new ExpressionException("Unknown operator");
        }
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(left.deepCopy(), operator, right.deepCopy());
    }

    public String toString(){
        return left.toString() + " " + operator + " " + right.toString();
    }
}
