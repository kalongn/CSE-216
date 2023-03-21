package Java.Rec_7;

public class Sample {
    public static void main(String[] args) {
        C1 c1 = new C1("I am in c1");
    }
}

class C1 extends C2 {
    public C1() {
        super();
    }

    public C1(String s) {
        System.out.println("Am I in C2?");
    }
}

class C2 {
    public C2() {
        System.out.println("Is this from C1?");
    }

    public C2(String p) {
        System.out.println("Am I in C1?");
    }
}
