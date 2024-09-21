import java.util.Scanner;
import java.util.Random;

import static java.lang.System.exit;

public class Infiltration {

    static int canFastAttack(boolean a) {
        if (!a) {
            System.out.println("You can fast attack");
            return 1;
        } else {
            System.out.println("He is awake");
            return 0;
        }
    }

    static int canSpy(boolean a, boolean b) {
        if (!a || !b) {
            System.out.println("You can spy");
            return 1;
        } else {
            System.out.println("Spying is pointless");
            return 0;
        }
    }

    static int Spy(boolean a, boolean b) {
        if (!a && !b) {
            System.out.println("Both are asleep");
        } else if (!a && b) {
            System.out.println("Archer is awake");
        } else if (a && !b) {
            System.out.println("Knight is awake");
        } else {
            System.out.println("Both are awake");
        }
        return 0;
    }

    static int canSignal(boolean a, boolean b) {
        if (!a&&b) {
            System.out.println("You can signal");
            return 1;
        } else if(a&&b){
            System.out.println("Archer can hear you");
            return 0;
        } else if(!a&&!b){
            System.out.println("Friend is asleep");
            return 0;
        }else{
            System.out.println("Friend is asleep");
            return 0;
        }
    }
    static int Signal(boolean a){
        System.out.println("pst pst");
        return 0;
    }

    static int canRescue(boolean a, boolean b, boolean c, boolean d) {
        if (d) {
            if (!b && c) {
                System.out.println("You can rescue her, but knock down the guards and signal her first");
                return 1;
            } else {
                System.out.println("Wait for a better time");
                return 0;
            }
        } else {
            if (!a && !b && c) {
                System.out.println("You can rescue her, but knock down the guards and signal her first");
                return 1;
            } else {
                System.out.println("Wait for a better time");
                return 0;
            }
        }
    }
    static int Rescue(){
        System.out.println("You did it!");
        return 0;
    }

    static boolean waitTime(boolean a) {
        Random random = new Random();
        a = random.nextBoolean();
        return a;
    }


    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean knightIsAwake = random.nextBoolean();
        boolean archerIsAwake = random.nextBoolean();
        boolean friendIsAwake = random.nextBoolean();
        boolean petDogIsPresent = random.nextBoolean();
        boolean canSpy=false;
        boolean canAttackKnight=false;
        boolean canAttackArcher=false;
        boolean canSignal=false;
        boolean canRescue=false;
        boolean knightAlive=true;
        boolean archerAlive=true;
        boolean friendAware=false;
        int action, set;
        System.out.println(knightIsAwake + " " + archerIsAwake + " " + friendIsAwake + " " + petDogIsPresent);
        do {
            System.out.println("Which set of actions will you choose?");
            System.out.println("""
                    1:Check
                    2:Action
                    0:Quit""");
            set = scanner.nextInt();
            scanner.nextLine();
            if (set == 1) {
                System.out.println("What will you do? ");
                do {
                    System.out.println("""
                            1:Check if knight is asleep
                            2:Check if you can spy
                            3:Check if you can signal
                            4:Check if you can rescue her
                            5:Wait
                            0:Back""");
                    action = scanner.nextInt();
                    scanner.nextLine();
                    switch (action) {
                        case 1:
                            canFastAttack(knightIsAwake);
                            if(canFastAttack(knightIsAwake)==1){canAttackKnight=true;}else{canAttackKnight=false;}
                            break;
                        case 2:
                            canSpy(knightIsAwake, archerIsAwake);
                            if(canSpy(knightIsAwake, archerIsAwake)==1){canSpy=true;}else{canSpy=false;}
                            break;
                        case 3:
                            canSignal(archerIsAwake, friendIsAwake);
                            if(canSignal(archerIsAwake,friendIsAwake)==1){canSignal=true;canAttackArcher=true;}else{canSignal=false;canAttackArcher=false;}
                            break;
                        case 4:
                            canRescue(knightIsAwake, archerIsAwake, friendIsAwake, petDogIsPresent);
                            if(canRescue(knightIsAwake, archerIsAwake, friendIsAwake, petDogIsPresent)==1){canRescue=true;}else{canRescue=false;}
                            break;
                        case 5:
                            waitTime(knightIsAwake);
                            waitTime(archerIsAwake);
                            waitTime(friendIsAwake);
                            waitTime(petDogIsPresent);
                            System.out.println("Some time has passed");
                            break;
                        case 0:
                            break;
                    }
                } while (action != 0);
            } else if (set == 2) {
                do {
                    System.out.println("What will you do? ");
                    System.out.println("1:Knock out knight\n" +
                                       "2:Knock out archer\n" +
                                       "3:Spy them\n" +
                                       "4:Signal your friend\n" +
                                       "5:Rescue your friend\n" +
                                       "0:Back");
                    action = scanner.nextInt();
                    scanner.nextLine();
                    switch (action) {
                        case 1:
                            if(canAttackKnight)
                            {System.out.println("Knight is down");
                            knightAlive=false;
                            break;}
                            else{System.out.println("Knight blocked the attack");
                                 knightAlive=true;
                                 break;}
                        case 2:
                            if(canAttackArcher){
                            System.out.println("Archer is down");
                            archerAlive=false;
                            break;}else{
                                System.out.println("Archer dodged the attack");
                                archerAlive=true;
                                break;
                            }
                        case 3:
                            if(canSpy){
                            Spy(knightIsAwake, archerIsAwake);
                            break;}else{
                                System.out.println("Maybe later");
                                break;
                            }
                        case 4:
                            if(canSignal){
                            Signal(friendIsAwake);
                            friendAware=true;
                            break;}else{
                                System.out.println("It's too dangerous");
                                break;
                            }
                        case 5:
                            if(canRescue&&!knightAlive&&!archerAlive&&friendAware){
                            Rescue();
                            exit(0);}
                            else{System.out.println("Maybe later");
                                 break;}
                        case 0:
                            break;
                    }
                } while (action != 0);
            }else if (set == 0) {
                    System.out.println("Shutting down...");
                    break;
                } else {
                    System.out.println("Not a valid set of actions");
                }
            }while (set != 0);
        }
    }
