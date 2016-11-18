package de.mateware.dialog.licences;

import android.content.Context;

import de.mateware.dialog.R;

/**
 * Created by mate on 31.10.2016.
 */

public class Apache20Licence extends AssetFileLicence {

    public Apache20Licence(Context context, CharSequence title, CharSequence copyRightOwner, int copyRightYear) {
        super(context, title, context.getString(R.string.apache20_copyright, copyRightYear, copyRightOwner), "apache20.txt");
    }

}
