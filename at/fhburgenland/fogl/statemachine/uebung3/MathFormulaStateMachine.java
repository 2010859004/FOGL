package at.fhburgenland.fogl.statemachine.uebung3;

import at.fhburgenland.fogl.statemachine.Result;
import at.fhburgenland.fogl.statemachine.StateMachine;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 13:04
 */
class MathFormulaStateMachine implements StateMachine {
  private final String input;

  MathFormulaStateMachine(String input) {
    this.input = input;
  }

  public String getInput() {
    return input;
  }

  @Override
  public Result validate() {
    final ResultConstant error = ResultConstant.INPUT_INVALID;
    State currentState = State.S_START;

    if (input == null || input.trim().isEmpty()) {
      return createResultGenerator(error);
    }

    for (char characterToCheck : input.toCharArray()) {

      final Optional<Alphabet> optionalAlphabet = Alphabet.getAlphabetByCharacter(characterToCheck);

      if (optionalAlphabet.isEmpty()) {
        return createResultGenerator(error);
      }

      final Alphabet alphabet = optionalAlphabet.get();

      currentState = currentState.checkState(alphabet);
      if (currentState == State.INVALID) {
        return createResultGenerator(ResultConstant.INVALID);
      }
    }

    return createResultGenerator(currentState == State.Z_END ? ResultConstant.VALID : ResultConstant.INVALID);
  }

  private MathFormulaResult createResultGenerator(ResultConstant resultConstant) {
    return new MathFormulaResult(resultConstant, input);
  }
}
