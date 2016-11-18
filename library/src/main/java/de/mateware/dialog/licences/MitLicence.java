package de.mateware.dialog.licences;

import android.content.Context;

import de.mateware.dialog.R;

/**
 * Created by mate on 31.10.2016.
 */

public class MitLicence extends AssetFileLicence {

    public MitLicence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        super(context, title, context.getString(R.string.mit_copyright, copyRightYear, copyRightOwner), "mit.txt");
    }

}
