package com.ericsson.graduate.group2.CIstabilityproject;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

@Entity
public class TestBuildDb {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute(
                "create table BUILDS(" +
                        "jobId int auto_increment primary key, " +
                        "buildId int, " +
                        "start_time long, " +
                        "status varChar)");

        jdbcTemplate.update("insert into BUILDS (jobId, buildId, start_time, status) " +
                        "values (?, ?, ?, ?)",
                new Object[]{1, 205, 1649848378077l, "SUCCESS"});

        jdbcTemplate.update("insert into BUILDS (jobId, buildId, start_time, status) " +
                        "values (?, ?, ?, ?)",
                new Object[]{1, 204, 1649847044102l, "FAILURE"});

        jdbcTemplate.update("insert into BUILDS (jobId, buildId, start_time, status) " +
                        "values (?, ?, ?, ?)",
                new Object[]{1, 203, 16498412559099l, "FAILURE"});
    }
}


 */