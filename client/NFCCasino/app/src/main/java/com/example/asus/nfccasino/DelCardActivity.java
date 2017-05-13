package com.example.asus.nfccasino;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author Gregory Vesic
 * @version 13/05/2017
 */
public class DelCardActivity extends AppCompatActivity {

    User user;

    ArrayList<String> listCard = new ArrayList<>();
    int choice = -1;
    String card="";
    double amount;

    /**
     * Méthode onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delcard);

        user = getIntent().getExtras().getParcelable("user");

        listCard.add("132" + "* **** **** ****");
        listCard.add("497" + "* **** **** ****");
        listCard.add("851" + "* **** **** ****");

        ListView lvCard = (ListView) findViewById(R.id.listCardType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(DelCardActivity.this,
                android.R.layout.simple_list_item_single_choice, listCard);

        lvCard.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvCard.setSelector(android.R.color.holo_blue_light);
        lvCard.setAdapter(adapter);
        lvCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                choice = position;
                card = listCard.get(choice);
            }
        });

        Button btnCredit = (Button) findViewById(R.id.btnCredit);
        btnCredit.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Context context = getApplicationContext();
                CharSequence text = card;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        });

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            @Override
            public void onClick(View activity_main)    //Au clic sur le bouton
            {
                Intent intent = new Intent(DelCardActivity.this, ProfilActivity.class);  //Lancer l'activité
                intent.putExtra("user", user); // Envoyer l'activité
                startActivity(intent);    //Afficher la vue
                finish();
            }

        });

    }


}
