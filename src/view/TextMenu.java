package view;

import view.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {

    private Map<String, Command> map;

    public TextMenu(){
        this.map = new HashMap<>();
    }

    public void addCommand(Command command){
        map.put(command.getKey(), command);
    }

    private void printMenu(){
        for(Command command : map.values()){
            String line = String.format("%4s : %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner sc = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("Input the option: ");
            String key = sc.nextLine();
            Command command = map.get(key);
            if(command == null){
                System.out.println("Invalid option");
                continue;
            }

            try {
                command.execute();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


}
