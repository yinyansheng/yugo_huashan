package com.yugo.huashan.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Item {
    private String drawlocation;
    private String statustext;
    private String memo;
    private String status;
    private String type;
    private Date date;
    private int hosid;
    private boolean isadvance;
    private int docid;
    private String time;
    private String regfee;
    private String drawtime;
    private boolean showtime;
    private String statushint;
    private String district;
    private int clinictypeid;
    private long ctid;
    private String week;
}
