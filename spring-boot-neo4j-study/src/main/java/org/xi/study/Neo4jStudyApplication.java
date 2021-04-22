package org.xi.study;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.xi.study.model.Job;
import org.xi.study.repository.JobRepository;

@EnableNeo4jRepositories
@SpringBootApplication
public class Neo4jStudyApplication {

    private final static Logger log = LoggerFactory.getLogger(Neo4jStudyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Neo4jStudyApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(JobRepository jobRepository) {
        return args -> {
            Job job = jobRepository.findByJobCode("count_order");
            log.info("\t" + job);
//            jobRepository.deleteAll();
//
//            Job import_user = new Job(1,"import_user","import_user");
//            Job import_order = new Job(2,"import_order","import_order");
//            Job count_order = new Job(3,"count_order","count_order");
//            Job export_order = new Job(3,"export_order","export_order");
//
//            List<Job> jobs = Arrays.asList(import_user, import_order, count_order, export_order);
//
//            jobs.forEach(job -> log.info("\t" + job.toString()));
//
//            jobRepository.save(import_user);
//            jobRepository.save(import_order);
//            jobRepository.save(count_order);
//            jobRepository.save(export_order);
//
//            import_user = jobRepository.findByJobCode(import_user.getJobCode());
//            import_user.addDependBy(count_order);
//            jobRepository.save(import_user);
//
//            import_order = jobRepository.findByJobCode(import_order.getJobCode());
//            import_order.addDependBy(count_order);
//            jobRepository.save(import_order);
//
//            count_order = jobRepository.findByJobCode(count_order.getJobCode());
//            count_order.addDependBy(export_order);
//            jobRepository.save(count_order);
//
//            List<Job> all = jobRepository.findAll();
//            jobs.forEach(job -> log.info("\t" + jobRepository.findByJobCode(job.getJobCode()).toString()));
//
//            List<Job> teammates = jobRepository.findAllBy(export_order.getJobCode());
//            teammates.forEach(job -> log.info("\t" + job.getJobCode()));
        };
    }

}
