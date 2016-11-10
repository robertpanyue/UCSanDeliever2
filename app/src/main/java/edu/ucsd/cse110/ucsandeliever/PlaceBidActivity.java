package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.firebase.client.*;
import com.google.firebase.auth.*;
import android.text.TextUtils;

public class PlaceBidActivity extends android.app.Fragment implements TimePickerDialog.OnTimeSetListener {

    int hour, minute;
    Button bPick, bPlace;
    EditText money, timeFromClock;
    View myView;
    Firebase ref = new Firebase("https://uc-student-deliver.firebaseio.com/");

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_place_bid, container, false);

        bPick = (Button) myView.findViewById(R.id.timePicker);
        bPlace = (Button) myView.findViewById(R.id.button4);
        money = (EditText) myView.findViewById(R.id.editText12);
        timeFromClock = (EditText) myView.findViewById(R.id.editText7);

        bPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
                minute = calendar.get(java.util.Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), PlaceBidActivity.this, hour, minute, true);
                timePickerDialog.show();
            }
        });

        bPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDataBase1();
                Intent intent = new Intent(getActivity(), userRunnerWaitingActivity.class);
                startActivity(intent);
            }
        });

        return myView;
    }

    public void updateDataBase1() {
        String id;
        id = mAuth.getCurrentUser().getUid();
        Bid bid = new Bid(money.getText().toString(), timeFromClock.getText().toString(), id);
        if (TextUtils.isEmpty(money.toString()) ||
                TextUtils.isEmpty(timeFromClock.getText()) ||
                TextUtils.isEmpty(id)) {
            System.out.println("Not enough");
        } else {
            ref.child("orders").child(orderNumb).child("bids").child(bid.getBidNum()).setValue(bid);
        }
    }





    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        timeFromClock.setText("" + hourOfDay + "" + minute);
    }

    private String orderNumb;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        orderNumb = ((drawerActivity) activity).getOrderNumSelected();
        System.out.println("order 已接受");

    }
}

