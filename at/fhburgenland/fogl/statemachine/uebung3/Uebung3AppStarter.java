package at.fhburgenland.fogl.statemachine.uebung3;

import at.fhburgenland.fogl.statemachine.CommandLineInterface;
import at.fhburgenland.fogl.statemachine.DBConfiguration;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 16.06.2021
 * Time: 23:01
 */
public class Uebung3AppStarter {

  public static void main(String[] args) {
    final String separator = System.getProperty("file.separator");
    final String filePath = String.join(separator, "src/at/fhburgenland/fogl/statemachine/uebung3/Uebung3_DEA_Examples.txt".split("/"));
    final File file = new File(System.getProperty("user.dir") + separator +
        filePath
    );
    final DBConfiguration dbConfiguration = new DBConfiguration("jdbc:postgresql://10.11.12.13:5432/fogl-db", "bswe", "fogl", "SELECT * FROM Formeln");
    new CommandLineInterface(StateMachineCreator::createMathFormulaStateMachine, file, "Bitte geben Sie eine Formel ein:", dbConfiguration)
        .startMenu();
  }
}
