package es.uca.oftapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class ProgramaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);
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
        switch (id) {
            case R.id.action_comite:
                Intent intentComite = new Intent(ProgramaActivity.this, ComiteActivity.class);
                startActivity(intentComite);
                return true;
            case R.id.action_programa:
                Intent intentPrograma = new Intent(ProgramaActivity.this, ProgramaActivity.class);
                startActivity(intentPrograma);
                return true;
            case R.id.action_fechas:
                Intent intentFechas = new Intent(ProgramaActivity.this, FechasActivity.class);
                startActivity(intentFechas);
                return true;
            case R.id.action_localizacion:
                Intent intentLocalizacion = new Intent(ProgramaActivity.this, LocalizacionActivity.class);
                startActivity(intentLocalizacion);
                return true;
            case R.id.action_inicio:
                Intent intentMain = new Intent(ProgramaActivity.this, MainActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
