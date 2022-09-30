package com.ferhad.excellentjobs.repository;

import com.ferhad.excellentjobs.model.Label;
import com.ferhad.excellentjobs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("select distinct u from User u join u.labels l where l in :labels")
    List<User> findAllByLabels(Set<Label> labels);
}
