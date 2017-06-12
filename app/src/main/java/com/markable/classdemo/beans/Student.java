package com.markable.classdemo.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Markable on 2017/6/10.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class Student implements Serializable {


    /**
     * id : 1281
     * s_num : 2011210062
     * s_name : 张炎
     * s_sex : 男
     * s_class : 01041402
     * s_status : 在校
     * r_count : 00000
     */

    @SerializedName("id")
    public int id;
    @SerializedName("s_num")
    public String sNum;
    @SerializedName("s_name")
    public String sName;
    @SerializedName("s_sex")
    public String sSex;
    @SerializedName("s_class")
    public String sClass;
    @SerializedName("s_status")
    public String sStatus;
    @SerializedName("r_count")
    public String rCount;
}
