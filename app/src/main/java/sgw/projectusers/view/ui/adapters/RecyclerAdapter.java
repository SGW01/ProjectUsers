package sgw.projectusers.view.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import sgw.projectusers.R;
import sgw.projectusers.model.entities.Users;
import sgw.projectusers.view.ui.widgets.UsersWidget;

/**
 * Created by Катя on 27.12.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterViewHolder> {

    @Inject
    Context context;
    private final List<Users> usersArrayList = new ArrayList<>();
    private RecyclerAdapter.RecyclerAdapterViewHolder mHolder;


    @Override
    public RecyclerAdapter.RecyclerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.setLayoutParams(lp);
        return new RecyclerAdapterViewHolder(new UsersWidget(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerAdapterViewHolder holder, int position) {
        mHolder = holder;
        Users users = usersArrayList.get(position);
        mHolder.view.setTvUserLogin(users.getLogin());
        mHolder.view.setTvUserLink(users.getUrl());
        mHolder.view.setIvUserIcon(Uri.parse(users.getAvatarUrl()));
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public void setList(Collection<Users> collection) {
        usersArrayList.clear();
        if (collection!=null)
        {
            usersArrayList.addAll(collection);
        }

       notifyDataSetChanged();

    }

    class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

        UsersWidget view;

        RecyclerAdapterViewHolder(View itemView) {
            super(itemView);
            view = (UsersWidget) itemView;
        }
    }

}