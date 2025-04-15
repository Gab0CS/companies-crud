package com.gabo.companies_crud.services;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;


import com.gabo.companies_crud.entities.Category;
import com.gabo.companies_crud.entities.Company;
import com.gabo.companies_crud.repositories.CompanyRepository;

import io.micrometer.tracing.Tracer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional //Debe ser de Jakarta
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final Tracer tracer;

    @Override
    public Company create(Company company) {

        company.getWebSites().forEach(website -> {
            if (Objects.isNull(website.getCategory())) {
                website.setCategory(Category.NONE);
            }
        });

        return this.companyRepository.save(company);
    }

    @Override
    public Company readByName(String name) {
        var span = tracer.nextSpan().name("readByName");
        try (Tracer.SpanInScope spanInScope = this.tracer.withSpan(span.start())) {
            log.info("Getting company from DB");
        } finally {
            span.end();
        }
        return this.companyRepository.findByName(name)
        .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company update(Company company, String name) {
        Company companyToUpdate = this.companyRepository.findByName(name)
        .orElseThrow(() -> new NoSuchElementException("Company not found"));

        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        companyToUpdate.setFounder(company.getFounder()); 

        return this.companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        Company companyToDelete = this.companyRepository.findByName(name)
        .orElseThrow(() -> new NoSuchElementException("Company not found"));

        this.companyRepository.delete(companyToDelete);
    }
    
}
