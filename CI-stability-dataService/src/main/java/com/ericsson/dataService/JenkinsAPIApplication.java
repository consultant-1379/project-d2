package com.ericsson.dataService;

import java.io.IOException;
import java.sql.SQLException;

public class JenkinsAPIApplication {

    public static void main(String[] args) throws IOException, SQLException {

        JenkinsServiceInterface jenkinsService= new JenkinsService();

        //Update tables every x milliseconds
        // try {
        //     while (true) {
        //         Thread.sleep(500000);
        //         jenkinsService.updateAll();
        //     }
        // }catch(InterruptedException ex){
        //     System.out.println(ex);
        // }
        jenkinsService.updateAll();
    }
}
