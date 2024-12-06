package view.commands;

import controller.Controller;
import exceptions.*;

public class RunExampleCommand extends Command{

    private Controller controller;

    public RunExampleCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute(){
        try{
            controller.allStep();
        } catch (StatementException | ExpressionException | ADTException | RepositoryException | HeapException e){
            System.out.println(e.getMessage());
        }
    }


}
