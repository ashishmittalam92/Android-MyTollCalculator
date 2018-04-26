package com.mytollcalculator.mytollcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mytollcalculator.mytollcalculator.db.DatabaseHelper;
import com.mytollcalculator.mytollcalculator.model.TollDetail;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchButton;
    private EditText sourceEditText;
    private EditText destinationEditText;
    private TextView resultTextView;
    private LinearLayout tollDetailLinearLayout;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        searchButton = (Button) findViewById(R.id.search);
        searchButton.setOnClickListener(this);

        sourceEditText = (EditText) findViewById(R.id.source);
        destinationEditText = (EditText) findViewById(R.id.destination);

        resultTextView = (TextView) findViewById(R.id.result);

        tollDetailLinearLayout = (LinearLayout) findViewById(R.id.tollDetail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:

                resultTextView.setText("");

                tollDetailLinearLayout.removeAllViews();

                Log.i("source", sourceEditText.getText().toString());
                Log.i("destination", destinationEditText.getText().toString());

                String source = sourceEditText.getText().toString();
                String destination = destinationEditText.getText().toString();

                if(!source.equals("") && !destination.equals("")) {
                    List<TollDetail> tollDetails = databaseHelper.getTollDetails(source, destination);

                    if(tollDetails.size() > 0) {
                        double totalDistance = 0;
                        int totalCost = 0;

                        for(TollDetail tollDetail: tollDetails) {
                            totalDistance += tollDetail.getDistance();
                            totalCost += tollDetail.getSingleCost();

                            TextView textView = new TextView(this);
                            textView.setText(tollDetail.getTollName()
                                        + "\n Single Cost:" + tollDetail.getSingleCost()
                                        + "\n Return Cost:" + tollDetail.getReturnCost());

                            tollDetailLinearLayout.addView(textView);
                        }

                        String text = "Total distance: " + totalDistance
                                + "\n Total toll: " + tollDetails.size()
                                + "\n Total cost: " + totalCost;

                        resultTextView.setText(text);
                    } else {
                        resultTextView.setText("No toll exist.");
                    }
                }
        }
    }
}
