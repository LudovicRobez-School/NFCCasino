package com.example.asus.nfccasino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Greg on 14/05/2017.
 */

public class AddPaymentActivity extends AppCompatActivity {
    User user;
    Payment payment;
    //CreditCard creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpayment);

        user = getIntent().getExtras().getParcelable("user");
        payment = getIntent().getExtras().getParcelable("payment");

        MyHostApduService apduService = new MyHostApduService();
        //apduService.setToken("tokenPaiement" + payment.getAmount() + "e");
        apduService.setToken("token");
        Log.i("token", apduService.getToken());

        TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtAmount.setText("Montant du paiement : " + payment.getAmount() +"€");

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(AddPaymentActivity.this, ProfilActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                intent.putExtra("payment", payment); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

    }
}
