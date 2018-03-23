package hiboude.rpglife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucas on 14/03/2018.
 */

public class MySQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 2;
    private static MySQLite sInstance;

    public static synchronized MySQLite getInstance(Context context) {
        if (sInstance == null) { sInstance = new MySQLite(context); }
        return sInstance;
    }

    private MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Création de la base de données
        // on exécute ici les requêtes de création des tables
        sqLiteDatabase.execSQL(UtilisateurManager.CREATE_TABLE_UTILISATEUR); // création table "Utilisateur"
        sqLiteDatabase.execSQL(CaracteristiqueManager.CREATE_TABLE_CARACTERISTIQUE); //création table "Caracteristique"
        sqLiteDatabase.execSQL(CompetenceManager.CREATE_TABLE_COMPETENCE); //création table "Competence"
        sqLiteDatabase.execSQL(QueteManager.CREATE_TABLE_QUETE); //création table "Quete"
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Mise à jour de la base de données
        // méthode appelée sur incrémentation de DATABASE_VERSION
        // on peut faire ce qu'on veut ici, comme recréer la base :
        onCreate(sqLiteDatabase);
    }

} // class MySQLite