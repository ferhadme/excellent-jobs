package com.ferhad.excellentjobs.repository;

import com.ferhad.excellentjobs.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByHash(String hash);
}
