package xjtlu.cpt111.assignment.quiz;

import xjtlu.cpt111.assignment.quiz.model.Question;
import xjtlu.cpt111.assignment.quiz.model.Option;
import xjtlu.cpt111.assignment.quiz.util.IOUtilities;

import java.io.*;
import java.util.Random;

import java.util.Scanner;

public class Quiz {
    private String topic;
    private final String name;
    private final String id;
    private final int order;
    private Question[] questions;
    String[] prefix = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    int[] randomNumber;
    private final Question[] wrongQuestions;
    private int score;
    private String[] tool1;
    private String wholeTopicList;
    private int topicNumber;
    private String[] tool2;

    public Quiz(int order, String name, String id) {
        questions = getQuestions();
        wrongQuestions = new Question[questions.length];
        score = 0;
        this.order = order;
        this.name = name;
        this.id = id;
    }

    // Read the questions
    public void readQuestions() {
        String filename = "resources/questionsBank/questions_" + topic + ".xml";
        questions = IOUtilities.readQuestions(filename);
    }

    // Choose the topic
    public void printSubjectMenu() {
        System.out.println("**************************************");
        System.out.println("Welcome to the Quiz System.");
        System.out.println("Please choose a subject:");
        System.out.println("1. Computer Science");
        System.out.println("2. Electronic Engineering");
        System.out.println("3. English");
        System.out.println("4. Mathematics");
        System.out.println("**************************************");
        System.out.print("Enter your choice (1, 2, 3, or 4): ");
    }

