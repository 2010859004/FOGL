package at.fhburgenland.fogl.statemachine.uebung3;

import at.fhburgenland.fogl.statemachine.Result;

import java.util.StringJoiner;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 13:00
 */
class MathFormulaResult implements Result {
  private final ResultConstant resultConstant;
  private final String input;

  MathFormulaResult(ResultConstant resultConstant, String input) {
    this.resultConstant = resultConstant;
    this.input = input == null ? "" : input;
  }

  @Override
  public String getResultText() {
    return String.format(resultConstant.getMessage(), input);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MathFormulaResult.class.getSimpleName() + "[", "]")
        .add("resultConstant=" + resultConstant)
        .add("input='" + input + "'")
        .toString();
  }
}
