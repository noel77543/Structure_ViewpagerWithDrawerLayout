package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by noel on 2018/6/17.
 */

public class DetailData implements Parcelable {
    private String viewTitle;
    private int index;

    public String getViewTitle() {
        return viewTitle;
    }

    public void setViewTitle(String viewTitle) {
        this.viewTitle = viewTitle;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.viewTitle);
        dest.writeInt(this.index);
    }

    public DetailData() {
    }

    protected DetailData(Parcel in) {
        this.viewTitle = in.readString();
        this.index = in.readInt();
    }

    public static final Parcelable.Creator<DetailData> CREATOR = new Parcelable.Creator<DetailData>() {
        @Override
        public DetailData createFromParcel(Parcel source) {
            return new DetailData(source);
        }

        @Override
        public DetailData[] newArray(int size) {
            return new DetailData[size];
        }
    };
}
