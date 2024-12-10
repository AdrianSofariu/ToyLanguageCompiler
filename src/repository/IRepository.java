package repository;

import exceptions.RepositoryException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.values.IValue;

import java.util.List;

public interface IRepository {

    List<PrgState> getStates();
    void setPrgList(List<PrgState> prgList);
    void addPrgState(PrgState prgState);
    void logPrgStateExec(PrgState state) throws RepositoryException;
    void clearLogFile() throws RepositoryException;
    public List<MyIDictionary<String, IValue>> symTables();
}
