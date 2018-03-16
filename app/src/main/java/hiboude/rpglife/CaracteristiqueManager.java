package hiboude.rpglife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lucas on 14/03/2018.
 */

public class CaracteristiqueManager {
    private static final String TABLE_NAME = "Caracteristique";
    public static final String KEY_ID_CARACTERISTIQUE = "caId";
    public static final String KEY_NOM_CARACTERISTIQUE = "caNom";
    public static final String KEY_ICONE_CARACTERISTIQUE = "caIcone";
    public static final String KEY_COLOR_CARACTERISTIQUE = "caColor";
    public static final String KEY_XPREQUIS_CARACTERISTIQUE = "caXpRequis";
    public static final String KEY_XPACTUELLE_CARACTERISTIQUE = "caXpActuelle";
    public static final String KEY_NIVEAU_CARACTERISTIQUE = "caNiveau";
    public static final String CREATE_TABLE_CARACTERISTIQUE = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID_CARACTERISTIQUE + " INTEGER primary key," +
            " " + KEY_NOM_CARACTERISTIQUE + " TEXT" +
            " " + KEY_ICONE_CARACTERISTIQUE + " INTEGER" +
            " " + KEY_COLOR_CARACTERISTIQUE + " INTEGER" +
            " " + KEY_XPREQUIS_CARACTERISTIQUE + " INTEGER" +
            " " + KEY_XPACTUELLE_CARACTERISTIQUE + " INTEGER" +
            " " + KEY_NIVEAU_CARACTERISTIQUE + " INTEGER" +
            ");";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public CaracteristiqueManager(Context context) {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open() {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addCaracteristique(Caracteristique caracteristique) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_CARACTERISTIQUE, caracteristique.getCaNom());
        values.put(KEY_ICONE_CARACTERISTIQUE, caracteristique.getCaIcone());
        values.put(KEY_COLOR_CARACTERISTIQUE, caracteristique.getColor());
        values.put(KEY_XPREQUIS_CARACTERISTIQUE, caracteristique.getXpRequis());
        values.put(KEY_XPACTUELLE_CARACTERISTIQUE, caracteristique.getXpActuel());
        values.put(KEY_NIVEAU_CARACTERISTIQUE, caracteristique.getNiveau());


        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME, null, values);
    }

    public int modCaracteristique(Caracteristique caracteristique) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_CARACTERISTIQUE, caracteristique.getCaNom());
        values.put(KEY_ICONE_CARACTERISTIQUE, caracteristique.getCaIcone());
        values.put(KEY_COLOR_CARACTERISTIQUE, caracteristique.getColor());
        values.put(KEY_XPREQUIS_CARACTERISTIQUE, caracteristique.getXpRequis());
        values.put(KEY_XPACTUELLE_CARACTERISTIQUE, caracteristique.getXpActuel());
        values.put(KEY_NIVEAU_CARACTERISTIQUE, caracteristique.getNiveau());

        String where = KEY_ID_CARACTERISTIQUE + " = ?";
        String[] whereArgs = {caracteristique.getCaId() + ""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supCaracteristique(Caracteristique caracteristique) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_CARACTERISTIQUE + " = ?";
        String[] whereArgs = {caracteristique.getCaId() + ""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Caracteristique getCaracteristique(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Caracteristique c = new Caracteristique();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID_CARACTERISTIQUE + "=" + id, null);
        if (cur.moveToFirst()) {
            c.setCaId(cur.getInt(cur.getColumnIndex(KEY_ID_CARACTERISTIQUE)));
            c.setCaNom(cur.getString(cur.getColumnIndex(KEY_NOM_CARACTERISTIQUE)));
            c.setCaIcone(cur.getInt(cur.getColumnIndex(KEY_ICONE_CARACTERISTIQUE)));
            c.setNiveau(cur.getInt(cur.getColumnIndex(KEY_NIVEAU_CARACTERISTIQUE)));
            c.setXpRequis(cur.getInt(cur.getColumnIndex(KEY_XPREQUIS_CARACTERISTIQUE)));
            c.setXpActuel(cur.getInt(cur.getColumnIndex(KEY_XPACTUELLE_CARACTERISTIQUE)));
            c.setColor(cur.getInt(cur.getColumnIndex(KEY_COLOR_CARACTERISTIQUE)));
             /* TODO: GERER GET ARRAYLIST CORRESPONDANT AUX COMPETENCES D UNE CARACTERISTIQUE */

            cur.close();
        }

        return c;
    }

    public Cursor getCaracteristiques() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}