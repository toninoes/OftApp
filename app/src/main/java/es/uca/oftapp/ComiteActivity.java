package es.uca.oftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class ComiteActivity extends AppCompatActivity {

    private TextView txt1;
    private CheckBox chk1;
    private EditText edt1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comite);

        txt1 = (TextView) findViewById(R.id.txt1);
        chk1 = (CheckBox) findViewById(R.id.chk1);
        edt1 = (EditText) findViewById(R.id.edTxt1);
        btn1 = (Button)   findViewById(R.id.btn1);

        //Some url endpoint that you may have
        final String urlTodosPonentes = "http://10.0.2.2:8080/ServicioWeb/oftalmologia/todosPonentes";
        final String urlUnPonente = "http://10.0.2.2:8080/ServicioWeb/oftalmologia/obtenerPonente/";
        //String to place our result in
        String resultados = getResultados(urlTodosPonentes);
        imprimirSinDetalles(resultados);

        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultados = getResultados(urlTodosPonentes);

                if(chk1.isChecked()) {
                    if (resultados != null) {
                        JSONObject a ;
                        StringBuilder  nombre = new StringBuilder();
                        try {
                            a = new JSONObject (resultados) ;
                            Iterator<String> iter = a.keys();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                JSONObject value = a.getJSONObject(key);
                                nombre.append("*\t").append(value.getString("nombre")).append(" ")
                                        .append(value.getString("apellidos")).append("\n\t")
                                        .append(value.getString("afiliacion")).append("\n\t")
                                        .append(value.getString("pais")).append("\n");
                            }
                            txt1.setText(nombre);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    imprimirSinDetalles(resultados);
                }
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = getResultados(urlUnPonente + edt1.getText());
                String eliminar1, eliminar2, eliminar3;
                eliminar1 = "</div>";
                eliminar2 = "<div style='color: red; font-weight: bold;'>";
                eliminar3 = "<div style='color: orange; font-weight: bold;'>";
                resultado = resultado.replaceAll(eliminar1, "")
                                     .replaceAll(eliminar2, "")
                                     .replaceAll(eliminar3, "")
                                     .replaceAll("NOMBRE", "\nNOMBRE")
                                     .replaceAll("APELLIDOS", "\nAPELLIDOS")
                                     .replaceAll("AFILIACIÓN", "\nAFILIACIÓN")
                                     .replaceAll("PAÍS", "\nPAÍS");
                txt1.setText(resultado);
            }
        });



    }

    private void imprimirSinDetalles(String resultados) {
        if (resultados != null) {
            JSONObject a;
            StringBuilder nombre = new StringBuilder();
            try {
                a = new JSONObject(resultados);
                Iterator<String> iter = a.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    JSONObject value = a.getJSONObject(key);
                    nombre.append("* ").append(value.getString("nombre")).append("\n");
                }
                txt1.setText(nombre);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private String getResultados(String URL) {
        String r = null;

        ComiteREST getRequest = new ComiteREST();
        //Perform the doInBackground method, passing in our url
        try {
            r = getRequest.execute(URL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            r = null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            r = null;
        }

        return r;
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
                Intent intentComite = new Intent(ComiteActivity.this, ComiteActivity.class);
                startActivity(intentComite);
                return true;
            case R.id.action_programa:
                Intent intentPrograma = new Intent(ComiteActivity.this, ProgramaActivity.class);
                startActivity(intentPrograma);
                return true;
            case R.id.action_fechas:
                Intent intentFechas = new Intent(ComiteActivity.this, FechasActivity.class);
                startActivity(intentFechas);
                return true;
            case R.id.action_localizacion:
                Intent intentLocalizacion = new Intent(ComiteActivity.this, LocalizacionActivity.class);
                startActivity(intentLocalizacion);
                return true;
            case R.id.action_inicio:
                Intent intentMain = new Intent(ComiteActivity.this, MainActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
