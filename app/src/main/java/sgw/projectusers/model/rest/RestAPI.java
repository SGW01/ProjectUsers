package sgw.projectusers.model.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import sgw.projectusers.model.entities.Users;

/**
 * Created by Катя on 27.12.2017.
 */

public interface RestAPI {


    @GET("/users")
    Observable<Users> getUsers(@Header("rel") String rel,
            @Path("since") String since);

    }

