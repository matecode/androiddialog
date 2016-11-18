package de.mateware.dialog.licences;

import android.os.Parcel;
import android.os.Parcelable;

import de.mateware.dialog.DialogAdapterList;

/**
 * Created by Mate on 30.10.2016.
 */

public class StandardLicence extends DialogAdapterList.DialogAdapterListEntry {

    private CharSequence title;
    private CharSequence subTitle;
    private CharSequence licenceText;

    public StandardLicence(CharSequence title, CharSequence subTitle, CharSequence licenceText) {
        this.title = title;
        this.subTitle = subTitle;
        this.licenceText = licenceText;
    }

    public CharSequence getTitle() {
        return title;
    }

    public CharSequence getSubTitle() {
        return subTitle;
    }

    public CharSequence getLicenceText() {
        return licenceText;
    }

    public void setLicenceText(CharSequence licenceText) {
        this.licenceText = licenceText;
    }

    public void setSubTitle(CharSequence subTitle) {
        this.subTitle = subTitle;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    protected StandardLicence(Parcel in) {
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
    public static final Parcelable.Creator<StandardLicence> CREATOR = new Parcelable.Creator<StandardLicence>() {
        @Override
        public StandardLicence createFromParcel(Parcel in) {
            return new StandardLicence(in);
        }

        @Override
        public StandardLicence[] newArray(int size) {
            return new StandardLicence[size];
        }
    };
}
