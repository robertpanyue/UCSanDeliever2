package edu.ucsd.cse110.ucsandeliever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class userRunnerWaitingActivity extends AppCompatActivity {

    private Button refresh;
    private String requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_runner_waiting);





        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int )(width*0.9),(int)(height* 0.35));


        // get the requestor for this bid
        Intent i = getIntent();
        Bundle data = i.getExtras();
        requester = data.getString("requestorGet");

        // get the runner for this bid aka the current user
        final String currUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersRef = mRootRef.child("users");
        DatabaseReference currRef = usersRef.child(currUid);
        usersRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.getValue(Student.class).getuid().contentEquals(currUid)) {
                    if (dataSnapshot.getValue(Student.class).getRunnerStatusIndicator()) {

                        // to change because chat needs to get done first - Zihan
                        // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                        // startActivity(intent);
                        //
                        //go to runner_contact page - he chang

                        Intent oldIntent = getIntent();
                        Bundle oldData = oldIntent.getExtras();
                        String payment = oldData.getString("payment");


                        Intent intent = new Intent(userRunnerWaitingActivity.this,runner_contactActivity.class);
                        Bundle b = new Bundle();
                        b.putString("requestorGet", requester);

                        String balance = dataSnapshot.getValue(Student.class).getBalance();
                        b.putString("payment", payment);
                        b.putString("balance", balance);
                        intent.putExtras(b);
                        startActivity(intent);
                    } else {
                        System.out.println("datachange not related to current user");
                    }
                }

            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.getValue(Student.class).getuid().contentEquals(currUid)) {
                    if (dataSnapshot.getValue(Student.class).getRunnerStatusIndicator()) {

                        // to change because chat needs to get done first - Zihan
                        // Intent intent = new Intent(userRunnerWaitingActivity.this, Chat.class);
                        // startActivity(intent);
                        //
                        //go to runner_contact page - he chang

                        Intent oldIntent = getIntent();
                        Bundle oldData = oldIntent.getExtras();
                        String payment = oldData.getString("payment");


                        Intent intent = new Intent(userRunnerWaitingActivity.this,runner_contactActivity.class);
                        Bundle b = new Bundle();
                        b.putString("requestorGet", requester);

                        String balance = dataSnapshot.getValue(Student.class).getBalance();
                        b.putString("payment", payment);
                        b.putString("balance", balance);
                        intent.putExtras(b);
                        startActivity(intent);
                    } else {
                        System.out.println("datachange not related to current user");
                    }
                }
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
