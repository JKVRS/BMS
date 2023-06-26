package com.development.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theater extends BaseModel {
    String theaterName;
    String address;
    @OneToMany
    List<Audi> audiList;
    @ManyToOne
    private City city;
}
