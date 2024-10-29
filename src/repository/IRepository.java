package repository;

import model.state.PrgState;

public interface IRepository {

    PrgState getCurrentProgram();
    void setCurrentProgram(PrgState currentProgram);
}
