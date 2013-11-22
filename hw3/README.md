COMSW4701: Artificial Intelligence
----------------------------------
Assignment 3
------------
William Falk-Wallace (wgf2104)

---

The submitted zip folder contains my submission for AI Assignment 3. It has a command line prompt which requests board size, winning chain length, and turn time limit. Those parameters may also be specified as command line arguments.

#How To Run:
1. make sure `runtests.sh` is executable: `chmod a+x runtests.sh`
2. run `./runtests.sh`
	
Alternatively,
	
1. run `make`
2. run `java player_wgf2104 n m s` 
	
	Where
	- `n` is an integer corresponding to the board size (n x n)
	- `m` is an integer corresponding to the winning chain length
	- `s` is an integer corresponding to the turn time limit (in seconds)		
	(ex. `make; java player_wgf2104 10 5 30`)
		
Or, for a Command Line Prompt,

1. run `make`
2. run `java player_wgf2104` 

#Notes:
The &alpha;-&beta; Search function is coded in _GTree.java_, which calls a heuristic funtion `getUtility` in _GState.java_.

#####Heuristic Analysis:
My heuristic evaluation function computes the longest consecutive chain of each player's tokens (ie. longest string of x's or o's) less than the chainlength. The resulting heuristic value is the difference between the two (maximize own chainlength, minimize opponent's). This is effective first, since any chain longer than the winning chainlength is not valuable to the player. It also balances the simultaneous (zero-sum) goals of diminishing an opponents chances of winning while optimizing its own. The algorithm uses a depth limit of 4 during its execution to widen the breadth of its search given time constraints. 

Attempts to further limit the set of searched actions following a given state to enable further depth of search diminished accuracy for this heuristic and did not see worthwhile gains. The current design consistently wins against a random player, both when moving first (as x) as well as second (as y).

My algorithm runs in polynomial time. A better Actions function could reduce this significantly in the same way a more directed start position for the evaluation function would: given the time cutoff, a more directed start of the search would result in significantly better performance and an ability to increase depth without losing accuracy.


