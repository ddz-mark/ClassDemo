package com.markable.classdemo.common;

import android.content.Context;

import com.markable.classdemo.BuildConfig;
import com.markable.classdemo.base.BaseApplication;
import com.markable.classdemo.beans.ClassList;
import com.markable.classdemo.beans.Depart;
import com.markable.classdemo.beans.LossStudent;
import com.markable.classdemo.beans.Student;
import com.markable.classdemo.beans.RandomClass;
import com.markable.classdemo.utils.ToastUtil;
import com.markable.classdemo.utils.Util;
import com.markable.classdemo.utils.rxUtils.RxHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 */
public class RetrofitSingleton {

    private static OkHttpClient okHttpClient = null;
    private static Api api = null;

    private RetrofitSingleton() {
        initOkHttp();
        api = getApiService();
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(BaseApplication.getCachedir(), "/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Util.isNetworkConnected(BaseApplication.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (Util.isNetworkConnected(BaseApplication.getAppContext())) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//
//        CookieJar mCookieJar = new CookieJar() {
//            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
//
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                cookieStore.put(url.host(), cookies);
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                List<Cookie> cookies = cookieStore.get(url.host());
//
//                //cookies.get(0).getClass().
//                return cookies != null ? cookies : new ArrayList<Cookie>();
//            }
//        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();

    }

    private static Api getApiService() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit.create(Api.class);
    }


    public static void disposeFailureInfo(Throwable t, Context context) {
        if (context != null) {
            if (t.toString().contains("GaiException")
                    || t.toString().contains("SocketTimeoutException")
                    || t.toString().contains("UnknownHostException")) {
                ToastUtil.showLongToast(context, "网络不给力");
            } else if (t.toString().contains("ConnectException")) {
                ToastUtil.showLongToast(context, "网络出错");
            } else {
                ToastUtil.showLongToast(context, t.getMessage());
            }
        }
    }

    /**推荐将请求接口都写在这里,让 Activity 更专注于自身**/

    /**
     * 得到所有的学院和专业列表
     *
     * @return
     */
    public Observable<ArrayList<Depart>> getAllDepartList() {
        return api.getAllDepartList().compose(RxHelper.<ArrayList<Depart>>rxSchedulerHelper());
    }

    /**
     * 得到学院列表
     *
     * @return
     */
    public Observable<ArrayList<Depart>> getDepartList() {
        return api.getDepartList().compose(RxHelper.<ArrayList<Depart>>rxSchedulerHelper());
    }

    /**
     * 得到学院的专业列表
     *
     * @param dePart
     * @return
     */
    public Observable<ArrayList<Depart>> getSpecList(String dePart) {
        return api.getSpecList(dePart).compose(RxHelper.<ArrayList<Depart>>rxSchedulerHelper());
    }

    /**
     * 得到专业的班级列表
     *
     * @param special
     * @return
     */
    public Observable<ArrayList<ClassList>> getClassList(String special) {
        return api.getClassList(special).compose(RxHelper.<ArrayList<ClassList>>rxSchedulerHelper());
    }

    /**
     * 得到班级的学生列表
     *
     * @param classId
     * @return
     */
    public Observable<ArrayList<Student>> getClass(String classId) {
        return api.getStudentList(classId).compose(RxHelper.<ArrayList<Student>>rxSchedulerHelper());
    }

    /**
     * 得到抽点的学生名单
     *
     * @param classId
     * @param num
     * @return
     */
    public Observable<RandomClass> getRandomClass(String classId, int num) {
        return api.getRandomClass(classId, num).compose(RxHelper.<RandomClass>rxSchedulerHelper());
    }

    public Observable<LossStudent> getLossStudent(String list, long time) {
        return api.getLossStudent(list, time).compose(RxHelper.<LossStudent>rxSchedulerHelper());
    }

}
