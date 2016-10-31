package de.mateware.dialog.licences;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;

import de.mateware.dialog.R;

/**
 * Created by Mate on 30.10.2016.
 */

public class StandardLicence extends AbstractLicence {

    private final CharSequence title;
    private final CharSequence subTitle;
    private final CharSequence licenceText;

    public StandardLicence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear, String licenceFilename) {
        this.title = title;
        this.subTitle = context.getString(R.string.apache20_copyright,copyRightYear,copyRightOwner);
        CharSequence tempLicenceText;
        try {
            tempLicenceText = readFromLicencesAssetFolder(context,licenceFilename);
        } catch (IOException e) {
            tempLicenceText = context.getString(R.string.default_no_text);
        }
        this.licenceText = tempLicenceText;
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
