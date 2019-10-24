import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import sun.launcher.resources.launcher;

import java.io.*;

public class Main
{
  public static void main(String[] args) throws IOException
  {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter name of file");
    String file = keyboard.nextLine();
    String bareName = file.substring(0, file.indexOf('.'));
    
    PrintWriter pw = new PrintWriter(bareName + ".hack");
    UtilityMethods um = new UtilityMethods();
    Parser pf = new Parser(file);
    Parser ps = new Parser(file);
    Code code = new Code();
    int count = 16;
    String hold;

    //Symbol Table
    SymbolTable symT = new SymbolTable();
    symT.addEntry("R0", 0); symT.addEntry("R1", 1);
    symT.addEntry("R2", 2); symT.addEntry("R3", 3);
    symT.addEntry("R4", 4); symT.addEntry("R5", 5);
    symT.addEntry("R6", 6); symT.addEntry("R7", 7);
    symT.addEntry("R8", 8); symT.addEntry("R9", 9);
    symT.addEntry("R10", 10); symT.addEntry("R11", 11);
    symT.addEntry("R12", 12); symT.addEntry("R13", 13);
    symT.addEntry("R14", 14); symT.addEntry("R15", 15);
    symT.addEntry("SCREEN", 16384); symT.addEntry("KDB", 24576);
    symT.addEntry("SP", 0); symT.addEntry("LCL", 1);
    symT.addEntry("ARG", 2); symT.addEntry("THIS", 3);
    symT.addEntry("THAT", 4);
    
    while(pf.hasMoreCommands()) {
      pf.advance();
      if(pf.commandType() == 2){
        symT.addEntry(pf.symbol(), count);
        count++;
      }
    }
    while(ps.hasMoreCommands()){
      ps.advance();
      if(ps.commandType() == 0){
        hold = ps.symbol();
        if(um.isNumeric(hold)){
          pw.print("0" + um.toBinary(Integer.parseInt(hold)) +"\n");
        }
        else if(symT.contains(hold)){
            pw.print("0" + um.toBinary(symT.getAddress(hold))+"\n");
          }
          else{
            symT.addEntry(hold, count);
            count++;
            pw.print("0" + um.toBinary(symT.getAddress(hold))+"\n");
          }
        }
      else if(ps.commandType() == 1){
        String d = code.dest(ps.dest());
        String c = code.comp(ps.comp());
        String j = code.jump(ps.jump());

        pw.print("111" + c + d + j+"\n");
      }
    }
    pw.close();
  }
}
