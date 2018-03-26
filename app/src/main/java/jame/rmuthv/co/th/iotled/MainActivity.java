package jame.rmuthv.co.th.iotled;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference LED1,LED2,LED3,TEXT;
    private static final String TAG = "LEDs Control";
    public Button Switch1,Switch2,Switch3;
    public Integer Value,Value1,Value2,Value_refer,Value_refer1,Value_refer2;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();

        LED1 =firebaseDatabase.getReference("Control/Switch/Blue");
        LED2 =firebaseDatabase.getReference("Control/Switch/Green");
        LED3 =firebaseDatabase.getReference("Control/Switch/Red");


        Switch1 = (Button)findViewById(R.id.btSwitch);
        Switch2 = (Button)findViewById(R.id.btSwitch2);
        Switch3 = (Button)findViewById(R.id.btSwitch3);

        LED1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Value = dataSnapshot.getValue(Integer.class);
                Log.d(TAG, "Value is: " + Value);
                if (Value == 1){
                    Switch1.setText("Blue LED ON");
                    Value_refer = 0;
                }else {
                    Switch1.setText("Blue LED OFF");
                    Value_refer = 1;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        Switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LED1.setValue(Value_refer);
            }
        });

        LED2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Value1 = dataSnapshot.getValue(Integer.class);
                Log.d(TAG, "Value is: " + Value1);
                if (Value1 == 1){
                    Switch2.setText("Green LED ON");
                    Value_refer1 = 0;
                }else {
                    Switch2.setText("Green LED OFF");
                    Value_refer1 = 1;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        Switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LED2.setValue(Value_refer1);
            }
        });

        LED3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Value2 = dataSnapshot.getValue(Integer.class);
                Log.d(TAG, "Value is: " + Value2);
                if (Value2 == 1){
                    Switch3.setText("RED LED ON");
                    Value_refer2 = 0;
                }else {
                    Switch3.setText("RED LED OFF");
                    Value_refer2 = 1;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        Switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LED3.setValue(Value_refer2);
            }
        });
        firebaseDatabase =FirebaseDatabase.getInstance();
        TEXT = firebaseDatabase.getReference();

        textView = (TextView)findViewById(R.id.textView);


        TEXT.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String led = String.valueOf(map.get("Ultrasonic"));
                textView.setText(led);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
