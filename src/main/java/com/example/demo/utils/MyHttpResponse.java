package com.example.demo.utils;

/********************************************
 *   @author Bazarbayev_Mansurjon
 *   @date 14.01.2020
 *   @project VisaSubscriberService
 *   @package uz.hamkor.creditConveyor.utils
 ********************************************/
public class MyHttpResponse {
    int httpStatus;
    String response;

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
