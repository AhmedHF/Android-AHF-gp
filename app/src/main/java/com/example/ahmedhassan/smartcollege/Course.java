package com.example.ahmedhassan.smartcollege;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Hassan on 13/06/2017.
 */

public class Course implements Parcelable {
    String ID;
    String name;
    String code;
    String description;
    String grade;
    String count;

    public Course() {
    }

    public Course(String ID, String name, String code, String description, String grade, String count) {
        this.ID = ID;
        this.name = name;
        this.code = code;
        this.description = description;
        this.grade = grade;
        this.count = count;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public static Creator<Course> getCREATOR() {
        return CREATOR;
    }

    protected Course(Parcel in) {
        ID = in.readString();
        name = in.readString();

        code = in.readString();
        description = in.readString();
        grade = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(name);
        dest.writeString(code);
        dest.writeString(description);
        dest.writeString(grade);
    }
}
