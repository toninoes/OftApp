package es.uca.oftapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class FechasActivity extends AppCompatActivity {

    private ImageButton btnEdicion1, btnEdicion2;
    private static final  int NOTIFIC_ALERTA_ID = 1;

    private Calendar ahora = Calendar.getInstance();
    private Calendar Edicion1 = Calendar.getInstance();
    private Calendar Edicion2 = Calendar.getInstance();

    public FechasActivity() {
        Edicion1.set(2017, 10, 27);
        Edicion2.set(2018, 10, 27);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechas);

        btnEdicion1 = (ImageButton) findViewById(R.id.btnEdicion1);
        btnEdicion2 = (ImageButton) findViewById(R.id.btnEdicion2);

        btnEdicion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ahora.compareTo(Edicion1) < 0) {
                    NotificationCompat.Builder notificacion = new NotificationCompat.Builder(FechasActivity.this)
                            .setSmallIcon(R.drawable.logo)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_warning))
                            .setContentTitle(getResources().getString(R.string.mensaje_alerta))
                            .setContentText(getResources().getString(R.string.todavia_no))
                            .setTicker(getResources().getString(R.string.nueva_notif));

                    Intent intent = new Intent(FechasActivity.this, LocalizacionActivity.class);
                    PendingIntent pIntent = PendingIntent.getActivity(FechasActivity.this, 0, intent, 0);
                    notificacion.setContentIntent(pIntent); //Asociamos el PendinIntent a la notificación
                    NotificationManager mNotMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotMan.notify(NOTIFIC_ALERTA_ID, notificacion.build()); //Genera la notificación
                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.ya_paso), Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                    toast1.show();
                }
            }
        });


        btnEdicion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ahora.compareTo(Edicion2) < 0) {
                    NotificationCompat.Builder notificacion = new NotificationCompat.Builder(FechasActivity.this)
                            .setSmallIcon(R.drawable.logo)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_warning))
                            .setContentTitle(getResources().getString(R.string.mensaje_alerta))
                            .setContentText(getResources().getString(R.string.todavia_no))
                            .setTicker(getResources().getString(R.string.nueva_notif));

                    Intent intent = new Intent(FechasActivity.this, LocalizacionActivity.class);
                    PendingIntent pIntent = PendingIntent.getActivity(FechasActivity.this, 0, intent, 0);
                    notificacion.setContentIntent(pIntent); //Asociamos el PendinIntent a la notificación
                    NotificationManager mNotMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotMan.notify(NOTIFIC_ALERTA_ID, notificacion.build()); //Genera la notificación
                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.ya_paso), Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                    toast1.show();
                }
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
        switch (id) {
            case R.id.action_comite:
                Intent intentComite = new Intent(FechasActivity.this, ComiteActivity.class);
                startActivity(intentComite);
                return true;
            case R.id.action_programa:
                Intent intentPrograma = new Intent(FechasActivity.this, ProgramaActivity.class);
                startActivity(intentPrograma);
                return true;
            case R.id.action_fechas:
                Intent intentFechas = new Intent(FechasActivity.this, FechasActivity.class);
                startActivity(intentFechas);
                return true;
            case R.id.action_localizacion:
                Intent intentLocalizacion = new Intent(FechasActivity.this, LocalizacionActivity.class);
                startActivity(intentLocalizacion);
                return true;
            case R.id.action_inicio:
                Intent intentMain = new Intent(FechasActivity.this, MainActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
