package com.example.asus.nfccasino;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

/**
 * @author Gregory Vesic
 * @version 28/03/2017
 */
public class LectureNFCActivity extends AppCompatActivity {
    // Profil
    User user;
    // NFC
    IntentFilter[] intentFiltersArray = null;
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;

    //Autre
    TextView resultat;

    /**
     * Méthode onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturenfc);

        user = getIntent().getExtras().getParcelable("user");

        //Lecture NFC
        resultat = (TextView) findViewById(R.id.txtReponse);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                nfcAdapter.disableForegroundDispatch(LectureNFCActivity.this);
                Intent intent = new Intent(LectureNFCActivity.this, ProfilActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });
    }

    /**
     * Méthode onResume
     */
    public void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, null);
    }

    /**
     * Méthode onPause
     */
    public void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    /**
     * Méthode onNewIntent
     * @param intent
     */
    public void onNewIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            resolveIntent(intent);
        }
    }

    /**
     * Méthode getTextData
     * @param payload
     * @return
     */
    String getTextData(byte[] payload) {
        String texteCode = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
        int langageCodeTaille = payload[0] & 0077;
        try
        {
            return new String(payload, langageCodeTaille + 1, payload.length - langageCodeTaille - 1, texteCode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Méthode resolveIntent
     * @param intent
     */
    public void resolveIntent(Intent intent) {

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        byte[] id = tag.getId();
        String[] technologies = tag.getTechList();
        int content = tag.describeContents();
        Ndef ndef = Ndef.get(tag);
        boolean isWritable = ndef.isWritable();
        boolean canMakeReadOnly = ndef.canMakeReadOnly();


        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage[] msgs;
        String message = null;
        if (rawMsgs != null){
            msgs = new NdefMessage[rawMsgs.length];

            for (int i = 0; i < rawMsgs.length; i++) {
                msgs[i] = (NdefMessage) rawMsgs[i];
                NdefRecord record = msgs[i].getRecords()[i];
                byte[] idRec = record.getId();
                short tnf = record.getTnf();
                byte[] type = record.getType();
                message = getTextData(record.getPayload());
            }
        }

        Log.i("msg", message);
        resultat.setText(message);

    }
}
