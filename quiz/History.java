package xjtlu.cpt111.assignment.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class History {
    private String userInformation;
    private final int order;

    public History(int order) {
        this.order = order;
    }

    // Read exact information of the user who log in the system
    public void checkUser() {
        try {
            int i = 0;
            FileReader file = new FileReader("data/users.txt");
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                if (i == order) {
                    userInformation = line;
                }
                i++;
            }
            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    // Show last three quizzes you take
    public void seeHistory() {
        checkUser();
        String[] tool = userInformation.split("\\.");
        String[] score;
        String[] topic;
        // The case you don't take any quiz
        if (tool.length == 1) {
            System.out.println("You haven't taken any exams yet.");
        }
        // The case you only take 1 quiz
        else if (tool.length == 2) {
            topic = new String[1];
            score = new String[1];

            topic[0] = tool[1].split("-")[0];
            score[0] = tool[1].split("-")[1].split(",")[0];

            System.out.println("1." + "Score: " + score[0] + ", " + "Topic: " + topic[0]);
            System.out.println("You only take the test once.");
        }
        // The case you only take 2 quizzes
        else if (tool.length == 3) {
            topic = new String[2];
            score = new String[2];

            topic[0] = tool[2].split("-")[0];
            score[0] = tool[2].split("-")[1].split(",")[0];
            topic[1] = tool[1].split("-")[0];
            score[1] = tool[1].split("-")[1].split(",")[0];

            System.out.println("1." + "Score: " + score[0] + ", " + "Topic: " + topic[0]);
            System.out.println("2." + "Score: " + score[1] + ", " + "Topic: " + topic[1]);
            System.out.println("You only take the test twice.");
        }

        else {
            topic = new String[3];
            score = new String[3];

            topic[0] = tool[tool.length-1].split("-")[0];
            score[0] = tool[tool.length-1].split("-")[1].split(",")[0];

            topic[1] = tool[tool.length-2].split("-")[0];
            score[1] = tool[tool.length-2].split("-")[1].split(",")[0];

            topic[2] = tool[tool.length-3].split("-")[0];
            score[2] = tool[tool.length-3].split("-")[1].split(",")[0];

            System.out.println("1." + "Score: " + score[0] + ", " + "Topic: " + topic[0]);
            System.out.println("2." + "Score: " + score[1] + ", " + "Topic: " + topic[1]);
            System.out.println("3." + "Score: " + score[2] + ", " + "Topic: " + topic[2]);
        }

    }

    // Show the achievements you got in different topics
    public void showAchievement() {
        checkUser();
        int cs = 0;
        int csScore = 0;
        int ee = 0;
        int eeScore = 0;
        int english = 0;
        int englishScore = 0;
        int math = 0;
        int mathScore = 0;
        String[] tool = userInformation.split("\\.");
        if (tool.length > 1) {
            for (int i = 1; i < tool.length; i++) {
                switch (tool[i].split("-")[0]) {
                    case "cs":
                        cs++;
                        csScore = Math.max(csScore, Integer.parseInt(tool[i].split("-")[1].split(",")[0]));
                        break;
                    case "ee":
                        ee++;
                        eeScore = Math.max(eeScore, Integer.parseInt(tool[i].split("-")[1].split(",")[0]));
                        break;
                    case "english":
                        english++;
                        englishScore = Math.max(englishScore, Integer.parseInt(tool[i].split("-")[1].split(",")[0]));
                        break;
                    case "mathematics":
                        math++;
                        mathScore = Math.max(mathScore, Integer.parseInt(tool[i].split("-")[1].split(",")[0]));
                        break;
                    default:
                        break;
                }
            }
        }

        // Show the achievements you got in cs
        System.out.print("cs: ");
        if (cs == 0) {
            System.out.println("You didn't take any cs quiz.");
        }
        else {
            System.out.println("Number of participation: " + cs + ", The highest score: " + csScore);
        }

        // Show the achievements you got in ee
        System.out.print("ee: ");
        if (ee == 0) {
            System.out.println("You didn't take any ee quiz.");
        }
        else {
            System.out.println("Number of participation: " + ee + ", The highest score: " + eeScore);
        }

        // Show the achievements you got in English
        System.out.print("English: ");
        if (english == 0) {
            System.out.println("You didn't take any English quiz.");
        }
        else {
            System.out.println("Number of participation: " + english + ", The highest score: " + englishScore);
        }

        // Show the achievements you got in Math
        System.out.print("Math: ");
        if (math == 0) {
            System.out.println("You didn't take any Mathematics quiz.");
        }
        else {
            System.out.println("Number of participation: " + math + ", The highest score: " + mathScore);
        }
    }
}
