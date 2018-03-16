package hiboude.rpglife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lucas on 16/03/2018.
 */

public class UtilisateurManager {
    private static final String TABLE_NAME = "Utilisateur";
    public static final String KEY_ID_UTILISATEUR = "uId";
    public static final String KEY_PSEUDO_UTILISATEUR = "uPseudo";
    public static final String KEY_PIECE_UTILISATEUR = "uPiece";
    public static final String KEY_COLOR_UTILISATEUR = "uColor";
    public static final String KEY_XPREQUIS_UTILISATEUR = "uXpRequis";
    public static final String KEY_XPACTUELLE_UTILISATEUR = "uXpActuelle";
    public static final String KEY_NIVEAU_UTILISATEUR = "uNiveau";
    public static final String CREATE_TABLE_UTILISATEUR = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + KEY_ID_UTILISATEUR + " INTEGER primary key," +
            " " + KEY_PSEUDO_UTILISATEUR + " TEXT," +
            " " + KEY_PIECE_UTILISATEUR + " INTEGER," +
            " " + KEY_COLOR_UTILISATEUR + " INTEGER," +
            " " + KEY_XPREQUIS_UTILISATEUR + " INTEGER," +
            " " + KEY_XPACTUELLE_UTILISATEUR + " INTEGER," +
            " " + KEY_NIVEAU_UTILISATEUR + " INTEGER" +
            ");";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    public UtilisateurManager(Context context) {
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

    public long addUtilisateur(Utilisateur utilisateur) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_ID_UTILISATEUR,utilisateur.getuId());
        values.put(KEY_PSEUDO_UTILISATEUR, utilisateur.getPseudo());
        values.put(KEY_PIECE_UTILISATEUR, utilisateur.getPiece());
        values.put(KEY_COLOR_UTILISATEUR,utilisateur.getColor());
        values.put(KEY_XPREQUIS_UTILISATEUR, utilisateur.getXpRequis());
        values.put(KEY_XPACTUELLE_UTILISATEUR, utilisateur.getXpActuel());
        values.put(KEY_NIVEAU_UTILISATEUR, utilisateur.getNiveau());
        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME, null, values);
    }

    public int modUtilisateur(Utilisateur utilisateur) {
        System.out.println("MODUTILISATEUR");

        ContentValues values = new ContentValues();
        values.put(KEY_PSEUDO_UTILISATEUR, utilisateur.getPseudo());
        values.put(KEY_PIECE_UTILISATEUR, utilisateur.getPiece());
        values.put(KEY_COLOR_UTILISATEUR,utilisateur.getColor());
        values.put(KEY_XPREQUIS_UTILISATEUR, utilisateur.getXpRequis());
        values.put(KEY_XPACTUELLE_UTILISATEUR, utilisateur.getXpActuel());
        values.put(KEY_NIVEAU_UTILISATEUR, utilisateur.getNiveau());
        String where = KEY_ID_UTILISATEUR + " = ?";
        String[] whereArgs = {utilisateur.getuId() + ""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public Utilisateur getUtilisateur() {

        Utilisateur u = new Utilisateur();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        if (cur.moveToFirst()) {
            u.setuId(cur.getInt(cur.getColumnIndex(KEY_ID_UTILISATEUR)));
            u.setuPseudo(cur.getString(cur.getColumnIndex(KEY_PSEUDO_UTILISATEUR)));
            u.setuPiece(cur.getInt(cur.getColumnIndex(KEY_PIECE_UTILISATEUR)));
            u.setColor(cur.getInt(cur.getColumnIndex(KEY_COLOR_UTILISATEUR)));
            u.setXpRequis(cur.getInt(cur.getColumnIndex(KEY_XPREQUIS_UTILISATEUR)));
            u.setXpActuel(cur.getInt(cur.getColumnIndex(KEY_XPACTUELLE_UTILISATEUR)));
            u.setNiveau(cur.getInt(cur.getColumnIndex(KEY_NIVEAU_UTILISATEUR)));
            cur.close();
        }

        return u;
    }
}
