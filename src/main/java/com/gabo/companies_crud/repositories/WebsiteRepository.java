package com.gabo.companies_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabo.companies_crud.entities.WebSite;

public interface WebsiteRepository extends JpaRepository<WebSite, Long> {
    
}
