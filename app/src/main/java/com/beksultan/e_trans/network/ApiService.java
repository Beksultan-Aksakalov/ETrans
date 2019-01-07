package com.beksultan.e_trans.network;

import com.beksultan.e_trans.model.expeditor.get.OrderCount;
import com.beksultan.e_trans.model.expeditor.options.City;
import com.beksultan.e_trans.model.expeditor.put.StatusParam;
import com.beksultan.e_trans.model.expeditor.send.OrderParam;
import com.beksultan.e_trans.model.expeditor.send.OrderParamReturn;
import com.beksultan.e_trans.model.stockman.get.WaybillCount;
import com.beksultan.e_trans.model.user.User;
import com.beksultan.e_trans.model.user.AuthParam;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.OPTIONS;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // ----------------------------- Authorization and permission -------------------------------- //

    @POST("auth/login/")
    Single<User> authorization(@Body AuthParam param);

    @GET("stock/permissions/")
    Single<String> checkRole();

    // ------------------------------------------------------------------------------------------- //

    // --------------------------------------- Expeditor ----------------------------------------- //

    @GET("stock/order/")
    Single<OrderCount> getOrder(@Query("page") String page);

    // ------------------------------------------------------------------------------------------- //

    // --------------------------------- Expeditor put "get or in stock" ------------------------- //

    @PUT("stock/order/{id}/")
    Single<OrderCount> putOrder(@Path("id") String id, @Body StatusParam param);

    // ------------------------------------------------------------------------------------------- //

    // --------------------------------- Expeditor get history ----------------------------------- //

    @GET("stock/order_history/")
    Single<OrderCount> getOrderHistory(@Query("page") String page);

    // ------------------------------------------------------------------------------------------- //


    // --------------------- Expeditor get sender & receiver city, weight type ------------------- //

    @OPTIONS("stock/waybill/")
    Single<City> getCity();

    // ------------------------------------------------------------------------------------------- //

    // --------------------------------- Expeditor post waybill -----------------------------------//

    @POST("stock/waybill/")
    Single<OrderParamReturn> postWaybill(@Body OrderParam param);

    // ------------------------------------------------------------------------------------------- //

    // --------------------------------- Expeditor get krizh -----------------------------------//
//
//    @GET("stock/krizh/")
//    Single<String> getKrizh(@Query("receiver_city") String receiver_city, @Query("sender_city") String sender_city);

    @GET("stock/krizh/")
    Call<String> getKrizh(@Query("receiver_city") String receiver_city, @Query("sender_city") String sender_city);


    // ------------------------------------------------------------------------------------------- //

    // --------------------------------- Stockman get waybill ------------------------------------ //

    @GET("stock/waybill/")
    Single<WaybillCount> getWaybill(@Query("sender_city") String sender_city, @Query("page") String page);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Stockman get waybill history ------------------------------- //

    @GET("stock/waybill_history/")
    Single<WaybillCount> getWaybillHistory(@Query("page") String page);

    // ------------------------------------------------------------------------------------------- //

    // ------------------------------ Stockman put waybill  -------------------------------------- //

    @PUT("stock/waybill/{id}/")
    Single<WaybillCount> putWaybill(@Path("id") String id);

    // ------------------------------------------------------------------------------------------- //


}
