import nodes.Node;
import printer.Printer;
import semantic.GlobalScope;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        int a1;
        int a2;
        int a3;
        int a4;
        Scanner scanner = new Scanner(System.in);
        a1 = Integer.parseInt(scanner.nextLine());
        a2 = Integer.parseInt(scanner.nextLine());
        a3 = Integer.parseInt(scanner.nextLine());
        a4 = Integer.parseInt(scanner.nextLine());

        boolean a = a1 >= a2 || a3 < a1 && a4 >= a2;
        System.out.println(a);
//        JavaParserFromPascal parser = new JavaParserFromPascal(args.length != 0 ? Files.newInputStream(Paths.get(args[0])) : System.in);
//        Node nodes = parser.program();
//        System.out.println("Парсинг завершен!");
//        System.out.println(Printer.printTree(nodes.tree(), "\n"));
//        nodes.initialize(new GlobalScope());
//        nodes.semanticCheck();
//        System.out.println("Семантических ошибок не обнаружено");
//        System.out.println(Printer.printTree(nodes.tree(), "\n"));
    }
}
