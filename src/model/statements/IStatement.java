package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.HeapException;
import exceptions.StatementException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.types.IType;

public interface IStatement {
    PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException, HeapException;
    IStatement deepCopy();
    MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws StatementException, ADTException, ExpressionException;
}
