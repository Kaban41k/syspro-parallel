static int x = 0;
static int y = 0;
static int z = 0;

class A extends Thread {
  public void run() {
    int a_x = x;   // A.1
    int a_z = z;   // A.2
    y = a_x + a_z; // A.3    
  }
}

class B extends Thread {
  public void run() {
    int b_x = x; // B.1
    x = b_x + 1; // B.2
    int b_z = z; // B.3
    z = b_z + 1; // B.4    
  }
}

class C extends Thread {
  public void run() {
    int c_y = y;    // C.1
    if (c_y == 2) { // C.2
      int c_x = x;  // C.3
      x = c_x - 1;  // C.4
    }
  }
}