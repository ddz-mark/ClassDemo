package com.markable.classdemo.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Markable on 2017/6/10.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class Depart implements Serializable {


    /**
     * id_spec : 83
     * s_num : null
     * s_name : 广播电视编导
     * depart : 传媒艺术学院
     */

    @SerializedName("id_spec")
    public int idSpec;
    @SerializedName("s_num")
    public Object sNum;
    @SerializedName("s_name")
    public String sName;
    @SerializedName("depart")
    public String depart;
}
