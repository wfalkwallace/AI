JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
files = Entail.java KB.java KBLoader.java

.PHONY: default
default: $(files:.java=.class)

.PHONY : test
test : default testall.sh
	./testall.sh
	
.PHONY: clean 
clean:
	rm -f *.class