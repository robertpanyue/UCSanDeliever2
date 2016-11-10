package edu.ucsd.cse110.ucsandeliever;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class confirmPickingBidActivity extends AppCompatActivity {

    private String monFromStatus;
    private String timFromStatus;
    private String runFromStatus;

    private TextView moneyShow;
    private TextView timeShow;
    private TextView runnerShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_picking_bid);

        // get data from last page
        Intent i = getIntent();
        Bundle data = i.getExtras();
        monFromStatus = data.getString("moneyGet");
        timFromStatus = data.getString("timeGet");
        runFromStatus = data.getString("runnerGet");

        // find the textviews
        moneyShow = (TextView) findViewById(R.id.moneyText);
        timeShow = (TextView) findViewById(R.id.timeText);
        runnerShow = (TextView) findViewById(R.id.runnerText);

        // assign values to the textviews
        moneyShow.setText(monFromStatus);
        timeShow.setText(timFromStatus);
        runnerShow.setText(runFromStatus);

    }
    public void confirmSystem(View view){

        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Yes, choose this runner")){

            // to change because chat needs to get done first - Zihan
            Intent intent = new Intent(this,Chat.class);
            startActivity(intent);

        }else if(button_text.equals("No, go back to last page")){

            Intent intent = new Intent(this,orderStatus.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
    }

/*

    //回调Function
    titleSelectInterface aSelectInterface = new titleSelectInterface() {
        @Override
        public void onTitleSelect(String mon, String tim, String run) {

        }

    };


    public interface titleSelectInterface{
        public void onTitleSelect(String mon, String tim, String run);
    }



    public void onAttach(Activity activity) {
        monFromStatus = ((orderStatus) activity).getMoneySelected();
        timFromStatus = ((orderStatus) activity).getTimeSelected();
        runFromStatus = ((orderStatus) activity).getRunnerSelected();
    }

*/
}