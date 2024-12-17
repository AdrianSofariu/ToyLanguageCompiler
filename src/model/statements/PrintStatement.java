package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.MyIDictionary;
import model.expressions.IExpression;
import model.state.PrgState;
import model.types.IType;
import model.values.IValue;

public class PrintStatement implements IStatement {

    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException {

        //evaluate the expression
        IValue result = expression.eval(state.getSymTable(), state.getHeap());

        //add it as a string to the output list
        state.getOutputList().add(result.toString());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws StatementException, ADTException, ExpressionException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    public String toString(){
        return "print(" + expression + ")";
    }
}
