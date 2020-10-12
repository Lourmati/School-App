package Presenter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Student implements Parcelable {

    private String name;
    private int progression;

    public Student(String name, int progression) {
        this.name = name;
        this.progression = progression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgression() {
        return progression;
    }

    public void setProgression(int progression) {
        this.progression = progression;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.progression);
    }

    protected Student(Parcel in) {
        this.name = in.readString();
        this.progression = in.readInt();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return "Note{" +
                "titre='" + name + '\'' +
                ", note='" + progression + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(progression, student.progression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, progression);
    }
}
