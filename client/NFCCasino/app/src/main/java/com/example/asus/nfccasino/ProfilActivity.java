package com.example.asus.nfccasino;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Asus on 21/02/2017.
 */

public class ProfilActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = getIntent().getExtras().getParcelable("user");

        TextView txtLogin = (TextView) findViewById(R.id.txtLogin);
        txtLogin.setText(user.getLogin());

        Button nfc = (Button) findViewById(R.id.btnCredit);
        nfc.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, NFCActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

        Button lecture = (Button) findViewById(R.id.btnLecture);
        lecture.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, LectureNFCActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(ProfilActivity.this, MainActivity.class);  //Lancer l'activité
                //intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });
    }
}

