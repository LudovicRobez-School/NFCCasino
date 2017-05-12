package com.example.asus.nfccasino;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *  Evenements lors du clic sur les différents boutons
         */
        final EditText editLogin = (EditText) findViewById(R.id.editLogin);
        final EditText editMdp = (EditText) findViewById(R.id.editMdp);
        Button btnConnexion = (Button) findViewById(R.id.btnConnexion);   //Appel du Bouton
        btnConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                if (editLogin.getText().toString().equals("test") && editMdp.getText().toString().equals("test")){
                    // if (user.checkCustomer(user, editLogin.getText().toString(), editMdp.getText().toString()) == true) {
                    user = new User(editLogin.getText().toString());
                    //user.initProfil(user);
                    Intent intent = new Intent(MainActivity.this, ProfilActivity.class);  //Lancer l'activité
                    intent.putExtra("user", user); // Envoyer l'activité
                    startActivity(intent);    //Afficher la vue
                    finish();

                } else
                {
                    Log.i("login",editLogin.getText().toString());
                    Log.i("mdp",editMdp.getText().toString());
                    // Toast
                    Context context = getApplicationContext();
                    CharSequence text = "Login ou MDP incorrect !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
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
            }
        });
    }
}
