package de.mateware.dialog.licences;

import android.content.Context;

import de.mateware.dialog.R;

/**
 * Created by mate on 31.10.2016.
 */

public class Agpl30Licence extends AssetFileLicence {

    public Agpl30Licence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        super(context, title, context.getString(R.string.agpl30_copyright, copyRightYear, copyRightOwner), "agpl30.txt");
    }

}
