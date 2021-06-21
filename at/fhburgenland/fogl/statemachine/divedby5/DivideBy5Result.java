package at.fhburgenland.fogl.statemachine.divedby5;

import at.fhburgenland.fogl.statemachine.Result;
import at.fhburgenland.fogl.statemachine.ResultType;

import java.util.StringJoiner;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 19.05.2021
 * Time: 13:00
 */
class DivideBy5Result implements Result {
  private final ResultConstant resultConstant;
  private final String input;

  DivideBy5Result(ResultConstant resultConstant, String input) {
    this.resultConstant = resultConstant;
    this.input = input == null ? "" : input;
  }

  @Override
  public String getResultText() {
    return String.format(resultConstant.getMessage(), input);
  }

  @Override
  public ResultType getType() {
    return resultConstant == ResultConstant.DIVIDABLE_BY_5 ? ResultType.OK : resultConstant == ResultConstant.NOT_DIVIDABLE_BY_5 ? ResultType.INFO : ResultType.ERROR;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DivideBy5Result.class.getSimpleName() + "[", "]")
        .add("resultConstant=" + resultConstant)
        .add("input='" + input + "'")
        .toString();
  }
}
