import nodes.ProgramNode;
import printer.Printer;
import semantic.GlobalScope;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        JavaParserFromPascal parser = new JavaParserFromPascal(args.length != 0 ? Files.newInputStream(Paths.get(args[0])) : System.in);
        ProgramNode programNode = parser.program();
        System.out.println("Парсинг завершен!");
        System.out.println(Printer.printTree(programNode.tree(), "\n"));
        programNode.initialize(new GlobalScope());
        programNode.semanticCheck();
        System.out.println("Семантических ошибок не обнаружено");
        System.out.println(Printer.printTree(programNode.tree(), "\n"));
        FileWriter fw = new FileWriter("output/" + programNode.getProgramName() + ".j");
        fw.write(programNode.generateCode().toString());
        fw.close();
    }
}
