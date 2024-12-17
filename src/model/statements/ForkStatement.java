package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.HeapException;
import exceptions.StatementException;
import model.adt.MyIDictionary;
import model.adt.MyStack;
import model.state.PrgState;
import model.types.IType;

import java.beans.Statement;

public class ForkStatement implements IStatement{

    IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException, HeapException {
        return new PrgState(state.getOutputList(), state.getSymTable().deepCopy(), state.getFileTable(),
                new MyStack<IStatement>(), state.getHeap(), statement);
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws StatementException, ADTException, ExpressionException {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    public String toString(){
        return "fork(" + statement + ")";
    }
}