    // Show different information depend on the topic you choose
    public void topicSelect() {
        Scanner sc = new Scanner(System.in);
        int W = 0;
        while (W == 0){
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    W++;
                    topic = "cs";
                    System.out.println("\nNow let's begin the Computer Science quiz!");
                    break;
                case "2":
                    W++;
                    topic = "ee";
                    System.out.println("\nNow let's begin the Electronic Engineering quiz!");
                    break;
                case "3":
                    W++;
                    topic = "english";
                    System.out.println("\nNow let's begin the English quiz!");
                    break;
                case "4":
                    W++;
                    topic = "mathematics";
                    System.out.println("\nNow let's begin the Mathematics quiz!");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    break;
            }
        }
    }

    // Create an array of questions to put wrong questions
    public void printWrongQuestions() {
        for (Question wrongQuestion : wrongQuestions) {
            System.out.println();
            if (wrongQuestion != null) {
                System.out.println(wrongQuestion.getQuestionStatement());
                Option[] a = wrongQuestion.getOptions();
                for (int j = 0; j < 4; j++) {
                    if (a[j].isCorrectAnswer()) {
                        System.out.println("Correct answer: " + a[j].getAnswer());
                    }
                }
            }
        }
    }

    // Get the questions array
    public Question[] getQuestions() {
        printSubjectMenu();
        topicSelect();
        readQuestions();
        return questions;
    }

    public boolean checkAnswer(Question question, String userAnswer) {
        // Check the answer
        int number = 0;
        Option[] option = question.getOptions();
        for (int i = 0; i < option.length; i++){
            if (userAnswer.equalsIgnoreCase(prefix[i])) {
                if (option[randomNumber[i]].isCorrectAnswer()) {
                    number++;
                }
            }
        }
        // Record wrong questions
        if (number != 1) {
            for (int i = 0; i < wrongQuestions.length; i++) {
                if (wrongQuestions[i] == null) {
                    wrongQuestions[i] = question;
                    break;
                }
            }
        }
        return number == 1;
    }

    // Do questions of the quiz
    public void taking() {
        int currentQuestionIndex = 0;
        Scanner sc = new Scanner(System.in);

        // Print question and options
        for (Question question : questions) {
            System.out.println();
            System.out.println("Question #" + (++currentQuestionIndex) + ": " + question.getQuestionStatement());

            // Random order of options to make sure the options will appear in random order
            Random random = new Random(System.currentTimeMillis());
            Option[] options = question.getOptions();
            randomNumber = new int[options.length];
            for (int i = 0; i < randomNumber.length; i++) {
                randomNumber[i] = i;
            }
            for (int i = randomNumber.length - 1; i > 0; i--) {
                // Generate an index between 0 and i
                int j = random.nextInt(i + 1);
                // Change randomNumber[i] and randomNumber[j]
                int temp = randomNumber[i];
                randomNumber[i] = randomNumber[j];
                randomNumber[j] = temp;
            }
            for (int i = 0; i < options.length; i++) {
                System.out.println(prefix[i] + ". " + options[randomNumber[i]].toString().replace("[correctAnswer] ", ""));
            }

            // Set scores of questions of different difficulty
            while (true) {
                System.out.print("Your answer: ");
                String userAnswer = sc.nextLine();
                int check = 0;
                for (int i = 0; i < question.getOptions().length; i++) {
                    if (userAnswer.equalsIgnoreCase(prefix[i])) {
                        check++;
                    }
                }
                if (check != 0) {
                    if (checkAnswer(question, userAnswer)) {
                        switch (question.getDifficulty()) {
                            case EASY:
                                score += 5;
                                break;
                            case MEDIUM:
                                score += 10;
                                break;
                            case HARD:
                                score += 12;
                                break;
                            case VERY_HARD:
                                score += 26;
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                } else System.out.println("Illegal answer.\nEnter a new one.");
            }
        }

        // Store this quiz information into users.txt
        try {
            int lines = 0;
            FileReader file1 = new FileReader("data/users.txt");
            BufferedReader reader1 = new BufferedReader(file1);
            while (reader1.readLine() != null) {
                lines++;
            }
            tool1 = new String[lines];
            reader1.close();
            lines = 0;
            FileReader file2 = new FileReader("data/users.txt");
            BufferedReader reader2 = new BufferedReader(file2);
            String line;
            while ((line = reader2.readLine()) != null) {
                tool1[lines] = line;
                lines++;
            }
            reader2.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        try {
            FileWriter file = new FileWriter("data/users.txt");
            BufferedWriter buffer = new BufferedWriter(file);
            for (int i = 0; i < tool1.length; i++) {
                if (i == order) {
                    buffer.write(tool1[i]);
                    if (tool1[i].split("I").length == 1) {
                        buffer.write(1 + "." + topic + "-" + score + ",");
                        buffer.newLine();
                    }
                    else {
                        buffer.write(tool1[i].split("\\.").length + "." + topic + "-" + score + ",");
                        buffer.newLine();
                    }
                }
                else {
                    buffer.write(tool1[i]);
                    buffer.newLine();
                }
            }
            buffer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        // Update ranking list
        switch (topic) {
            case "cs":
                topicNumber = 0;
                break;
            case "ee":
                topicNumber = 1;
                break;
            case "english":
                topicNumber = 2;
                break;
            case "mathematics":
                topicNumber = 3;
                break;
            default:
                break;
        }
        try {
            int lines = 0;
            FileReader file1 = new FileReader("data/ranking_list");
            BufferedReader reader1 = new BufferedReader(file1);
            while (reader1.readLine() != null) {
                lines++;
            }
            tool2 = new String[lines];
            reader1.close();
            lines = 0;
            FileReader file2 = new FileReader("data/ranking_list");
            BufferedReader reader2 = new BufferedReader(file2);
            String line;
            while ((line = reader2.readLine()) != null) {
                tool2[lines] = line;
                if (topicNumber == lines) {
                    wholeTopicList = line;
                }
                lines++;
            }
            reader2.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        // Case: No one in the ranking list
        if (wholeTopicList.split(":").length == 1) {
            wholeTopicList = wholeTopicList + name + "(ID: " + id + "):" + score;
        }
        else {
            int now = Integer.parseInt(wholeTopicList.split(":")[wholeTopicList.split(":").length - 1]);
            // The score equal to the highest score in the ranking list
            if (score == now) {
                String change = ":" + wholeTopicList.split(":")[1] + ":" + wholeTopicList.split(":")[2] + ", " + name + "(ID: " + id + ")" + ":";
                wholeTopicList = wholeTopicList.split(":")[0] + change + now;
            }
            // The score higher the highest score in the ranking list
            else if (score > now) {
                String change = ":" + name + "(ID: " + id + ")" + ":";
                wholeTopicList = wholeTopicList.split(":")[0] + change + score;
            }
        }
        tool2[topicNumber] = wholeTopicList;
        try {
            FileWriter file = new FileWriter("data/ranking_list");
            BufferedWriter buffer = new BufferedWriter(file);
            for (int i = 0; i < 4; i++) {
                buffer.write(tool2[i]);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        // Display the score at once
        System.out.println("\n-----------------------------------------------");
        System.out.println("In this " + topic + " quiz " + "your scored is " + score + ".");
        System.out.println("-----------------------------------------------");
        System.out.println();
        System.out.println("You have finished this question.");
        System.out.println("Please choose an option:");

        // Display the wrong questions
        System.out.println("1. End this quiz.");
        System.out.println("2. Review wrong questions.");
        int True = 0;
        while (True != 1) {
            String choice2 = sc.nextLine();
            switch (choice2) {
                case "1":
                    True++;
                    break;
                case "2":
                    printWrongQuestions();
                    True++;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }
}