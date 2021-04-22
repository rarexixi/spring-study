package org.xi.study.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    public Job() {
    }

    public Job(Integer jobId, String jobCode, String jobName) {
        this.jobId = jobId;
        this.jobCode = jobCode;
        this.jobName = jobName;
    }

    private Integer jobId;

    private String jobCode;

    private String jobName;

    @Relationship(type = "IS_DEPENDED_ON", direction = Relationship.Direction.INCOMING)
    public Set<Job> dependOn;

    @Relationship(type = "IS_DEPENDED_ON", direction = Relationship.Direction.OUTGOING)
    public Set<Job> dependBy;

    public void addDependBy(Job job) {
        if (dependBy == null) {
            dependBy = new HashSet<>();
        }
        dependBy.add(job);
    }

    public void addDependOn(Job job) {
        if (dependOn == null) {
            dependOn = new HashSet<>();
        }
        dependOn.add(job);
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", jobCode='" + jobCode + '\'' +
                ", jobName='" + jobName + '\'' +
                ", isDependedOn=" + dependBy +
                '}';
    }
}
