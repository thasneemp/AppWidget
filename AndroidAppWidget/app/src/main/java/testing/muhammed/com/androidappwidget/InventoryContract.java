package testing.muhammed.com.androidappwidget;

import android.net.Uri;

/**
 * Created by muhammed on 10/9/2017.
 */

public class InventoryContract {

    public static final String DB_NAME = "inventoryapp";
    public static final int DB_VERSION = 2;

    public static final String INVENTORY_AUTHORITY = "com.inventory.app";
    public static final Uri INVENTORY_AUTHORITY_URI = Uri.parse("content://com.inventory.app");
    public static final Uri INVENTORY_PRODUCT = Uri.parse("content://com.inventory.app/" + InventoryTable.PRODUCT_TABLE);
    public static final Uri INVENTORY_PRODUCT_ID = Uri.parse("content://com.inventory.app/" + InventoryTable.PRODUCT_TABLE);
    public static final int PRODUCT = 100;
    public static final int PRODUCT_ID = 101;


    public InventoryContract() {
    }

    public static final class InventoryTable {
        public static final String PRODUCT_TABLE = "product_details";

        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String QUANTITY = "quantity";
        public static final String PRICE = "price";
        public static final String IMAGE = "image";


        public static final String CREATE_INVENTORY_TABLE = "CREATE TABLE " + PRODUCT_TABLE + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT NOT NULL,"
                + QUANTITY + " INTEGER DEFAULT 0," + PRICE + " INTEGER," + IMAGE + " TEXT)";


    }
}
