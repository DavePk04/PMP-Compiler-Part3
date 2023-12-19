
default: build

jflex:
	jflex src/LexicalAnalyzer.flex

build: jflex
	javac -d more -cp src/ src/Main.java
	jar cfe dist/part2.jar Main -C more .
testing:
	java -jar dist/part2.jar -wt outputs/EuclidParseTree.tex tests/00-euclid.pmp
#	java -jar dist/part2.jar -wt outputs/AssignParseTree.tex tests/01-Assign.pmp

all: build testing
