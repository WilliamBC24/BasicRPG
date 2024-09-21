public class Account {

    public void acc(Account a) {
        System.out.println(a.gender + '.' + a.name);
        System.out.println(a.balance + "VND");
    }

    public double out(Account a, double b) {
        a.balance = a.balance - b;
        return a.balance;
    }

    public double nextm(Account a, double c) {
        a.balance = a.balance - c;
        return a.balance;
    }

    public double nextp(Account b, double c) {
        b.balance = b.balance + c;
        return b.balance;
    }


    String name;
    int id;
    int pass;
    double balance;
    String gender;

    Account(String name, int id, int pass, double balance, String gender) {
        this.name = name;
        this.id = id;
        this.pass = pass;
        this.balance = balance;
        this.gender = gender;
    }


}