package com.example.asus.nfccasino;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.nio.charset.Charset;
import java.util.Locale;

/**
 * @author Gregory Vesic
 * @version 28/03/2017
 */
public class NFCActivity extends AppCompatActivity {
    // Profil
    User user;
    // NFC
    NfcAdapter nfcAdapter;

    /**
     * Méthode onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        user = getIntent().getExtras().getParcelable("user");
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                nfcAdapter.disableForegroundDispatch(NFCActivity.this);
                Intent intent = new Intent(NFCActivity.this, ProfilActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });
    }

    /**
     * Méthode onPause
     */
    protected void onPause()
    {
        super.onPause();
        nfcAdapter.disableForegroundNdefPush(this);
    }

    /**
     * Méthode onResume
     */
    protected void onResume()
    {
        super.onResume();
        NdefRecord record = creerRecord("Test");
        NdefMessage message = creerMessage(record);
        nfcAdapter.enableForegroundNdefPush(this, message);
    }

    /**
     * Méthode creerRecord
     * @param message
     * @return
     */
    NdefRecord creerRecord(String message)
    {
        byte[] langBytes = Locale.ENGLISH.getLanguage().getBytes(Charset.forName("US-ASCII"));
        byte[] textBytes = message.getBytes(Charset.forName("UTF-8"));
        char status = (char) (langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    /**
     * Méthode creerMessage
     * @param record
     * @return
     */
    NdefMessage creerMessage(NdefRecord record)
    {
        NdefRecord[] records = new NdefRecord[1];
        records[0] = record;
        NdefMessage message = new NdefMessage(records);
        return message;
    }
}
