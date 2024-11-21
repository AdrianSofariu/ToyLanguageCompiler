package controller;

import exceptions.ADTException;
import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.MyIStack;
import model.state.PrgState;
import model.statements.IStatement;
import model.values.IValue;
import model.values.RefValue;
import repository.IRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    IRepository repo;
    boolean displayFlag = true;

    public Controller(IRepository repo){
        this.repo = repo;
    }

    public void setDisplayFlag(){
        displayFlag = !displayFlag;
    }

    public void setCurrentProgram(PrgState prg){
        repo.addPrgState(prg);
    }

    public PrgState oneStep(PrgState state) throws StatementException, ExpressionException, ADTException {
        //get execution stack
        MyIStack<IStatement> st = state.getExecStack();

        //check if it is empty
        if(st.isEmpty()){
            throw new EmptyStackException("Stack is empty");
        }

        //execute the top statement
        IStatement currentStatement = st.pop();
        PrgState executedState = currentStatement.execute(state);

        //display the state after execution if the flag is set
        if(displayFlag){
            System.out.println(executedState);
        }
        return executedState;
    }

    public PrgState allStep() throws StatementException, ExpressionException, ADTException {

        //get the current program
        PrgState prg = repo.getCurrentProgram();

        //clear the log file
        repo.clearLogFile();
        repo.logPrgStateExec();

        //display the initial state
        if(displayFlag){
            System.out.println(prg);
        }

        //execute all the statements in the program
        while(!prg.getExecStack().isEmpty()){
            oneStep(prg);
            repo.logPrgStateExec();
            List<Integer> symTableAddresses = getAddrFromSymTable(prg.getSymTable().getMap().values());
            List<Integer> indirectRefAddr = getIndirectlyReferencedAddresses(
                symTableAddresses,
                prg.getHeap().getContent()
            );
            prg.getHeap().setContent(garbageCollector(
                symTableAddresses,
                indirectRefAddr,
                prg.getHeap().getContent()
            ));
            repo.logPrgStateExec();
        }

        //return final state
        return prg;
    }

    //eliminate all addresses from the heap that are not referenced by other heap entries or by the symbol table
    Map<Integer, IValue> garbageCollector(List<Integer> symTableAddresses, List<Integer> indirectRefAddr, Map<Integer, IValue> heap){
        return heap.entrySet().stream()
            .filter(e -> symTableAddresses.contains(e.getKey()) || indirectRefAddr.contains(e.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //get all addresses from reference values in the symbol table
    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
            .filter(v -> v instanceof RefValue)
            .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddr();})
            .collect(Collectors.toList());

    }

    //given a list of addresses, return a list of all other addresses that are indirectly referenced by the given addresses
    List<Integer> getIndirectlyReferencedAddresses(List<Integer> symTableAddr, Map<Integer, IValue>heap){

        List<Integer> indirectlyRefAddr = new ArrayList<>();

        //go through all addresses in the sym table
        //for each of them with a RefValue as value go recursively
        //to the referenced addresses until the value is not a ref
        //add all these addresses to the list
        symTableAddr.forEach(addr -> {
            IValue val = heap.get(addr);
            while(val instanceof RefValue refVal){
                int refAddr = refVal.getAddr();
                if(!indirectlyRefAddr.contains(refAddr)){
                    indirectlyRefAddr.add(refAddr);
                }
                val = heap.get(refAddr);
            }
        });
        return indirectlyRefAddr;
    }
}