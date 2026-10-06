
package prog2.model;

import java.io.Serializable;
import java.util.Iterator;
import prog2.vista.ExcepcioEstacio;

/**
 *
 * @author claudia
 * 
 * Classe abstracta que és la superclasse de totes les classes que corresponen a accessos. 
 * Conté els atributs comuns a tots els accessos.
 * 
 */
public abstract class Acces implements InAcces, Serializable {
    
    /**
     * Nom de l'accés
     */
    private String nom_; 
    
    /**
     * Estat d'obertura de l'accés
     */
    private boolean estat_;  
    
    /**
     * Llista de vies a les quals permet l'accés
     */
    private LlistaVies llistaVies_; 

    
    /**
     * Constructor amb paràmetres. Estableix la il·luminació en funció de l'estat rebut i inicialitza la llista de vies com un ArrayList buit
     * @param nom String
     * @param estat boolean
     */
    public Acces(String nom, boolean estat) {
        this.nom_ = nom;
        this.estat_ = estat;
        this.llistaVies_ = new LlistaVies();
    }

    
    /**
     * Retorna el nom de l'accés
     * @return String
     */
    public String getNom() {
        return nom_;
    }

    /**
     * Estableix el nom de l'accés
     * @param nom String
     */
    public void setNom(String nom) {
        this.nom_ = nom;
    }

    
    /**
     * Retorna l'estat de l'accés
     * @return boolean
     */
    public boolean isEstat() {
        return estat_;
    }

    /**
     * Estableix l'estat de l'accés
     * @param estat boolean
     */
    public void setEstat(boolean estat) {
        this.estat_ = estat;
    }

    
    /** 
     * Retorna la llista de vies a les quals permet l'accés 
     * @return Objecte de tipus LlistaVies
     */
    public LlistaVies getLlistaVies() {
        return llistaVies_;
    }

    /**
     * Estableix la llista de vies a les quals permet l'accés 
     * @param llistaVies Objecte de tipus LlistaVies
     */
    public void setLlistaVies(LlistaVies llistaVies) {
        this.llistaVies_ = llistaVies;
    }
    
    
    /**
     * Afegeix una via rebuda com a paràmetre a la llista de vies de l'accés. Invoca el mètode afegirVia de la classe LlistaVies.
     * @param via Objecte de tipus Via
     */
    @Override
    public void afegirVia(Via via) throws ExcepcioEstacio {
        llistaVies_.afegirVia(via);
    }
    
    
    /**
     * Canvia l'estat de l'accés a tancat 
     */
    @Override
    public void tancarAcces() {
        this.estat_ = false;
    }
    
    
    /**
     * Canvia l'estat de l'accés a obert 
     */
    @Override
    public void obrirAcces() {
        this.estat_ = true;
    }
    
    
    /**
     * Retorna si l'accés permet accessibilitat o no. 
     * @return boolean
     */
    @Override
    public abstract boolean isAccessibilitat();
    
    
    /**
     * Retorna una cadena de text amb la informació de l'accés
     * @return String
     */
    @Override
    public String toString() {
        String info;
        
        info = nom_ + ": estat = " + (estat_ ? "obert" : "tancat");
        info += ", accessibilitat = " + (isAccessibilitat() ? "sí" : "no");
        info += ", vies amb accés = ";
                
        Iterator<Via> it = llistaVies_.getLlistaVies().iterator();
        while (it.hasNext()) {
            Via via = it.next();
            info += via.getNom() + ", ";
        }     
                
        return info;
    }
    
}
