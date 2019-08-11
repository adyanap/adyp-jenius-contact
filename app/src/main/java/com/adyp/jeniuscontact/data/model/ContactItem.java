package com.adyp.jeniuscontact.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ContactItem implements Parcelable {
    @SerializedName("id") private String id;
    @SerializedName("firstName") private String firstName;
    @SerializedName("lastName") private  String lastName;
    @SerializedName("age") private  String age;
    @SerializedName("photo") private String photoPath;

    public ContactItem(){}

    protected ContactItem(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        age = in.readString();
        photoPath = in.readString();
    }

    public static final Creator<ContactItem> CREATOR = new Creator<ContactItem>() {
        @Override
        public ContactItem createFromParcel(Parcel in) {
            return new ContactItem(in);
        }

        @Override
        public ContactItem[] newArray(int size) {
            return new ContactItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.age);
        parcel.writeString(this.photoPath);
    }
}
