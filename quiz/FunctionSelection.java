package xjtlu.cpt111.assignment.quiz;

import java.util.Scanner;

public class FunctionSelection {

    public FunctionSelection() {
    }

    // Creat the scene of selecting function
    public void selection(int order, String name, String id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Quiz System, " + name + ".");
        while (true) { // Make sure after you finish the function you choose, you will come back to this scene
            // Give you different options
            System.out.println("""
                Please choose an option:
                1. Start a new quiz
                2. Check your last three quizzes
                3. View your achievements
                4. See the subject rankings
                5. Exit
                """);
            System.out.print("Enter your option: ");
            String option = sc.nextLine();
            History h = new History(order);
            switch (option) {
                case "1":
                    // Function of starting a new quiz
                    Quiz q = new Quiz(order, name, id);
                    q.taking();
                    System.out.println("You have already finished this quiz");
                    System.out.println("Please enter 1 to come back to the previous interface.");
                    while (true) {
                        String input = sc.nextLine();
                        if (input.equals("1")) {
                            break;
                        }
                        else System.out.println("Invalid option. Please choose enter 1.");
                    }
                    break;
                case "2":
                    // Function of displaying the latest 3 quizzes you participate in
                    h.seeHistory();
                    System.out.println("You have already checked your quizzes.");
                    System.out.println("Please enter 1 to come back to the previous interface.");
                    while (true) {
                        String input = sc.nextLine();
                        if (input.equals("1")) {
                            break;
                        }
                        else System.out.println("Invalid option. Please choose enter 1.");
                    }
                    break;
                case "3":
                    // Function of displaying your achievements in each topic
                    h.showAchievement();
                    System.out.println("You have already view your achievements.");
                    System.out.println("Please enter 1 to come back to the previous interface.");
                    while (true) {
                        String input = sc.nextLine();
                        if (input.equals("1")) {
                            break;
                        }
                        else System.out.println("Invalid option. Please choose enter 1.");
                    }
                    break;
                case "4":
                    // Function of displaying the ranking list of each topic
                    RankingList rl = new RankingList();
                    rl.displayList();
                    System.out.println("You have already checked ranking list.");
                    System.out.println("Please enter 1 to come back to the previous interface.");
                    while (true) {
                        String input = sc.nextLine();
                        if (input.equals("1")) {
                            break;
                        }
                        else System.out.println("Invalid option. Please choose enter 1.");
                    }
                    break;
                case "5":
                    // Function of exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter a new number");
                    break;
            }
        }
    }
}
