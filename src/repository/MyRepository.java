package repository;

import exceptions.RepositoryException;
import model.adt.MyList;
import model.state.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class MyRepository implements IRepository{

    private List<PrgState> prgStateList;
    private String filename;
    private int currentIndex;

    public MyRepository(String filename) {
        prgStateList = new ArrayList<PrgState>();
        this.filename = filename;
        currentIndex = 0;
    }

    @Override
    public List<PrgState> getStates() {
        return this.prgStateList;
    }

    @Override
    public void addPrgState(PrgState prgState) {
        prgStateList.add(prgState);
    }

    @Override
    public PrgState getCurrentProgram() {
        return prgStateList.get(currentIndex);
    }

    @Override
    public void logPrgStateExec() throws RepositoryException {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            logFile.println(getCurrentProgram());
            logFile.close();
        }
        catch (IOException e){
            throw new RepositoryException("Could not open log file");
        }
    }

    @Override
    public void clearLogFile() throws RepositoryException {
        try{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, false)));
            logFile.close();
        }
        catch (IOException e){
            throw new RepositoryException("Could not open log file");
        }
    }

}
