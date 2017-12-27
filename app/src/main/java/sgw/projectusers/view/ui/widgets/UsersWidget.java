package sgw.projectusers.view.ui.widgets;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sgw.projectusers.R;

/**
 * Created by Катя on 27.12.2017.
 */

public class UsersWidget extends RelativeLayout {
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;

    @BindView(R.id.tv_user_login)
    TextView tvUserLogin;

    @BindView(R.id.tv_user_link)
    TextView tvUserLink;

    @BindView(R.id.holder)
    LinearLayout holder;

    public UsersWidget(Context context) {
        super(context);
        init(context);
    }

    public UsersWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UsersWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context, ViewGroup parent) {
        inflate(context, R.layout.item_user, parent);
        ButterKnife.bind(this, this);
    }

    protected void init(Context context) {
        inflate(context, R.layout.item_user, this);
        ButterKnife.bind(this, this);
    }


    public void setTvUserLogin(String userLogin) {
     tvUserLogin.setText(userLogin);
    }

    public void setTvUserLink(String userLink) {
     tvUserLink.setText(userLink);
    }

    public void setIvUserIcon(Uri uri){
        ivUserIcon.setImageURI(uri);
    }


/*    public void setGameOutcomeAppearance(int gameStatus) {
        if (gameStatus==1) {
            holder.setBackgroundColor(getResources().getColor(R.color.backgroundColorWin));
            tvGameStatus.setTextColor(getResources().getColor(R.color.fontColorWin));
            tvBet.setTextColor(getResources().getColor(R.color.colorYellowFromLogoAccentText));
            tvGameName.setTextColor(getResources().getColor(R.color.colorYellowFromLogoAccentText));
        } else {
            holder.setBackgroundColor(getResources().getColor(R.color.backgroundColorLose));
            tvGameStatus.setTextColor(getResources().getColor(R.color.fontColorLose));
            tvBet.setTextColor(getResources().getColor(R.color.colorGrayFontHintOnEditText));
            tvGameName.setTextColor(getResources().getColor(R.color.colorGrayFontHintOnEditText));
        }
    }*/
}
