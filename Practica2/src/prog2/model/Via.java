
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author claudia
 * 
 * Classe que representa una via amb les seves característiques.
 * 
 */
public class Via implements Serializable {
    
    /**
     * Nom de la via
     */
    private String nom_;
    
    /**
     * Amplada de la via
     */
    private String amplada_; 
    
    /**
     * Número de túnels de la via
     */
    private int num_;
    
    /**
     * Estat d'obertura de la via
     */
    private boolean estat_;
    
    /**
     * Estat de il·luminació de la via
     */
    private String iluminacio_; 

    
    /**
     * Constructor amb paràmetres.
     * @param nom String
     * @param amplada String
     * @param num int
     * @param estat boolean
     * @param iluminacio String
     */
    public Via(String nom, String amplada, int num, boolean estat, String iluminacio) {
        this.nom_ = nom;
        this.amplada_ = amplada;
        this.num_ = num;
        this.estat_ = estat;
        this.iluminacio_ = iluminacio;
    }

    
    /**
     * Retorna el nom de la via
     * @return String
     */
    public String getNom() {
        return nom_;
    }

    /**
     * Estableix el nom de la via
     * @param nom String
     */
    public void setNom(String nom) {
        this.nom_ = nom;
    }

    
    /**
     * Retorna l'amplada de la via
     * @return String
     */
    public String getAmplada() {
        return amplada_;
    }

    /**
     * Estableix l'amplada de la via
     * @param amplada String
     */
    public void setAmplada(String amplada) {
        this.amplada_ = amplada;
    }

    
    /**
     * Retorna el número de túnels de la via
     * @return int
     */
    public int getNum() {
        return num_;
    }

    /**
     * Estableix el número de túnels de la via
     * @param num int
     */
    public void setNum(int num) {
        this.num_ = num;
    }

    
    /**
     * Retorna l'estat de la via
     * @return boolean
     */
    public boolean isEstat() {
        return estat_;
    }

    /**
     * Estableix l'estat de la via
     * @param estat boolean
     */
    public void setEstat(boolean estat) {
        this.estat_ = estat;
    }

    
    /**
     * Retorna l'estat d'il·luminació de la via
     * @return boolean
     */
    public String getIluminacio() {
        return iluminacio_;
    }

    /**
     * Estableix l'estat d'il·luminació de la via
     * @param iluminacio String
     */
    public void setIluminacio(String iluminacio) {
        this.iluminacio_ = iluminacio;
    }
    
    
    /**
     * Modifica l'estat de la via a Tancada i la il·luminació depenent de la incidència rebuda com a paràmetre
     * @param in Objecte de tipus Incidencia
     */
    public void tancarVia(Incidencia in) {
        estat_ = false;
        iluminacio_ = in.getIluminacioVia();
    }
    
    
    /**
     * Modifica l'estat de la via a Oberta i la il·luminació al 100%.
     */
    public void obrirVia() {
        estat_ = true;
        iluminacio_ = "100%";
    }

    
    /**
     * Retorna una cadena de text amb la informació de la via
     * @return String
     */
    @Override
    public String toString() {
        return "Via " + nom_ + ": amplada = " + amplada_ + ", número de túnels = " + num_ 
                + ", estat = " + (estat_ ? "oberta" : "tancada") + ", iluminació = " + iluminacio_; 
    }
    
}
