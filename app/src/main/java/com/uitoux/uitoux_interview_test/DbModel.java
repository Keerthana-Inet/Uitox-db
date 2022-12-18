package com.uitoux.uitoux_interview_test;

public class DbModel {
    DbModel(String id,String sno,String title, int priority,String mark1,String mark2,String mark3,int total) {
        this.sno = sno;
        this.title = title;
        this.priority = priority;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.total = total;
        this.id = id;
    }

    String title;
    int priority;
    int total;
    String mark1;
    String mark2,mark3,sno,id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String mark2) {
        this.sno = sno;
    }


    public String getMark3() {
        return mark3;
    }

    public void setMark3(String mark3) {
        this.mark3 = mark3;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
