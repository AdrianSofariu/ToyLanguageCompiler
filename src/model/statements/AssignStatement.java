package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.values.IValue;

public class AssignStatement implements IStatement {
    private String variableName;
    private IExpression expression;

    public AssignStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException {

        //check if the variable already exists in the symbol table
        if(!state.getSymTable().contains(variableName)){
            throw new StatementException("Variable not found");
        }

        //if it does, get its value
        IValue value = state.getSymTable().get(variableName);

        //evaluate the new value
        IValue expValue = expression.eval(state.getSymTable());

        //check if the types of the 2 values are compatible
        if(!value.getType().equals(expValue.getType())){
            throw new StatementException("Value type mismatch");
        }

        //update the variable in the symbol table
        state.getSymTable().insert(variableName, expValue);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(this.variableName, this.expression.deepCopy());
    }

    public String toString() {
        return this.variableName + " = " + this.expression.toString();
    }
}
