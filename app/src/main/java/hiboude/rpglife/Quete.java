package hiboude.rpglife;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Quete implements Serializable {

    private int qId;
    private String qNom;
    private String qDescription;
    private int qComplexite;
    private int qImportance;
    private int qApprehension;
    private int qDuree;
    private int qCId;
    private HashMap<String,Integer> qRepetitions ;
    private Date qDateDebut;
    private Date qDateFin;


    public Quete(int qId ,int qCId ,String qNom, String qDescription, int qComplexite,int qImportance, int qApprehension, int qDuree, HashMap<String, Integer> qRepetitions, Date qDateDebut) {
        this.qId = qId;
        this.qCId = qCId;
        this.qNom = qNom;
        this.qDescription = qDescription;
        this.qComplexite = qComplexite;
        this.qImportance=qImportance;
        this.qApprehension = qApprehension;
        this.qDuree = qDuree;
        this.qRepetitions = qRepetitions;
        this.qDateDebut = qDateDebut;
        Calendar cal = Calendar.getInstance();
        cal.setTime(qDateDebut);
        cal.add(Calendar.DATE, qDuree);
        qDateFin =  cal.getTime();

    }
    public Quete() {
        this.qId = 0;
        this.qCId=0;
        this.qNom = "";
        this.qDescription = "";
        this.qComplexite = 0;
        this.qImportance=0;
        this.qApprehension = 0;
        this.qDuree = 0;
        this.qDateDebut = new Date();
        this.qDateFin = new Date();
    }

    public int getqId() {
        return qId;
    }

    public String getqNom() {
        return qNom;
    }

    public String getqDescription() {
        return qDescription;
    }

    public int getqComplexite() {
        return qComplexite;
    }

    public int getqApprehension() {
        return qApprehension;
    }

    public int getqDuree() {
        return qDuree;
    }

    public int getqImportance() {
        return qImportance;
    }

    public Date getqDateDebut() {
        return qDateDebut;
    }
    public String formatqDateDebut() {
       return new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(qDateDebut);
    }
    public String formatqDateFin() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(qDateFin);
    }

    public int getqCId() {
        return qCId;
    }

    public Date getqDateFin() {
        return qDateFin;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public void setqNom(String qNom) {
        this.qNom = qNom;
    }

    public void setqDescription(String qDescription) {
        this.qDescription = qDescription;
    }

    public void setqComplexite(int qComplexite) {
        this.qComplexite = qComplexite;
    }

    public void setqImportance(int qImportance) {
        this.qImportance = qImportance;
    }

    public void setqApprehension(int qApprehension) {
        this.qApprehension = qApprehension;
    }

    public void setqDuree(int qDuree) {
        this.qDuree = qDuree;
    }

    public void setqDateDebut(Date qDateDebut) {
        this.qDateDebut = qDateDebut;
    }

    public void setqCId(int qCId) {
        this.qCId = qCId;
    }

    public void setqDateFin(Date qDateFin) {
        this.qDateFin = qDateFin;
    }

    public int calculXp() { return 10;
    }

    public int calculPiece() { return 5;
    }
}
