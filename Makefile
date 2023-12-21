
default: build

jflex:
	jflex src/LexicalAnalyzer.flex

build: jflex
	javac -d more -cp src/ src/Main.java
	jar cfe dist/part2.jar Main -C more .
testing:
#	java -jar dist/part2.jar -wt outputs/AssignParseTree.tex tests/01-Assign.pmp											# OK
#	java -jar dist/part2.jar -wt outputs/EuclidParseTree.tex tests/00-euclid.pmp											# OK
#	java -jar dist/part2.jar -wt outputs/IfThen1ParseTree.tex tests/02-IfThen1.pmp
	java -jar dist/part2.jar -wt outputs/IfThen2ParseTree.tex tests/02-IfThen2.pmp											# OK
#	java -jar dist/part2.jar -wt outputs/IfThenElseParseTree.tex tests/02-IfThenElse.pmp									# OK
#	java -jar dist/part2.jar -wt outputs/IfThenWrongParseTree.tex tests/02-IfThenWrong.pmp
#	java -jar dist/part2.jar -wt outputs/WhileParseTree.tex tests/03-While.pmp												# OK
#	java -jar dist/part2.jar -wt outputs/WhileMultipleParseTree.tex tests/03-WhileMultiple.pmp								# OK
#	java -jar dist/part2.jar -wt outputs/WhileWrongParseTree.tex tests/03-WhileWrong.pmp
#	java -jar dist/part2.jar -wt outputs/PrintReadParseTree.tex tests/04-PrintRead.pmp                                  	# OK
#	java -jar dist/part2.jar -wt outputs/BeginEndParseTree.tex tests/05-BeginEnd.pmp
#	java -jar dist/part2.jar -wt outputs/BeginEndEmptyParseTree.tex tests/05-BeginEndEmpty.pmp								# OK
#	java -jar dist/part2.jar -wt outputs/BeginEndMultipleParseTree.tex tests/05-BeginEndMultiple.pmp  					 	# OK, but not sure
#	java -jar dist/part2.jar -wt outputs/ExprArithBigParseTree.tex tests/06-ExprArithBig.pmp
#	java -jar dist/part2.jar -wt outputs/ExprArithPrioritiesParseTree.tex tests/06-ExprArithPriorities.pmp
#	java -jar dist/part2.jar -wt outputs/ExprArithWithMinusParseTree.tex tests/06-ExprArithWithMinus.pmp
#	java -jar dist/part2.jar -wt outputs/ExprArithWithParenthesesParseTree.tex tests/06-ExprArithWithParentheses.pmp
#	java -jar dist/part2.jar -wt outputs/ExprArithWrongParseTree.tex tests/06-ExprArithWrong.pmp
#	java -jar dist/part2.jar -wt outputs/CondBigParseTree.tex tests/07-CondBig.pmp
#	java -jar dist/part2.jar -wt outputs/CondPrioritiesParseTree.tex tests/07-CondPriorities.pmp
#	java -jar dist/part2.jar -wt outputs/CondWithBracketsParseTree.tex tests/07-CondWithBrackets.pmp
#	java -jar dist/part2.jar -wt outputs/CondWrongParseTree.tex tests/07-CondWrong.pmp
#	java -jar dist/part2.jar -wt outputs/factorialParseTree.tex tests/08-factorial.pmp



all: build testing
