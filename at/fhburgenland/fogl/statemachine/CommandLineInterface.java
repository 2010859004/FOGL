package at.fhburgenland.fogl.statemachine;

import at.fhburgenland.fogl.statemachine.divedby5.StateMachineCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 14:08
 */
public class CommandLineInterface {

  private Scanner scanner;

  public static void main(String[] args) {
    new CommandLineInterface()
        .startMenu();
  }

  private void startMenu() {
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
            String input = askForInput();
            validateInput(input);
            return;
          }
          case "2" -> {
            System.out.println();
            fileMode();
            return;
          }
          case "3" -> {
            System.out.println();
            //          DEAd5Methods.dbMode();
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

  private void validateInput(String input) {
    final StateMachine machine = StateMachineCreator.createDivideBy5StateMachine(input);
    final Result validate = machine.validate();
    System.out.println(validate.getResultText());
  }

  private String askForInput() {
    System.out.print("Bitte geben Sie eine Zahl ein: ");
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

  private void fileMode(){
    File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "file.txt");

    try {
      Scanner fileReader = new Scanner(file);

      while(fileReader.hasNextLine()){
        String currentLine = fileReader.nextLine();
        validateInput(currentLine);
      }
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Die Datei: " + file.getAbsolutePath() + " konnte nicht gelesen werden!");
      e.printStackTrace();
    }
  }
}
