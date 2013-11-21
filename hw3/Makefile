JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
files = GomoTest.java GState.java GTree.java
default: $(files:.java=.class)
clean:
	rm -f *.class
