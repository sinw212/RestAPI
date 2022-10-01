package com.example.restapitest

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("getTest")
    fun getTestGet1(): Call<GetTestResponse>

    @GET("member/checkId/{userId}") //이거 사용
    fun getTestGet2(@Path("userId") userId: String): Call<GetTestResponse>

    @GET("gestTest/1")
    fun getTestGet3(@Query("school_id") schoolId: Int,
                    @Query("grade") grade: Int,
                    @Query("classroom") classroom: Int): Call<GetTestResponse>

    @POST("member/signup") //이거 사용
    fun getSignupPost(@Body jsonparams: PostSignupRequest): Call<PostSignupResponse>

    @POST("postTest")
    fun getTestPost(@Field("idx") idx: String): Call<PostSignupRequest>
}