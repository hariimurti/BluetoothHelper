package net.harimurti.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"HardwareIds"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Preferences pref = new Preferences(this);
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        ((TextView)findViewById(R.id.txtName)).setText(btAdapter.getName());
        ((TextView)findViewById(R.id.txtAddress)).setText(btAdapter.getAddress());
        ((TextView)findViewById(R.id.txtLastDevice)).setText(pref.getLastRequested());

        Switch swToggle = findViewById(R.id.switchOverride);
        swToggle.setChecked(pref.isOverridePairingRequest());
        swToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                pref.setOverridePairingRequest(checked);
            }
        });
    }
}
