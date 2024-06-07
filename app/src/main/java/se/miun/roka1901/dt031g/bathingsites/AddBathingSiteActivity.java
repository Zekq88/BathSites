package se.miun.roka1901.dt031g.bathingsites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.RatingBar;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class AddBathingSiteActivity extends AppCompatActivity {

    private boolean textNotEmpty = true;
    private ArrayDeque<String> textList = new ArrayDeque<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bathing_site);

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_bath_site, menu);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int save = R.id.save;
        int clear = R.id.clear;
        EditText name = findViewById(R.id.name);
        EditText desc = findViewById(R.id.desc);
        EditText address = findViewById(R.id.adress);
        EditText lat = findViewById(R.id.lat);
        EditText lon = findViewById(R.id.lon);
        RatingBar grade = findViewById(R.id.grade);
        EditText temp = findViewById(R.id.temp);
        EditText date = findViewById(R.id.date);

        int id = item.getItemId();

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        date.setText(currentDate.toString());

        if (id == R.id.backToSettings){
            return true;
        } else if (id == save) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            if (name.getText().toString().trim().isEmpty()){
                textNotEmpty = false;
                name.setError(name.getHint() + " is empty!", null);
                dialog.setTitle("Name is missing!")
                        .setMessage("You need to type in either the Name")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textList.clear();
                                textNotEmpty = true;
                            }
                        }).show();
            } else if ((address.getText().toString().isEmpty()) && (lat.getText().toString().isEmpty() || lon.getText().toString().isEmpty())){
                    address.setError(address.getHint() + " is empty!", null);
                    lat.setError(lat.getHint() + " is empty!", null);
                    lon.setError(lon.getHint() + " is empty!", null);
                    textNotEmpty = false;
                    dialog.setTitle("Coordinates or Address is missing!")
                            .setMessage("You need to type in either the coordinates or Address")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    textList.clear();
                                    textNotEmpty = true;
                                }
                            }).show();

            } else {


                putItIn("Date", date.getText().toString());
                putItIn("Temp", temp.getText().toString());
                if (!(lon.getText().toString().isEmpty() && lat.getText().toString().isEmpty())){
                textList.push("Longitude: " +lon.getText() + " & " +"Latitude: " + lat.getText());
                }
                String yeahStar = String.valueOf(grade.getRating());
                textList.push("Grade: " + yeahStar);
                putItIn("Address", address.getText().toString());
                putItIn("Description",desc.getText().toString());
                putItIn("Name", name.getText().toString());



                if (textNotEmpty) {
                    bathDialog();
                }
            }

        } else if (id == clear) {
            textNotEmpty = true;
            setClear(R.id.name);
            setClear(R.id.desc);
            setClear(R.id.adress);
            setClear(R.id.lat);
            setClear(R.id.lon);
            setClear(R.id.temp);
            date.setText(currentDate);
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }



        return super.onOptionsItemSelected(item);
    }

    private void bathDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("New bath Site")
                .setMessage(Arrays.toString(textList.toArray()).replace(",", "\n")
                        .replace("[", " ").replace("]", ""))
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textList.clear();
                    }
                })
                .show();
    }


    private void setClear(int id) {
        EditText view = findViewById(id);
        view.setText("");
        textList.clear();
    }

    private void putItIn(String a, String ss){

        if (!ss.isEmpty()){
            textList.push(a + ": " + ss);
        }
    }

    }
