package de.mateware.dialog.licences;

import android.content.Context;

import java.io.IOException;

import de.mateware.dialog.R;

/**
 * Created by mate on 18.11.2016.
 */

public abstract class AssetFileLicence extends StandardLicence {

    public AssetFileLicence(Context context, CharSequence title, CharSequence subtitle, String licenceFilename) {
        super(title, subtitle, null);
        CharSequence tempLicenceText;
        try {
            tempLicenceText = readFromLicencesAssetFolder(context,licenceFilename);
        } catch (IOException e) {
            tempLicenceText = context.getString(R.string.default_no_text);
        }
        setLicenceText(tempLicenceText);
    }
}
