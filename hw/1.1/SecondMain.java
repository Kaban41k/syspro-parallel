package ru.nsu;

class A extends Thread {
    private final Thread b;

    A(Thread b) {
        this.b = b;
    }

    @Override
    public void run() {
        b.start();

        try {
            b.join(); // wait B...
        } catch (InterruptedException ignored) {}

        // B died, it's okay

        System.out.println("A finished");
    }
}

class B extends Thread {
    @Override
    public void run() {
        int j = 42 / 0; // exception happened
                        // B died :(

        System.out.println("B finished");
    }
}

class C extends Thread {
    private final Thread b;

    C(Thread b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            b.join(); // wait B...
        } catch (InterruptedException ignored) {}

        // B died already, it's okay

        System.out.println("C finished");
    }
}

public class SecondMain {
    public static void main(String[] args) throws Exception {
        Thread b = new B();
        Thread a = new A(b);
        Thread c = new C(b);

        a.start();
        a.join();

        c.start();
        c.join();
    }
}
