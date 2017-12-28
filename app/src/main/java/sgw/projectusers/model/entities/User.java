package sgw.projectusers.model.entities;


import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    private String url;
    private String login;
    private String avatar_url;
    private int id;

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.login);
        dest.writeString(this.avatar_url);
        dest.writeInt(this.id);
    }


    protected User(Parcel in) {
        this.url = in.readString();
        this.login = in.readString();
        this.avatar_url = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String  getUrl(){
        return url;
    }
    public String getLogin(){
        return login;
    }
    public String getAvatar_url(){
        return avatar_url;
    }
    public int getId(){
        return id;
    }
}