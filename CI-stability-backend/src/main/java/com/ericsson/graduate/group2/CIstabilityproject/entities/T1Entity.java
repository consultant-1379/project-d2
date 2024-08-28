package com.ericsson.graduate.group2.CIstabilityproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "all_jobs")
public class T1Entity {
    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer job_id;

    @Column(name="job_name")
    private String job_name;

    public Integer getId() {
        return job_id;
    }

    public void setId(Integer id) {
        this.job_id = id;

    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
}
