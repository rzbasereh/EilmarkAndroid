package com.example.eilmarkandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eilmarkandroid.ui.cart.model.CartItem;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    Context context;
    public DBHelper(Context context) {
        super(context, "Eilmark.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table if not exists CartItem(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, productID integer not null, quantity integer default 1) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists CartItem");
    }

    //  CartItem Table Start
    public boolean insertCartItem(int productID, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("productID", productID);
        values.put("quantity", quantity);
        long result = db.insert("CartItem", null, values);
        return result != -1;
    }

    public boolean updateCartItem(int id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantity", quantity);
        Cursor cursor = db.rawQuery("select * from CartItem where id=?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            long result = db.update("CartItem", values, "id=?", new String[]{String.valueOf(id)});
            return result != -1;
        }
        return false;
    }

    public boolean deleteCartItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CartItem where id=?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            long result = db.delete("CartItem", "id=?", new String[]{String.valueOf(id)});
            return result != -1;
        }
        return false;
    }

    public ArrayList<CartItem> getCartItems() {
        ArrayList<CartItem> cardItems = new ArrayList<CartItem>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CartItem", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                int productID = cursor.getInt(1);
                int quantity = cursor.getInt(2);
                cardItems.add(new CartItem(id, productID, quantity));
            }
        }
        return cardItems;
    }
//    Cart Table End

}
