package at.fhburgenland.fogl.origversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DEAd5Methods {

    public static int DEAd5(String input){
        char state;
        int ret = DEAd5Constants.RETURN_INVALID;

        state = DEAd5Constants.START_STATE;

        for(char c : input.toCharArray()){
            switch(state){
                case DEAd5Constants.STATE_S:
                    switch(c){
                        case DEAd5Constants.INPUT_1:
                        case DEAd5Constants.INPUT_2:
                        case DEAd5Constants.INPUT_3:
                        case DEAd5Constants.INPUT_4:
                        case DEAd5Constants.INPUT_6:
                        case DEAd5Constants.INPUT_7:
                        case DEAd5Constants.INPUT_8:
                        case DEAd5Constants.INPUT_9:
                            state = DEAd5Constants.STATE_A;
                            ret = DEAd5Constants.RETURN_NICHT_TEILBAR;
                            break;
                        case DEAd5Constants.INPUT_0:
                        case DEAd5Constants.INPUT_5:
                            state = DEAd5Constants.STATE_B;
                            ret = DEAd5Constants.RETURN_TEILBAR;
                            break;
                        default:
                            state = DEAd5Constants.STATE_X;
                            ret = DEAd5Constants.RETURN_INVALID;
                            break;
                    }
                    break;
                case DEAd5Constants.STATE_A:
                case DEAd5Constants.STATE_B:
                    switch(c) {
                        case DEAd5Constants.INPUT_1:
                        case DEAd5Constants.INPUT_2:
                        case DEAd5Constants.INPUT_3:
                        case DEAd5Constants.INPUT_4:
                        case DEAd5Constants.INPUT_6:
                        case DEAd5Constants.INPUT_7:
                        case DEAd5Constants.INPUT_8:
                        case DEAd5Constants.INPUT_9:
                            state = DEAd5Constants.STATE_S;
                            ret = DEAd5Constants.RETURN_NICHT_TEILBAR;
                            break;
                        case DEAd5Constants.INPUT_0:
                        case DEAd5Constants.INPUT_5:
                            state = DEAd5Constants.STATE_B;
                            ret = DEAd5Constants.RETURN_TEILBAR;
                            break;
                        default:
                            state = DEAd5Constants.STATE_X;
                            ret = DEAd5Constants.RETURN_INVALID;
                            break;
                    }
                    break;
            }
        }

        return ret;
    }

    public static void fileMode(){
        File file = new File(DEAd5Constants.FILE_LOCATION);
        int check;

        try {
            Scanner fileReader = new Scanner(file);

            while(fileReader.hasNextLine()){
                String currentLine = fileReader.nextLine();
                check = DEAd5(currentLine);
                printMessage(check, currentLine);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            printError();
            System.out.println("Die Datei: " + DEAd5Constants.FILE_LOCATION + " konnte nicht gelesen werden!");
            e.printStackTrace();
        }
    }

    public static void manualMode(){
        Scanner sc = new Scanner(System.in);
        String input = null;
        int check;

        printInfo();
        System.out.print("Bitte geben Sie eine Zahl ein: ");
        input = sc.nextLine();

        if(!input.isEmpty()) {
            check = DEAd5Methods.DEAd5(input);
            DEAd5Methods.printMessage(check, input);
        }else {
            printError();
            System.out.println("Keine Eingabe erkannt!");
        }
    }

    public static void printMessage(int check, String input){
        if(check == DEAd5Constants.RETURN_TEILBAR) {
            printOK();
            System.out.println("Die eingegebene Zahl: " + input + " ist durch 5 teilbar!");
        } else if(check == DEAd5Constants.RETURN_NICHT_TEILBAR){
            printInfo();
            System.out.println("Die eingegebene Zahl: " + input + " ist NICHT durch 5 teilbar!");
        } else if(check == DEAd5Constants.RETURN_INVALID){
            printError();
            System.out.println("Die Eingabe: " + input + " enthält ungültige Zeichen!");
        }
    }

    public static void printOK(){
        System.out.print(DEAd5Constants.ANSI_GREEN + "[OK] " + DEAd5Constants.ANSI_RESET);
    }

    public static void printInfo(){
        System.out.print(DEAd5Constants.ANSI_YELLOW + "[INFO] " + DEAd5Constants.ANSI_RESET);
    }

    public static void printError(){
        System.out.print(DEAd5Constants.ANSI_RED + "[ERROR] " + DEAd5Constants.ANSI_RESET);
    }

    public static void printOptions(){
        System.out.println("*************************************************");
        System.out.println("*                                               *");
        System.out.println("*                  WILLKOMMEN                   *");
        System.out.println("*                                               *");
        System.out.println("*      Bitte wählen Sie eine der Optionen:      *");
        System.out.println("*      (1) manuelle Eingabe                     *");
        System.out.println("*      (2) einlesen aus Datei                   *");
        System.out.println("*      (3) einlesen aus Datenbank               *");
        System.out.println("*                                               *");
        System.out.println("*************************************************");
    }

    public static void dbMode(){
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DriverManager.getConnection(DEAd5Constants.DB_URL, DEAd5Constants.DB_USER, DEAd5Constants.DB_PW);
            pst = con.prepareStatement(DEAd5Constants.DB_QUERY);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                String nextLine = rs.getString(1);
                int ret = DEAd5Methods.DEAd5(nextLine);
                DEAd5Methods.printMessage(ret, nextLine);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException throwables) {
            DEAd5Methods.printError();
            System.out.println("Fehler beim Zugriff auf die DB!");
            throwables.printStackTrace();
        }
    }
}
