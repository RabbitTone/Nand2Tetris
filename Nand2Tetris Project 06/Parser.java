import java.util.Scanner;
import java.io.*;

public class Parser
{
  Scanner file;
  String command;
  
  static final int A_COMMAND = 0;
  static final int C_COMMAND = 1;
  static final int L_COMMAND = 2;
  
  public Parser(String filename) throws IOException
  {
    File f = new File(filename);
    this.file = new Scanner(f);
  }
  
  public boolean hasMoreCommands() {
    return file.hasNextLine();
  }
  
  public void advance() {
    this.command = file.nextLine();
  }

  public int commandType() {
    char first = command.charAt(0);
    
    if(first == '@')
      return A_COMMAND;
    else if(first == '(')
      return L_COMMAND;
    else
      return C_COMMAND;
  }
  
  public String symbol() {
    int length = command.length();
    int i;
    if(commandType() == A_COMMAND){
      return command.substring(1,length);
    }
    else if(commandType() == L_COMMAND){
      i = command.indexOf(')');
      return command.substring(1,i);
    }
    return null;
  }
  
  public String dest() {
    int equals = command.indexOf('=');
    int semicolon = command.indexOf(';');
    if(equals == -1){
      return command.substring(0, semicolon);
    }
    else{
      return command.substring(0, equals);
    }
  }
  
  public String comp() {
    int equals = command.indexOf('=');
    if(equals == -1){
      return "";
    }
    else
      return command.substring(equals + 1,command.length());
  }
  
  public String jump() {
    int semicolon = command.indexOf(';');
    if(semicolon == -1){
      return "";
    }
    else{
      return command.substring(semicolon + 1,command.length());
    }
  }
}




