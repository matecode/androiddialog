package de.mateware.dialog.licences;

import android.content.Context;
import android.text.TextUtils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mate on 18.11.2016.
 */

public abstract class AssetFileLicence extends StandardLicence {

    public AssetFileLicence(Context context, CharSequence title, String licenceFilename) {
        this(context,title,null,licenceFilename);
    }

    public AssetFileLicence(Context context, CharSequence title, CharSequence subtitle, String licenceFilename) {
        super();
        try {
            setLicenceText(readFromLicencesAssetFolder(context,licenceFilename));
        } catch (IOException e) {

        }
        setTitle(title);
        setSubTitle(subtitle);
    }

    private static String readFromLicencesAssetFolder(Context context, String filename) throws IOException {
        InputStream inputStream  = context.getAssets().open("licences/"+filename);
        String text = IOUtils.toString(inputStream,"UTF-8");
        inputStream.close();
        return text;
    }

    public void replaceInLicenceText(String tag, String replacement) {
        if (!TextUtils.isEmpty(getLicenceText())) {
            setLicenceText(getLicenceText().toString().replaceAll(tag,replacement));
        }
    }
}
