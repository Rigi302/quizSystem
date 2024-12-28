package quizsystem;

import java.util.Scanner;

public class quiz {
    private int mark;
    private Scanner sc1;
    private String type;
    private String account;
    private String content;

    public quiz(String account){
        this.mark = 0;
        this.sc1 = new Scanner(System.in);
        this.type = "";
        this.account = account;
        this.content = "";
    }
    public void newsystemscene(){
        System.out.println("Welcome to the System! Your have the following choice");
        System.out.println("1. Start a new quiz");
        System.out.println("2. Check the history");
        System.out.println("3. check the ranking");
        System.out.print("Please enter your choice: ");
        int i2=0;
        while (i2==0) {
            String line3 = sc1.nextLine();
            switch (line3) {
                case "1":
                    i2++;
                    newquiz();
                    break; //这时候可以省略this 因为上下文自动默认同一个对象
                case "2":
                    i2++;
                    history();
                    break;
                case "3":
                    i2++;
                    ranking();
                    break;
                default:
                    System.out.print("Your input is invaild! please enter again: ");
                    break;


            }
        }

    }

    public void newquiz(){
        System.out.println("choose a topic");
        System.out.println("1. math");
        System.out.println("2. engish");
        System.out.print("Please enter your choice: ");
        int i2=0;
        while (i2==0) {
            String line3 = sc1.nextLine();
            switch (line3) {
                case "1":
                    this.type = "math";
                    i2++;
                    break; //这时候可以省略this 因为上下文自动默认同一个对象
                case "2":
                    this.type = "english";
                    i2++;
                    history();
                    break;
                    default:
                    System.out.print("Your input is invaild! please enter again: ");
                    break;
            }
        }
        System.out.println("choose a content");
        System.out.println("1. hard");
        System.out.println("2. easy");
        System.out.print("Please enter your choice: ");
        int i3 = 0;
        while (i3==0) {
            String line3 = sc1.nextLine();
            switch (line3) {
                case "1":
                    this.content = "hard";
                    i3++;
                    break; //这时候可以省略this 因为上下文自动默认同一个对象
                case "2":
                    this.content = "east";
                    i3++;
                    history();
                    break;
                default:
                    System.out.print("Your input is invaild! please enter again: ");
                    break;
            }
        }
        System.out.println("You have successfully build a quiz! I will be start");
        Question a = new Question(account,type);
        a.newquiz();
    }

    public String history(){
        return "";
    }

    public String ranking(){
        return "";
    }
}
