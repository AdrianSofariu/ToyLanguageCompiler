package model.statements;

import exceptions.StatementException;
import model.state.PrgState;

public class CompoundStatement implements IStatement {

    private IStatement statement1;
    private IStatement statement2;

    public CompoundStatement(IStatement statement1, IStatement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException {
        //split the statement into its components and push them on the stack
        state.getExecStack().push(statement2);
        state.getExecStack().push(statement1);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(this.statement1.deepCopy(), this.statement2.deepCopy());
    }

    public String toString(){
        return statement1.toString() + ";" + statement2.toString();
    }
}
