package de.mateware.dialog.licences;

import android.content.Context;

/**
 * Created by mate on 31.10.2016.
 */

public class Agpl30Licence extends StandardLicence {

    public Agpl30Licence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        super(context, title, copyRightOwner, copyRightYear, "agpl30.txt");
    }

}
