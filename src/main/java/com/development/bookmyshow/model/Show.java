package com.development.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "Shows")
public class Show extends BaseModel {

    @ManyToOne
    private Audi audi;

    private Date showStartTime;
    private Date showEndTime;

    @Enumerated(EnumType.ORDINAL)
    private LanguageType languageType;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> featureList;

    @ManyToOne
    private Movie movie;
}
