package repository;

import exceptions.RepositoryException;
import model.state.PrgState;

import java.util.List;

public interface IRepository {

    PrgState getCurrentProgram();
    List<PrgState> getStates();
    void addPrgState(PrgState prgState);
    void logPrgStateExec() throws RepositoryException;
    void clearLogFile() throws RepositoryException;
}
