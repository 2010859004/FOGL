package fhburgenland;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 02.07.2021
 * Time: 14:20
 */
@Entity
@Table(name = "Team")
public class Team {
  @Id
  @Column(unique = true)
  private int teamID;

  @Column
  private String name;

  @Column
  private String leadSponsor;

  @Column
  private Date foundingDate;

  public Team() {
  }

  public Team(int teamID, String name, String leadSponsor, Date foundingDate) {
    this.teamID = teamID;
    this.name = name;
    this.leadSponsor = leadSponsor;
    this.foundingDate = foundingDate;
  }

  public int getTeamID() {
    return teamID;
  }

  public Team setTeamID(int teamID) {
    this.teamID = teamID;
    return this;
  }

  public String getName() {
    return name;
  }

  public Team setName(String name) {
    this.name = name;
    return this;
  }

  public String getLeadSponsor() {
    return leadSponsor;
  }

  public Team setLeadSponsor(String leadSponsor) {
    this.leadSponsor = leadSponsor;
    return this;
  }

  public Date getFoundingDate() {
    return foundingDate;
  }

  public Team setFoundingDate(Date foundingDate) {
    this.foundingDate = foundingDate;
    return this;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Team.class.getSimpleName() + "[", "]")
        .add("teamID=" + teamID)
        .add("name='" + name + "'")
        .add("leadSponsor='" + leadSponsor + "'")
        .add("foundingDate=" + foundingDate)
        .toString();
  }
}
