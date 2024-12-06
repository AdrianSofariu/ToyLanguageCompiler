package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.HeapException;
import exceptions.StatementException;
import model.state.PrgState;

public interface IStatement {
    PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException, HeapException;
    IStatement deepCopy();
}
