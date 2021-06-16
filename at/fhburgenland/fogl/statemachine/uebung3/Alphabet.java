package at.fhburgenland.fogl.statemachine.uebung3;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 15.05.2021
 * Time: 12:29
 */
enum Alphabet {
  INPUT_0('0'),
  INPUT_1('1'),
  INPUT_2('2'),
  INPUT_3('3'),
  INPUT_4('4'),
  INPUT_5('5'),
  INPUT_6('6'),
  INPUT_7('7'),
  INPUT_8('8'),
  INPUT_9('9'),
  INPUT_PLUS_SIGN('+'),
  INPUT_MINUS_SIGN('-'),
  INPUT_DIVIDE_SIGN('/'),
  INPUT_MULTIPLICATION_SIGN('*'),
  INPUT_EQUALS_SIGN('='),
  INPUT_OPENING_BRACKET_SIGN('('),
  INPUT_CLOSING_BRACKET_SIGN(')'),
  ;

  final char sign;
  final static EnumSet<Alphabet> NUMBERS = EnumSet.of(INPUT_0, INPUT_1, INPUT_2, INPUT_3, INPUT_4, INPUT_5, INPUT_6, INPUT_7, INPUT_8, INPUT_9);
  final static EnumSet<Alphabet> MATH_SIGNS = EnumSet.of(INPUT_PLUS_SIGN, INPUT_MINUS_SIGN, INPUT_DIVIDE_SIGN, INPUT_MULTIPLICATION_SIGN);

  Alphabet(char sign) {
    this.sign = sign;
  }

  static Optional<Alphabet> getAlphabetByCharacter(char input) {
    return Arrays.stream(values())
        .filter(alphabet -> alphabet.sign == input)
        .findFirst();
  }

  boolean isNumber() {
    return NUMBERS.contains(this);
  }

  boolean isMathSign() {
    return MATH_SIGNS.contains(this);
  }

  @Override
  public String toString() {
    return String.valueOf(sign);
  }
}
