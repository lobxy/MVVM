package com.lovish.mvvmapp.main.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
    private int id;
    private String name;
    private String image;
    private String lastName;
    private boolean checked;

    public UserData(int id, String name, String lastName, boolean checked, String image) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.checked = checked;
        this.image = image;
    }

    public UserData() {
        
    }

    protected UserData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        lastName = in.readString();
        checked = in.readByte() != 0;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(lastName);
        dest.writeByte((byte) (checked ? 1 : 0));
    }
}
