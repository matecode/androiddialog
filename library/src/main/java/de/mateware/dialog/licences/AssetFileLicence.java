package de.mateware.dialog.licences;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

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

    private static String readFromLicencesAssetFolder(Context context, String filename) throws IOException {
        InputStream inputStream  = context.getAssets().open("licences/"+filename);
        String text = IOUtils.toString(inputStream,"UTF-8");
        inputStream.close();
        return text;
    }
}
