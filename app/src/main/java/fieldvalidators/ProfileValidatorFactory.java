package fieldvalidators;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eleanor.segproject.HomeOwner;

import java.util.ArrayList;
import java.util.List;

public class ProfileValidatorFactory <T extends HomeOwner>{
   private ViewGroup viewGroup;

   private View view;

   private List<View> possibleTextViews = new ArrayList<View>();

   public void setUpValidators(T t){
       viewGroup = ((ViewGroup) t.findViewById(android.R.id.content));
       view = viewGroup.getChildAt(0);
       possibleTextViews = view.getFocusables(0);

       for(View possibleTextView : possibleTextViews){
           if(possibleTextView instanceof EditText){
               ((EditText) possibleTextView).addTextChangedListener(new
                       ProfileValidator((EditText) possibleTextView));
               ((EditText) possibleTextView).setOnFocusChangeListener(new
                       ProfileValidator((EditText) possibleTextView));
           }
       }
   }

   public boolean validate(){
       for(View possibleTextView : possibleTextViews){
           if(possibleTextView instanceof EditText){
               String error = ((EditText)
                       possibleTextView).getError() == null?null:((EditText)
                       possibleTextView).getError().toString();
               boolean validated = possibleTextView.toString().matches("");

               if(!validated) return false;
           }
       }

       return true;
   }
}
