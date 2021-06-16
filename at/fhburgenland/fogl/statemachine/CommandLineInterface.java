package at.fhburgenland.fogl.statemachine;

import at.fhburgenland.fogl.statemachine.divedby5.StateMachineCreator;

import java.io.File;
import java.io.FileNotFoundException;
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

  public CommandLineInterface(Function<String, StateMachine> stateMachineCreator, File file, String inputQuestion) {
    this.stateMachineCreator = stateMachineCreator;
    this.file = file;
    this.inputQuestion = inputQuestion;
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
}
