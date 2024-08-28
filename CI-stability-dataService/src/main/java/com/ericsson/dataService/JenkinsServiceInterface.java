package com.ericsson.dataService;

import com.offbytwo.jenkins.model.JobWithDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface JenkinsServiceInterface {
    public void updateAll() throws IOException;

    //Loop through job_list and read the individual jobs
    public void initialiseJobsList() throws IOException;

    //Get all the builds associated with the given job
    public void setJobBuilds(JobWithDetails job) throws IOException;

    default void updateJobTable(String job) throws SQLException {
        DatabaseServiceInterface databaseService = new DatabaseService();
        databaseService.updateSQLJobsTable(job);
    }

    default void updateBuildTable(DatabaseEntry databaseEntry) throws SQLException {
        DatabaseServiceInterface databaseService = new DatabaseService();
        databaseService.updateSQLBuildTable(databaseEntry);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------
    //Add all the job names we will use to a list
    default List<String> initialiseJobsListServer1(){
        List<String> jobs_list_string = new ArrayList<>();

        jobs_list_string.add("eric-oss-ran-topology-adapter_Publish");
        jobs_list_string.add("eric-oss-ran-topology-adapter_PreCodeReview");
        //jobs_list_string.add("eric-oss-enm-discovery-adapter_release");
        jobs_list_string.add("eric-oss-enm-discovery-adapter_PreCodeReview");
        jobs_list_string.add("eric-oss-enm-model-adapter_Publish");
        jobs_list_string.add("eric-oss-enm-model-adapter_PreCodeReview");
        jobs_list_string.add("eric-oss-enm-notification-adapter_Publish");
        jobs_list_string.add("eric-oss-enm-notification-adapter_PreCodeReview");

        return jobs_list_string;
    }

    default List<String> initialiseJobsListServer2(){
        List<String> jobs_list_string = new ArrayList<>();

        jobs_list_string.add("ENM-Adapter_release");
        jobs_list_string.add("ENM-Adapter_PreCodeReview");
        jobs_list_string.add("ENM-Stub_release");
        jobs_list_string.add("ENM-Stub_PreCodeReview");

        return jobs_list_string;
    }
}
