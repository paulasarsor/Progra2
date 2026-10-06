
package prog2.model;

import java.io.Serializable;

/**
 *
 * @author lauraigual
 * 
 * Classe que defineix la forma d'una incidència.
 */
public class Incidencia implements Serializable {
    
    /**
     * Declaració del tipus TipusIncidencia
     */
    public static enum TipusIncidencia {
        Reparacio,
        Objecte,
        Tancament,
    };
    
    /**
     * Número de la incidència
     */
    private int num_;
    
    /**
     * Tipus de la incidència
     */
    private TipusIncidencia tipus_; 
    
    /**
     * Via on s'ha produït la incidència
     */
    private Via via_;
    
    /**
     * Data quan succeeix la incidència
     */
    private String data_; 

    
    /**
     * Constructor amb paràmetres
     * @param num int
     * @param tipus Objecte de tipus TipusIncidencia
     * @param via Objecte de tipus Via
     * @param data String
     */
    public Incidencia(int num, TipusIncidencia tipus, Via via, String data) {
        this.num_ = num;
        this.tipus_ = tipus;
        this.via_ = via;
        this.data_ = data;
    }

    
    /**
     * Retorna el número de la incidència
     * @return int
     */
    public int getNum() {
        return num_;
    }
    
    /**
     * Estableix el número de la incidència
     * @param num 
     */
    public void setNum(int num) {
        this.num_ = num;
    }
    
    
    /**
     * Retorna el tipus de la incidència
     * @return Objecte de tipus TipusIncidencia
     */
    public TipusIncidencia getTipus() {
        return tipus_;
    }

    /**
     * Estableix el tipus de la incidència
     * @param tipus 
     */
    public void setTipus(TipusIncidencia tipus) {
        this.tipus_ = tipus;
    }
    
    
    /**
     * Retorna la via on s'ha produït la incidència
     * @return Objecte de tipus Via
     */
    public Via getVia() {
        return via_;
    }

    /**
     * Estableix la via on s'ha produït la incidència
     * @param via Objecte de tipus Via
     */
    public void setVia(Via via) {
        this.via_ = via;
    }
    
    
    /**
     * Retorna la data quan succeeix la incidència
     * @return String
     */
    public String getData() {
        return data_;
    }

    /**
     * Estableix la data quan succeeix la incidència
     * @param data String
     */
    public void setData(String data) {
        this.data_ = data;
    }
    
    
    /**
     * Retorna la informació del percentatge de la il·luminació a la que s'ha de deixar la via segons el tipus de incidència.
     * @return String
     */
    public String getIluminacioVia(){
        String iluminacio = "";
        
        switch(tipus_) {
            case Reparacio:
                iluminacio = "100%";
                break;
                
            case Objecte:
                iluminacio = "50%";
                break;  
                
            case Tancament:
                iluminacio = "0%";
                break;   
        }
        
        return iluminacio;
    }

    
    /**
     * Retorna una cadena de text amb la informació de la incidència
     * @return String
     */
    @Override
    public String toString() {
        return "Incidencia " + num_ + ": tipus = " + tipus_ + ", via = " + via_.getNom() + ", data = " + data_;
    }

}
    
