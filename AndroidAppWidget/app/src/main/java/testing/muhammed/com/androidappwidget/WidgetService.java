package testing.muhammed.com.androidappwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

/**
 * Created by muhammed on 10/12/2017.
 */

public class WidgetService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        final String[] projection = {

                InventoryContract.InventoryTable.ID,
                InventoryContract.InventoryTable.NAME
        };
        final int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        getContentResolver().registerContentObserver(InventoryContract.INVENTORY_PRODUCT, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());


                for (int allWidgetId : allWidgetIds) {


                    ContentProviderClient client = getContentResolver().acquireContentProviderClient(InventoryContract.INVENTORY_AUTHORITY_URI);

                    try {
                        Cursor query = client.query(InventoryContract.INVENTORY_PRODUCT, projection, null, null, null);
                        if (query != null) {
                            if (query.moveToFirst()) {
                                String name = query.getString(query.getColumnIndex(InventoryContract.InventoryTable.NAME));
                                RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.new_app_widget);

                                remoteViews.setTextViewText(R.id.appwidget_text, name);

                                Intent clickIntent = new Intent(getApplicationContext().getApplicationContext(),
                                        NewAppWidget.class);

                                clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                                clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                                        allWidgetIds);
                                appWidgetManager.updateAppWidget(allWidgetId, remoteViews);

                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }


                }

                super.onChange(selfChange);
            }
        });


        return super.onStartCommand(intent, flags, startId);
    }
}
