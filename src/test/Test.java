import java.util.Arrays;
import java.util.Scanner;



public class Test {
    public static void menu(){
        System.out.println("""
                ===Menu===
                1. Withdraw money
                2. Transfer money
                3. Add money
                0. Exit
                """);
    }

    public static void main(String[] args) throws InterruptedException {

        int id, pass, choice,z,b,c;
        double out,next,add,d;
        int count = 0;
        int i = 0;
        int yes=0;
        String a,e;
        Account[] account = new Account[10];
        Account Son = new Account("Son", 4321000, 694, 1900000,"Mr");
        Account Quynh = new Account("Quynh", 1234000, 281, 2300000,"Mr");
        Account Ly = new Account("Ly", 9999000, 105, 4470000,"Mrs");
        Scanner scanner = new Scanner(System.in);
        account[0] = Son;
        account[1] = Quynh;
        account[2] = Ly;
        do{System.out.println("Please enter account id: ");
        id = scanner.nextInt();
        scanner.nextLine();
        for (i = 0; i <= 2; i++) {
            if (id == account[i].id) {
                yes++;
                System.out.println("Input password:");
                pass = scanner.nextInt();
                scanner.nextLine();
                        while (count < 2&&pass != account[i].pass){
                        System.out.println("Wrong password");
                        Thread.sleep(1000);
                        System.out.println("Input password:");
                        pass = scanner.nextInt();
                        scanner.nextLine();
                        count++;
                    }
                    if (count == 2) {
                        System.out.println("Too many wrongs!");
                        System.exit(0);
                    }else if(pass == account[i].pass){
                        System.out.println("Welcome,"+account[i].gender+'.'+account[i].name);
                        break;
                    }
            }else{continue;}
        }if(yes==0){
                System.out.println("Account does not exist.");
                Thread.sleep(1000);
        }}while(yes==0);
        account[i].acc(account[i]);
        Thread.sleep(700);
        menu();
        System.out.println("Please choose action:");
        choice=scanner.nextInt();
        scanner.nextLine();
        do {
            switch (choice) {
                case 1:
                    System.out.println("Input withdrawal amount:");
                    out=scanner.nextDouble();
                    scanner.nextLine();
                    while(out>account[i].balance){
                        System.out.println("Amount larger than balance.");
                        Thread.sleep(700);
                        System.out.println("Input withdrawal amount:");
                        out=scanner.nextDouble();
                        scanner.nextLine();
                    }
                    account[i].out(account[i],out);
                    System.out.println("Withdrawing");
                    Thread.sleep(700);
                    System.out.println('.');
                    Thread.sleep(700);
                    System.out.println('.');
                    Thread.sleep(700);
                    System.out.println('.');
                    System.out.println("New account balance:"+account[i].balance+"VND");
                    break;
                case 2:
                    System.out.println("Input receiver ID:");
                    id=scanner.nextInt();
                    scanner.nextLine();
                    do {
                        for (z = 0; z <= 2; z++) {
                            if (id == account[z].id) {
                                yes++;
                                break;
                            } else {
                                continue;
                            }
                        }
                    }while(yes==0);
                    System.out.println("Input transfer amount:");
                    next=scanner.nextDouble();
                    scanner.nextLine();
                    while(next>account[i].balance){
                        System.out.println("Amount larger than balance.");
                        Thread.sleep(700);
                        System.out.println("Input transfer amount:");
                        next=scanner.nextDouble();
                        scanner.nextLine();
                    }
                    account[i].nextm(account[i],next);
                    account[i].nextp(account[z],next);
                    System.out.println("Transferring");
                    Thread.sleep(700);
                    System.out.println('.');
                    Thread.sleep(700);
                    System.out.println('.');
                    Thread.sleep(700);
                    System.out.println('.');
                    System.out.println("New account balance:"+account[i].balance+"VND");
                    break;
                case 3:
                    System.out.println("Input amount desired");
                    add=scanner.nextDouble();
                    account[i].balance=account[i].balance+add;
                    System.out.println("New account balance:"+account[i].balance);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Not a valid action.");
                    break;
                }
            menu();
            System.out.println("Please choose action:");
            choice=scanner.nextInt();
            scanner.nextLine();
            }while(choice!=0);
        System.out.println("System shutting down");
        Thread.sleep(700);
        System.out.println('.');
        Thread.sleep(700);
        System.out.println('.');
        Thread.sleep(700);
        System.out.println('.');
        }
    }


