package com.thesis.brown.brown.my_support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.thesis.brown.brown.model.Category;
import com.thesis.brown.brown.model.Product;

import java.util.ArrayList;

/**
 * Created by Obi-Voin Kenobi on 04-Jun-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_brown";

    // Contacts table name
    private static final String TABLE_PRODUCTS = "tbl_products";
    private static final String TABLE_CATEGORIES = "tbl_categories";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE_URL = "img_url";

    private static final String KEY_CATEGORY_TYPE = "type";
    private static final String KEY_PARENT_CATEGORY = "parent";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @NonNull
    public static String toDisplayCase(String s) {

        final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
        // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
        }
        return sb.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_IMAGE_URL + " TEXT" + ")";

        final String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CATEGORY_TYPE + " TEXT," + KEY_PARENT_CATEGORY + " TEXT" + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);

        // Create tables again
        onCreate(db);
    }

    // Insert a record into tbl_products
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, product.get_id()); // product ID
        values.put(KEY_NAME, toDisplayCase(product.get_name())); // product Name
        values.put(KEY_DESCRIPTION, product.get_desc()); // product description
        values.put(KEY_IMAGE_URL, product.get_imgUrl()); // product image url

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); // Closing database connection
    }

    // Select all product from tbl_products
    public ArrayList<Product> getAllProducts(String categoryId) {
        ArrayList<Product> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        if (categoryId != null) {
            selectQuery += " WHERE ";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.set_id(cursor.getString(0));
                product.set_name(cursor.getString(1));
                product.set_desc(cursor.getString(2));
                product.set_imgUrl(cursor.getString(3));
                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }

    // Select a product from tbl_products
    public Product getProduct(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PRODUCTS,
                new String[]{KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_IMAGE_URL},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Product product = new Product(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return product
        return product;
    }

    public int getProductCount() {
        String countQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }

    // Insert a record into tbl_categories
    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, category.getId());
        values.put(KEY_NAME, toDisplayCase(category.getName()));
        values.put(KEY_CATEGORY_TYPE, category.getType());
        values.put(KEY_PARENT_CATEGORY, category.getParent());

        // Inserting Row
        db.insert(TABLE_CATEGORIES, null, values);
        db.close(); // Closing database connection
    }

    // Select all categories from tbl_categories
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                // Adding contact to list
                categoryList.add(category);
            } while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return categoryList;
    }

    // Select a product from tbl_products
    public Category getCategory(String categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_CATEGORIES,
                new String[]{KEY_ID, KEY_NAME, KEY_CATEGORY_TYPE, KEY_PARENT_CATEGORY},
                KEY_ID + "=?",
                new String[]{String.valueOf(categoryId)},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Category category = new Category(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return category;
    }

    public int getCategoryCount(String parentCategory) {
        String countQuery = "SELECT * FROM " + TABLE_CATEGORIES;
        if (parentCategory != null) {
            countQuery += " WHERE " + KEY_PARENT_CATEGORY + "=" + parentCategory;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }
}
