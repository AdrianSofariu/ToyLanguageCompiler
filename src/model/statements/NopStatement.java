package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.types.IType;

public class NopStatement implements IStatement{

    /*execution of nop statement:
    -return nothing*/
    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws StatementException, ADTException, ExpressionException {
        return typeEnv;
    }
}
