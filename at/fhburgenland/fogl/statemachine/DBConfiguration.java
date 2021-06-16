package at.fhburgenland.fogl.statemachine;

import java.util.StringJoiner;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 17.06.2021
 * Time: 01:10
 */
public class DBConfiguration {
  private final String dbUrl;
  private final String dbUser;
  private final String dbPw;
  private final String dbQuery;

  public DBConfiguration(String dbUrl, String dbUser, String dbPw, String dbQuery) {
    this.dbUrl = dbUrl;
    this.dbUser = dbUser;
    this.dbPw = dbPw;
    this.dbQuery = dbQuery;
  }

  public String getDbUrl() {
    return dbUrl;
  }

  public String getDbUser() {
    return dbUser;
  }

  public String getDbPw() {
    return dbPw;
  }

  public String getDbQuery() {
    return dbQuery;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DBConfiguration.class.getSimpleName() + "[", "]")
        .add("dbUrl='" + dbUrl + "'")
        .add("dbUser='" + dbUser + "'")
        .add("dbPw='" + dbPw + "'")
        .add("dbQuery='" + dbQuery + "'")
        .toString();
  }
}
