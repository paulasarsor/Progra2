
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import prog2.vista.ExcepcioEstacio;

/**
 *
 * @author claudia
 * 
 * Classe que permet gestionar una llista d'accessos mitjançant un ArrayList d'objectes de tipus Acces.
 * 
 */
public class LlistaAccessos implements InLlistaAccessos, Serializable {
    
    /**
     * Llista dels accessos
     */
    private ArrayList<Acces> llistaAccessos_; 
    
    
    /**
     * Constructorper defecte. Inicialitza la llista d'accessos com un ArrayList buit.
     */
    public LlistaAccessos() {
        this.llistaAccessos_ = new ArrayList<>();
    }
    
    
    /**
     * Retorna la llista d'accessos
     * @return ArrayList d'objectes de tipus Acces
     */
    public ArrayList<Acces> getLlistaAccessos() {
        return llistaAccessos_;
    }

    /**
     * Estableix la llista d'accessos
     * @param llistaAccessos Objecte de tipus LlistaAccessos
     */
    public void setLlistaAccessos(ArrayList<Acces> llistaAccessos) {
        this.llistaAccessos_ = llistaAccessos;
    }
    
    
    /**
     * Afegeix un accés rebut per paràmetre a la llista d'accessos.
     * @param acc Objecte de tipus Acces
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    @Override
    public void afegirAcces(Acces acc) throws ExcepcioEstacio {
        // Comprovem que l'accés no està repetit. Usem el mètode contains de la classe ArrayList
        if (llistaAccessos_.contains(acc)) {
            throw new ExcepcioEstacio("Aquest accés ja es troba a la llista.");
        }
        
        // Podem afegir l'accés
        llistaAccessos_.add(acc);
    }
    
    
    /**
     * Buida la llista d'accessos
     */
    @Override
    public void buidar() {
        // Usem el mètode clear de la classe ArrayList
        llistaAccessos_.clear(); 
    }
     
    
    /**
     * Itera sobre la llista d'accessos i retorna un String amb la informació de tots els accessos amb l'estat rebut per paràmetre.
     * En cas que no hi hagi accessos en l'estat passat com a paràmetre llança una excepció. 
     * @param estat boolean
     * @return String
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode llança una excepció en cas que no hi hagi accessos en l'estat passat com a parametre.
     * 
     */
    @Override
    public String llistarAccessos(boolean estat) throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaAccessos_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap accés.");
        }
        
        // Iterem sobre la llista de vies i afegim la informació de la via a un String
        String llista = "";
        Iterator<Acces> it = llistaAccessos_.iterator();
        while(it.hasNext()){
            Acces acc = it.next();
            if (acc.isEstat() == estat) {
                llista += acc.toString() + "\n\n";
            }
        }
        
        // Mirem si s'ha trobat alguna via en l'estat proporcionat
        if (llista.equals("")) {
            String message = "No hi ha cap accés ";
            if (estat) message += "obert.";
            else message += "tancat.";
            
            throw new ExcepcioEstacio(message);
        }
        
        return llista;
    }
     
    
    /**
     * Recorre tota la llista d'accessos i els tanca. Només decidirà obrir cadascun d'ells si permet l'accés a alguna via oberta.
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    @Override
    public void actualitzaEstatAccessos() throws ExcepcioEstacio {
        // Iterem sobre la llista d'accessos i, si permeten accés a alguna via oberta, l'obrim. En cas contrari, el tanquem.
        Iterator<Acces> it = llistaAccessos_.iterator();
        while(it.hasNext()){
            Acces acc = it.next();
            
            if (acc.getLlistaVies().containsViesObertes()) {
                acc.obrirAcces();
            } else {
                acc.tancarAcces();
            }
        }
    }
     
    
    /**
     * Itera sobre la llista d'accessos i retorna el número d'accessos amb accessibilitat.
     * @return int
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    @Override
    public int calculaAccessosAccessibles() throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaAccessos_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap accés.");
        }
        
        // Iterem sobre la llista d'accessos i comptem els que són accessibles
        int compt = 0;
        Iterator<Acces> it = llistaAccessos_.iterator();
        while(it.hasNext()){
            Acces acc = it.next();
            if (acc.isAccessibilitat()) {
                compt++;
            }
        }
        
        return compt;
    }
     
    
    /**
     * Itera sobre la llista d'accessos, i per els accessos a nivell suma la longitud i la retorna. 
     * @return float amb la longitud dels accessos a nivell
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    @Override
    public float calculaLongitudAccessosNivell() throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaAccessos_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap accés.");
        }
        
        // Iterem sobre la llista d'accessos i sumem les longituds dels accessos a nivell
        float longitud = 0;
        Iterator<Acces> it = llistaAccessos_.iterator();
        while(it.hasNext()){
            Acces acc = it.next();
            if (acc instanceof AccesNivell) {
                longitud += ((AccesNivell) acc).getLongitud();
            }
        }
        
        return longitud;
    }
     
}

