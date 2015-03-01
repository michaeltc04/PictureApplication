package com.example.michael.pictureapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int index = 0; //image array location
    private int invoker_state = -1;
    private ImageView iv;
    private int[] dota_images;
    private LinearLayout main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            invoker_state = savedInstanceState.getInt("InvokerState");
            index = savedInstanceState.getInt("HeroIndex");
        }

        main_layout = (LinearLayout) findViewById(R.id.main_activity_layout);
        Button back = (Button) findViewById(R.id.back_button);
        Button forward = (Button) findViewById(R.id.forward_button);
        dota_images = new int[]{R.drawable.techies, R.drawable.winter_wyvern, R.drawable.invoker, R.drawable.earth_spirit, R.drawable.templar_assassin};
        iv = (ImageView) findViewById(R.id.image_view);

        //setFavoriteHero();
        SharedPreferences sp = getSharedPreferences("Favorite Hero", MODE_PRIVATE);
        String fav;
        switch (sp.getInt("Favorite Hero", 0)) {
            case R.string.techies_string:
                fav = getResources().getString(R.string.techies_string);
                break;
            case R.string.winter_wyvern_string:
                fav = getResources().getString(R.string.winter_wyvern_string);
                break;
            case R.string.invoker_string:
                fav = getResources().getString(R.string.invoker_string);
                break;
            case R.string.earth_spirit_string:
                fav = getResources().getString(R.string.earth_spirit_string);
                break;
            case R.string.templar_assassin_string:
                fav = getResources().getString(R.string.templar_assassin_string);
                break;
            default: fav = "Need to select a favorite hero";
                break;
        }
        Toast.makeText(getApplicationContext(), "Your favorite hero is " + fav, Toast.LENGTH_LONG).show();

        iv.setImageResource(dota_images[index]);
        setMainBackgroundColor(index);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if(index < 0) index = dota_images.length - 1;
                setMainBackgroundColor(index);
                iv.setImageResource(dota_images[index]);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index > dota_images.length - 1) index = 0;
                setMainBackgroundColor(index);
                iv.setImageResource(dota_images[index]);
            }
        });
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sp = getSharedPreferences("Favorite Hero", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("Favorite Hero");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setFavoriteHero();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.invoker_main_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int sound_file = 0;
        switch (item.getItemId()){
            case R.id.invoker_quas:
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_quas_color));
                invoker_state = 0;
                sound_file = 0;
                break;
            case R.id.invoker_wex:
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_wex_color));
                invoker_state = 1;
                sound_file = 1;
                break;
            case R.id.invoker_exort:
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_exort_color));
                invoker_state = 2;
                sound_file = 2;
                break;
        }
        MediaPlayer mp = new MediaPlayer();

        switch (sound_file) {
            case 0:
                mp = MediaPlayer.create(this, R.raw.invoker_context_quas_select);
                break;
            case 1:
                mp = MediaPlayer.create(this, R.raw.invoker_context_wex_select);
                break;
            case 2:
                mp = MediaPlayer.create(this, R.raw.invoker_context_exort_select);
                break;

        }

        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;
            }
        });
        return super.onContextItemSelected(item);
    }

    public void setMainBackgroundColor(int index) {

        if(index == 2) {
            registerForContextMenu(iv);
            switch (invoker_state) {
                case 0:
                    index = 5;
                    break;
                case 1:
                    index = 6;
                    break;
                case 2:
                    index = 7;
                    break;
            }
        } else unregisterForContextMenu(iv);

        switch(index) {
            case 0:
                main_layout.setBackgroundColor(getResources().getColor(R.color.techies_color));
                break;
            case 1:
                main_layout.setBackgroundColor(getResources().getColor(R.color.winter_wyvern_color));
                break;
            case 2:
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_default_color));
                break;
            case 3:
                main_layout.setBackgroundColor(getResources().getColor(R.color.earth_spirit_color));
                break;
            case 4:
                main_layout.setBackgroundColor(getResources().getColor(R.color.templar_assassin_color));
                break;
            case 5:
                index = 2;
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_quas_color));
                break;
            case 6:
                index = 2;
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_wex_color));
                break;
            case 7:
                index = 2;
                main_layout.setBackgroundColor(getResources().getColor(R.color.invoker_exort_color));
                break;
        }
    }

    public void setFavoriteHero() {
        SharedPreferences sharedPrefs = getSharedPreferences("Favorite Hero", MODE_PRIVATE);
        switch (sharedPrefs.getInt("Favorite Hero", R.string.techies_string)) {
            case R.string.techies_string: index = 0; setMainBackgroundColor(index); break;
            case R.string.winter_wyvern_string: index = 1; setMainBackgroundColor(index); break;
            case R.string.invoker_string: index = 2; invoker_state = -1; setMainBackgroundColor(index); break;
            case R.string.earth_spirit_string: index = 3; setMainBackgroundColor(index); break;
            case R.string.templar_assassin_string: index = 4; setMainBackgroundColor(index); break;
            default: index = 0;
                setMainBackgroundColor(index);
                Toast.makeText(getApplicationContext(), "You should select a favorite hero in Settings", Toast.LENGTH_LONG).show();
                break;
        }
        iv.setImageResource(dota_images[index]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings_intent = new Intent(this, Settings.class);
            startActivity(settings_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("InvokerState", invoker_state);
        outState.putInt("HeroIndex", index);
        super.onSaveInstanceState(outState);
    }
}
