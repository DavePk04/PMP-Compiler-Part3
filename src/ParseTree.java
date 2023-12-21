import java.util.List;
import java.util.ArrayList;

/**
 * A skeleton class to represent parse trees.
 * 
 * The arity is not fixed: a node can have 0, 1 or more children.
 * Trees are represented in the following way: Tree :== Symbol * List&lt;Tree&gt;
 * In other words, trees are defined recursively: A tree is a root (with a label of type Symbol) and a list of trees children. Thus, a leaf is simply a tree with no children (its list of children is empty). This class can also be seen as representing the Node of a tree, in which case a tree is simply represented as its root.
 * 
 * @author Léo Exibard, Sarah Winter, edited by Mathieu Sassolas
 */

public class ParseTree {
    /**
     * The label of the root of the tree.
     */
    private Symbol label;
    
    /**
     * The list of childrens of the root node, which are trees themselves.
     */
    private List<ParseTree> children;

    /**
     * Creates a singleton tree with only a root labeled by lbl.
     * 
     * @param lbl The label of the root
     */
    public ParseTree(Symbol lbl) {
        this.label = lbl;
        this.children = new ArrayList<ParseTree>(); // This tree has no children
    }
    
    /**
     * Creates a singleton tree with only a root labeled by terminal lbl.
     * 
     * @param lbl The label of the root
     */
    public ParseTree(LexicalUnit lbl) {
        this.label = new Symbol(lbl);
        this.children = new ArrayList<ParseTree>(); // This tree has no children
    }
    
    /**
     * Creates a singleton tree with only a root labeled by variable lbl.
     * 
     * @param lbl The label of the root
     */
    public ParseTree(NonTerminal lbl) {
        this.label = new Symbol(null,lbl);
        this.children = new ArrayList<ParseTree>(); // This tree has no children
    }

    /**
     * Creates a tree with root labeled by lbl and children chdn.
     * 
     * @param lbl  The label of the root
     * @param chdn Its children
     */
    public ParseTree(Symbol lbl, List<ParseTree> chdn) {
        this.label = lbl;
        this.children = chdn;
    }
    /**
     * Creates a tree with root labeled by terminal lbl and children chdn.
     * 
     * @param lbl  The label of the root
     * @param chdn Its children
     */
    public ParseTree(LexicalUnit lbl, List<ParseTree> chdn) {
        this.label = new Symbol(lbl);
        this.children = chdn;
    }
    /**
     * Creates a tree with root labeled by variable lbl and children chdn.
     * 
     * @param lbl  The label of the root
     * @param chdn Its children
     */
    public ParseTree(NonTerminal lbl, List<ParseTree> chdn) {
        this.label = new Symbol(null,lbl);
        this.children = chdn;
    }

    /* Pure LaTeX version (using the forest package) */
    /**
     * Writes the tree as LaTeX code.
     * 
     * @return the String representation of the tree as LaTeX code.
     */
    public String toLaTexTree() {
        StringBuilder treeTeX = new StringBuilder();
        treeTeX.append("[");
        treeTeX.append("{" + label.toTexString() + "}");   // Implement this yourself in Symbol.java
        treeTeX.append(" ");

        for (ParseTree child : children) {
            treeTeX.append(child.toLaTexTree());
        }
        treeTeX.append("]");
        return treeTeX.toString();
    }

    /**
     * Writes the tree as a forest picture. Returns the tree in forest enviroment using the LaTeX code of the tree.
     * 
     * @return the String representation of the tree as forest LaTeX code.
     */
    public String toForestPicture() {
        return "\\begin{forest}for tree={rectangle, draw, l sep=20pt}" + toLaTexTree() + ";\n\\end{forest}";
    }

