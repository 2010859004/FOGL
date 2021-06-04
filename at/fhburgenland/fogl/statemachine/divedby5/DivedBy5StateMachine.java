package at.fhburgenland.fogl.statemachine.divedby5;

import at.fhburgenland.fogl.statemachine.Result;
import at.fhburgenland.fogl.statemachine.StateMachine;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 13:04
 */
class DivedBy5StateMachine implements StateMachine {
  private final String input;

  DivedBy5StateMachine(String input) {
    this.input = input;
  }

  public String getInput() {
    return input;
  }

  @Override
  public Result validate() {
    ResultConstant error = ResultConstant.INPUT_INVALID;
    State currentState = State.START;

    if (input == null || input.trim().isEmpty()) {
      return createResultGenerator(error);
    }

    for (char characterToCheck : input.toCharArray()) {

      final Optional<Alphabet> alphabet = Alphabet.getAlphabetByCharacter(characterToCheck);

      if (alphabet.isEmpty()) {
        return createResultGenerator(ResultConstant.INPUT_INVALID);
      }

      switch (alphabet.get()) {
        case INPUT_1, INPUT_2, INPUT_3, INPUT_4, INPUT_6, INPUT_7, INPUT_8, INPUT_9 -> {
          if (currentState == State.END) {
            currentState = State.START;
          }
          error = ResultConstant.NOT_DIVIDABLE_BY_5;
        }
        case INPUT_0, INPUT_5 -> {
          if (currentState == State.START) {
            currentState = State.END;
          }
          error = ResultConstant.DIVIDABLE_BY_5;
        }
      }
    }

    return createResultGenerator(error);
  }

  private DivideBy5Result createResultGenerator(ResultConstant resultConstant) {
    return new DivideBy5Result(resultConstant, input);
  }
}
