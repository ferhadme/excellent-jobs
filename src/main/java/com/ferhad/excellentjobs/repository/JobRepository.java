package com.ferhad.excellentjobs.repository;

import com.ferhad.excellentjobs.model.Job;
import com.ferhad.excellentjobs.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("select distinct j from Job j join j.labels l where l in :labels")
    List<Job> findAllByLabels(Set<Label> labels);
}
