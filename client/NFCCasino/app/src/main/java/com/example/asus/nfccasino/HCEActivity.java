package com.example.asus.nfccasino;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.asus.nfccasino.MyHostApduService;

/**
 * Created by Asus on 25/04/2017.
 */


public class HCEActivity extends AppCompatActivity {
    // Profil
    User user;
    // NFC
    NfcAdapter nfcAdapter;
    // APDU
    MyHostApduService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        user = getIntent().getExtras().getParcelable("user");
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

}