    /**
     * Writes the tree as a LaTeX document which can be compiled using PDFLaTeX.
     * 
     * This method uses the forest package.
     * <br>
     * <br>
     * The result can be used with the command:
     * 
     * <pre>
     * pdflatex some-file.tex
     * </pre>
     * 
     * @return a String of a full LaTeX document (to be compiled with pdflatex)
     */
    public String toLaTeXusingForest() {
        return "\\documentclass[border=5pt]{standalone}\n\n\\usepackage{forest}\n\n\\begin{document}\n\n" +
                toForestPicture()
                + "\n\n\\end{document}\n%% Local Variables:\n%% TeX-engine: lualatex\n%% End:";
    }

    /* Tikz version (using graphs and graphdrawing libraries, with GD library trees, requiring LuaLaTeX) */
    /**
     * Writes the tree as TikZ code. TikZ is a language to specify drawings in LaTeX files.
     * 
     * @return the String representation of the tree as TikZ code.
     */
    public String toTikZ() {
        StringBuilder treeTikZ = new StringBuilder();
        treeTikZ.append("node {");
        treeTikZ.append(label.toTexString());  // Implement this yourself in Symbol.java
        treeTikZ.append("}\n");
        for (ParseTree child : children) {
            treeTikZ.append("child { ");
            treeTikZ.append(child.toTikZ());
            treeTikZ.append(" }\n");
        }
        return treeTikZ.toString();
    }

    /**
     * Writes the tree as a TikZ picture. A TikZ picture embeds TikZ code so that LaTeX undertands it.
     * 
     * @return the String representation of the tree as a TikZ picture.
     */
    public String toTikZPicture() {
        return "\\begin{tikzpicture}[tree layout,every node/.style={draw,rounded corners=3pt}]\n\\" + toTikZ() + ";\n\\end{tikzpicture}";
    }

    /**
     * Writes the tree as a LaTeX document which can be compiled using LuaLaTeX.
     * 
     * This method uses the Tikz package.
     * <br>
     * <br>
     * The result can be used with the command:
     * 
     * <pre>
     * lualatex some-file.tex
     * </pre>
     * 
     * @return a String of a full LaTeX document (to be compiled with lualatex)
     */
    public String toLaTeXusingTikz() {
        return "\\documentclass[border=5pt]{standalone}\n\n\\usepackage{tikz}\\usetikzlibrary{graphs,graphdrawing}\\usegdlibrary{trees}\n\n\\begin{document}\n\n" +
                toTikZPicture()
                + "\n\n\\end{document}\n%% Local Variables:\n%% TeX-engine: lualatex\n%% End:";
    }

    /* Alias */
    /**
     * Writes the tree as a LaTeX document which can be compiled using LuaLaTeX.
     * 
     * This is an alias of {@link () toLaTeXusingForest}.
     * <br>
     * <br>
     * The result can be used with the command:
     * 
     * <pre>
     * pdflatex some-file.tex
     * </pre>
     * 
     * @return a String of a full LaTeX document (to be compiled with pdflatex)
     */
    public String toLaTeX() {
        return this.toLaTeXusingForest();
    }

    private static final StringBuilder codeToOut = new StringBuilder(); // contain the LLVM AS code to print out
    private static Integer varCounter = 0; // count the number of variable %1, %2 ...
    private static final ArrayList<String> varStored = new ArrayList<>();  // store variable introduced by user
    private static Integer ifCounter = 0; // if1, if2 ...
    private static Integer whileCounter = 0;
    private static boolean read = false;
    private static boolean print = false;

    public void program() {
        // [1] <Program>  ->  begin <Code> end
        codeToOut.append("define i32 @main() {\n");
        children.get(1).code();
        codeToOut.append("ret i32 0\n}\n");

        if (read) {codeToOut.append(get_read());}
        if (print) {codeToOut.append(get_print());}
    }

    public void code() {
        // [2] <Code>  ->  <InstList>
        // [3] <Code>  ->  EPSILON
        // if it has a child, it is not EPSILON so we call instList
        if (children.get(0).label.isNonTerminal()) {
            children.get(0).instructionList();
        }
    }

