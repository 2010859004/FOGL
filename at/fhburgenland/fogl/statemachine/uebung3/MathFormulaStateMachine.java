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

      if (currentState == State.S_START) {
        if (alphabet.isNumber()) {
          currentState = State.A;
        } else if (alphabet == Alphabet.INPUT_OPENING_BRACKET_SIGN) {
          currentState = State.C;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.A) {
        if (alphabet.isNumber()) {
          continue;
        } else if (alphabet.isMathSign()) {
          currentState = State.S_START;
        } else if (alphabet == Alphabet.INPUT_EQUALS_SIGN) {
          currentState = State.B;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.B) {
        if (!alphabet.isNumber()) {
          return createResultGenerator(ResultConstant.INVALID);
        } else {
          currentState = State.Z_END;
        }
      }

      else if (currentState == State.Z_END) {
        if (alphabet.isNumber()) {
          continue;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.C) {
        if (alphabet.isNumber()) {
          currentState = State.D;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.D) {
        if (alphabet.isNumber()) {
          continue;
        } else if (alphabet == Alphabet.INPUT_CLOSING_BRACKET_SIGN) {
          currentState = State.G;
        } else if (alphabet.isMathSign()) {
          currentState = State.E;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.E) {
        if (alphabet.isNumber()) {
          currentState = State.F;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.F) {
        if (alphabet.isNumber()) {
          continue;
        } else if (alphabet == Alphabet.INPUT_CLOSING_BRACKET_SIGN) {
          currentState = State.G;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

      else if (currentState == State.G) {
        if (alphabet == Alphabet.INPUT_EQUALS_SIGN) {
          currentState = State.B;
        } else if (alphabet.isMathSign()) {
          currentState = State.S_START;
        } else {
          return createResultGenerator(ResultConstant.INVALID);
        }
      }

    }

    return createResultGenerator(currentState == State.Z_END ? ResultConstant.VALID : ResultConstant.INVALID);
  }

  private MathFormulaResult createResultGenerator(ResultConstant resultConstant) {
    return new MathFormulaResult(resultConstant, input);
  }
}
