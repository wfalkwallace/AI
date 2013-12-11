COMSW4701: Artificial Intelligence
----------------------------------
Assignment 4
------------
William Falk-Wallace (wgf2104)

---

The submitted zip folder contains my submission for AI Assignment 4. It has a command line prompt which requests which algorith to use, Knowledge Base File, and Query. Those parameters may also be specified as command line arguments. The main class is named `Entail`.

#How To Run:
1. make sure `runtests.sh` is executable: `chmod a+x runtests.sh`
2. run `./runtests.sh`
	
Alternatively,
	
1. run `make`
2. run `./entail a kb q` 
	
	Where
	- `a` is an algorithm to use (forward/backward)
	- `kb` is the path to a file with horn-form knowledge base as specified in the assignment specifications
	- `q` is a symbolic query
	(ex. `make; ./entail forward kb.txt Q`)
		
Or, for a Command Line Prompt,

1. run `make`
2. run `java Entail` 

#####Tests
All tests can be (make'd and) run by executing the runtests.sh script. these output to the file log.txt.

My test kbs:
1. kbs/GWH.txt - this is the scene in the movie Good Will Hunting where Clark challenges Chuckie, and Will steps in before getting Skylar's number and asking clark "how do you like them apples"
1. kbs/MM.txt - when sarah (my girlfriend) and I want lunch, we order milano. when it's snowing (like monday 10/10) they're late. if they're late, we get very hungry and therefore angry, and we devour our H17 sandwiches.
1. kbs/NEWPC.txt - This summer, Sarah and I worked in SF, made some money and bought a new PC so we could have mapping and CAD software which makes us happy. when we're both happy, we have fun.
1. kbs/PIERNIKI.txt - Sarah's family has a trdition every holidays (between xmas and thanksgiving) where they bake THOUSANDS of polish cookies called pierniki. we drink and bake and eat a lot of them and are fat as a result.
