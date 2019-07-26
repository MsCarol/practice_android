package com.example.upload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textResult;
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.text_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        //getPosts();
        //getComments();
        //createPosts();
        //updatePost();
        deletePost();


    }

    private void deletePost() {

        Call<Void> call = jsonPlaceHolderAPI.deletePost(2);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textResult.setText("Code " + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textResult.setText(t.getMessage());

            }
        });
    }

    private void updatePost() {

        Post post = new Post(12, "Oyaa", "Wamnyonyez");

        Call<Post> call = jsonPlaceHolderAPI.putPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textResult.setText("Code " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content  = "";
                content += "Code " + response.code() + "\n";
                content += "ID " + postResponse.getID() + "\n";
                content += "User ID " + postResponse.getUserId() + "\n";
                content += "Title " + postResponse.getTitle() + "\n";
                content += "Text " + postResponse.getText() + "\n";

                textResult.setText(content);


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textResult.setText(t.getMessage());

            }
        });

    }

    private void createPosts() {
        Post post = new Post(23, "Wamlambez", "Wamnyonyez");

        Map<String, String> fields = new HashMap<>();
        fields.put("user Id ", "25");
        fields.put("title ", "Wamlammbez");


        Call<Post> call = jsonPlaceHolderAPI.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textResult.setText("Code :" + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content  = "";
                content += "Code " + response.code() + "\n";
                content += "ID " + postResponse.getID() + "\n";
                content += "User ID " + postResponse.getUserId() + "\n";
                content += "Title " + postResponse.getTitle() + "\n";
                content += "Text " + postResponse.getText() + "\n";

                textResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textResult.setText(t.getMessage());

            }
        });




    }

    private void getComments() {

        Call<List<Comment>> call = jsonPlaceHolderAPI.getComments("https://jsonplaceholder.typicode.com/posts/3/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textResult.setText("Code :" + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {

                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostID() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textResult.append(content);


                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textResult.setText(t.getMessage());

            }
        });
    }

    private void getPosts() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textResult.setText("Code" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getID() + "\n\n";
                    content += "ID: " + post.getUserId() + "\n\n";
                    content += "ID: " + post.getTitle() + "\n\n";
                    content += "ID: " + post.getText() + "\n\n";

                    textResult.append(content);

                }


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textResult.setText(t.getMessage());

            }
        });
    }
}
