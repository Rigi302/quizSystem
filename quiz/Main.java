package xjtlu.cpt111.assignment.quiz;

public class Main {
    public static void main(String[] args) {
        StartScene a = new StartScene();
        // Get the Start Scene
        a.displayMenu();
        FunctionSelection b = new FunctionSelection();
        // Get the scene of selecting function
        b.selection(a.getOrder(), a.getName(), a.getId());
    }
}