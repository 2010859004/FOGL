package igor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class person {
    @Id
    @Column(name="nr", unique = true)
    private int nr;

    @Column(name="vname")
    private String vname;

    @Column(name="nname")
    private String nname;

    @Column(name="gehalt")
    private int gehalt;

    public person(){};

    public person(int nr, String vname, String nname, int gehalt){
        this.nr = nr;
        this.vname = vname;
        this.nname = nname;
        this.gehalt = gehalt;
    }

    public String getNname() {
        return nname;
    }

    public int getGehalt() {
        return gehalt;
    }

    public int getNr() {
        return nr;
    }

    public String getVname() {
        return vname;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public void setGehalt(int gehalt) {
        this.gehalt = gehalt;
    }
}
