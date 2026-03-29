static boolean A_flag = false;
static boolean B_flag = false;
static Lock l = new ReentrantLock();

void raise_A()        { l.lock(); try { A_flag = true;  } finally { l.unlock(); } }
void raise_B()        { l.lock(); try { B_flag = true;  } finally { l.unlock(); } }

void lower_A()        { l.lock(); try { A_flag = false; } finally { l.unlock(); } }
void lower_B()        { l.lock(); try { B_flag = false; } finally { l.unlock(); } }

boolean is_raised_A() { l.lock(); try { return A_flag;  } finally { l.unlock(); } }
boolean is_raised_B() { l.lock(); try { return B_flag;  } finally { l.unlock(); } }

static volatile boolean should_continue = true;

class ThreadA extends Thread {
  public void run() {
    while (should_continue) {
      raise_A();              // A.1 
      while (is_raised_B()) { // A.2
        continue;             // A.3      
      }
      critical_section();     // A.4
      lower_A();              // A.5
  }
  }
}
class ThreadB extends Thread {
  public void run() {
    while (should_continue) {
      raise_B();              // B.1
      while (is_raised_A()) { // B.2    
        lower_B();            // B.3
        while (is_raised_A());// B.4
        raise_B();            // B.5
      }
      critical_section();     // B.6
      lower_B();              // B.7
    }
  }
}
static void main() {
  Thread a = new ThreadA();
  Thread b = new ThreadB();
  a.start(); b.start();
  should_continue = false;
  a.join(); b.join();
}