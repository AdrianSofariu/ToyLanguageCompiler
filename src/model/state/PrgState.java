package model.state;

import model.adt.MyIDictionary;
import model.adt.MyIList;
import model.adt.MyIStack;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class PrgState {

    private MyIList<String> outputList;
    private MyIDictionary<String, IValue> symTable;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIStack<IStatement> execStack;
    private IStatement initialState;


    public PrgState(MyIList<String> outputList, MyIDictionary<String, IValue> symTable, MyIDictionary<StringValue, BufferedReader> fileTable, MyIStack<IStatement> execStack, IStatement initialState) {
        this.outputList = outputList;
        this.symTable = symTable;
        this.execStack = execStack;
        this.fileTable = fileTable;
        this.initialState = initialState.deepCopy();

        this.execStack.push(this.initialState);
    }

    public IStatement getInitialState() {
        return initialState;
    }

    public void setInitialState(IStatement initialState) {
        this.initialState = initialState;
    }

    public MyIList<String> getOutputList() {
        return outputList;
    }

    public void setOutputList(MyIList<String> outputList) {
        this.outputList = outputList;
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public MyIStack<IStatement> getExecStack() {
        return execStack;
    }

    public void setExecStack(MyIStack<IStatement> execStack) {
        this.execStack = execStack;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public String FileTableToString() {
        StringBuilder text = new StringBuilder();
        text.append("File Table:\n");
        for(StringValue key : fileTable.getKeys()) {
            text.append(key).append("\n");
        }
        return text.toString();
    }

    @Override
    public String toString() {
        String output = "-------Program State-------\n";
        output += "Symbol Table:\n" + symTable.toString() +
                "\nExecution Stack:\n" + execStack.toString() +
                "\nOutput List:\n" + outputList.toString() + "\n" + FileTableToString();
        output += "\n---------------------------\n";
        return output;
    }
}
