package igor;

import javax.persistence.*;
import java.util.List;

public class main {
    private static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("Person");
    public static void main(String[] args) {
        // CREATE
        addPerson(1, "Michael", "Jordan", 1000);
        addPerson(2, "Scottie", "Pippen", 2000);
        addPerson(3, "Dennis", "Rodman", 3000);

        // READ
        //readAll();
        //readPerson(1);

        // UPDATE
        //System.out.println("vorher");
        //readAll();
        //updatePerson(1, "Mike", "Air Jordan", 6000);
        //System.out.println("nachher");
        //readAll();

        // DELETE
//        System.out.println("vorher");
//        readAll();
//        deletePerson(1);
//        System.out.println("nachher");
//        readAll();

        EMF.close();
    }

    public static void addPerson(int nr, String vname, String nname, int gehalt){
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            person p = new person(nr, vname, nname, gehalt);
            em.persist(p);
            et.commit();
        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void readAll(){
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT p FROM person p ORDER BY p.nr";
        TypedQuery<person> tp = em.createQuery(query, person.class);
        List<person> pList = null;
        try{
            pList = tp.getResultList();
            pList.forEach(p -> System.out.println("Nr: " + p.getNr() + ", Vorname: " + p.getVname() + ", Nachname: " + p.getNname() + ", Gehalt: " + p.getGehalt()));
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }

    public static void readPerson(int nr){
        EntityManager em = EMF.createEntityManager();
        String query = "SELECT p FROM person p WHERE p.nr = :custNR";
        TypedQuery<person> tp = em.createQuery(query, person.class);
        tp.setParameter("custNR", nr);
        person p = null;

        try{
            p = tp.getSingleResult();
            System.out.println("Nr: " + p.getNr() + ", Vorname: " + p.getVname() + ", Nachname: " + p.getNname() + ", Gehalt: " + p.getGehalt());
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }

    public static void updatePerson(int nr, String vname, String nname, int gehalt){
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;
        person p = null;

        try{
            et = em.getTransaction();
            et.begin();

            p = em.find(person.class, nr);

            if(p != null){
                p.setVname(vname);
                p.setNname(nname);
                p.setGehalt(gehalt);
            }

            em.persist(p);
            et.commit();
        }catch(Exception ex){
            if(et != null){
                et.rollback();
            }
        }finally {
            em.close();
        }
    }

    public static void deletePerson(int nr){
        EntityManager em = EMF.createEntityManager();
        EntityTransaction et = null;
        person p = null;

        try{
            et = em.getTransaction();
            et.begin();
            p = em.find(person.class, nr);
            em.remove(p);
            et.commit();
        }catch (Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
}
