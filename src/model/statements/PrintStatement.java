package model.statements;

import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;

public class PrintStatement implements IStatement {

    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) {

        //evaluate the expression
        IValue result = expression.eval(state.getSymTable(), state.getHeap());

        //add it as a string to the output list
        state.getOutputList().add(result.toString());
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    public String toString(){
        return "print(" + expression + ")";
    }
}
