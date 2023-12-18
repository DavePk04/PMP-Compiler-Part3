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
        System.out.println("Program1");
        // [1] <Program>  ->  begin <Code> end
        codeToOut.append("define i32 @main() {\n");
        children.get(1).code();
        codeToOut.append("ret i32 0\n}\n");

        if (read) {codeToOut.append(get_print());}
        if (print) {codeToOut.append(get_read());}
        System.out.println("Program2");
    }

    public void code() {
        // [2] <Code>  ->  <InstList>
        // [3] <Code>  ->  EPSILON
        System.out.println("Code1");

        // if it has a child, it is not EPSILON so we call instList
        if (children.get(0).label.isNonTerminal()) {
            children.get(0).instructionList();
        }
        System.out.println("Code2");
    }

    public void instructionList() {
        // [4] <InstList>  ->  <Instruction><InstListTail>
        System.out.println("InstructionList1");

        // call instruction
        if (children.get(0).label.isNonTerminal()) {
            children.get(0).instruction();
        }

        // call instructionListTail
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).instructionListTail();
        }
        System.out.println("InstructionList");
    }

    public void instructionListTail() {
        // [5] <InstListTail>  ->  ...<Instruction><InstListTail>
        // [6] <InstListTail>  ->  EPSILON
        System.out.println("InstructionListTail1");

        // if it has a child, it is not EPSILON so we call Instruction
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).instruction();
        }

        // if it has a child, it is not EPSILON so we call instructionListTail (treat the next instruction)
        if (children.get(2).label.isNonTerminal()) {
            children.get(2).instructionListTail();
        }

        System.out.println("InstructionListTail");
    }

    public void instruction() {
        // [7] <Instruction>  ->  <Assign>
        // [8] <Instruction>  ->  <If>
        // [9] <Instruction>  ->  <While>
        // [10] <Instruction>  ->  <Print>
        // [11] <Instruction>  ->  <Read>
        // [12] <Instruction>  ->  begin <InstList> end

        System.out.println("Instruction1");

        if (children.get(0).label.isNonTerminal()) {
            switch (children.get(0).label.getNonTerminal()) {
                case Assign -> children.get(0).assignExpr();
                case If -> children.get(0).ifExpr();
                case While -> children.get(0).whileExpr();
                case Print -> children.get(0).printExpr();
                case Read -> children.get(0).readExpr();
            }
        }
        // call instructionList
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).instructionList();
        }
        System.out.println("Instruction");
    }

    public void assignExpr() {
        // [13] <Assign>  ->  [Varname] := <ExprArith>

        System.out.println("Assign1");

        String code;
        if (!varStored.contains(children.get(0).label.getValue())) {
            // if the variable is not stored yet
            // Memory allocation of current variable
            code = "  %" + children.get(0).label.getValue() + "= alloca i32\n";
            codeToOut.append(code);
            varStored.add(children.get(0).label.getValue().toString());
        }
        children.get(2).exprArith();
        code = "  store i32 %" + varCounter + ", i32* %" + children.get(0).label.getValue() + "\n";
        codeToOut.append(code);

        System.out.println("Assign");
    }

    public void exprArith() {

        System.out.println("ExprArith1");
        // [14] <ExprArith>  ->  <Prod> <ExprArith'>
        children.get(0).prod();
        children.get(1).exprArithPrime();

        System.out.println("ExprArith");
    }

    public void exprArithPrime() {
        System.out.println("ExprArithPrime1");
        // [15] <ExprArith'>  ->  + <Prod> <ExprArith'>
        // [16] <ExprArith'>  ->  - <Prod> <ExprArith'>
        // [17] <ExprArith'>  ->  EPSILON

        // if it has a child, it is not EPSILON so we call Prod
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).prod();
        }
        // if it has a child, it is not EPSILON so we call exprArithPrime (treat the next Prod)
        if (children.get(2).label.isNonTerminal()) {
            children.get(2).exprArithPrime();
        }

        System.out.println("ExprArithPrime");
    }

    public void prod() {
        System.out.println("Prod1");
        // [18] <Prod>  ->  <Atom> <Prod'>
        children.get(0).atom();
        children.get(1).prodPrime();

        System.out.println("Prod");
    }

    public void prodPrime() {
        System.out.println("ProdPrime1");
        // [19] <Prod'>  ->  * <Atom> <Prod'>
        // [20] <Prod'>  ->  / <Atom> <Prod'>
        // [21] <Prod'>  ->  EPSILON

        // if it has a child, it is not EPSILON so we call Atom
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).atom();
        }
        // if it has a child, it is not EPSILON so we call prodPrime (treat the next Atom)
        if (children.get(2).label.isNonTerminal()) {
            children.get(2).prodPrime();
        }

        System.out.println("ProdPrime");
    }

    public void atom() {
        // [22] <Atom>  ->  - <Atom>
        // [23] <Atom>  ->  ( <ExprArith> )
        // [24] <Atom>  ->  [Varname]
        // [25] <Atom>  ->  [Number]

        System.out.println("Atom1");

        LexicalUnit lu = children.get(0).label.getTerminal();
        switch (lu) {
            case NUMBER -> {
                String nextVar = "%" + ++varCounter;
                String code = "  " + nextVar + "= add i32 0 , " + children.get(0).label.getValue() + "\n";
                codeToOut.append(code);
            }
            case MINUS -> {
                children.get(1).atom();
                String currentVar = "%" + varCounter;
                String nextVar = "%" + ++varCounter;
                String code = "  " + nextVar +"= mul i32 " + " -1" + " , " + currentVar + "\n";
                codeToOut.append(code);
            }
            case VARNAME -> {
                String code = "  " + "%" + ++varCounter + "= load i32, i32* " + "%" + children.get(0).label.getValue() + "\n";
                codeToOut.append(code);
            }
            case LPAREN -> children.get(1).exprArith();
        }

        System.out.println("Atom");
    }

    public void ifExpr() {
        System.out.println("If1");
        // [26] <If>  -> if <Cond> then <Instruction> else <IfTail>
        children.get(1).cond();
        String code = "  br i1 %" + varCounter + ", label %if" + ifCounter; // conditional jump to if or else
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

        System.out.println("If");
    }

    public void ifTail() {
        System.out.println("IfTail1");
        // [27] <IfTail>  ->  <Instruction>
        // [28] <IfTail>  ->  EPSILON
        if (children.get(0).label.isNonTerminal()) {
            children.get(0).instruction();
        }
        System.out.println("IfTail");
    }

    public void cond() {
        // [29] <Cond>  ->  <Conj> <Cond'>
        children.get(0).conj();
        children.get(1).condPrime();

        System.out.println("Cond");
    }

    public void condPrime() {
        System.out.println("CondPrime1");
        // [30] <Cond'>  ->  or <Conj> <Cond'>
        // [31] <Cond'>  ->  EPSILON
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).conj();
        }
        if (children.get(2).label.isNonTerminal()) {
            children.get(2).condPrime();
        }

        System.out.println("CondPrime");
    }

    public void conj() {
        System.out.println("Conj1");
        // [32] <Conj>  ->  <SimpleCond> <Conj'>
        children.get(0).simpleCond();
        children.get(1).conjPrime();

        System.out.println("Conj");
    }

    public void conjPrime() {
        System.out.println("ConjPrime1");
        // [33] <Conj'>  ->  and <SimpleCond> <Conj'>
        // [34] <Conj'>  ->  EPSILON
        if (children.get(1).label.isNonTerminal()) {
            children.get(1).simpleCond();
        }
        if (children.get(2).label.isNonTerminal()) {
            children.get(2).conjPrime();
        }

        System.out.println("ConjPrime");
    }

    public void simpleCond() {
        System.out.println("SimpleCond1");
        // [35] <SimpleCond>  ->  {<Cond>}
        // [36] <SimpleCond>  ->  <ExprArith> <Comp> <ExprArith>

        if (children.get(0).label.isNonTerminal()) { // if it is a ExprArith
            children.get(0).exprArith();
            children.get(1).compOp();
            children.get(2).exprArith();
        }
        if (children.get(1).label.isNonTerminal()) { // if it is a Cond
            children.get(1).cond();
        }

        System.out.println("SimpleCond");
    }

    public String compOp() {
        // [37] <Comp>  ->  =
        // [38] <Comp>  ->  <
        System.out.println("Comp");
        return switch (children.get(0).label.getTerminal()) {
            case EQUAL -> "eq";
            case SMALLER -> "slt";
            default -> null;
        };

    }

    public void whileExpr() {
        System.out.println("While1");
        // [39] <While>  ->  while <Cond> do <Instruction>
        String code = "While" + whileCounter + ":\n";
        codeToOut.append(code);
        children.get(1).cond();
        code = "  br i1 %" + varCounter + ", label %WhileBody" + whileCounter + ", label %EndWhile" + whileCounter + "\n";
        code += "WhileBody" + whileCounter + ":\n";
        codeToOut.append(code);
        children.get(3).instruction();
        code = "  br label %While" + whileCounter + "\n";
        code += "EndWhile" + whileCounter + ":\n";
        codeToOut.append(code);
        whileCounter++;

        System.out.println("While");
    }

    public void printExpr() {
        System.out.println("Print1");
        // [40] <Print>  ->  print([VarName])
        String code = "  " + "%" + ++varCounter + "= load i32, i32* %" + children.get(2).label.getValue().toString() + "\n"
                + "  call void @println(i32 " + "%" + varCounter + ")\n";
        codeToOut.append(code);
        print = true;

        System.out.println("Print");
    }

    public void readExpr() {
        System.out.println("Read1");
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

        System.out.println("Read");
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
                  %1= call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32 0, i32 0), i32* %var)
                  %2= load i32, i32* %var, align 4
                  ret i32 %2
                }
                declare i32 @__isoc99_scanf(i8*, ...)
                """;
    }

    public StringBuilder getCodeToOut() {
        return codeToOut;
    }
}
