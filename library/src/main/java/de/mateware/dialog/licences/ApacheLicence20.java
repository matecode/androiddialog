package de.mateware.dialog.licences;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import de.mateware.dialog.R;

/**
 * Created by Mate on 30.10.2016.
 */

public class ApacheLicence20 extends AbstractLicence {

    private final CharSequence title;
    private final CharSequence subTitle;
    private final CharSequence licenceText;

    public ApacheLicence20(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        this.title = title;
        this.subTitle = context.getString(R.string.apache20_copyright,copyRightYear,copyRightOwner);
        this.licenceText = context.getString(R.string.apache20_text);
    }

    @Override
    public CharSequence getTitle() {
        return title;
    }

    @Override
    public CharSequence getSubTitle() {
        return subTitle;
    }

    @Override
    public CharSequence getLicenceText() {
        return licenceText;
    }

    protected ApacheLicence20(Parcel in) {
        title = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
        subTitle = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
        licenceText = (CharSequence) in.readValue(CharSequence.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(subTitle);
        dest.writeValue(licenceText);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ApacheLicence20> CREATOR = new Parcelable.Creator<ApacheLicence20>() {
        @Override
        public ApacheLicence20 createFromParcel(Parcel in) {
            return new ApacheLicence20(in);
        }

        @Override
        public ApacheLicence20[] newArray(int size) {
            return new ApacheLicence20[size];
        }
    };
}
