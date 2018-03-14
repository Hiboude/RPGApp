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
    public static final String KEY_ID_CARACTERISTIQUE="caId";
    public static final String KEY_NOM_CARACTERISTIQUE="caNom";
    public static final String KEY_ICONE_CARACTERISTIQUE="caIcone";
    public static final String KEY_COLOR_CARACTERISTIQUE="caColor";
    public static final String KEY_XPREQUIS_CARACTERISTIQUE="caXpRequis";
    public static final String KEY_XPACTUELLE_CARACTERISTIQUE="caXpActuelle";
    public static final String KEY_NIVEAU_CARACTERISTIQUE="caNiveau";
    public static final String CREATE_TABLE_CARACTERISTIQUE = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_CARACTERISTIQUE+" INTEGER primary key," +
            " "+KEY_NOM_CARACTERISTIQUE+" TEXT" +
            " "+KEY_ICONE_CARACTERISTIQUE+" INTEGER" +
            " "+KEY_COLOR_CARACTERISTIQUE+" INTEGER" +
            " "+KEY_XPREQUIS_CARACTERISTIQUE+" INTEGER" +
            " "+KEY_XPACTUELLE_CARACTERISTIQUE+" INTEGER" +
            " "+KEY_NIVEAU_CARACTERISTIQUE+" INTEGER" +
            ");";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public CaracteristiqueManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addAnimal(Caracteristique caracteristique) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_CARACTERISTIQUE, caracteristique.getCaNom());
        values.put(KEY_ICONE_CARACTERISTIQUE, caracteristique.getCaIcone());
        values.put(KEY_COLOR_CARACTERISTIQUE, caracteristique.getColor());
        values.put(KEY_XPREQUIS_CARACTERISTIQUE, caracteristique.getXpRequis());
        values.put(KEY_XPACTUELLE_CARACTERISTIQUE, caracteristique.getXpActuel());
        values.put(KEY_NIVEAU_CARACTERISTIQUE, caracteristique.getNiveau());



        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modAnimal(Caracteristique caracteristique) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_CARACTERISTIQUE, caracteristique.getCaNom());
        values.put(KEY_ICONE_CARACTERISTIQUE, caracteristique.getCaIcone());
        values.put(KEY_COLOR_CARACTERISTIQUE, caracteristique.getColor());
        values.put(KEY_XPREQUIS_CARACTERISTIQUE, caracteristique.getXpRequis());
        values.put(KEY_XPACTUELLE_CARACTERISTIQUE, caracteristique.getXpActuel());
        values.put(KEY_NIVEAU_CARACTERISTIQUE, caracteristique.getNiveau());

        String where = KEY_ID_CARACTERISTIQUE+" = ?";
        String[] whereArgs = {caracteristique.getCaId()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supCaracteristique(Caracteristique caracteristique) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_CARACTERISTIQUE+" = ?";
        String[] whereArgs = {caracteristique.getCaId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Caracteristique getCaracteristique(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Caracteristique c =new Caracteristique(0,"");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_ANIMAL+"="+id, null);
        if (c.moveToFirst()) {
            a.setId_animal(c.getInt(c.getColumnIndex(KEY_ID_ANIMAL)));
            a.setNom_animal(c.getString(c.getColumnIndex(KEY_NOM_ANIMAL)));
            c.close();
        }

        return a;
    }

    public Cursor getAnimaux() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
}
