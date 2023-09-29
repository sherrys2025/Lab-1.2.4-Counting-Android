package com.example.lab_124_counting_android;

import android.content.res.AssetManager;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab_124_counting_android.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssetManager assetManager = this.getApplicationContext().getAssets();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = String.valueOf(binding.editText1.getText());
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                Counter counter;
                try {
                    counter = new Counter(assetManager,filename);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String toDisplay = "The most common word in the text file \"<font color=\"#6682B5\"><b>" + filename + "</b></font>\" is \"<font color=\"#6682B5\"><b>" + counter.topN(0)[0] + "</b></font>\" with <font color=\"#6682B5\"><b>" + counter.topN(0)[1] + "</b></font> occurences.";
                binding.textView.setText(Html.fromHtml(toDisplay));
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = String.valueOf(binding.editText1.getText());
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                Counter counter;
                try {
                    counter = new Counter(assetManager,filename);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String toDisplay = "The top five most common words in the text file \"<font color=\"#6682B5\"><b>" + filename + "</b></font>\" are <br><br>";
                for (int i = 0; i < 5; i++) {
                    toDisplay += i+1 + ". \"" + "<font color=\"#6682B5\"><b>"+counter.topN(i)[0] + "</b></font>\" with <font color=\"#6682B5\"><b>" + counter.topN(i)[1] + "</b></font> occurences <br>";
                }
                binding.textView.setText(Html.fromHtml(toDisplay));
            }
        });


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}