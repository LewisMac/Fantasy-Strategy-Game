package com.example.user.fantasystrategygame;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by user on 07/01/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    //DATABASE NAME
    private static final String DATABASE_NAME = "fantasyStrategyGame";

    //TABLE NAMES
    private static final String TABLE_PLAYER_STATS = "player";
    private static final String TABLE_HELD_DOMAINS = "held_domains";
    private static final String TABLE_AVAILABLE_DOMAINS = "available_domains";

    //TABLE COLUMN NAMES
    private static final String KEY_ID = "id";

    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_PLAYER_SPECIES = "player_species";
    private static final String KEY_DOMAINS_HELD = "domains_held";
    private static final String KEY_GEMS = "player_gems";
    private static final String KEY_FOOD = "player_food";
    private static final String KEY_WOOD = "player_wood";
    private static final String KEY_STONE = "player_stone";
    private static final String KEY_IRON = "player_iron";

    private static final String KEY_DOMAIN_NAME = "domain_name";
    private static final String KEY_BUILDINGS = "domain_buildings";
    private static final String KEY_BUILDING_SLOTS = "domain_building_slots";

    private static final String KEY_UNIT_ONE = "archer";
    private static final String KEY_UNIT_TWO = "swordsman";
    private static final String KEY_UNIT_THREE = "shield";
    private static final String KEY_UNIT_FOUR = "cavalry";
    private static final String KEY_UNIT_FIVE = "pikemen";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    //CREATING TABLES
    @Override
    public void onCreate(SQLiteDatabase db){

//        db.execSQL("DROP TABLE " + TABLE_ENCLOSURES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMALS);

        String CREATE_PLAYER_TABLE =
                "CREATE TABLE " + TABLE_PLAYER_STATS + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_PLAYER_NAME + " TEXT,"
                        + KEY_PLAYER_SPECIES + " TEXT,"
                        + KEY_DOMAINS_HELD + " TEXT,"
                        + KEY_GEMS + " TEXT,"
                        + KEY_FOOD + " TEXT,"
                        + KEY_WOOD + " TEXT,"
                        + KEY_STONE + " TEXT,"
                        + KEY_IRON + " TEXT )";
        db.execSQL(CREATE_PLAYER_TABLE);

        String CREATE_DOMAIN_FREE_TABLE =
                "CREATE TABLE " + TABLE_AVAILABLE_DOMAINS + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_DOMAIN_NAME + " TEXT,"
                        + KEY_BUILDING_SLOTS + " TEXT )";
        db.execSQL(CREATE_DOMAIN_FREE_TABLE);

        String CREATE_DOMAIN_HELD_TABLE =
                "CREATE TABLE " + TABLE_HELD_DOMAINS + "("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_DOMAIN_NAME + " TEXT,"
                        + KEY_BUILDING_SLOTS + " TEXT,"
                        + KEY_BUILDINGS + " TEXT )";
        db.execSQL(CREATE_DOMAIN_HELD_TABLE);
    }

    public void createPlayer(Player player){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_NAME, player.getName());
        values.put(KEY_PLAYER_SPECIES, player.getSpecies());
        values.put(KEY_DOMAINS_HELD, player.getDomainsHeld());

        //insert row
        db.insert(TABLE_PLAYER_STATS, null, values);
        db.close(); //close connection
    }

    public void createFreeDomains(Domain domain){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOMAIN_NAME, domain.getName());
        values.put(KEY_BUILDING_SLOTS, domain.getSize());

        //insert row
        db.insert(TABLE_AVAILABLE_DOMAINS, null, values);
        db.close(); //close connection
    }

    public void captureDomain(Domain domain){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOMAIN_NAME, domain.getName());
        values.put(KEY_BUILDING_SLOTS, domain.getSize());
        values.put(KEY_BUILDINGS, domain.getBuildings());
        values.put(KEY_UNIT_ONE, domain.getUnitOne());
        values.put(KEY_UNIT_TWO, domain.getUnitOne());
        values.put(KEY_UNIT_THREE, domain.getUnitOne());
        values.put(KEY_UNIT_FOUR, domain.getUnitOne());
        values.put(KEY_UNIT_FIVE, domain.getUnitOne());

        db.insert(TABLE_HELD_DOMAINS, null, values);
        db.close(); //close connection

        int id = domain.getId();
        String sql = "DELETE FROM " + TABLE_AVAILABLE_DOMAINS + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);

    }

    public void

}
