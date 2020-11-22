package com.example.OnRange;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserClient {
/*
    @Headers({"Accept: application/json",
            "Authorization:Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp" // OnboardCB:alperna6799energi  T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp
            })
    @POST(".")
    Call<User> addUser(@Body User user);

 */

    @Headers("Accept: application/json")
    @POST(".")
    Call<User> addUser(@Header("Authorization") String header,@Body User user);
    /*
    @Headers({"Accept: application/json",
                "Authorization: Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp" // MgmtCB:linslus1687pennskrin   TWdtdENCOmxpbnNsdXMxNjg3cGVubnNrcmlu
                })
        @DELETE(".")
        Call<String> deleteUser( );

     */
    @Headers("Accept: application/json")
    @DELETE("{Device}")
    Call<String> deleteUser(@Header("Authorization") String Auth, @Path("Device") String device);
    /*
    @Headers({"Accept: application/json",
                "Authorization: Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp" // T
                })

        @GET ("")// POST alt 6 för debugg
        Call<String> getUser(); // @Header("Authorization") String authHeader, // @Body User user  // @Query("groupName")String Fetchall

        // https://impact.idc.nokia.com/m2m/endpoints?groupName=groupName=Europe.SWEDEN.HALMSTAD.Testbed.Student.OnbApp&fetchAll=True
     */
    @Headers("Accept: application/json")
    @GET ("{Device}")// POST alt 6 för debugg
    Call<String> getUser(@Header("Authorization") String Auth,@Path("Device") String device);








// GET /endpoints/{serialNumber}



}

