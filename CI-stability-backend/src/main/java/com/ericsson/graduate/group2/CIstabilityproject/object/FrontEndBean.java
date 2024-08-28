package com.ericsson.graduate.group2.CIstabilityproject.object;

import java.util.List;

public class FrontEndBean {
    private List<String> jobs;
    private String startDate;
    private String endDate;

    public FrontEndBean(List<String> jobs, String startDate, String endDate) {
        this.jobs = jobs;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        return "StartDate " + this.startDate + ". endDate " + this.endDate;
    }
}
