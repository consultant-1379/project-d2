package com.ericsson.graduate.group2.CIstabilityproject.Controllers;

import com.ericsson.graduate.group2.CIstabilityproject.entities.BuildEntity;
import com.ericsson.graduate.group2.CIstabilityproject.entities.T1Entity;

import com.ericsson.graduate.group2.CIstabilityproject.object.Build;
import com.ericsson.graduate.group2.CIstabilityproject.object.CustomYear;
import com.ericsson.graduate.group2.CIstabilityproject.object.Environment;

import com.ericsson.graduate.group2.CIstabilityproject.object.*;

import com.ericsson.graduate.group2.CIstabilityproject.repositories.BuildRepository;
import com.ericsson.graduate.group2.CIstabilityproject.repositories.JobNameRepository;
import com.ericsson.graduate.group2.CIstabilityproject.services.BuildService;
import com.ericsson.graduate.group2.CIstabilityproject.services.JobNameService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = "*")
public class UiController {

    @Autowired
    private JobNameService jobNameRepository;

    @Autowired
    private BuildService buildRepository;

    private Environment environment;

    @GetMapping("/test")
    public @ResponseBody Iterable<BuildEntity> getAllBuilds() {
    //public @ResponseBody String getAllBuilds() {
        //return buildRepository.findAll();
        /*String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://mysql_database:3306/ci_stability_database";

        String select = "SELECT * FROM all_builcs(job_id, build_num, timestamp, status)";
        String ma = "test";

        try(Connection con = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = con.createStatement()){
            Class.forName(myDriver);

            String create = "CREATE TABLE IF NOT EXISTS blooper( id INT NOT NULL AUTO INCREMENT, name VARCHAR(100) UNIQUE, PRIMARY KEY(id)";

        } catch (Exception ex) {
            ma += "first fail";
        }
        try(Connection con = DriverManager.getConnection(myUrl, "root", "password");
            Statement statement = con.createStatement()){
            ma += " went into the try block";
            Class.forName(myDriver);

            //Boolean ret = statement.execute(select);
            return "anything";
        } catch (SQLException e) {
            ma += "fail";
        } catch (ClassNotFoundException x) {

        }
        return ma;

         */
        return buildRepository.findAll();
    }


    @PostMapping("/getData")
    public ResponseEntity dataRequestFromUI(@RequestBody FrontEndBean frontEndBean) {

        List<Integer> jobIds = new ArrayList<Integer>();
        for(String x : frontEndBean.getJobs()) {
            jobIds.add(jobNameRepository.getId(x));
        }
        // now use this to get all jobs that share this ID and fall between start date and end date
        Iterable<BuildEntity> listOfBuilds = buildRepository.findAll();
        CustomYear SYear = getCustomYear(frontEndBean.getStartDate());
        CustomYear EYear = getCustomYear(frontEndBean.getEndDate());
        List<Build> convertedBuilds = getAllValidBuilds(SYear, EYear, jobIds, listOfBuilds);

        //FORMAT TO RETURN DATA -
        // Return a list of values (doubles)
        // return a list of the dates for each applicable job
        Environment environment = new Environment(convertedBuilds);

        ChartBean bean = new ChartBean(environment.getDates(), environment.getFailDates());
        bean.setFailRate(environment.getOrderedBFR());
        bean.setSd(environment.getOrderedSD());
        bean.setMedian(environment.getOrderedMedians());

        bean.trimLists();
        System.out.println("frontEndBean: " + frontEndBean);
        // String json = new Gson().toJson(bean);

        return ResponseEntity.ok().body(bean);
    }

    @GetMapping("/getJobNames")
    public ResponseEntity getJobNames() {

        Iterable<T1Entity> jobName = jobNameRepository.findAll();
        List<String> jobNameList = new ArrayList<String>();

        for(T1Entity x : jobName) {
            jobNameList.add(x.getJob_name());
        }

        String json = new Gson().toJson(jobNameList);
        return ResponseEntity.ok().body(json);
    }

    public List<Build> getAllValidBuilds(CustomYear start, CustomYear end, List<Integer> jobIds, Iterable<BuildEntity> list) {

        List<Build> result = new ArrayList<Build>();
        CustomYear y = null;
        boolean pf = true;
        Build convenienceBuild = null;
        for(BuildEntity x: list) {

            if (jobIds.contains(x.getJob_id())) {

                if (jobIds.contains(x.getJob_id())) {

                    switch (x.getStatus()) {
                        case "SUCCESS":
                            pf = true;
                            break;
                        default:
                            pf = false;
                    }
                    convenienceBuild = new Build(x.getTimestamp(), pf);
                    if (convenienceBuild.getDate().compareTo(start) >= 0 && convenienceBuild.getDate().compareTo(end) <= 0) {
                        result.add(convenienceBuild);
                    }
                }
            }
        }
        return result;
    }

    public CustomYear getCustomYear(String date) {

        String[] res = date.split("-");

        if(res.length == 3) {
            String[] notRes = new String[5];
            notRes[0] = res[0];
            notRes[1] = res[1];
            notRes[2] = res[2];
            notRes[3] = "0";
            notRes[4] = "0";

            res = notRes;
        }

        int y = Integer.parseInt(res[0]);
        int m = Integer.parseInt(res[1]);
        int d = Integer.parseInt(res[2]);
        int h = Integer.parseInt(res[3]);
        int min = Integer.parseInt(res[4]);

        CustomYear year = new CustomYear(y, m, d, h, min);

        return year;
    }

    /*public int getJobId(String jobName) {
        //Use jobName from before to get JobID
        Iterable<T1Entity> nameList = jobNameRepository.findAll();
        for(T1Entity x : nameList) {
            if(x.getJob_name() == jobName) {
                return x.getId();
            }
        }

        return -1;

    }

     */

    public void setJobNameService(JobNameService repo) {
        this.jobNameRepository = repo;
    }

    public void setBuildService(BuildService repo) {
        this.buildRepository = repo;
    }

}
