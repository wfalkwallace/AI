COMSW4701: Artificial Intelligence
----------------------------------
Assignment 3
------------
William Falk-Wallace (wgf2104)

---

The submitted zip folder contains my submission for AI Assignment 3. It has a command line prompt which requests board size, winning chain length, and turn time limit. Those parameters may also be specified as command line arguments.

1. How To Run:
	1. make sure `runtests.sh` is executable: `chmod a+x runtests.sh`
	2. run `./runtests.sh`
	
	Alternatively
	
	1. run `make`
	2. run `java GomoTest n m s` 
	
		Where
		- `n` is an integer corresponding to the board size (n x n)
		- `m` is an integer corresponding to the winning chain length
		- `s` is an integer corresponding to the turn time limit (in seconds)		
		(ex. `make`; `java GomoTest 8 5 30`)
		
	Alternatively
	1. run `make`
	2. run `java GomoTest` 

	
	
	