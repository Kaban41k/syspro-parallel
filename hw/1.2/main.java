static int x = 0;

static int r_y = 0;
static int r_z = 0;

class A extends Thread {
  public void run() {
    int y = -1;       // A.1
    int a_x = x;      // A.2
    if (a_x == 0) {   // A.3
      y = x;          // A.4
      x = y + 1;      // A.5
    }
    r_y = y;
  }
}

class B extends Thread {
  public void run() {
    int z = -1;       // B.1
    int b_x = x;      // B.2
    if (b_x == 0) {   // B.3
      z = x;          // B.4
      x = z + 1;      // B.5
    }
    r_z = z;
  }
}

// Could main thread observe x == 1, r_y == 0, r_z == 0?
// Yes.
// 
// A.1->A.2->A.3->A.4->B.1->B.2->B.3->B.4->A.5->B.5
// 
// A.1->A.2->A.3->A.4 x == 0, y == 0
// B.1->B.2->B.3->B.4 x == 0, y == 0, z == 0 
// A.5 x == 1, y == 0, z == 0
// B.5 x == 1, y == 0, z == 0
// r_y == y, r_z == z always
// x == 1, r_y == 0, r_z == 0!
//
// 
// Could main thread observe x == 2, r_y == 0, r_z == 1?
// Yes.
// 
// A.1->A.2->A.3->B.1->B.2->B.3->A.4->A.5->B.4->B.5
//
// A.1->A.2->A.3 x == 0, y == -1, a_x == 0
// B.1->B.2->B.3 x == 0, z == -1, b_x == 0
// A.4->A.5 x == 1, y == 0, z == -1
// B.4->B.5 x == 2, y == 0, z == 1
// x == 2, r_y == 0, r_z == 1!
//
// Could main thread observe x == 1, r_y == 0, r_z == 1?
// No.
//
// At the end x == 1, z == 1 and y == 0.
// (1) For z == 1 A.5 with y == 0 always happens earlier than B.4, because of x incrementation.
// (2) B.5 is x = 2 always after (1).
// Then for x == 1 A.5 with y == 0 always happened after (2), because of x == 2 after (2).
// !!!But A.5 already earlier than B.4!!!
// Conflict of z == 1 and x == 1
