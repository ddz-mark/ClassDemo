package com.markable.classdemo.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Markable on 2017/6/12.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class ClassList implements Serializable {


    /**
     * class_id : 47
     * c_name : 0141301
     * c_count : 35
     * c_spec : 信息工程
     */

    @SerializedName("class_id")
    public int classId;
    @SerializedName("c_name")
    public String cName;
    @SerializedName("c_count")
    public int cCount;
    @SerializedName("c_spec")
    public String cSpec;
}
