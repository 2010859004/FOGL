package at.fhburgenland.fogl.origversion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option = null;
        boolean repeat = true;

        while(repeat) {
            DEAd5Methods.printOptions();
            System.out.println();
            DEAd5Methods.printInfo();
            System.out.print("Auswahl: ");
            option = sc.nextLine();

            if (option.equals(DEAd5Constants.OPTION_MANUAL)) {
                System.out.println();
                DEAd5Methods.manualMode();
                repeat = false;
            }
            else if (option.equals(DEAd5Constants.OPTION_FILE)){
                System.out.println();
                DEAd5Methods.fileMode();
                repeat = false;
            }
            else if(option.equals(DEAd5Constants.OPTION_DB)){
                System.out.println();
                DEAd5Methods.dbMode();
                repeat = false;
            }
            else {
                System.out.println();
                DEAd5Methods.printError();
                System.out.println("Bitte wählen Sie eine der angeführten Optionen aus!");
                System.out.println();
            }
        }
    }
}
