package at.fhburgenland.fogl.origversion;

public interface DEAd5Constants {

    // Konstanten für die Zustände des DEA
    public static final char STATE_S = 'S';
    public static final char STATE_A = 'A';
    public static final char STATE_B = 'B';
    public static final char STATE_X = 'X';
    public static final char START_STATE = STATE_S;
    public static final char END_STATE = STATE_B;

    public static final int RETURN_TEILBAR = 0;
    public static final int RETURN_NICHT_TEILBAR = 1;
    public static final int RETURN_INVALID = -1;

    // Konstanten für das Eingabealphabet (Ziffern 0 - 9)
    public static final char INPUT_0 = '0';
    public static final char INPUT_1 = '1';
    public static final char INPUT_2 = '2';
    public static final char INPUT_3 = '3';
    public static final char INPUT_4 = '4';
    public static final char INPUT_5 = '5';
    public static final char INPUT_6 = '6';
    public static final char INPUT_7 = '7';
    public static final char INPUT_8 = '8';
    public static final char INPUT_9 = '9';

    public static final String FILE_LOCATION = "/Users/igo3r/IdeaProjects/DEA/file.txt";

    // Color Constants
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String OPTION_MANUAL = "1";
    public static final String OPTION_FILE = "2";
    public static final String OPTION_DB = "3";

    public static String DB_URL = "jdbc:postgresql://10.11.12.13:5432/fogl-db";
    public static String DB_USER = "bswe";
    public static String DB_PW = "fogl";
    public static String DB_QUERY = "SELECT * FROM zahlen";
}
