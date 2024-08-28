package com.ericsson.graduate.group2.CIstabilityproject.object;

public class CustomYear {
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;


    public CustomYear(int year, int month, int day, int hours, int minutes) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;

    }

    public CustomYear(){}

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }



    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }


    public int compareTo(CustomYear date) {
        //feb 2 2022 compareTo jan 17 2021
        //if this is 2022 and date is 2021, write something that will return 1
        if(this.getYear() != date.getYear()) {
            if(this.getYear() < date.getYear()) {
                return -1;
            } else { return 1;}
        } else if(this.getMonth() != date.getMonth()) {
            if(this.getMonth() < date.getMonth()) {
                return -1;
            } else { return 1;}
        } else if(this.getDay() != date.getDay()) {
            if(this.getDay() < date.getDay()) {
                return -1;
            } else { return 1; }
        } else if(this.getHours() != date.getHours()) {
            if(this.getHours() < date.getHours()) {
                return -1;
            } else { return 1;}
        } else if(this.getMinutes() != date.getMinutes()) {
            if(this.getMinutes() < date.getMinutes()) {
                return -1;
            } else { return 1; }
        }

        return 0;
        /*
        if(this.getYear() != date.getYear() && !(this.getYear() > date.getYear())) {
            return -1;
        }
        if(this.getMonth() < date.getMonth()) {
            return -1;
        }
        if(this.getDay() < date.getDay()) {
            return -1;
        }
        if(this.getHours() < date.getHours()) {
            return -1;
        }
        if(this.getMinutes() < date.getMinutes()) {
            return -1;
        }
        return 1;

         */
    }

    public String toString() {
        return this.getYear() + "-" + this.getMonth() + "-" + this.getDay() + "-" + this.getHours() + "-" + this.getMinutes();
    }

    public double getFractionOfDays() {
        if(this.getHours() == 0 && this.getMinutes() ==0) {
            return 0.0;
        }

        double wholeDay = 24 * 60 * 60;
        return wholeDay / ((this.getHours() * 60) + this.getMinutes());
    }

}
