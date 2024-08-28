package com.ericsson.dataService;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.JobWithDetails;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.net.URISyntaxException;

public class JenkinsService implements JenkinsServiceInterface{

    private JenkinsServer jenkinsServer1;
    private JenkinsServer jenkinsServer2;

    //List to hold the individual jobs
    private List<String> jobs_list_server1;
    private List<String> jobs_list_server2;

    private List<JobWithDetails> jobs_list;

    private DatabaseEntry databaseEntry = new DatabaseEntry();

    public JenkinsService() throws IOException, SQLException {
        //set up connection to jenkins servers
        // this.jenkinsServer1 = new JenkinsServer(
        //         URI.create("https://fem1s11-eiffel216.eiffel.gic.ericsson.se:8443/jenkins"),
        //         "EWADAAR", "XdhbCY33Z");
        try {
            this.jenkinsServer1 = new JenkinsServer(new URI("https://fem1s11-eiffel216.eiffel.gic.ericsson.se:8443/jenkins/"), "EWADAAR", "XdhbCY33Z");

        // this.jenkinsServer2 = new JenkinsServer(
        //         URI.create("https://fem2s11-eiffel052.eiffel.gic.ericsson.se:8443/jenkins"),
        //         "EWADAAR", "XdhbCY33Z");

            this.jenkinsServer2 = new JenkinsServer(new URI("https://fem2s11-eiffel052.eiffel.gic.ericsson.se:8443/jenkins/"), "EWADAAR", "XdhbCY33Z");
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }

        updateAll();
    }

    public void updateAll() throws IOException {
        jobs_list_server1 = new ArrayList<>();
        jobs_list_server2 = new ArrayList<>();
        jobs_list = new ArrayList<>();
        jobs_list_server1 = initialiseJobsListServer1();
        jobs_list_server2 = initialiseJobsListServer2();
        initialiseJobsList();

        for(JobWithDetails job: jobs_list){
            System.out.println(job);
            setJobBuilds(job);
        }
    }

    //Loop through string list of jobs and read the individual jobs names
    public void initialiseJobsList() throws IOException {
        //Jobs from Server 1
        for(String job: jobs_list_server1){
            if(!jenkinsServer1.getJob(job).equals(null)){
                jobs_list.add(jenkinsServer1.getJob(job).details());
            }

            System.out.println("Added From Server 1...");

            try{
                updateJobTable(job);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        //Jobs from Server 2
        for(String job: jobs_list_server2){
            if(!jenkinsServer2.getJob(job).equals(null)){
                jobs_list.add(jenkinsServer2.getJob(job).details());
            }

            System.out.println("Added from Server 2...");

            try{
                updateJobTable(job);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //Get all the builds associated with the given job
    public void setJobBuilds(JobWithDetails job) throws IOException{

        System.out.println("Current job name: " + job.details().getDisplayName());

        List<Build> builds = new ArrayList<>(job.getBuilds());
        databaseEntry.setJob_name(job.getDisplayName());

        for(Build build: builds){
            //if build is still being build, skip
            if(build.details().isBuilding()){
                System.out.println("Is building");
                continue;
            }
            databaseEntry.setBuild_number(build.details().getNumber());
            databaseEntry.setResult(build.details().getResult().toString());
            databaseEntry.setTimestamp(build.details().getTimestamp());

            try{
                updateBuildTable(databaseEntry);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
