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
import model.types.StringType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.MyRepository;
import view.TextMenu;
import view.View;
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

        IRepository repo = new MyRepository("log.txt");
        repo.addPrgState(new PrgState(new MyList<String>(), new MyDictionary<String, IValue>(), new MyDictionary<StringValue, BufferedReader>(), new MyStack<IStatement>(), ex4));
        Controller ctrl = new Controller(repo);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex4.toString(), ctrl));
        menu.show();

    }
}