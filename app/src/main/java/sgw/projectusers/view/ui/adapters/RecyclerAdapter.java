package sgw.projectusers.view.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.projectusers.R;
import sgw.projectusers.model.entities.User;




public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<User> users;
    Context context;
    private final View.OnClickListener mOnClickListener;


    public RecyclerAdapter(View.OnClickListener onClickListener, ArrayList<User> users, Context context) {

        mOnClickListener=onClickListener;
        this.users = users;
        this.context = context;
    }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            RecyclerView.ViewHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            View view = inflater.inflate(R.layout.item_user, parent, false);
            viewHolder = new UserViewHolder(view);

            return viewHolder;
        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        UserViewHolder userViewHolder = (UserViewHolder) holder;
        User user = users.get(position);
        userViewHolder.userName.setText(user.getLogin());
        userViewHolder.loginId.setText(String.valueOf(user.getUrl()));
        userViewHolder.setAvatarOnClickListener(mOnClickListener);
        if (!TextUtils.isEmpty(user.getAvatar_url())) {
            Glide.with(context)
                    .load(user.getAvatar_url())
                    .into(userViewHolder.avatar);
        }
    }


    public void setUsers(List<User> u) {
        int count = getItemCount();
        users.addAll(u);
        notifyItemRangeInserted(count, u.size());
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public int getLastVisibleItemId() {
        if (users.isEmpty()) {
            return 0;
        }
        return users.get(users.size() - 1).getId();
    }



    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_login)
        TextView userName;
        @BindView(R.id.tv_user_link)
        TextView loginId;
        @BindView(R.id.iv_user_icon)
        ImageView avatar;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setAvatarOnClickListener(View.OnClickListener onClickListener)
        {
            avatar.setOnClickListener(onClickListener);
        }
    }

}