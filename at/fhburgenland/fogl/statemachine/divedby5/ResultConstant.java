package at.fhburgenland.fogl.statemachine.divedby5;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 15.05.2021
 * Time: 12:32
 */
enum ResultConstant {
  DIVIDABLE_BY_5("Die eingegeben Zahl [%s] ist durch 5 teilbar"),
  NOT_DIVIDABLE_BY_5("Die eingegeben Zahl [%s] ist NICHT durch 5 teilbar"),
  INPUT_INVALID("Die Eingabe [%s] enthält ungültige Zeichen!");

  final String message;

  ResultConstant(String message) {
    this.message = message;
  }

  String getMessage() {
    return message;
  }
}
