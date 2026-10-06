
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import prog2.vista.ExcepcioEstacio;

/**
 *
 * @author paula
 *
 *
 * Classe que permet gestionar una llista de vies mitjançant un ArrayList d'objectes de tipus Via.
 * 
 */
public class LlistaVies implements InLlistaVies, Serializable {
    
    /**
     * Llista de les vies
     */
    private ArrayList<Via> llistaVies_; 
    
    
    /**
     * Constructor per defecte. Inicialitza la llista de vies com un ArrayList buit.
     */
    public LlistaVies() {
        this.llistaVies_ = new ArrayList<>();
    }

    
    /**
     * Retorna la llista de vies
     * @return ArrayList d'objectes de tipus Via
     */
    public ArrayList<Via> getLlistaVies() {
        return llistaVies_;
    }

    /**
     * Estableix la llista de vies
     * @param llistaVies Objecte de tipus LlistaVies
     */
    public void setLlistaVies(ArrayList<Via> llistaVies) {
        this.llistaVies_ = llistaVies;
    }
    
    
    /**
     * Afegeix una via rebuda per paràmetre a la llista de vies.
     * @param via Objecte de tipus Via
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    @Override
    public void afegirVia(Via via) throws ExcepcioEstacio {
        // Comprovem que la via no està repetida
        if (llistaVies_.contains(via)) {
            throw new ExcepcioEstacio("La via ja es troba a la llista.");
        }
        
        // Podem afegir la via
        llistaVies_.add(via);
    }
    
    
    /**
     * Buida la llista de vies.
     */
    @Override
    public void buidar() {
        // Usem el mètode clear de la classe ArrayList
        llistaVies_.clear(); 
    }
    
    
    /**
     * Itera sobre la llista de vies i retorna un String amb la informació de totes les vies amb l'estat rebut per paràmetre.
     * En cas que no hi hagi vies en l'estat passat com a paràmetre llança una excepció. 
     * @param estat String
     * @return String
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode llança una excepció en cas que no hi hagi vies en l'estat passat com a parametre.
     */
    @Override
    public String llistarVies(String estat) throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaVies_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap via.");
        }
        
        String llista = "";
        switch(estat) {
            case "oberta":
            {
                // Iterem sobre la llista de vies i afegim la informació de les vies obertes
                Iterator<Via> it = llistaVies_.iterator();
                while(it.hasNext()){
                    Via via = it.next();
                    if (via.isEstat()) {
                        llista += via.toString() + "\n";
                    }
                }
                
                break;
            }
            
            case "tancada":
            {
                // Iterem sobre la llista de vies i afegim la informació de les vies tancades
                Iterator<Via> it = llistaVies_.iterator();
                while(it.hasNext()){
                    Via via = it.next();
                    if (!via.isEstat()) {
                        llista += via.toString() + "\n";
                    }
                }
                
                break;
            }
            
            case "totes":
            {
                // Iterem sobre la llista de vies i afegim la informació de totes les vies
                Iterator<Via> it = llistaVies_.iterator();
                while(it.hasNext()){
                    Via via = it.next();
                    llista += via.toString() + "\n";
                }
                
                break;
            }
        }
        
        // Mirem si s'ha trobat alguna via en l'estat proporcionat
        if (llista.equals("")) {
            throw new ExcepcioEstacio("No hi ha cap via " + estat + ".");
        }
        
        return llista;
    }
    
    
    /**
     * Mira si la llista de vies conté alguna via oberta.
     * @return boolean
     */
    @Override
    public boolean containsViesObertes() {
        // Iterem sobre la llista de vies i mirem si n'hi ha alguna d'oberta
        Iterator<Via> it = llistaVies_.iterator();
        while(it.hasNext()){
            Via via = it.next();
            if (via.isEstat()) { 
                return true;
            }
        }
        
        // No s'ha trobat cap via oberta
        return false;
    }
    
    
    /**
     * Mira si la llista de vies conté la via rebuda per paràmetre i retorna un booleà amb la informació.
     * @param via Objecte de tipus Via
     * @return boolean
     */
    @Override
    public boolean contains(Via via) {
        // Iterem sobre la llista de vies i busquem la via proporcionada. Usem el nom per comparar-les
        Iterator<Via> it = llistaVies_.iterator();
        while(it.hasNext()){
            Via viaAux = it.next();
            if (viaAux.getNom().equals(via.getNom())) {
                return true;
            }
        }
        
        // No s'ha trobat la via
        return false;
    }
    
    
    /**
     * Busca la via amb el nom rebut per paràmetre i la retorna. En cas que no existeixi llança una excepció.
     * @param nom String amb el nom de la Via
     * @return Objecte de tipus Via
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    @Override
    public Via getVia(String nom) throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaVies_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap via.");
        }
        
        // Iterem sobre la llista de vies i busquem la via amb el nom rebut
        Iterator<Via> it = llistaVies_.iterator();
        while(it.hasNext()){
            Via via = it.next();
            if (via.getNom().equals(nom)) {
                return via;
            }
        }
        
        // No s'ha trobat la via
        throw new ExcepcioEstacio("No s'ha trobat cap via amb aquest nom.");
    }

}

