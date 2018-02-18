package es.uca.oftapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NuestroWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.nuestro_widget);
        views.setTextViewText(R.id.appwidget_image, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);
            Intent intent = new Intent(context, ProgramaActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
            //establece un canal de comunicacion entre nuestra aplicacion y el widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.nuestro_widget);
            //cuando se realice un click sobre el widget se lance el Intent
            views.setOnClickPendingIntent(R.id.appwidget_image, pIntent);
            //actualizamos nuestro widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

