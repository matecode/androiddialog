package de.mateware.dialog.licences;

import android.content.Context;

import de.mateware.dialog.R;

/**
 * Created by mate on 31.10.2016.
 */

public class BsdLicence extends AssetFileLicence {

    public BsdLicence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        super(context, title, context.getString(R.string.bsd_copyright, copyRightYear, copyRightOwner), "bsd.txt");
        replaceInLicenceText("\\[\\[OWNER\\]\\]", String.valueOf(copyRightOwner));
    }



}
