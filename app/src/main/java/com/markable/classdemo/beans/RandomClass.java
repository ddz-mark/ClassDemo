package com.markable.classdemo.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Markable on 2017/6/10.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public class RandomClass implements Serializable {


    /**
     * code : 100
     * data : [{"id":1297,"s_num":2014210278,"s_name":"张力文","s_sex":"男","s_class":"01041402","s_status":"在校","r_count":"00000"},{"id":1307,"s_num":2014210343,"s_name":"谢赛东","s_sex":"男","s_class":"01041402","s_status":"在校","r_count":"00000"},{"id":1293,"s_num":2014210172,"s_name":"杨奇奇","s_sex":"女","s_class":"01041402","s_status":"在校","r_count":"00000"}]
     */

    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1297
         * s_num : 2014210278
         * s_name : 张力文
         * s_sex : 男
         * s_class : 01041402
         * s_status : 在校
         * r_count : 00000
         */

        @SerializedName("id")
        public int id;
        @SerializedName("s_num")
        public int sNum;
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
}
