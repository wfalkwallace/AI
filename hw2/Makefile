JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
files = LevelLoader.java ManhDist.java OpenGoals.java SokoTest.java StarMD.java StarOG.java State.java STree.java
default: $(files:.java=.class)
clean:
	rm -f *.class
