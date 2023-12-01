package com.redhat.training.speaker;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Speaker extends PanacheEntity {
    //public String id = UUID.randomUUID().toString();
    public String name;
    public String organization;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Talk> talks;

    // public Speaker(){
    //     talks = new ArrayList<>();
    // }
    
}
