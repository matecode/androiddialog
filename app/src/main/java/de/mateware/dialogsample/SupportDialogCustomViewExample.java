package de.mateware.dialogsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import de.mateware.dialog.SupportDialogCustomView;

/**
 * Created by mate on 19.10.2016.
 */

public class SupportDialogCustomViewExample extends SupportDialogCustomView {

    public static final String EXTRA_TEST_ARGUMENT = "extraArgument";

    EditText editText;

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View result = inflater.inflate(R.layout.custom_view_dialog_content,parent);
        editText = (EditText) result.findViewById(R.id.editText);
        return result;
    }

//    @Override
//    public Bundle addArgumentsToDialogAfterButtonClick(Bundle dialogArguments, int which) {
//        //This is how you can read content from your Dialog, just save your view as a local field and read the content here and pass it as an argument to the bundle.
//        dialogArguments.putString(EXTRA_TEST_ARGUMENT,editText.getText().toString());
//        return dialogArguments;
//    }

    public static class Builder extends SupportDialogCustomView.AbstractBuilder<Builder,SupportDialogCustomViewExample> {
        public Builder() {
            super(SupportDialogCustomViewExample.class);
        }
    }

}
