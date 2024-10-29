package controller;

import exceptions.ADTException;
import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.adt.MyIStack;
import model.state.PrgState;
import model.statements.IStatement;
import repository.IRepository;

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
        repo.setCurrentProgram(prg);
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

        //display the initial state
        if(displayFlag){
            System.out.println(prg);
        }

        //execute all the statements in the program
        while(!prg.getExecStack().isEmpty()){
            oneStep(prg);
        }

        //return final state
        return prg;
    }
}
