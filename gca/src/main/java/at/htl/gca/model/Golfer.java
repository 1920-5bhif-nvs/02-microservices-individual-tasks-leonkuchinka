package at.htl.gca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="Golfer.findall", query="select g from Golfer g")
@DiscriminatorColumn
public abstract class Golfer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    protected double hcp;
    protected int age;

    @Column(name="DTYPE", insertable = false, updatable = false)
    private String dType;

    //region Constructors
    public Golfer() {
    }

    public Golfer(String name, double hcp, int age) {
        this();
        this.name = name;
        this.hcp = hcp;
        this.age = age;
    }
    //endregion

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getHcp() {
        return hcp;
    }

    public void setHcp(double hcp) {
        this.hcp = hcp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
