package at.fhburgenland.fogl.statemachine.divedby5;

import at.fhburgenland.fogl.statemachine.CommandLineInterface;
import at.fhburgenland.fogl.statemachine.DBConfiguration;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 16.06.2021
 * Time: 23:01
 */
public class DivedBy5AppStarter {

  public static void main(String[] args) {
    final File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "file.txt");
    final DBConfiguration dbConfiguration = new DBConfiguration("jdbc:postgresql://10.11.12.13:5432/fogl-db", "bswe", "fogl", "SELECT * FROM zahlen");
    new CommandLineInterface(StateMachineCreator::createDivideBy5StateMachine, file, "Bitte geben Sie eine Zahl ein: ", dbConfiguration)
        .startMenu();
  }
}
