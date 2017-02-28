package com.example.asus.nfccasino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *  Evenements lors du clic sur les différents boutons
         */
        Button btnConnexion = (Button) findViewById(R.id.btnConnexion);   //Appel du Bouton
        btnConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);  //Lancer l'activité
                startActivity(intent);    //Afficher la vue

                //Intent i = new Intent(getApplicationContext(), ProfilActivity.class);
                //startActivity(i);
            }
        });

        Button btnInscription = (Button) findViewById(R.id.btnInscription);   //Appel du Bouton
        //btnInscription.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        btnInscription.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);  //Lancer l'activité
                startActivity(intent);    //Afficher la vue
                //Intent i = new Intent(getApplicationContext(), InscriptionActivity.class);
                //startActivity(i);
            }
        });
    }
}
