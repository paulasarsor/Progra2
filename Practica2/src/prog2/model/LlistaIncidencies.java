
package prog2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import prog2.vista.ExcepcioEstacio;

/**
 *
 * @author paula
 * 
 * Classe que permet gestionar una llista d'incidències mitjançant un ArrayList d'objectes de tipus Incidencia.
 * 
 */
public class LlistaIncidencies implements InLlistaIncidencies, Serializable {
    
    /**
     * ArrayList d'objectes de tipus Incidencia
     */
    private ArrayList<Incidencia> llistaIncidencies_; 
    
    
    /**
     * Constructor per defecte. Inicialitza la llista d'incidències com un ArrayList buit.
     */
    public LlistaIncidencies() {
        this.llistaIncidencies_ = new ArrayList<>();
    }
    
    
    /**
     * Retorna la llista d'incidències
     * @return ArrayList d'objectes de tipus Incidencia
     */
    public ArrayList<Incidencia> getLlistaIncidencies() {
        return llistaIncidencies_;
    }
    
    /**
     * Estableix la llista d'incidències
     * @param llistaIncidencies Objecte de tipus LlistaIncidencies
     */
    public void setLlistaIncidencies(ArrayList<Incidencia> llistaIncidencies) {
        this.llistaIncidencies_ = llistaIncidencies;
    }
    
    
    /**
     * Aquest mètode crea una incidència amb la informació passada com a paràmetres 
     * (número d'identificador, tipus, la via on s'ha produït i la data) i l'afegeix a la llista.
     * A més, s'ha de comprovar que aquesta via no té ja una incidència si ja té una incidència s'ha de llançar una excepció.
     * Una vegada creada la incidència s'ha de tancar la via corresponent.
     * @param num Número d'identificació de la incidència.
     * @param tipus Aquest String permet crear el enum TipusIncidencia
     * @param via Via on es produiex la incidència
     * @param data Data quan es produeix la incidènica.
     * @throws ExcepcioEstacio Per comprovar i avisar si la via ja té una incidènica o si el tipus d’incidència que es vol afegir no existeix.
     */
    @Override
    public void afegirIncidencia(int num, String tipus, Via via, String data) throws ExcepcioEstacio {
        // Comprovem que tipus correspon a un dels tres tipus de incidència
        if (!tipus.equals("Reparacio") && !tipus.equals("Objecte") && !tipus.equals("Tancament")){
            throw new ExcepcioEstacio("El tipus d’incidència que es vol afegir no existeix.");
        }
        
        // Convertim tipus en un objecte TipusIncidencia
        Incidencia.TipusIncidencia tipusIncidencia = Incidencia.TipusIncidencia.valueOf(tipus);
        
        // Comprovem que la via no té una incidència. Només cal comprovar que està oberta.
        if (!via.isEstat()) {
            throw new ExcepcioEstacio("La via ja té una incidència.");
        }

        // Comprovem que no existeix una incidència amb el mateix número
        Iterator<Incidencia> it = llistaIncidencies_.iterator();
        while (it.hasNext()) {
            Incidencia in = it.next();
            if (in.getNum() == num) {
                throw new ExcepcioEstacio("L'estació ja conté una incidència amb aquest número.");
            } 
        }
        
        // Creem la nova incidència i l'afegim
        Incidencia in = new Incidencia(num, tipusIncidencia, via, data);
        llistaIncidencies_.add(in);
        
        // Tancament de la via
        via.tancarVia(in);
    }
    
    
    /**
    * Aquest mètode elimina una incidència de la llista i actualitza l'estat de la via mitjançant el mètode obrirVia de la classe Via.
    * @param in Objecte de tipus Incidència
    * @throws ExcepcioEstacio 
     */
    @Override
    public void eliminarIncidencia(Incidencia in) throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaIncidencies_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap incidència.");
        }
        
        // Comprovem que la incidència sigui a la llista. Usem el mètode contains de la classe ArrayList.
        if (!llistaIncidencies_.contains(in)) {
           throw new ExcepcioEstacio("La incidencia no és a la llista.");
        }
        
        // Eliminem la incidència i obrim la via.
        llistaIncidencies_.remove(in);
        in.getVia().obrirVia();
    }
    
    
    /**
     * Itera sobre la llista d'incidències i retorna un String amb la informació de totes les incidències. 
     * En cas que no hi hagi cap incidència llança una excepció. 
     * @return String
     * @throws ExcepcioEstacio 
     */
    @Override
    public String llistarIncidencies() throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaIncidencies_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap incidència.");
        }
        
        //Iterem sobre la llista d'incidències per recopilar la informació
        String missatge = "";
        Iterator<Incidencia> it = llistaIncidencies_.iterator();
        while (it.hasNext()) {
            Incidencia in = it.next();
            missatge += in.toString() + "\n"; 
        }
        
        return missatge;
    }
    
    
    /**
     * Busca la incidència amb el número rebut per paràmetre i la retorna. 
     * En cas que no existeixi llança una excepció.
     * @param num Número d'identificació de la incidència.
     * @return Objecte de tipus Incidència
     * @throws ExcepcioEstacio Aquest mètode llança una excepció si no existeix cap incidència amb el número passat per paràmetre.
     */
    @Override
    public Incidencia getIncidencia(int num) throws ExcepcioEstacio {
        // Comprovem que la llista no és buida 
        if (llistaIncidencies_.isEmpty()) {
            throw new ExcepcioEstacio("No hi ha cap incidència.");
        }
        
        //Iterem sobre la llista d'incidències buscant una amb número num
        Iterator<Incidencia> it = llistaIncidencies_.iterator();
        while (it.hasNext()){
            Incidencia in = it.next();
            if (in.getNum() == num) { 
                return in;
            }
        }
        
        // No s'ha trobat la incidència
        throw new ExcepcioEstacio("No existeix cap incidència amb aquest número."); 
    }
    
}
