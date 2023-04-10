package fr.odddd.vachesdefrance;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/fr.odddd.vachesdefrance/databases/";
    private static String DB_NAME = "Vaches.sqlite";
    private static String path = DB_PATH+DB_NAME;
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    //test
    public void onOpen(SQLiteDatabase db) {
        db.disableWriteAheadLogging();  // Here the solution
        super.onOpen(db);
    }
    //test

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean isdbExist = checkDataBase();

        if(isdbExist) {
            SQLiteDatabase db = null;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            db.execSQL("DROP TABLE " + "caracteristiques" + ";");
            db.close();
            // si l'erreur de la table definition qui n'exite pas, met un try catch avec l'erreur recup
        }

        try{
            this.getReadableDatabase();
            copyDataBase();
        }
        catch(IOException e){
            throw new Error("Probleme de copie de la base de données");
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        // TODO Auto-generated method stub
        SQLiteDatabase db = null;
        try{
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            if (db != null)
                db.close();
        }
        catch(SQLiteException e){
            e.printStackTrace();
        }

        return db!=null?true:false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
        // TODO Auto-generated method stub
        InputStream inputStream = myContext.getAssets().open(DB_NAME);
        OutputStream outputStream = new FileOutputStream(path);

        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer))>0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void openDataBase() throws SQLException {
        myDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close(){

        if(myDataBase !=null)
            myDataBase.close();
        super.close();
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String requete = "SELECT race, robe, type, region, lvl FROM caracteristiques";
        Cursor result = db.rawQuery(requete, null);
        Log.d("requete", requete);
        return result;
    }

    public Cursor getAll_lvl(int lvl){
        SQLiteDatabase db = this.getWritableDatabase();
        String requete = "SELECT race, robe, type, region FROM caracteristiques WHERE lvl == " + lvl;
        Cursor result = db.rawQuery(requete, null);
        Log.d("requete", requete);
        return result;
    }

    public Cursor getRace(){
        SQLiteDatabase db = this.getWritableDatabase();
        String requete = "SELECT race FROM caracteristiques";
        Cursor result = db.rawQuery(requete, null);
        Log.d("requete", requete);
        return result;
    }

    public Cursor getType(String race){
        SQLiteDatabase db = this.getWritableDatabase();
        String requete = "SELECT type FROM caracteristiques WHERE race == " + '"' + race + '"';
        Cursor result = db.rawQuery(requete, null);
        Log.d("requete", requete);
        return result;
    }

    public Cursor getRegion(String race){
        SQLiteDatabase db = this.getWritableDatabase();
        String requete = "SELECT region FROM caracteristiques WHERE race == " + '"' + race + '"';
        Cursor result = db.rawQuery(requete, null);
        Log.d("requete", requete);
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
