package xjtlu.cpt111.assignment.quiz;

import java.io.*;
import java.util.Scanner;

public class StartScene {
    private String name;
    private String id;
    private int order;
    private String[] ID;
    private String[] NAME;
    private String[] PASSWORD;
    private String[] QUIZ;
    private boolean CHECK;

    public StartScene() {
        name = "";
        id = "";
        order = -1;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    // Read information from users.txt and put them into array
    public void readInformation() {
        try {
            int lines = 0;
            FileReader file1 = new FileReader("data/users.txt");
            BufferedReader reader1 = new BufferedReader(file1);
            while (reader1.readLine() != null) {
                lines++;
            }
            NAME = new String[lines];
            ID = new String[lines];
            PASSWORD = new String[lines];
            QUIZ = new String[lines];
            reader1.close();
            String line;
            lines = 0;
            FileReader file2 = new FileReader("data/users.txt");
            BufferedReader reader2 = new BufferedReader(file2);
            while ((line = reader2.readLine()) != null) {
                String[] tool1 = line.split(",");
                NAME[lines] = tool1[0];
                ID[lines] = tool1[1];
                PASSWORD[lines] = tool1[2];
                String[] tool2 = line.split("Quiz: ");
                QUIZ[lines] = tool2[1];
                lines++;
            }
            reader2.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    //Show the start scene
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        CHECK = false;
        while (!CHECK) { // Make sure that if you fail to log in or register you will come back to this scene
            System.out.println("""
                ****************************
                Welcome to the quiz system!
                Please choose an option:
                1. Login
                2. Register
                3. Exit
                ****************************
                """);
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    readInformation();
                    login();
                    break;
                case "2":
                    readInformation();
                    register();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter a new number");
                    break;
            }
        }
    }

    // The method of log in
    public void login() {
        Scanner sc = new Scanner(System.in);
        int order = -1;
        System.out.print("Enter your student ID: ");
        String Id = sc.nextLine();
        // Check if you're registered
        for (int i = 0; i < ID.length; i++) {
            if (Id.equals(ID[i])) {
                order = i;
            }
        }
        if (order == -1) {
            // If you don't register, it will give you options again
            System.out.println("""
                    You don't register.
                    Please choose an option:
                    1. Try again
                    2. Register
                    3. Exit
                    """);
            System.out.print("Enter your option: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> login();
                case "2" -> register();
                case "3" -> System.exit(0);
                default -> System.out.println("Invalid input. Please enter a new number");
            }
        }

        else {
            // Check your password
            while (true) {
                System.out.print("Enter your password: ");
                String Password = sc.nextLine();
                if (PASSWORD[order].equals(Password)) {
                    name = NAME[order];
                    id = ID[order];
                    System.out.println();
                    this.order = order;
                    CHECK = true;
                    break;
                }
                else {
                    // If your password is wrong, it will give you options again
                    System.out.println("""
                            Your password is wrong.
                            Please choose an option:
                            1. Try again
                            2. Return to initial screen
                            """);
                    while (true) {
                        System.out.print("Enter your choice: ");
                        String Option1 = sc.nextLine();
                        if (Option1.equals("1")) {
                            CHECK = true;
                            break;
                        }
                        else if (Option1.equals("2")) {
                            CHECK = false;
                            break;
                        }
                        else {
                            System.out.println("Invalid input. Please enter 1 or 2.");
                        }
                    }
                    if (!CHECK) {
                        break;
                    }
                }
            }
        }
    }

    // The method of log in
    public void register() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter your name: ");
            String Name = sc.nextLine();
            String Id;
            int tool = 1;
            while (tool != 0) {
                tool = 0;
                System.out.print("Enter your student ID: ");
                String IdTool = sc.nextLine();

                // Check if this ID has been used (ID is used to identify different users, name can be repeated but the ID cannot)
                for (String s : ID) {
                    if (IdTool.equals(s)) {
                        tool++;
                    }
                }
                if (tool != 0) {
                    System.out.println("""
                            This student ID already exists.
                            Please choose an option:
                            1. Try again
                            2. Return to initial screen
                            """);
                    System.out.print("Enter your choice: ");
                    String Option1 = sc.nextLine();
                    if (Option1.equals("1")) {
                        continue;
                    }
                    else if (Option1.equals("2")) {
                        CHECK = false;
                        break;
                    }
                    else {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                    }
                }
                else {
                    Id = IdTool;
                    // Set your password
                    System.out.print("Enter your password: ");
                    String Password = sc.nextLine();
                    // Put the information you read and the information you register into users.txt
                    FileWriter file = new FileWriter("data/users.txt");
                    BufferedWriter buffer = new BufferedWriter(file);
                    for (int i = 0; i < NAME.length; i++) {
                        buffer.write(NAME[i] + ",");
                        buffer.write(ID[i] + ",");
                        buffer.write(PASSWORD[i] + ",");
                        buffer.write("Quiz: " + QUIZ[i]);
                        buffer.newLine();
                    }
                    buffer.write(Name + ",");
                    buffer.write(Id + ",");
                    buffer.write(Password + ",");
                    buffer.write("Quiz: I");
                    buffer.newLine();
                    buffer.close();
                    System.out.println("""
                            You have finished registered.
                            Now you will come back to initial screen.
                            """);
                    CHECK = false;
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}