package com.gabo.companies_crud.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.companies_crud.entities.Company;
import com.gabo.companies_crud.services.CompanyService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
public class CompanyController {
    
    private final CompanyService companyService;

    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name){
        log.info("GET: Company {}" ,name);
        return ResponseEntity.ok(this.companyService.readByName(name));
    }

    @PostMapping
    public ResponseEntity<Company> post(@RequestBody Company company){
        log.info("POST: Company {}" ,company.getName());
        
        return ResponseEntity.created(URI.create(this.companyService.create(company).getName())).build();
    }

    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(
        @RequestBody Company company, 
        @PathVariable String name) {
            log.info("PUT: company {}" ,name);
            return ResponseEntity.ok(this.companyService.update(company, name));
    }

    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(@PathVariable String name){
        log.info("PUT: company {}" ,name);

        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }

}
