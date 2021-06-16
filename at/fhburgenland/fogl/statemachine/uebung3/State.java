package at.fhburgenland.fogl.statemachine.uebung3;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 15.05.2021
 * Time: 12:28
 */
enum State implements Check {
  S_START {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return State.A;
      } else if (alphabet == Alphabet.INPUT_OPENING_BRACKET_SIGN) {
        return State.C;
      } else {
        return INVALID;
      }
    }
  },
  A {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return this;
      } else if (alphabet.isMathSign()) {
        return State.S_START;
      } else if (alphabet == Alphabet.INPUT_EQUALS_SIGN) {
        return State.B;
      } else {
        return INVALID;
      }
    }
  },
  B {
    @Override
    public State checkState(Alphabet alphabet) {
      if (!alphabet.isNumber()) {
        return INVALID;
      } else {
        return State.Z_END;
      }
    }
  },
  C {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return State.D;
      } else {
        return INVALID;
      }
    }
  },
  D {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return this;
      } else if (alphabet == Alphabet.INPUT_CLOSING_BRACKET_SIGN) {
        return State.G;
      } else if (alphabet.isMathSign()) {
        return State.E;
      } else {
        return INVALID;
      }
    }
  },
  E {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return State.F;
      } else {
        return INVALID;
      }
    }
  },
  F {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return this;
      } else if (alphabet == Alphabet.INPUT_CLOSING_BRACKET_SIGN) {
        return State.G;
      } else {
        return INVALID;
      }
    }
  },
  G {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet == Alphabet.INPUT_EQUALS_SIGN) {
        return State.B;
      } else if (alphabet.isMathSign()) {
        return State.S_START;
      } else {
        return INVALID;
      }
    }
  },
  Z_END {
    @Override
    public State checkState(Alphabet alphabet) {
      if (alphabet.isNumber()) {
        return this;
      } else {
        return INVALID;
      }
    }
  },
  INVALID;

  @Override
  public State checkState(Alphabet alphabet) {
    return INVALID;
  }
}

interface Check {
  State checkState(Alphabet alphabet);
}
