package com.gabo.companies_crud.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "company") //Si la tabla tiene un nombre distinto a la clase necesito esta anotación
@Data //Getters, setters, contructor, toString, equals y hashCode
public class Company {
    
    @Id //Anotación para decir que el id es un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Estrategia de autoincrementación de id
    private Long id;
    private String name;
    private String founder;
    private String logo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE} )
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    private List<Website> websites;
}