    public void instructionList() {
        // [4] <InstList>  ->  <Instruction><InstListTail>
        // call instruction
        if (children.get(0).label.isNonTerminal()) {
            children.get(0).instruction();
        }

        // call instructionListTail
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).instructionListTail();
        }
    }

    public void instructionListTail() {
        // [5] <InstListTail>  ->  ...<Instruction><InstListTail>
        // [6] <InstListTail>  ->  EPSILON

        LexicalUnit lu = children.get(0).label.getTerminal();
        if (lu == LexicalUnit.DOTS) {
            children.get(1).instruction();
            children.get(2).instructionListTail();
        }
    }

    public void instruction() {
        // [7] <Instruction>  ->  <Assign>
        // [8] <Instruction>  ->  <If>
        // [9] <Instruction>  ->  <While>
        // [10] <Instruction>  ->  <Print>
        // [11] <Instruction>  ->  <Read>
        // [12] <Instruction>  ->  begin <InstList> end

        if (children.get(0).label.isNonTerminal()) {
            switch (children.get(0).label.getNonTerminal()) {
                case Assign -> children.get(0).assignExpr();
                case If -> children.get(0).ifExpr();
                case While -> children.get(0).whileExpr();
                case Print -> children.get(0).printExpr();
                case Read -> children.get(0).readExpr();
            }
            return;
        }

        // [12] <Instruction>  ->  begin <InstList> end
        if (children.get(0).label.getType().equals(LexicalUnit.BEG)) {
            children.get(1).instructionList();
            return;
        }

        throw new RuntimeException("Error in instruction");
    }

    public String assignExpr() {
        // [13] <Assign>  ->  [Varname] := <ExprArith>

        String varName = children.get(0).label.getValue().toString();
        String code;

        // Allocation de mémoire pour la variable si elle n'a pas été allouée auparavant
        if (!varStored.contains(varName)) {
            code = "  %" + varName + "= alloca i32\n";
            codeToOut.append(code);
            varStored.add(varName);
        }

        // Évaluer l'expression arithmétique (côté droit de l'affectation)
        String exprResultVar = children.get(2).exprArith();

        // Stocker le résultat dans la variable (côté gauche de l'affectation)
        code = "  store i32 " + exprResultVar + ", i32* %" + varName + "\n";
        codeToOut.append(code);

        return null;
    }
    public String exprArith() {
        // [14] <ExprArith>  ->  <Prod> <ExprArith'>
        String var1 = children.get(0).prod();
        if (children.size() > 1) {
            return exprArithPrime(var1, children.get(1));
        } else {
            return var1;
        }
    }


