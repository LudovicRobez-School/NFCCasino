package com.example.asus.nfccasino;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author Gregory Vesic
 * @version 13/05/2017
 */
public class AddCardActivity extends AppCompatActivity {
    User user;
    CreditCard creditCard;

    ArrayList<String> listCard = new ArrayList<>();
    int choice = -1;
    String cardType="";

    /**
     * Méthode onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        user = getIntent().getExtras().getParcelable("user");
        creditCard = getIntent().getExtras().getParcelable("creditCard");

        listCard.add("EuroCard/ MasterCard");
        listCard.add("Visa");
        listCard.add("American Express");
        listCard.add("JCB");

        ListView listCardType = (ListView) findViewById(R.id.listCardType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddCardActivity.this,
                android.R.layout.simple_list_item_single_choice, listCard);

        listCardType.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listCardType.setSelector(android.R.color.holo_blue_light);
        listCardType.setAdapter(adapter);
        listCardType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                choice = position;
                cardType = listCard.get(choice);
            }
        });

        final EditText editNumber = (EditText) findViewById(R.id.editNumber);
        final EditText editDateExp = (EditText) findViewById(R.id.editDateExp);
        final EditText editCtrlNumber = (EditText) findViewById(R.id.editCtrlNumber);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
            if (editNumber.getText().toString() != null && editDateExp.getText().toString() != null && editCtrlNumber != null && cardType != null) {
                creditCard.setNumber(Long.valueOf(editNumber.getText().toString()));
                creditCard.setExpDate(editDateExp.getText().toString());
                creditCard.setCtrlNumber(Integer.valueOf(editCtrlNumber.getText().toString()));
                creditCard.setType(cardType);
                Log.i("creditCard", creditCard.getNumber() + " " + creditCard.getExpDate() + " " + creditCard.getCtrlNumber() + " " + creditCard.getType());
                // addCreditCard()
                Context context = getApplicationContext();
                CharSequence text = "Ajout de la carte " + cardType + " effectuée.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Veuillez remplir tous les champs.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            }

        });

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            /**
             *  Méthode onClick
             * @param activity_main
             */
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(AddCardActivity.this, ProfilActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                intent.putExtra("creditCard", creditCard); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });
    }
}
