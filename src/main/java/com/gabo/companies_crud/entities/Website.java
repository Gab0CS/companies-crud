package com.gabo.companies_crud.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class WebSite implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String name;
    @Column(columnDefinition = "category")
    @Enumerated( value = EnumType.STRING) //Para que un enumerador pueda ser mapeado como string
    private Category category;
    private String description;

}
