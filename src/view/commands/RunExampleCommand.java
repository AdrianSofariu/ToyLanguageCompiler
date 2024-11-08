package view.commands;

import controller.Controller;
import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;

public class RunExampleCommand extends Command{

    private Controller controller;

    public RunExampleCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute(){
        controller.allStep();
    }


}
