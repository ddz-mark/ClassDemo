package com.markable.classdemo.common;

import com.markable.classdemo.beans.ClassList;
import com.markable.classdemo.beans.Depart;
import com.markable.classdemo.beans.LossStudent;
import com.markable.classdemo.beans.Student;
import com.markable.classdemo.beans.RandomClass;


import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 * Github: https://github.com/ddz-mark
 * Info:
 */

public interface Api {

    String HOST = "http://120.24.49.153:8000/api/";

    //得到所有学院及专业列表
    // 如：http://120.24.49.153:8000/api/all
    @GET("all")
    Observable<ArrayList<Depart>> getAllDepartList();

    //得到学院列表
    // 如：http://120.24.49.153:8000/api/departList
    @GET("departList")
    Observable<ArrayList<Depart>> getDepartList();

    //得到某一学院专业列表
    // 如：http://120.24.49.153:8000/api/specList?depart=通信与信息工程学院
    @GET("specList")
    Observable<ArrayList<Depart>> getSpecList(@Query("depart") String depart);

    //得到某专业班级列表
    // 如：http://120.24.49.153:8000/api/classList?special=信息工程
    @GET("classList")
    Observable<ArrayList<ClassList>> getClassList(@Query("special") String special);

    //得到班级学生名单
    // 如：http://120.24.49.153:8000/api/fellowList?class=01041402
    @GET("fellowList")
    Observable<ArrayList<Student>> getStudentList(@Query("class") String classId);


//=================================================================================

    //得到抽点名单
    // 如：http://120.24.49.153:8000/api/getRandomByClass?class=01041402&num=24
    @GET("getRandomByClass")
    Observable<RandomClass> getRandomClass(
            @Query("class") String classId,
            @Query("num") int num);

    //上传旷课名单
    //http://127.0.0.1:3000/api/updatePresent?list=2014210385,2014210399&_=14200000
    @GET("updatePresent")
    Observable<LossStudent> getLossStudent(
            @Query("list") String list,
            @Query("_") long time);
}
