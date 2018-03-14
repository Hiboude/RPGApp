package hiboude.rpglife;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Quete {

    private int qId;
    private String qNom;
    private String qDescription;
    private float qComplexite;
    private float qApprehension;
    private int qDuree;
    private HashMap<String,Integer> qRepetitions ;
    private Date qDateDebut;
    private Date qDateFin;


    public Quete(int qId ,String qNom, String qDescription, float qComplexite, float qApprehension, int qDuree, HashMap<String, Integer> qRepetitions, Date qDateDebut) {
        this.qId = qId;
        this.qNom = qNom;
        this.qDescription = qDescription;
        this.qComplexite = qComplexite;
        this.qApprehension = qApprehension;
        this.qDuree = qDuree;
        this.qRepetitions = qRepetitions;
        this.qDateDebut = qDateDebut;
        Calendar cal = Calendar.getInstance();
        cal.setTime(qDateDebut);
        cal.add(Calendar.DATE, qDuree);
        qDateFin =  cal.getTime();

    }
}
