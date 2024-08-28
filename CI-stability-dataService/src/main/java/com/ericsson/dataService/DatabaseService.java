package com.ericsson.dataService;

import java.sql.*;

public class DatabaseService implements DatabaseServiceInterface {
    String myDriver = "com.mysql.cj.jdbc.Driver";
    String myUrl = "jdbc:mysql://mysql_database:3306/ci_stability_database";


    //update builds table
    @Override
    public void updateSQLBuildTable(DatabaseEntry databaseEntry) throws SQLException{
        System.out.println(databaseEntry.getJob_name());
        System.out.println(databaseEntry.getBuild_number());
        System.out.println(databaseEntry.getTimestamp());
        System.out.println(databaseEntry.getResult());
        System.out.println();

        //convert timestamp to string to insert into builds table
        String timestamp = databaseEntry.getTimestamp() +"";

        String insert_sql = "INSERT INTO all_builds(job_id, build_num, timestamp, status) VALUES( " +
                "(SELECT job_id FROM all_jobs WHERE job_name=\"" +
                databaseEntry.getJob_name() + "\")," +
                databaseEntry.getBuild_number() + "," +
                "\"" + timestamp + "\"," +
                "\"" + databaseEntry.getResult() + "\")";

        try(Connection conn = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = conn.createStatement();){
            Class.forName(myDriver);

            //Check if table exists
            buildsTableExists();

            Boolean ret = statement.execute(insert_sql);
            System.out.println("Return value is: " + ret.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //update jobs table
    @Override
    public void updateSQLJobsTable(String job) throws SQLException {
        System.out.println("Updating Jobs Table");

        String insert_sql = "INSERT INTO all_jobs(job_name) VALUES(\"" + job + "\")";

        try(Connection conn = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = conn.createStatement();){
            Class.forName(myDriver);

            //Check if jobs table exists
            jobsTableExists();

            Boolean ret = statement.execute(insert_sql);
            System.out.println("Return value is: " + ret.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Check if Jobs Tables Exists
    @Override
    public void jobsTableExists() throws SQLException{

        try(Connection conn = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = conn.createStatement();){
            Class.forName(myDriver);

            //Check if jobs table exists
            Boolean does_exist = false;
            ResultSet rs = conn.getMetaData().getTables(null, null, "%", null);
            while(rs.next()){
                if(rs.getString(3).equals("all_jobs")){
                    does_exist = true;
                    break;
                }
            }

            if(does_exist == false){
                createJobsTable();
                System.out.println("Jobs Table Created");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Create jobs table
    @Override
    public void createJobsTable() throws SQLException{

        String create_sql = "CREATE TABLE IF NOT EXISTS all_jobs(" +
                "job_id INT NOT NULL AUTO_INCREMENT," +
                "job_name VARCHAR(100) UNIQUE," +
                "PRIMARY KEY(job_id))";

        try(Connection conn = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = conn.createStatement();){
            Class.forName(myDriver);

            statement.execute(create_sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Check if builds table exists
    @Override
    public void buildsTableExists() throws SQLException{

        try(Connection conn = DriverManager.getConnection(myUrl, "root", "password")){
            Class.forName(myDriver);

            //Check if jobs table exists
            Boolean does_exist = false;
            ResultSet rs = conn.getMetaData().getTables(null, null, "%", null);
            while(rs.next()){
                if(rs.getString(3).equals("all_builds")){
                    does_exist = true;
                    break;
                }
            }

            if(does_exist == false){
                createBuildsTable();
                System.out.println("Builds Table Created");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Create builds table
    @Override
    public Boolean createBuildsTable() throws SQLException{

        String create_sql = "CREATE TABLE IF NOT EXISTS all_builds(" +
                "build_id INT NOT NULL AUTO_INCREMENT," +
                "job_id INT," +
                "build_num INT," +
                "timestamp VARCHAR(100)," +
                "status VARCHAR(100)," +
                "PRIMARY KEY(build_id))";

        String alter_sql = "ALTER TABLE all_builds add UNIQUE unique_key(job_id, build_num)";

        try(Connection conn = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = conn.createStatement();){
            Class.forName(myDriver);

            statement.execute(create_sql);
            statement.execute(alter_sql);

            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}