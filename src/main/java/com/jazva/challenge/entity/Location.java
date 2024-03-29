package com.jazva.challenge.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Location() {
    }

    public Location(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
