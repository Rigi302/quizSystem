package xjtlu.cpt111.assignment.quiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RankingList {

    public RankingList() {
    }

    public void displayList() {

        // Read ranking_list information and put them into an array
        String[] LIST = new String[4];
        File file = new File("data/ranking_list");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (line.split(":").length == 1) {
                    LIST[i] = "No one take the " + line.split(":")[0] + " quiz.";
                }
                else {
                    LIST[i] = line;
                }
                i++;
            }
            reader.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        // Print ranking list
        for (int i = 0; i < 4; i++) {
            System.out.println(LIST[i]);
        }
    }
}