package de.mateware.dialog;

import de.mateware.dialog.licences.LicenceInterface;

/**
 * Created by Mate on 30.10.2016.
 */

public class LicenceDialog extends Dialog {


    public abstract static class AbstractBuilder<T extends AbstractBuilder, K extends LicenceDialog> extends Dialog.AbstractBuilder<T,K> {

        public AbstractBuilder(Class<K> dialogBaseClass) {
            super(dialogBaseClass);
        }

        public T addLicence(LicenceInterface licence) {
         return (T) this;
        }

    }

}
