package com.development.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel {

    private String cityName;

    @OneToMany(mappedBy = "city")
    // put the name of the attribute of another class where same relationship represented ,
//   we do it because we tell to ORM that same relation is representing by another class with this attribute
    private List<Theater> theaterList;


}
