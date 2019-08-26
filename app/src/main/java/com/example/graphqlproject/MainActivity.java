package com.example.graphqlproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button anime_button,poke_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseViews();
    }

    private void initialiseViews(){
        anime_button=findViewById(R.id.anime_button);
        poke_button=findViewById(R.id.pokemon_button);

        anime_button.setOnClickListener(this);
        poke_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.anime_button:
                Fragment AnimeFragment=new AnimeFragment();
                performTransaction(AnimeFragment);
                break;

            case R.id.pokemon_button:
                PokemonFragment pokemonFragment=new PokemonFragment();
                performTransaction(pokemonFragment);
                break;
        }
    }

    public void performTransaction(Fragment fragment){
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container_for_fragment,fragment).commit();
    }

}
