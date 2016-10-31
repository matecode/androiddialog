package de.mateware.dialog.licences;

import android.content.Context;

/**
 * Created by mate on 31.10.2016.
 */

public class MitLicence extends StandardLicence {

    public MitLicence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        super(context, title, copyRightOwner, copyRightYear, "mit.txt");
    }

}
