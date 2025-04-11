package net.javaguides.springboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private int salary;

    @ElementCollection
    @CollectionTable(name = "employee_languages", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "language")
    private List<String> languages;

    public Employee(){
        super();
    }

    public Employee(String firstName, String lastName, String email, int salary, List<String> languages){
        super();
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.salary=salary;
        this.languages=languages;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
