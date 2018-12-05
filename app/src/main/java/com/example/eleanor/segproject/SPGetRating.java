package com.example.eleanor.segproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class SPGetRating extends AppCompatActivity {
    int sum = 0, numRatings = 0;
    double avg = 0.0;
    DatabaseReference hoDB;
    TextView ratingText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_rating);

        hoDB = FirebaseDatabase.getInstance().getReference().child("homeOwners");

        ratingText = findViewById(R.id.ratingText);

        findRatings();

    }


    public void findRatings(){
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Iterate through service providers in DB
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();

                    if(next.child("Bookings").exists()){
                        System.out.println("BOOKINGS EXIST");
                        DataSnapshot bookShot = next.child("Bookings");

                        Iterable<DataSnapshot> bookShotIterator = bookShot.getChildren();
                        Iterator<DataSnapshot> bookIterator = bookShotIterator.iterator();

                        while(bookIterator.hasNext()){
                            DataSnapshot nextBooking = bookIterator.next();

                            if(nextBooking.child("Rating").exists()){
                                System.out.println("RATING EXISTS");

                                int n = getNumRatings();
                                n++;
                                setNumRatings(n);

                                String score = nextBooking.child("Rating").child("Score")
                                        .getValue().toString();
                                int s = Integer.valueOf(score);
                                System.out.println("SCORE: " + s);

                                int currentSum = getSum();
                                s += currentSum;
                                System.out.println("SUM " + s);

                                setSum(s);
                                setAverage(s, n);

                            }

                        }
                    }

                }
                String avgString = Double.toString(getAverage());
                System.out.println("SUMSTRING: " + avgString);
                
                ratingText.setText(avgString);
                ratingText.setVisibility(View.VISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        hoDB.addValueEventListener(valueEventListener);

    }

    public void setSum(int sum){
        System.out.println("Setting sum: " + sum);
        this.sum = sum;
    }

    public int getSum(){
        return sum;
    }

    public void setNumRatings(int num){
        this.numRatings = num;
    }

    public int getNumRatings(){
        return numRatings;
    }

    public void setAverage(int sum, int num){
        this.avg = sum/num;
    }

    public double getAverage(){
        return avg;
    }
}
