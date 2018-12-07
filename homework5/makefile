JFLAGS = -g #-Xlint for extra info 
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(wildcard *.java)
#Game.java Direction.java Place.java GameTester.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
