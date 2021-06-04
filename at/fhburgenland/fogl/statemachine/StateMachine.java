package at.fhburgenland.fogl.statemachine;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 12:55
 */
public interface StateMachine {

  /**
   * Validates the given input and returns a result.
   * @return the validated result.
   */
  Result validate();
}
