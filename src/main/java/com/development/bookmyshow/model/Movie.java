package com.development.bookmyshow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "Movies")
public class Movie extends BaseModel {

    private String movieName;
    private String movieDesc;
    private String cast;
    private int rating;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<LanguageType> languageTypeList;


}
