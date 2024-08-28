package com.ericsson.dataService;

import java.sql.SQLException;

public interface DatabaseServiceInterface {

    //public void createConnection();

    public void updateSQLBuildTable(DatabaseEntry databaseEntry) throws SQLException;

    public void updateSQLJobsTable(String job) throws SQLException;

    public void createJobsTable() throws SQLException;

    public Boolean createBuildsTable() throws SQLException;

    public void jobsTableExists() throws SQLException;

    public void buildsTableExists() throws SQLException;
}
