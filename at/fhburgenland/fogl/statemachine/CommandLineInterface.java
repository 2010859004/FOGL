package at.fhburgenland.fogl.statemachine;

import at.fhburgenland.fogl.origversion.DEAd5Constants;
import at.fhburgenland.fogl.origversion.DEAd5Methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 14:08
 */
public class CommandLineInterface {

  private Scanner scanner;
  private final Function<String, StateMachine> stateMachineCreator;
  private final File file;
  private final String inputQuestion;
  private final DBConfiguration dbConfiguration;

  public CommandLineInterface(Function<String, StateMachine> stateMachineCreator, File file, String inputQuestion, DBConfiguration dbConfiguration) {
    this.stateMachineCreator = stateMachineCreator;
    this.file = file;
    this.inputQuestion = inputQuestion;
    this.dbConfiguration = dbConfiguration;
  }

  public void startMenu() {
    try {
      scanner = new Scanner(System.in);
      String option;

      while (true) {
        printOptions();
        System.out.println();
        System.out.print("Auswahl: ");
        option = scanner.nextLine();

        switch (option) {
          case "1" -> {
            System.out.println();
            String input = askForInput(inputQuestion);
            validateInput(stateMachineCreator.apply(input));
            return;
          }
          case "2" -> {
            System.out.println();
            fileMode(file);
            return;
          }
          case "3" -> {
            System.out.println();
            runDBMode();
            return;
          }
          default -> {
            System.out.println();
            System.out.println("Bitte wählen Sie eine der angeführten Optionen aus!");
            System.out.println();
          }
        }
      }
    } finally {
      scanner.close();
    }
  }

  private void validateInput(StateMachine machine) {
    final Result validate = machine.validate();
    System.out.println(validate.getResultText());
  }

  private String askForInput(String inputQuestion) {
    System.out.print(inputQuestion);
    return scanner.nextLine();
  }

  private void printOptions() {
    System.out.println("*************************************************");
    System.out.println("*                                               *");
    System.out.println("*                  WILLKOMMEN                   *");
    System.out.println("*                                               *");
    System.out.println("*      Bitte wählen Sie eine der Optionen:      *");
    System.out.println("*      (1) manuelle Eingabe                     *");
    System.out.println("*      (2) einlesen aus Datei                   *");
    System.out.println("*      (3) einlesen aus Datenbank               *");
    System.out.println("*                                               *");
    System.out.println("*************************************************");
  }

  private void fileMode(File file) {

    try {
      Scanner fileReader = new Scanner(file);

      while (fileReader.hasNextLine()) {
        String currentLine = fileReader.nextLine();
        validateInput(stateMachineCreator.apply(currentLine));
      }
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Die Datei: " + file.getAbsolutePath() + " konnte nicht gelesen werden!");
      e.printStackTrace();
    }
  }

  private void runDBMode(){
    Connection con;
    PreparedStatement pst;

    try {
      final String dbUrl = dbConfiguration.getDbUrl();
      final String dbUser = dbConfiguration.getDbUser();
      final String dbPw = dbConfiguration.getDbPw();
      final String dbQuery = dbConfiguration.getDbQuery();
      con = DriverManager.getConnection(dbUrl, dbUser, dbPw);
      pst = con.prepareStatement(dbQuery);
      ResultSet rs = pst.executeQuery();

      while(rs.next()){
        String nextLine = rs.getString(1);
        validateInput(stateMachineCreator.apply(nextLine));
      }
      rs.close();
      pst.close();
      con.close();
    } catch (SQLException throwables) {
      DEAd5Methods.printError();
      System.out.println("Fehler beim Zugriff auf die DB!");
      throwables.printStackTrace();
    }
  }
}
