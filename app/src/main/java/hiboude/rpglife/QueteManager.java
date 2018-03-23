package hiboude.rpglife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by lucas on 14/03/2018.
 */

public class QueteManager {
    private static final String TABLE_NAME = "Quete";
    public static final String KEY_ID_QUETE = "qId";
    public static final String KEY_ID_COMPETENCE= "qCId";
    public static final String KEY_NOM_QUETE = "qNom";
    public static final String KEY_COMPLEXITE_QUETE = "qComplexite";
    public static final String KEY_APPREHENSION_QUETE = "qApprehension";
    public static final String KEY_IMPORTANCE_QUETE = "qImportance";
    public static final String KEY_DUREE_QUETE = "qDuree";
    public static final String KEY_DATEDEBUT_QUETE = "qDateDebut";
    public static final String KEY_DATEFIN_QUETE = "qDateFin";

    public static final String CREATE_TABLE_QUETE = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID_QUETE + " INTEGER primary key," +
            " " + KEY_ID_COMPETENCE + " INTEGER ," +
            " " + KEY_NOM_QUETE + " TEXT," +
            " " + KEY_COMPLEXITE_QUETE + " INTEGER," +
            " " + KEY_APPREHENSION_QUETE + " INTEGER," +
            " " + KEY_IMPORTANCE_QUETE + " INTEGER," +
            " " + KEY_DUREE_QUETE + " INTEGER," +
            " " + KEY_DATEDEBUT_QUETE + " TEXT," +
            " " + KEY_DATEFIN_QUETE + " TEXT," +
            " " + "FOREIGN KEY("+KEY_ID_COMPETENCE+") REFERENCES Competence(cId)" +
            ");";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public QueteManager(Context context) {
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

    public long addQuete(Quete quete) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_ID_COMPETENCE, quete.getqCId());
        values.put(KEY_NOM_QUETE, quete.getqNom());
        values.put(KEY_COMPLEXITE_QUETE, quete.getqComplexite());
        values.put(KEY_APPREHENSION_QUETE, quete.getqApprehension());
        values.put(KEY_IMPORTANCE_QUETE, quete.getqImportance());
        values.put(KEY_DUREE_QUETE, quete.getqDuree());
        values.put(KEY_DATEDEBUT_QUETE, quete.formatqDateDebut());
        values.put(KEY_DATEFIN_QUETE, quete.formatqDateFin());


        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME, null, values);
    }

    public int modQuete(Quete quete) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_ID_COMPETENCE, quete.getqCId());
        values.put(KEY_NOM_QUETE, quete.getqNom());
        values.put(KEY_COMPLEXITE_QUETE, quete.getqComplexite());
        values.put(KEY_APPREHENSION_QUETE, quete.getqApprehension());
        values.put(KEY_IMPORTANCE_QUETE, quete.getqImportance());
        values.put(KEY_DUREE_QUETE, quete.getqDuree());
        values.put(KEY_DATEDEBUT_QUETE, quete.formatqDateDebut());
        values.put(KEY_DATEFIN_QUETE, quete.formatqDateFin());

        String where = KEY_ID_QUETE + " = ?";
        String[] whereArgs = {quete.getqId() + ""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supQuete(Quete quete) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_QUETE + " = ?";
        String[] whereArgs = {quete.getqId() + ""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Quete getQuete(int id) {
        // Retourne la quete dont l'id est passé en paramètre

        Quete q = new Quete();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID_QUETE + "=" + id, null);
        if (cur.moveToFirst()) {
            q.setqId(cur.getInt(cur.getColumnIndex(KEY_ID_QUETE)));
            q.setqCId(cur.getInt(cur.getColumnIndex(KEY_ID_COMPETENCE)));
            q.setqNom(cur.getString(cur.getColumnIndex(KEY_NOM_QUETE)));
            q.setqApprehension(cur.getInt(cur.getColumnIndex(KEY_APPREHENSION_QUETE)));
            q.setqImportance(cur.getInt(cur.getColumnIndex(KEY_IMPORTANCE_QUETE)));
            q.setqComplexite(cur.getInt(cur.getColumnIndex(KEY_COMPLEXITE_QUETE)));
            q.setqDuree(cur.getInt(cur.getColumnIndex(KEY_DUREE_QUETE)));
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
            try {
                q.setqDateDebut(dt.parse(cur.getString(cur.getColumnIndex(KEY_DATEDEBUT_QUETE))));
                q.setqDateFin(dt.parse(cur.getString(cur.getColumnIndex(KEY_DATEFIN_QUETE))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

             /* TODO: GERER GET ARRAYLIST CORRESPONDANT AUX JOURS DE REPETITIONS */
            cur.close();
        }

        return q;
    }

    public ArrayList<Quete> getQuetes(int cId) {
        // sélection de toutes les quetes associées à une compétences de la table
        Cursor c = db.rawQuery("SELECT "+KEY_ID_QUETE +" FROM " + TABLE_NAME + " WHERE " + KEY_ID_COMPETENCE + "=" +cId , null);
        ArrayList<Quete> quetes = new ArrayList<>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int qId = c.getInt(c.getColumnIndex("qId"));
                    quetes.add(getQuete(qId));

                } while (c.moveToNext());
            }
        }
        return quetes;
    }
    public ArrayList<Quete> getQuetes() {
        // sélection de toutes les quetes associées à une compétences de la table
        Cursor c = db.rawQuery("SELECT "+KEY_ID_QUETE +" FROM " + TABLE_NAME , null);
        ArrayList<Quete> quetes = new ArrayList<>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    int qId = c.getInt(c.getColumnIndex("qId"));
                    quetes.add(getQuete(qId));

                } while (c.moveToNext());
            }
        }
        return quetes;
    }
}
