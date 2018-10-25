package fieldvalidators;

import android.view.View;
import android.widget.TextView;

public class ProfileValidator extends TextValidator implements View.OnFocusChangeListener{

    public ProfileValidator(TextView textView){
        super(textView);
    }

    @Override
    public boolean validate(TextView textView){
        if (textView.getText().toString().matches("")){
            textView.setError(textView.getHint() + " is required ");
            return false;
        } else {
            textView.setError(null);
            return true;
        }
    }

    public void onFocusChange(View textView, boolean hasFocus){
        if(!hasFocus){
            validate((TextView) textView);
        }
    }
}
