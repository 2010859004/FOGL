package fhburgenland;

/**
 * Created with IntelliJ IDEA.
 * User: a.doetzl
 * Date: 05.07.2021
 * Time: 10:18
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.List;

public class HibernateUtil {
  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public HibernateUtil() {
  }

  public SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        // Create registry
        registry = new StandardServiceRegistryBuilder().configure().build();

        // Create MetadataSources
        MetadataSources sources = new MetadataSources(registry);

        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();

      } catch (Exception e) {
        e.printStackTrace();
        shutdown();
      }
    }
    return sessionFactory;
  }

  public void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  public <T> void persistObjects(List<T> objects) {
    Transaction transaction = null;
    try (Session session = getSessionFactory().openSession()) {
      transaction = session.beginTransaction();

      for (T object : objects) {
        session.save(object);
      }
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  public void printAllTeams() {
    printTeams("from Team");
  }

  public void printTeams(String query) {
    try (Session session = getSessionFactory().openSession()) {
      List<Team> teams = session.createQuery(query, Team.class).list();
      teams.forEach(System.out::println);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Team readTeam(int teamID) {
    String query = "SELECT t FROM Team t WHERE t.teamID = :teamID";
    try (Session session = getSessionFactory().openSession()) {
      TypedQuery<Team> typedQuery = session.createQuery(query, Team.class);
      typedQuery.setParameter("teamID", teamID);
      final Team team = typedQuery.getSingleResult();
      return team;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void deleteTeam(int teamID) {
    try (Session session = getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();

      final Team team = session.find(Team.class, teamID);
      if (team != null) {
        System.out.println("Delete: " + team);
        session.remove(team);
      }
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateTeam(Team team) {
    try (Session session = getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();

      final Team tempTeam = session.find(Team.class, team.getTeamID());
      if (tempTeam != null) {
        tempTeam.setFoundingDate(team.getFoundingDate());
        tempTeam.setLeadSponsor(team.getLeadSponsor());
        tempTeam.setName(team.getName());
      }
      session.persist(tempTeam);
      transaction.commit();
    }
  }
}
