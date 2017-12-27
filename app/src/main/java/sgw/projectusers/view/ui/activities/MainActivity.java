package sgw.projectusers.view.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import sgw.projectusers.R;
import sgw.projectusers.dagger.base.BaseActivity;
import sgw.projectusers.dagger.components.ActivityComponent;
import sgw.projectusers.model.entities.Users;
import sgw.projectusers.presenter.MainActivityInterface;
import sgw.projectusers.presenter.MainActivityPresenter;
import sgw.projectusers.view.ui.adapters.RecyclerAdapter;

public class MainActivity extends BaseActivity implements MainActivityInterface {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainActivityPresenter mainActivityPresenter;

    private final RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_users);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainActivityPresenter.startPresenter();

    }

    @Override
    public void inject(ActivityComponent injector) {
        injector.inject(this);
    }

    @Override
    public void onShowUsers(Users users) {

     //   recyclerAdapter.setList(users);


    }
}
