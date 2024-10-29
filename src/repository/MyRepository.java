package repository;

import model.adt.MyList;
import model.state.PrgState;


public class MyRepository implements IRepository{

    private MyList<PrgState> prgStateList;
    private PrgState currentProgram;

    public MyRepository() {
        prgStateList = new MyList<>();
    }

    @Override
    public void setCurrentProgram(PrgState currentProgram) {
        this.currentProgram = currentProgram;
    }

    @Override
    public PrgState getCurrentProgram() {
        return currentProgram;
    }

}
