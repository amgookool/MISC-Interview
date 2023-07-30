package misc.backend.CRUDdemo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {

    // Model Attributes
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Constructors
    public ItemEntity() {
    }

    public ItemEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    // Getter for ID
    public long getId() {
        return id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for Description
    public String getDescription() {
        return description;
    }

    // Setter for Description
    public void setDescription(String description) {
        this.description = description;
    }
}
