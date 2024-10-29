package model.statements;

import model.state.PrgState;

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
}
