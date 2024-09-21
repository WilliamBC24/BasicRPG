package test;

public class Practice {
    public static void main(String[] args){
        check(4,6,2);
    }
    public static void check(int a,int b,int c){
        if(a<b&&a<c){
            System.out.println(a);
        }else if(b<a&&b<c){
            System.out.println(b);
        }else if(c<a&&c<b){
            System.out.println(c);
        }
    }
}
