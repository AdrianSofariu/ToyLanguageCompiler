package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.MyIDictionary;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpression implements IExpression {

    private IExpression left;
    private IExpression right;
    private ArithmeticalOperator operator;

    public ArithmeticExpression(IExpression left, ArithmeticalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> symTable) throws ADTException, ExpressionException {
        //evaluate both sides of the expression
        IValue leftVal = left.eval(symTable);
        IValue rightVal = right.eval(symTable);

        //check if they are both numeric values
        if(!leftVal.getType().equals(new IntType())){
            throw new ExpressionException("left expression is not a numeric value");
        }
        if(!rightVal.getType().equals(new IntType())){
            throw new ExpressionException("right expression is not a numeric value");
        }
        IntValue leftIntVal = (IntValue) leftVal;
        IntValue rightIntVal = (IntValue) rightVal;

        //compute the arithmetic operation
        switch(operator){
            case PLUS:
                return new IntValue(leftIntVal.getValue() + rightIntVal.getValue());
            case MINUS:
                return new IntValue(leftIntVal.getValue() - rightIntVal.getValue());
            case MULTIPLY:
                return new IntValue(leftIntVal.getValue() * rightIntVal.getValue());
            case DIVIDE:
                //handle division by 0
                if(rightIntVal.getValue() == 0){
                    throw new ExpressionException("division by zero");
                }
                return new IntValue(leftIntVal.getValue() / rightIntVal.getValue());
            default:
                throw new ExpressionException("operator not recognized");
        }
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(left.deepCopy(), operator, right.deepCopy());
    }

    public String toString(){
        return left.toString() + " " + operator.toString() + " " + right.toString();
    }

}
