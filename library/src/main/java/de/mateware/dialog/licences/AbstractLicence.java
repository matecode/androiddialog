package de.mateware.dialog.licences;

import android.content.Context;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import de.mateware.dialog.DialogAdapterList;

/**
 * Created by Mate on 30.10.2016.
 */

public abstract class AbstractLicence extends DialogAdapterList.DialogAdapterListEntry {
    public abstract CharSequence getTitle();
    public abstract CharSequence getSubTitle();
    public abstract CharSequence getLicenceText();

    public static String readFromLicencesAssetFolder(Context context, String filename) throws IOException {
        InputStream inputStream  = context.getAssets().open("licences/"+filename);
        String text = IOUtils.toString(inputStream,"UTF-8");
        inputStream.close();
        return text;
    }
}
