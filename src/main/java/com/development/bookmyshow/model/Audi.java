package com.development.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Audi extends BaseModel {
    private String audiName;

    @OneToMany
    private List<Seat> seatList;

    // to represent the enum tables we use annotation Enumerated with enum type as ORDINAL or STRING
//  if the enum use as a list than we use one more annotation also @ElementCollection
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> featureList;

}
