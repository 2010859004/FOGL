package at.fhburgenland.fogl.statemachine.divedby5;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 15.05.2021
 * Time: 12:29
 */
enum Alphabet {
  INPUT_1('1'),
  INPUT_2('2'),
  INPUT_3('3'),
  INPUT_4('4'),
  INPUT_5('5'),
  INPUT_6('6'),
  INPUT_7('7'),
  INPUT_8('8'),
  INPUT_9('9'),
  INPUT_0('0');

  final char sign;

  Alphabet(char sign) {
    this.sign = sign;
  }

  static Optional<Alphabet> getAlphabetByCharacter(char input) {
    return Arrays.stream(values())
        .filter(alphabet -> alphabet.sign == input)
        .findFirst();
  }

  @Override
  public String toString() {
    return String.valueOf(sign);
  }
}
