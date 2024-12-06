import controller.Controller;
import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.adt.MyList;
import model.adt.MyStack;
import model.expressions.*;
import model.state.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.MyRepository;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExampleCommand;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {

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

        IStatement ex4 = new CompoundStatement(new VarDecStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VarDecStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))))))))));

        //make a statement for if(2 < 3) then print(4) else print(5)
        IStatement ex5 = new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(2)), ComparisonOperator.LESS,
                new ValueExpression(new IntValue(3))), new PrintStatement(new ValueExpression(new IntValue(4))),
                new PrintStatement(new ValueExpression(new IntValue(5))));

        //Ref int v; new(v,20); Ref Ref int a; new(a,v); print(v); print(a)
        IStatement ex6 = new CompoundStatement(new VarDecStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStatement ex7 = new CompoundStatement(new VarDecStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                                        ArithmeticalOperator.PLUS, new ValueExpression(new IntValue(5)))))))));

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement ex8 = new CompoundStatement(new VarDecStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")),
                                                ArithmeticalOperator.PLUS, new ValueExpression(new IntValue(5))))))));

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex9 = new CompoundStatement(new VarDecStatement("v", new IntType()), new CompoundStatement(
                new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), ComparisonOperator.GREATER,
                                new ValueExpression(new IntValue(0))), new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), ArithmeticalOperator.MINUS,
                                        new ValueExpression(new IntValue(1)))))), new PrintStatement(new VariableExpression("v")))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex10 = new CompoundStatement(new VarDecStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));

        //generate a different example to test the garbage collector
        //Ref int v;new(v,20);Ref Ref int a; new(v,30);new(a,v);new(v,40);Ref Ref Ref int b; new(b,a); new(a,v); new(b,a); print(rH(rH(a)))
        IStatement ex11 = new CompoundStatement(new VarDecStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDecStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(30))),
                                        new CompoundStatement(new HeapAllocStatement("a", new VariableExpression("v")),
                                                new CompoundStatement(new HeapAllocStatement("v", new ValueExpression(new IntValue(40))),
                                                        new CompoundStatement(new VarDecStatement("b", new RefType(new RefType(new RefType(new IntType())))),
                                                                new CompoundStatement(new HeapAllocStatement("b", new VariableExpression("a")),
                                                                        new CompoundStatement(new HeapAllocStatement("a", new VariableExpression("v")),
                                                                                new CompoundStatement(new HeapAllocStatement("b", new VariableExpression("a")),
                                                                                        new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))))))))))));


        //ex10
        IRepository repo = new MyRepository("log.txt");
        repo.addPrgState(new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(),new MyHeap(), ex10));
        Controller ctrl = new Controller(repo);

        //ex11
        IRepository repo2 = new MyRepository("log.txt");
        repo2.addPrgState(new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), new MyHeap(), ex11));
        Controller ctrl2 = new Controller(repo2);

        //ex8
        IRepository repo3 = new MyRepository("log.txt");
        repo3.addPrgState(new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), new MyHeap(), ex8));
        Controller ctrl3 = new Controller(repo3);

        //ex9
        IRepository repo4 = new MyRepository("log.txt");
        repo4.addPrgState(new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), new MyHeap(), ex9));
        Controller ctrl4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", "run example garbageCol1", ctrl));
        menu.addCommand(new RunExampleCommand("2", "run example garbageCol2", ctrl2));
        menu.addCommand(new RunExampleCommand("3", "run example rH/wH", ctrl3));
        menu.addCommand(new RunExampleCommand("4", "run example while", ctrl4));

        menu.show();

    }
}