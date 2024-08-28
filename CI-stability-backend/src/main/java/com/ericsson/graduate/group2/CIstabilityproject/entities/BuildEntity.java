package com.ericsson.graduate.group2.CIstabilityproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "all_builds")
public class BuildEntity {
    @Id
    @Column(name = "build_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int build_id;

    @Column(name = "job_id")
    private int job_id;

    @Column(name="build_num")
    private int build_num;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name="status")
    private String status;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getBuild_id() {
        return build_id;
    }

    public void setBuild_id(int build_id) {
        this.build_id = build_id;
    }

    public long getTimestamp() {
        return Long.parseLong(timestamp);
    }

    public void setTimestamp(String timestamp) {

        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBuild_num() {
        return build_num;
    }

    public void setBuild_num(int build_num) {
        this.build_num = build_num;
    }

}
