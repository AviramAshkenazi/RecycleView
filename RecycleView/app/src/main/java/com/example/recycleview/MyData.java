package com.example.recycleview;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MyData {

//    static String[] nameArray = {"Cupcake", "Dount", "Eclair"};
//    static String[] versionArray = {"1.5", "1.6", "2.0-2.1"};
//    static Integer[] drawableArray = {R.drawable.images, R.drawable.images, R.drawable.images};
//
//    static Integer[] id = {0,1,2};

    static DataModel currentCard ;
    static ArrayList<DataModel> dataset = new ArrayList<DataModel>();

    public MyData(){

    }

    public static void GetDataset(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cards");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                dataset.clear();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   dataSnapshot.getChildren().forEach((n) -> {
                       dataset.add ( (n.getValue(DataModel.class)) );
                       });
                }
                MainFragment.updateRecyclerview();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });






    }

}
