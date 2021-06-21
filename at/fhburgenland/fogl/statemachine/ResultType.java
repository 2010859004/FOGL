package at.fhburgenland.fogl.statemachine;

public enum ResultType  {
  OK(AnsiColorCodes.GREEN, "OK"),
  INFO(AnsiColorCodes.YELLOW, "INFO"),
  ERROR(AnsiColorCodes.RED, "ERROR")
  ;

  private final AnsiColorCodes ansiColorCodes;
  private final String outputText;

  ResultType(AnsiColorCodes ansiColorCodes, String outputText) {
    this.ansiColorCodes = ansiColorCodes;
    this.outputText = outputText;
  }

  void printWithAnsiColor() {
    System.out.print(this.ansiColorCodes.getColorCode() + "[" + this.outputText + "] " + AnsiColorCodes.RESET.getColorCode());
  }
}

/**
 * Definition of the used ansi color constants
 */
enum AnsiColorCodes {
  RED("\u001B[31m"),
  GREEN("\u001B[32m"),
  YELLOW("\u001B[33m"),
  RESET("\u001B[0m");

  private final String colorCode;

  AnsiColorCodes(String colorCode) {
    this.colorCode = colorCode;
  }

  String getColorCode() {
    return colorCode;
  }
}