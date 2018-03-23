package hiboude.rpglife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by lucas on 14/03/2018.
 */

public class CompetenceManager {
    private static final String TABLE_NAME = "Competence";
    public static final String KEY_ID_COMPETENCE = "cId";
    public static final String KEY_ID_CARACTERISIQUE  = "cCaId";
    public static final String KEY_NOM_COMPETENCE = "cNom";
    public static final String KEY_ICONE_COMPETENCE = "cIcone";
    public static final String KEY_COLOR_COMPETENCE = "cColor";
    public static final String KEY_XPREQUIS_COMPETENCE = "cXpRequis";
    public static final String KEY_XPACTUELLE_COMPETENCE = "cXpActuelle";
    public static final String KEY_NIVEAU_COMPETENCE = "cNiveau";
    public static final String CREATE_TABLE_COMPETENCE = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID_COMPETENCE + " INTEGER primary key," +
            " " + KEY_ID_CARACTERISIQUE + " INTEGER ," +
            " " + KEY_NOM_COMPETENCE + " TEXT" +
            " " + KEY_ICONE_COMPETENCE + " INTEGER" +
            " " + KEY_COLOR_COMPETENCE + " INTEGER" +
            " " + KEY_XPREQUIS_COMPETENCE + " INTEGER" +
            " " + KEY_XPACTUELLE_COMPETENCE + " INTEGER" +
            " " + KEY_NIVEAU_COMPETENCE + " INTEGER" +
            " " + "FOREIGN KEY("+KEY_ID_CARACTERISIQUE+") REFERENCES Caracteristique(caId)" +
            ");";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public CompetenceManager(Context context) {
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

    public long addCompetence(Competence competence) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_COMPETENCE, competence.getcNom());
        values.put(KEY_ID_CARACTERISIQUE,competence.getcCaId());
        values.put(KEY_ICONE_COMPETENCE, competence.getcIcone());
        values.put(KEY_COLOR_COMPETENCE, competence.getColor());
        values.put(KEY_XPREQUIS_COMPETENCE, competence.getXpRequis());
        values.put(KEY_XPACTUELLE_COMPETENCE, competence.getXpActuel());
        values.put(KEY_NIVEAU_COMPETENCE, competence.getNiveau());


        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME, null, values);
    }

    public int modCompetence(Competence competence) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_COMPETENCE, competence.getcNom());
        values.put(KEY_ID_CARACTERISIQUE,competence.getcCaId());
        values.put(KEY_ICONE_COMPETENCE, competence.getcIcone());
        values.put(KEY_COLOR_COMPETENCE, competence.getColor());
        values.put(KEY_XPREQUIS_COMPETENCE, competence.getXpRequis());
        values.put(KEY_XPACTUELLE_COMPETENCE, competence.getXpActuel());
        values.put(KEY_NIVEAU_COMPETENCE, competence.getNiveau());

        String where = KEY_ID_COMPETENCE + " = ?";
        String[] whereArgs = {competence.getcId() + ""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supCompetence(Competence competence) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_COMPETENCE + " = ?";
        String[] whereArgs = {competence.getcId() + ""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Competence getCompetence(int id,Context context) {
        // Retourne l'animal dont l'id est passé en paramètre

        Competence c = new Competence();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID_COMPETENCE + "=" + id, null);
        if (cur.moveToFirst()) {
            c.setcId(cur.getInt(cur.getColumnIndex(KEY_ID_COMPETENCE)));
            c.setcCaId(cur.getInt(cur.getColumnIndex(KEY_ID_CARACTERISIQUE)));
            c.setcNom(cur.getString(cur.getColumnIndex(KEY_NOM_COMPETENCE)));
            c.setcIcone(cur.getInt(cur.getColumnIndex(KEY_ICONE_COMPETENCE)));
            c.setNiveau(cur.getInt(cur.getColumnIndex(KEY_NIVEAU_COMPETENCE)));
            c.setXpRequis(cur.getInt(cur.getColumnIndex(KEY_XPREQUIS_COMPETENCE)));
            c.setXpActuel(cur.getInt(cur.getColumnIndex(KEY_XPACTUELLE_COMPETENCE)));
            c.setColor(cur.getInt(cur.getColumnIndex(KEY_COLOR_COMPETENCE)));
             /* TODO: GERER GET ARRAYLIST CORRESPONDANT AUX QUETES D UNE COMPETENCE */
             QueteManager qm = new QueteManager(context);
             qm.open();
             c.setcQuetes(qm.getQuetes(cur.getInt(cur.getColumnIndex(KEY_ID_COMPETENCE))));
             qm.close();
            cur.close();
        }

        return c;
    }


    public ArrayList<Competence> getCompetences(int cCaId,Context context) {
        // sélection de toutes les compétences associées à une caracteristique de la table
        Cursor c = db.rawQuery("SELECT "+KEY_ID_COMPETENCE +" FROM " + TABLE_NAME + " WHERE " + KEY_ID_CARACTERISIQUE + "=" +cCaId , null);
        ArrayList<Competence> competences = new ArrayList<>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int cId = c.getInt(c.getColumnIndex("cId"));
                    competences.add(getCompetence(cId,context));

                } while (c.moveToNext());
            }
        }
        return competences;
    }
}