//    public void exprArithPrime() {
//        // [15] <ExprArith'>  ->  + <Prod> <ExprArith'>
//        // [16] <ExprArith'>  ->  - <Prod> <ExprArith'>
//        // [17] <ExprArith'>  ->  EPSILON
//
//        LexicalUnit lu = children.get(0).label.getTerminal();
//        switch (lu) {
//            case PLUS -> {
//                String var1 = children.get(1).prod();
//                String rightVar = "%" + (varCounter-1);
//                String resVar = "%" + ++varCounter;
//                String code = "  " + resVar + "= add i32 " + var1 + ", " + rightVar + "\n";
//                codeToOut.append(code);
//                children.get(2).exprArithPrime();
//            }
//            case MINUS -> {
//                String var1 = children.get(1).prod();
//                String rightVar = "%" + (varCounter-1);
//                String restVar = "%" + ++varCounter;
//                String code = "  " + restVar + "= sub i32 " + var1 + ", " + rightVar + "\n";
//                codeToOut.append(code);
//                children.get(2).exprArithPrime();
//            }
//        }
//        System.out.println("Error in exprArithPrime");
//        System.out.println(lu);
//    }

    public String exprArithPrime(String leftVar, ParseTree exprArithPrimeTree) {
        // [15] <ExprArith'>  ->  + <Prod> <ExprArith'>
        // [16] <ExprArith'>  ->  - <Prod> <ExprArith'>
        // [17] <ExprArith'>  ->  EPSILON
        LexicalUnit lu = exprArithPrimeTree.children.get(0).label.getTerminal();
        switch (lu) {
            case PLUS -> {
                String rightVar = exprArithPrimeTree.children.get(1).prod();
                String resultVar = "%" + ++varCounter;
                String code = "  " + resultVar + "= add i32 " + leftVar + ", " + rightVar + "\n";
                codeToOut.append(code);
                if (exprArithPrimeTree.children.size() > 2) {
                    return exprArithPrime(resultVar, exprArithPrimeTree.children.get(2));
                } else {
                    return resultVar;
                }
            }
            case MINUS -> {
                String rightVar = exprArithPrimeTree.children.get(1).prod();
                String resultVar = "%" + ++varCounter;
                String code = "  " + resultVar + "= sub i32 " + leftVar + ", " + rightVar + "\n";
                codeToOut.append(code);
                if (exprArithPrimeTree.children.size() > 2) {
                    return exprArithPrime(resultVar, exprArithPrimeTree.children.get(2));
                } else {
                    return resultVar;
                }
            }
            // Gérer d'autres cas si nécessaire
        }
        // Retourner le résultat de la dernière opération effectuée
        return leftVar;
    }



    public String prod() {
        // [18] <Prod>  ->  <Atom> <Prod'>
        String var1 = children.get(0).atom();
        if (children.size() > 1) {
            return prodPrime(var1, children.get(1));
        } else {
            return var1;
        }
    }

    public String prodPrime(String leftVar, ParseTree prodPrimeTree) {
        // [19] <Prod'>  ->  * <Atom> <Prod'>
        // [20] <Prod'>  ->  / <Atom> <Prod'>
        // [21] <Prod'>  ->  EPSILON

        LexicalUnit lu = prodPrimeTree.children.get(0).label.getTerminal();

        switch (lu) {
            case TIMES -> {
                String rightVar = prodPrimeTree.children.get(1).atom();
                String resultVar = "%" + ++varCounter;
                String code = "  " + resultVar + "= mul i32 " + leftVar + ", " + rightVar + "\n";
                codeToOut.append(code);
                if (prodPrimeTree.children.size() > 2) {
                    return prodPrime(resultVar, prodPrimeTree.children.get(2));
                } else {
                    return resultVar;
                }
            }
            case DIVIDE -> {
                String rightVar = prodPrimeTree.children.get(1).atom();
                String resultVar = "%" + ++varCounter;
                String code = "  " + resultVar + "= sdiv i32 " + leftVar + ", " + rightVar + "\n";
                codeToOut.append(code);
                if (prodPrimeTree.children.size() > 2) {
                    return prodPrime(resultVar, prodPrimeTree.children.get(2));
                } else {
                    return resultVar;
                }
            }
            // Gérer d'autres cas si nécessaire
        }
        // Retourner le résultat de la dernière opération effectuée
        return leftVar;
    }




    public String atom() {
        // [22] <Atom>  ->  - <Atom>
        // [23] <Atom>  ->  ( <ExprArith> )
        // [24] <Atom>  ->  [Varname]
        // [25] <Atom>  ->  [Number]

        LexicalUnit lu = children.get(0).label.getTerminal();
        switch (lu) {
            case NUMBER -> {
                String nextVar = "%" + ++varCounter;
                String code = "  " + nextVar + "= add i32 0 , " + children.get(0).label.getValue() + "\n";
                codeToOut.append(code);
                return nextVar;
            }
            case MINUS -> {
                children.get(1).atom();
                String prevVar = "%" + (varCounter-1);
                String currentVar = "%" + varCounter;
                String nextVar = "%" + ++varCounter;
                String code = "  " + nextVar +"= mul i32 " + " -1" + " , " + currentVar + "\n";
                codeToOut.append(code);
//                code = "  %" + ++varCounter + "= mul i32 " + prevVar + " , " + nextVar + "\n";
//                codeToOut.append(code);
                return nextVar;
            }
            case VARNAME -> {
                String code = "  " + "%" + ++varCounter + "= load i32, i32* " + "%" + children.get(0).label.getValue() + "\n";
                codeToOut.append(code);
                return "%" + varCounter;
            }
            case LPAREN -> children.get(1).exprArith();
        }
        return "%" + varCounter;

    }

    public void ifExpr() {
        // [26] <If>  -> if <Cond> then <Instruction> else <IfTail>
        System.out.println("ifExpr: ");
        String var = children.get(1).cond();
        String code = "  br i1 " + var + ", label %if" + ifCounter; // conditional jump to if or else
        if (children.get(3).label.isNonTerminal()) {
            code += ", label %Else" + ifCounter + "\n";
        } else {
            code += ", label %EndIf" + ifCounter + "\n";
        }

        code += "if" + ifCounter + ":\n";
        codeToOut.append(code);
        children.get(3).instruction();
        code = "  br label %EndIf" + ifCounter + "\n";

        if (children.get(5).label.isNonTerminal()) { // if there is an else statement
            code += "Else" + ifCounter + ":\n";
            codeToOut.append(code);
            children.get(5).ifTail();
            code = "  br label %EndIf" + ifCounter + "\n";
        }

        code += "EndIf" + ifCounter + ":\n";
        codeToOut.append(code);
        ifCounter++;
    }

    public void ifTail() {
        // [27] <IfTail>  ->  <Instruction>
        // [28] <IfTail>  ->  EPSILON
        System.out.println("ifTail: ");
        if (children.get(0).label.isNonTerminal()) {
            children.get(0).instruction();
        }
    }

    public String cond() {
        // [29] <Cond>  ->  <Conj> <Cond'>

        String var = children.get(0).conj();
        if (children.size() > 1) {
            return condPrime(var, children.get(1));
        } else {
            return var;
        }
    }

    public String condPrime(String leftVar, ParseTree condPrimeTree) {
        // [30] <Cond'>  ->  or <Conj> <Cond'>
        // [31] <Cond'>  ->  EPSILON

        LexicalUnit lu = condPrimeTree.children.get(0).label.getTerminal();
        if (lu == LexicalUnit.OR) {
            String rightVar = condPrimeTree.children.get(1).conj();
            String code = "  %" + ++varCounter + "= or i1 " + leftVar + ", " + rightVar + "\n";
            codeToOut.append(code);
            if (condPrimeTree.children.size() > 2) {
                return condPrime("%" + varCounter, condPrimeTree.children.get(2));
            } else {
                return "%" + varCounter;
            }
        }
        return "%" + varCounter;
    }

    public String conj() {
        // [32] <Conj>  ->  <SimpleCond> <Conj'>
        String var = children.get(0).simpleCond();
        if (children.size() > 1) {
            return conjPrime(var, children.get(1));
        } else {
            return var;
        }
    }

    public String conjPrime(String leftVar, ParseTree conjPrimeTree) {
        // [33] <Conj'>  ->  and <SimpleCond> <Conj'>
        // [34] <Conj'>  ->  EPSILON

        LexicalUnit lu = conjPrimeTree.children.get(0).label.getTerminal();
        if (lu == LexicalUnit.AND) {
            String rightVar = conjPrimeTree.children.get(1).simpleCond();
            String code = "  %" + ++varCounter + "= and i1 " + leftVar + ", " + rightVar + "\n";
            codeToOut.append(code);
            if (conjPrimeTree.children.size() > 2) {
                return conjPrime("%" + varCounter, conjPrimeTree.children.get(2));
            } else {
                return "%" + varCounter;
            }
        }
        return "%" + varCounter;
    }

    public String simpleCond() {
        // [35] <SimpleCond>  ->  {<Cond>}
        // [36] <SimpleCond>  ->  <ExprArith> <Comp> <ExprArith>

        LexicalUnit lu = children.get(0).label.getTerminal();
        String var = null;

        if (lu == LexicalUnit.LBRACK) {
            // Condition encapsulée
            var = children.get(1).cond();  // Capturer le résultat de la condition
        } else {
            // Comparaison arithmétique
            String leftVar = children.get(0).exprArith();  // Évaluer la première expression arithmétique
            String comp = children.get(1).compOp();        // Obtenir l'opérateur de comparaison
            String rightVar = children.get(2).exprArith(); // Évaluer la deuxième expression arithmétique
            System.out.println("leftVar: " + leftVar + " comp: " + comp + " rightVar: " + rightVar);

            // Générer le code de comparaison
            String resultVar = "%" + ++varCounter;
            String code = "  " + resultVar + "= icmp " + comp + " i32 " + leftVar + ", " + rightVar + "\n";
            codeToOut.append(code);
            var = resultVar;
        }

        return var;
    }


    public String compOp() {
        // [37] <Comp>  ->  =
        // [38] <Comp>  ->  <
        return switch (children.get(0).label.getTerminal()) {
            case EQUAL -> "eq";
            case SMALLER -> "slt";
            default -> null;
        };

    }

    public void whileExpr() {
        Integer whileCount = whileCounter++;
        // [39] <While>  ->  while <Cond> do <Instruction>
        String code = "  br label %CondWhile" + whileCount + "\n" +  // unconditional jump to while
                "CondWhile" + whileCount +":\n";
        codeToOut.append(code); // get code of WHILE condition
        children.get(1).cond();
        code = "  br i1 " + "%" + varCounter + ", label %While" + whileCount + ", label %WhileEnd" +
                whileCount + "\n" +
                "While" + whileCount + ":\n";
        codeToOut.append(code);
        children.get(3).instruction();
        code = "  br label %CondWhile" + whileCount+ "\n" +
                "WhileEnd" + whileCount + ":\n";
        codeToOut.append(code);
    }


    public String printExpr() {
        // [40] <Print>  ->  print([VarName])
        String code = "  " + "%" + ++varCounter + "= load i32, i32* %" + children.get(2).label.getValue().toString() + "\n"
                + "  call void @println(i32 " + "%" + varCounter + ")\n";
        codeToOut.append(code);
        print = true;
        return "%" + varCounter;
    }

    public String readExpr() {
        String code;
        if (!varStored.contains(children.get(2).label.getValue())){
            code = "  %" + children.get(2).label.getValue() + "= alloca i32\n";
            codeToOut.append(code);
            varStored.add(children.get(2).label.getValue().toString());
        }
        code = "  " + "%" + ++varCounter + "= call i32 @readInt()\n" +
                "  store i32 " + "%" + varCounter + ", i32* %" + children.get(2).label.getValue() + "\n";
        codeToOut.append(code);
        read = true;
        return "%" + varCounter;
    }

    private static String get_print(){
        return """
                @.strP= private unnamed_addr constant [4 x i8] c"%d\\0A\\00", align 1
                define void @println(i32 %var) {
                  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
                  ret void
                }
                declare i32 @printf(i8*, ...)
                """;
    }

    private static String get_read(){
        return """
                @.strR= private unnamed_addr constant [3 x i8] c"%d\\00", align 1
                define i32 @readInt() {
                  %var= alloca i32, align 4
                  %1= call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32 0, i32 0), i32* %var)
                  %2= load i32, i32* %var, align 4
                  ret i32 %2
                }
                declare i32 @scanf(i8*, ...)
                """;
    }

    public StringBuilder getCodeToOut() {
        return codeToOut;
    }
}
