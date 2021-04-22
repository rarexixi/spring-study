package org.xi.study.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.xi.study.model.Job;

import java.util.List;

public interface JobRepository extends Neo4jRepository<Job, Long> {

    Job findByJobCode(String jobCode);

    List<Job> findByDependByJobCode(String jobCode);
    List<Job> findByDependByJobCode(String jobCode, @Depth int depth);
    List<Job> findByDependOnJobCode(String jobCode);
}
