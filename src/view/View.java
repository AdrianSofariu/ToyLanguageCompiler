package view;

import controller.Controller;
import model.adt.MyDictionary;
import model.adt.MyList;
import model.adt.MyStack;
import model.expressions.ArithmeticExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.state.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class View {

    Controller controller;
    int option;

    //hardcoded programs that will be removed
    IStatement ex1 = new CompoundStatement(new VarDecStatement("v", new IntType()),
            new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                    new PrintStatement(new VariableExpression("v"))));

    IStatement ex2 = new CompoundStatement(new VarDecStatement("a", new IntType()),
                    new CompoundStatement(new VarDecStatement("b", new IntType()),
                    new CompoundStatement(new AssignStatement("a",
                    new ArithmeticExpression(new ValueExpression(new IntValue(2)), ArithmeticalOperator.PLUS,
                    new ArithmeticExpression(new ValueExpression(new IntValue(3)), ArithmeticalOperator.MULTIPLY,
                    new ValueExpression(new IntValue(5))))), new CompoundStatement(new AssignStatement("b",
                    new ArithmeticExpression(new VariableExpression("a"), ArithmeticalOperator.PLUS,
                    new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

    IStatement ex3 = new CompoundStatement(new VarDecStatement("a", new BoolType()),
                    new CompoundStatement(new VarDecStatement("v", new IntType()),
                    new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                    new CompoundStatement(new IfStatement(new VariableExpression("a"),
                    new AssignStatement("v", new ValueExpression(new IntValue(2))),
                    new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                    new PrintStatement(new VariableExpression("v"))))));

    public View(Controller controller) {
        this.controller = controller;
    }

    public void executeProgram(){
        PrgState finalState;
        try{
            finalState = controller.allStep();
            System.out.println(finalState.getOutputList());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void printMenu(){
        System.out.println("1. Input program");
        System.out.println("2. Run program");
        System.out.println("3. Set/Unset display flag");
        System.out.println("0. Exit");
    }

    public void chooseExample(){
        //read example number from user
        System.out.println("Choose example 1/2/3/0: ");
        int example = 0;
        try{
            example = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e){
            System.out.println("Invalid example");
        }

        //set the current program to the chosen example
        PrgState prg;
        switch(example){
            case 1:
                prg = new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), ex1);
                controller.setCurrentProgram(prg);
                break;
            case 2:
                prg = new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), ex2);
                controller.setCurrentProgram(prg);
                break;
            case 3:
                prg = new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), ex3);
                controller.setCurrentProgram(prg);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid example");
        }
    }

    public void setDisplayFlag(){
        controller.setDisplayFlag();
    }

    public void start(){
        while(true) {
            printMenu();

            //read option from user
            option = 0;
            System.out.println("Enter option: ");
            try {
                option = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
                continue;
            }

            //execute the chosen option
            switch (option) {
                case 1:
                    chooseExample();
                    break;
                case 2:
                    executeProgram();
                    break;
                case 3:
                    setDisplayFlag();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
