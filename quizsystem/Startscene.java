package quizsystem;

import java.io.*;
import java.util.*;

public class Startscene {
    private String account;
    private String password;
    private List<String> tool;
    private Scanner sc1;
    private Map<String,String> map1;

    public Startscene(){
        this.account="";
        this.password="";
        this.tool = new ArrayList<>();
        this.sc1 = new Scanner(System.in);
        this.map1 = new HashMap<>();
    }

    public void newscene(){
        try(Scanner a1 = new Scanner(new File("account"))){
            while (a1.hasNextLine()){
                String line = a1.nextLine();
                String[] tool1 = line.split(",");
                this.tool.add(tool1[0]);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());

        }
        int i =1;
        while (i==1){
            System.out.println("Welcome to the quiz system!");
            System.out.println("1. login");
            System.out.println("2. register");
            System.out.println("3. exit");
            System.out.print("Please enter your options: ");
            String line = sc1.nextLine();
            switch (line) {
                case "1":
                    this.login();
                    i++;
                    break;

                case "2":
                    this.register(); //这里就诠释了static只能调用static函数
                    break;

                case "3":
                    System.out.println("exit");
                    i++;
                    break;

                default:
                    System.out.println("your input is invalid! Please try again!");
                    break;
            }


        }
    }

    public void register()
        {

        while (true){
        System.out.print("You are going to creat an account! \nNow please enter a name: ");
        try{
            String ppp = sc1.nextLine();
            if (ppp.equals("")) throw new RuntimeException("Exploded");

            for (String str : this.tool){
                if (ppp.equals(str)) throw new Exception();}
                this.tool.add(ppp);
            System.out.print("please enter the password: ");
            String p1 = sc1.nextLine();

            try(BufferedWriter bf2 = new BufferedWriter(new FileWriter("account",true))){
                  bf2.write(ppp+","+p1);
                  bf2.newLine();


                }catch (IOException e){
                    System.out.println(e.getMessage());
                }
            System.out.println("new account is been up!");
            System.out.println("Now you can choose again!");
            break;

        }
        catch (RuntimeException a){
            System.out.println("\nThe system exploded! Please enter again!\n");

        }
        catch (Exception e){
            System.out.println("\nThe account is existed, please try again!\n");
        }
        }
        }



        public void login(){
        try(BufferedReader br1 = new BufferedReader(new FileReader("account"))){
            String line;
            while ((line = br1.readLine())!=null){
                String[] read = line.split(",");
                map1.put(read[0],read[1]);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.print("Please enter your account:");
            String line = sc1.nextLine();
            if (map1.keySet().contains(line)){

                this.checkpassword(line);
            }
            else {
            System.out.println("The account is not existed! Please choose:");
            System.out.println("1. try again");
            System.out.println("2. go to register");
            System.out.println("3. exit");
            System.out.print("Please enter your choice:");
            int i3=0;
            while (i3==0){
            String line4 = sc1.nextLine();
            switch (line4){
                case "1":
                    i3++;
                    this.login();
                    break;
                case "2":
                    i3++;
                    this.register();
                    this.newscene();
                    break;
                case "3":
                    i3++;
                    break;
                default:
                    System.out.print("invalid! enter again:");
                    break;
            }
            }
        }
            break;
    }
    }

    public void checkpassword(String line){
        while (true){
        System.out.print("please enter the password:");
        String line2 = sc1.nextLine();
        if (line2.equals(map1.get(line))){
            System.out.println("You have successfully enter the system!");
            this.account = line;
            this.password = password;
            quiz a = new quiz(account);
            a.newsystemscene();
            break;
        }
        else {
            int i1 = 0;
            int i2 = 0;
            while (i1==0){
                System.out.println("Your password is incorrect!\tPlease choose the following!");
                System.out.println("1. enter again");
                System.out.println("2. go to register");
                System.out.println("3. change a account");
                System.out.print("Please enter your choice:");
                while (i2==0) {
                    String line3 = sc1.nextLine();
                    switch (line3) {
                        case "1":
                            i2++;
                            i1++;
                            break;
                        case "2":
                            i1++;
                            i2++;
                            this.register();
                            this.newscene();
                            break;
                        case "3":
                            this.login();
                            break;
                        default:
                            System.out.print("Your input is invaild! please enter again: ");
                            break;


                    }
                }
            }
        }

    }
    }
}
