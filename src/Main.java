import controller.Controller;
import repository.IRepository;
import repository.MyRepository;
import view.View;

public class Main {
    public static void main(String[] args) {

        IRepository repo = new MyRepository("log.txt");
        Controller ctrl = new Controller(repo);
        View view = new View(ctrl);

        view.start();

    }
}