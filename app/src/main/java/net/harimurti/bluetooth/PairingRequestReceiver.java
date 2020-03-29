package net.harimurti.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.nio.charset.StandardCharsets;

public class PairingRequestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(BluetoothDevice.ACTION_PAIRING_REQUEST)) {
            try {
                Preferences pref = new Preferences(context);
                if (!pref.isOverridePairingRequest()) return;

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String pin = Integer.toString(
                        intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", 0));

                assert device != null;
                String deviceName = String.format("%s (%s)", device.getName(), device.getAddress());
                pref.setLastRequested(deviceName);
                Log.d("PairRequestReceiver", deviceName);
                Log.d("PairRequestReceiver", String.format("Pin Code is %s", pin));

                byte[] pinBytes = pin.getBytes(StandardCharsets.UTF_8);
                device.setPin(pinBytes);
                device.setPairingConfirmation(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
