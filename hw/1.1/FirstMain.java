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

public class FirstMain {
    void main() {
        B b = new B();
        A a = new A(b);

        a.start();
        a.join();
    }
}
