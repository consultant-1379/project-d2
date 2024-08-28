package com.ericsson.graduate.group2.CIstabilityproject.object;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Build {

    private CustomYear date;
    private Boolean buildSuccess;

    public Build(long date, Boolean buildSuccess) {
        this.date = convertToCustomDate(date);
        this.buildSuccess = buildSuccess;
    }

    public Build(CustomYear date, Boolean buildSuccess) {
        this.date = date;
        this.buildSuccess = buildSuccess;
    }

    public Build() {}


    public CustomYear getDate() {
        return date;
    }

    public Boolean getBuildSuccess() {
        return buildSuccess;
    }

    public CustomYear convertToCustomDate(long date) {
        Date data = new Date (date);


        Format format = new SimpleDateFormat("yyyy MM dd kk mm");

        String bloop = format.format(date);

        String[] res = bloop.split(" ");
        int y = Integer.parseInt(res[0]);
        int m = Integer.parseInt(res[1]);
        int d = Integer.parseInt(res[2]);
        int h = Integer.parseInt(res[3]);
        int min = Integer.parseInt(res[4]);

        CustomYear year = new CustomYear(y, m, d, h, min);
        return year;
    }
}
