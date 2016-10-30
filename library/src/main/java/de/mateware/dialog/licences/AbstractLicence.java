package de.mateware.dialog.licences;

import de.mateware.dialog.DialogAdapterList;

/**
 * Created by Mate on 30.10.2016.
 */

public abstract class AbstractLicence extends DialogAdapterList.DialogAdapterListEntry {
    public abstract CharSequence getTitle();
    public abstract CharSequence getSubTitle();
    public abstract CharSequence getLicenceText();
}
