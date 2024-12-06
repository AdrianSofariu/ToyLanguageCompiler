package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.VariableExpression;
import model.state.PrgState;
import model.types.IType;
import model.values.BoolValue;
import model.values.IntValue;

public class VarDecStatement implements IStatement{

    String variableName;
    IType typ;

    public VarDecStatement(String variableName, IType typ) {
        this.variableName = variableName;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException {
        state.getSymTable().insert(variableName, typ.defaultValue());
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDecStatement(variableName, typ);
    }

    public String toString() {
        return typ + " " + variableName;
    }
}
