import nodes.Node;
import printer.Printer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        JavaParserFromPascal parser = new JavaParserFromPascal(args.length != 0 ? Files.newInputStream(Paths.get(args[0])) : System.in);
        Node nodes = parser.program();
        System.out.println("Парсинг завершен!");
        System.out.println(Printer.printTree(nodes.tree(), "\n"));
    }
}
