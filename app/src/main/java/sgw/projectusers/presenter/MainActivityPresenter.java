package sgw.projectusers.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sgw.projectusers.model.entities.Users;
import sgw.projectusers.model.rest.RestAPI;

/**
 * Created by Катя on 27.12.2017.
 */

public class MainActivityPresenter {
    private final static String TAG = MainActivityPresenter.class.getSimpleName();
    private MainActivityInterface mainActivityInterface;
    private Disposable usersDisposable;
    @Inject
    RestAPI mRestApi;

    @Inject
    public MainActivityPresenter(){

    }

    public void startPresenter(){
        pollEndroint();
    }

    private void pollEndroint(){
        Log.d(TAG, "pollEndroint");

        if (usersDisposable != null && !usersDisposable.isDisposed()) {
            usersDisposable.dispose();
        }
        usersDisposable = mRestApi.getUsers(String.valueOf(0),"next")
                .subscribeOn(Schedulers.io())
                .doOnError(throwable -> Log.d(TAG, throwable.getMessage()))
                .subscribe(
                        users -> onPollUsersEndpointSucceed(users),
                        throwable -> onPollUsersEndpointFailed(throwable));



    }
    private void onPollUsersEndpointSucceed(Users users) {
        if (mainActivityInterface==null||users == null) return;
        mainActivityInterface.onShowUsers(users);
    }

    private void onPollUsersEndpointFailed(Throwable throwable) {
        Log.e(TAG, throwable == null ? "throwable is null" : throwable.getMessage());
        throwable.printStackTrace();
    }



}
