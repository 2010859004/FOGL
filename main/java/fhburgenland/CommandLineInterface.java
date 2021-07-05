package fhburgenland;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 02.07.2021
 * Time: 19:35
 */
public class CommandLineInterface {
  private static final int PRINT_ALL = 1;
  private static final int PRINT_ONE = 2;
  private static Scanner scanner;
  private DateFormat dateFormat;
  private HibernateUtil hibernateUtil;
  private static final String MENU_TEMPLATE = "*  (%-1s) %-20s *";

  public CommandLineInterface() {
    scanner = new Scanner(System.in);
    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    hibernateUtil = new HibernateUtil();
  }

  public void startMenu() {
    final DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");

    List<Team> teams = new ArrayList<>();
    try {
      teams.add(new Team(1, "Racing Idiots", "Rewe Group", dateInstance.parse("2013-01-01")));
      teams.add(new Team(2, "Driving Killers", "Honda", dateInstance.parse("2016-09-01")));
      teams.add(new Team(3, "Corona Slayers", "Astra Zenica", dateInstance.parse("2021-06-01")));
      teams.add(new Team(4, "Head Hunters", "Pfizer (Aspirin)", dateInstance.parse("2017-08-01")));

    } catch (ParseException e) {
      e.printStackTrace();
    }
    hibernateUtil.persistObjects(teams);
    do {
      Menu menu = printAndGetOption();
      switch (menu) {
        case CREATE -> createTeam();
        case READ -> readTeams();
        case UPDATE -> updateTeam();
        case DELETE -> deleteTeam();
        case EXIT -> {
          System.exit(0);
        }
      }
    } while (true);
  }

  private void updateTeam() {
    System.out.print("Bitte geben Sie die Nummer des zu aktualisierenden Teams ein: ");
    final int teamID = readInt();
    final Team team = hibernateUtil.readTeam(teamID);
    System.out.println("Teamname (ALT): " + team.getName());
    System.out.print("Teamname (Neu): ");
    team.setName(scanner.nextLine());
    System.out.println("Hauptsponsor (ALT): " + team.getLeadSponsor());
    System.out.print("Hauptsponsor (Neu): ");
    team.setLeadSponsor(scanner.nextLine());
    System.out.println("Gründungsjahr (ALT): " + dateFormat.format(team.getFoundingDate()));
    System.out.print("Gründungsjahr (Neu) [yyyy-MM-dd]: ");
    team.setFoundingDate(parseInputDate());
    hibernateUtil.updateTeam(team);
  }

  private Date parseInputDate()  {
    try {
      return dateFormat.parse(scanner.nextLine());
    } catch (ParseException e) {
      System.out.println("Ungültige Eingabe!");
      e.printStackTrace();
    }
    return null;
  }

  private void deleteTeam() {
    System.out.print("Bitte geben Sie die Nummer des zu löschenden Teams ein: ");
    hibernateUtil.deleteTeam(readInt());
  }

  private void readTeams() {
    List<String> options = new ArrayList<>();
    options.add(String.format(MENU_TEMPLATE, "1", "alle Teams"));
    options.add(String.format(MENU_TEMPLATE, "2", "ein Team "));
    createAndPrintMenu(options);
    final int option = readInt();
    if (option == PRINT_ALL) {
      hibernateUtil.printAllTeams();
    } else if (option == PRINT_ONE) {
      System.out.print("Bitte geben Sie die Nummer des Teams ein: ");
      final Team team = hibernateUtil.readTeam(readInt());
      System.out.println(team);
    }
  }

  private int readInt() {
    final int anInt = scanner.nextInt();
    scanner.nextLine();//Hack
    return anInt;
  }

  private void createTeam() {
    try {
      System.out.println("Bitte geben Sie die TeamId ein: ");
      final int teamID = readInt();
      System.out.println("Bitte geben Sie den Teamnamen ein: ");
      final String teamName = scanner.nextLine();
      System.out.println("Bitte geben Sie den Hauptsponsor ein: ");
      final String leadSponsor = scanner.nextLine();
      System.out.println("Bitte geben Sie das Gründungsjahr ein (yyyy-MM-dd):");
      final Date foundingDate = parseInputDate();
      final Team team = new Team(teamID, teamName, leadSponsor, foundingDate);
      hibernateUtil.persistObjects(Collections.singletonList(team));
    } catch (Exception e) {
      System.out.println("Ungültige Eingabe!");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }


  private static Menu printAndGetOption() {
    List<String> options = new ArrayList<>();
    String menuTemplate = "*  (%-1s) %-20s *";
    Arrays.stream(Menu.values()).forEach(menu -> options.add(String.format(menuTemplate, menu.getOption(), menu.getDescription())));
    createAndPrintMenu(options);
    try {
      return Menu.getMenuByOption(scanner.nextLine());
    } catch (InputMismatchException e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return Menu.EXIT;
  }

  private static void createAndPrintMenu(List<String> options) {
    List<String> menuList = new ArrayList<>();
    menuList.add("*****************************");
    menuList.add("*      Willkommen           *");
    menuList.add("* Bitte wähle eine Option   *");
    menuList.addAll(options);
    menuList.add("*****************************");

    System.out.println(String.join("\n", menuList));
    System.out.print("Auswahl: ");
  }

  private enum Menu {
    CREATE("C", "Create"),
    READ("R", "Read"),
    UPDATE("U", "Update"),
    DELETE("D", "Delete"),
    EXIT("X", "Exit [illegal input]");

    private final String option;
    private final String description;

    Menu(String option, String description) {
      this.option = option;
      this.description = description;
    }

    private String getOption() {
      return option;
    }

    private String getDescription() {
      return description;
    }

    static Menu getMenuByOption(String option) {
      return Arrays.stream(values()).filter(menu -> menu.getOption().equalsIgnoreCase(option))
          .findFirst()
          .orElse(EXIT);
    }
  }
}
