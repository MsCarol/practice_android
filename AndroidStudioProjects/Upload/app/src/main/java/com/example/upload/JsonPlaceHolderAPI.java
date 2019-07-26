package com.example.upload;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order")String order);

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);


    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    //adding data to the JSON URL
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("User Id") int userId,
            @Field("Title") String title,
            @Field("Body") String body
            );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @FieldMap Map<String, String> fields
    );


    @POST("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);



}
