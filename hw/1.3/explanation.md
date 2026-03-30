### limitation of possible cases
**z != 1 is unreachable**  
In the entire code there is only 1 line of modification of z.  
It's B.4 (z = b_z + 1, where b_z == z).  
Because of z is not modified in other threads, and initially z == 0,   
It can be only 1.  
  
**x < 0, x > 1 are unreachable**  
There are 2 lines of modification of x.  
Initially x == 0.  
It's B.2 (x = b_x + 1, where b_z == z).  
Because of there is no more incrementation of x,  
x can't be higher than 1.  
And C.4 (x = c_x - 1, where c_x == z).  
Because of there is no more decrementation of x,  
x can't be lower than -1.

But x == -1 also unreachable,  
For decrementation of x, y == 2 needed(C.2 *if*).  
There is 1 lines of modification of y.  
It's A.3 (y = a_x + a_z, where a_x == x, a_z == z).  
Max z is 1, then y == x + 1 == 2.  
Then x == 1,  
But after decrementation x will be 0 != -1.  
Conflict of result of x == -1 and y == 2 сondition! 

**y < 0, y > 2 are unreachable**    
Because of limits on x and z,  
y can't be be higher than 2 and lower than 0.

### possible execution results
x == 0, z == 1, y == 2  
B->A->C

x == 1, z == 1, y == 0  
A->B->C

x == 1, z == 1, y == 1  
A.1->B->A.2->A.3->C

x == 1, z == 1, y == 2  
C->B->A

### proof of exhaustiveness
x == 0, z == 1, y == 0 and  
x == 0, z == 1, y == 1 are unreachable.  
For x == 0 needs decrementation in C.4.  
Then y == 2 needs, which is initially 0.  
In entire code there is only 1 lines of modification of y.  
But 2 modification of y needed!  
First for C.4 happened.  
Second for y == 0 or y == 1 at the end.
