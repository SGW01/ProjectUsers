package sgw.projectusers.view.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;
import sgw.projectusers.R;
import sgw.projectusers.dagger.base.BaseActivity;
import sgw.projectusers.dagger.components.ActivityComponent;
import sgw.projectusers.model.entities.Constants;
import sgw.projectusers.model.entities.User;
import sgw.projectusers.model.rest.RestAPI;
import sgw.projectusers.view.ui.UserPagination;
import sgw.projectusers.view.ui.adapters.RecyclerAdapter;
import sgw.projectusers.view.ui.adapters.RecyclerViewClickListener;


public class MainActivity extends BaseActivity  implements RecyclerViewClickListener{


    public boolean requestOnWay = false;
    @BindView(R.id.rv_users)
    RecyclerView recyclerView;
    @BindView(R.id.loadUser)
    ProgressBar loadUser;


    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<User> usersList;
    private RecyclerAdapter mAdapter;
    private PublishProcessor<Integer> pagination;
    private CompositeDisposable compositeDisposable;


    @Inject
    RestAPI restAPI;

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pagination = PublishProcessor.create();
        compositeDisposable = new CompositeDisposable();

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        usersList = new ArrayList<>();
        mAdapter = new RecyclerAdapter(new RecyclerViewClickListener() {
            @Override
            public void onViewClicked(View v, int position) {
                if(v.getId() == R.id.iv_user_icon){
                showDialog(usersList.get(position).getAvatar_url());

                }
            }
        }, usersList, this);
        recyclerView.setAdapter(mAdapter);

        final LinearLayoutManager layoutManager = (LinearLayoutManager) mLayoutManager;
        recyclerView.addOnScrollListener(new UserPagination(layoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount, View view) {
                if (!requestOnWay) {
                    pagination.onNext(mAdapter.getLastVisibleItemId());
                }
            }
        });
        Disposable disposable = pagination.onBackpressureDrop()
                .doOnNext(integer -> {
                    requestOnWay = true;
                    loadUser.setVisibility(View.VISIBLE);
                })
                .concatMap(new Function<Integer, Publisher<Response<List<User>>>>() {
                    @Override
                    public Publisher<Response<List<User>>> apply(Integer fromId) throws Exception {
                        return getUserList(fromId);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Response<List<User>>>() {
                    @Override
                    public void accept(Response<List<User>> gitHubUsers) throws Exception {
                        if (gitHubUsers.isSuccessful()) {
                            mAdapter.setUsers(gitHubUsers.body());
                        } else {
                            Log.e(TAG, gitHubUsers.code() + " " + gitHubUsers.message());
                        }
                        requestOnWay = false;
                        loadUser.setVisibility(View.INVISIBLE);
                    }
                })
                .doOnError(throwable -> {
                    if (throwable instanceof HttpException) {
                        Response<?> response = ((HttpException) throwable).response();
                        Log.d(TAG, response.message());
                    }

                })
                .subscribe();

        compositeDisposable.add(disposable);
        pagination.onNext(0);
    }

    @Override
    public void inject(ActivityComponent injector) {
        injector.inject(this);

    }

    private Flowable<Response<List<User>>> getUserList(int fromId) {
        return restAPI.getUser(fromId, Constants.PAGE_LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
   private void showDialog(String bitmap) {
       Toast.makeText(this,String.valueOf(bitmap),Toast.LENGTH_LONG).show();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        View linearlayout = getLayoutInflater().inflate(R.layout.dialog, null);
        alertDialog.setView(linearlayout);

        final ImageView imageViewBiggerIcon = (ImageView) linearlayout.findViewById(R.id.iv_bigger_icon);
       Glide.with(this)
               .load(bitmap)
               .into(imageViewBiggerIcon);
        alertDialog.setNegativeButton("Cancel",
                (dialog, id) -> dialog.cancel());

        alertDialog.create();
        alertDialog.show();
    }
  public void showBiggerImage(){
        Toast.makeText(this, "showBiggerImage"  ,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onViewClicked(View v, int position) {
        showBiggerImage();
    }


}