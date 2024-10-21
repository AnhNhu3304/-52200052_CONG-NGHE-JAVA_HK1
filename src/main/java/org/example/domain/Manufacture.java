package org.example.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @NotNull
    @Size(min = 3, max = 128)
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "Employee")
    private int employee;

    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    public Manufacture() {}

    public Manufacture( String name, String location, int employee) {
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employee=" + employee +
                '}';
    }

}