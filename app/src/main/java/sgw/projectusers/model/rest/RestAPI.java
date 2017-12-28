package sgw.projectusers.model.rest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sgw.projectusers.model.entities.User;


public interface RestAPI {

    @GET("/users")
    Flowable<Response<List<User>>> getUser(@Query("since") int since, @Query("per_page") int perPage);


}

