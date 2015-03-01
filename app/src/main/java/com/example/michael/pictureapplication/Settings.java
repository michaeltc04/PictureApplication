package com.example.michael.pictureapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Settings extends ActionBarActivity {
    //final SharedPreferences sharedPrefs = getSharedPreferences("Favorite Hero", MODE_PRIVATE);
    //final SharedPreferences.Editor editor = sharedPrefs.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup settings_radio_group = (RadioGroup) findViewById(R.id.radio_group_1);
        RadioButton techies_radio = (RadioButton) findViewById(R.id.radio_button_1);
        RadioButton winter_wyvern_radio = (RadioButton) findViewById(R.id.radio_button_2);
        RadioButton invoker_radio = (RadioButton) findViewById(R.id.radio_button_3);
        RadioButton earth_spirit_radio = (RadioButton) findViewById(R.id.radio_button_4);
        RadioButton templar_assassin_radio = (RadioButton) findViewById(R.id.radio_button_5);

        SharedPreferences sharedPrefs = getSharedPreferences("Favorite Hero", MODE_PRIVATE);

        switch (sharedPrefs.getInt("Favorite Hero", 0)) {
            case R.string.techies_string:
                settings_radio_group.check(R.id.radio_button_1);
                break;
            case R.string.winter_wyvern_string:
                settings_radio_group.check(R.id.radio_button_2);
                break;
            case R.string.invoker_string:
                settings_radio_group.check(R.id.radio_button_3);
                break;
            case R.string.earth_spirit_string:
                settings_radio_group.check(R.id.radio_button_4);
                break;
            case R.string.templar_assassin_string:
                settings_radio_group.check(R.id.radio_button_5);
                break;
            default: settings_radio_group.check(R.id.radio_button_1);
                break;
        }


        settings_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sharedPrefs = getSharedPreferences("Favorite Hero", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                switch (checkedId) {
                    case R.id.radio_button_1:
                        editor.putInt("Favorite Hero", R.string.techies_string);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Techies is your favorite!", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case R.id.radio_button_2:
                        editor.putInt("Favorite Hero", R.string.winter_wyvern_string);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Winter Wyvern is your favorite!", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case R.id.radio_button_3:
                        editor.putInt("Favorite Hero", R.string.invoker_string);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Invoker is your favorite!", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case R.id.radio_button_4:
                        editor.putInt("Favorite Hero", R.string.earth_spirit_string);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Earth Spirit is your favorite!", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case R.id.radio_button_5:
                        editor.putInt("Favorite Hero", R.string.templar_assassin_string);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Templar Assassin is your favorite!", Toast.LENGTH_LONG).show();
                        finish();
                        break;
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
