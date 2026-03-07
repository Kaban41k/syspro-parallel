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

class D extends Thread {
    private final Thread a;

    D(Thread a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.join(); // wait A...
        } catch (InterruptedException ignored) {}

        // A died, it's okay

        System.out.println("D finished");
    }
}

public class ThirdMain {
    public static void main(String[] args) throws Exception {
        Thread b = new B();
        Thread a = new A(b);
        Thread d = new D(a);

        a.start();
        d.start();
        a.join();
        d.join();
    }
}
