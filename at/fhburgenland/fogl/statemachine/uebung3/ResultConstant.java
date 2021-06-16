package at.fhburgenland.fogl.statemachine.uebung3;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 15.05.2021
 * Time: 12:32
 */
enum ResultConstant {
  INVALID("Die eingegeben Formel [%s] ist UNG�LTIG"),
  VALID("Die eingegeben Formel [%s] ist G�LTIG"),
  INPUT_INVALID("Die Eingabe [%s] enth�lt ung�ltige Zeichen!");

  final String message;

  ResultConstant(String message) {
    this.message = message;
  }

  String getMessage() {
    return message;
  }
}
