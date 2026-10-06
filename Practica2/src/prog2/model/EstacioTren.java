
package prog2.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import prog2.vista.ExcepcioEstacio;

/**
 *
 * @author paula
 *
 *
 * La classe EstacioTren defineix la forma d'una estació de tren.
 * Conté la llista de vies, la dels accessos a les vies, i la de les incidències de l'estació.
 * 
 */
public class EstacioTren implements Serializable {
    
    /**
     * Nom de l'estació
     */
    private String nom_; 
    
    /**
     * Llista de les vies de l'estació
     */
    private LlistaVies llistaVies_; 
    
    /**
     * Llista dels accessos de l'estació
     */
    private LlistaAccessos llistaAccessos_;
    
    /**
     * Llista de les incidències de l'estació
     */
    private LlistaIncidencies llistaIncidencies_;
    
    
    /**
     * Constructor amb paràmetres. Inicialitza les llistes de vies, accessos i incidències.
     * @param nom String
     */
    public EstacioTren(String nom) {
        this.nom_ = nom;
        llistaVies_ = new LlistaVies();
        llistaAccessos_ = new LlistaAccessos();
        llistaIncidencies_ = new LlistaIncidencies();
    }

    
    /**
     * Retorna el nom de l'estació
     * @return String
     */
    public String getNom() {
        return nom_;
    }

    /**
     * Estableix el nom de l'estació
     * @param nom String
     */
    public void setNom(String nom) {
        this.nom_ = nom;
    }

    
    /**
     * Retorna la llista de vies de l'estació
     * @return Objecte de tipus LlistaVies
     */
    public LlistaVies getLlistaVies() {
        return llistaVies_;
    }

    /**
     * Estableix la llista de vies de l'estació
     * @param llistaVies Objecte de tipus LlistaVies
     */
    public void setLlistaVies(LlistaVies llistaVies) {
        this.llistaVies_ = llistaVies;
    }

    
    /**
     * Retorna la llista d'accessos de l'estació
     * @return Objecte de tipus LlistaAccessos
     */
    public LlistaAccessos getLlistaAccessos() {
        return llistaAccessos_;
    }

    /**
     * Estableix la llista d'accessos de l'estació
     * @param llistaAccessos Objecte de tipus LlistaAccessos
     */
    public void setLlistaAccessos(LlistaAccessos llistaAccessos) {
        this.llistaAccessos_ = llistaAccessos;
    }

    
    /**
     * Retorna la llista d'incidències de l'estació
     * @return Objecte de tipus LlistaIncidencies
     */
    public LlistaIncidencies getLlistaIncidencies() {
        return llistaIncidencies_;
    }

    /**
     * Estableix la llista d'incidències de l'estació
     * @param llistaIncidencies Objecte de tipus LlistaIncidencies
     */
    public void setLlistaIncidencies(LlistaIncidencies llistaIncidencies) {
        this.llistaIncidencies_ = llistaIncidencies;
    }
    
    
    /**
     * Recupera la via amb el nom donat, invoca el mètode per afegirIncidencia de LlistaIncidencies, i actualitza l’estat de tots els accessos.
     * @param num int
     * @param tipus String
     * @param nomVia String
     * @param data String
     * @throws ExcepcioEstacio Si la incidència ja existeix.
     */
    public void afegirIncidencia(int num, String tipus, String nomVia, String data) throws ExcepcioEstacio {
        Via via = llistaVies_.getVia(nomVia);
        llistaIncidencies_.afegirIncidencia(num, tipus, via, data);
        llistaAccessos_.actualitzaEstatAccessos();
    }
    
    
    /** 
     * Recupera la incidènica amb el número donat, invoca el mètode per eliminarIncidencia de LlistaIncidencies, i actualitza l’estat de tots els accessos.
     * @param num int
     * @throws ExcepcioEstacio Si la incidència no existeix.
     */
    public void eliminarIncidencia(int num) throws ExcepcioEstacio {
        Incidencia incidencia = llistaIncidencies_.getIncidencia(num);
        llistaIncidencies_.eliminarIncidencia(incidencia);
        llistaAccessos_.actualitzaEstatAccessos();
    }
    
    
    /** 
     * Inicialitza les dades de l'estació de tren Green.
     * @throws prog2.vista.ExcepcioEstacio
     */
    public void inicialitzaDadesEstacioTren() throws ExcepcioEstacio {
    
        llistaAccessos_.buidar();
        
        float desnivell = 9;
        
        float pes = 200;
        Acces Acc1 = new Ascensor(pes, desnivell, "A1", true);      
        llistaAccessos_.afegirAcces(Acc1);
        
        
        String marca = "Schindler";
       
        Acces  Acc2 = new EscalaMecanica(marca, desnivell, "A2", true);     
          
        llistaAccessos_.afegirAcces(Acc2);
        
        float longitud = 30;
        Acces Acc3 = new Passadis(longitud, "A3", true); 
        llistaAccessos_.afegirAcces(Acc3);
        
        
        float vel = 0.3f;  
        Acces  Acc4 = new CintaTransportadora(vel, longitud, "A4", true);     
        llistaAccessos_.afegirAcces(Acc4);
        
        desnivell = 7;
        pes = 300;
        Acces Acc5 = new Ascensor(pes, desnivell, "A5", true);    
        llistaAccessos_.afegirAcces(Acc5);
        
        Acces Acc6 = new Escala(desnivell, "A6", true);    
        llistaAccessos_.afegirAcces(Acc6);
        
        desnivell = 8.5f;
        pes = 250;
        Acces  Acc7 = new Ascensor(pes, desnivell, "A7", true);      
        llistaAccessos_.afegirAcces(Acc7);
        
        Acces Acc8 = new Escala(desnivell, "A8", true);       
        llistaAccessos_.afegirAcces(Acc8);
        
        longitud = 25;
        Acces Acc9 = new Passadis(longitud, "A9", true);   
        llistaAccessos_.afegirAcces(Acc9);
        
        vel = 0.4f; 
        Acces Acc10 = new CintaTransportadora(vel, longitud, "A10", true);       
        llistaAccessos_.afegirAcces(Acc10);
        
        desnivell = 8;
        marca = "Otis";
        Acces Acc11 = new EscalaMecanica(marca, desnivell, "A11", true);   
        llistaAccessos_.afegirAcces(Acc11);
        
        longitud = 20;
        Acces Acc12 = new Passadis(longitud, "A12", true); 
        llistaAccessos_.afegirAcces(Acc12);
        
        desnivell = 20;
        Acces Acc13 = new Escala(desnivell, "A13", true);       
        llistaAccessos_.afegirAcces(Acc13);
        
        pes = 350;
        Acces Acc14 = new Ascensor(pes, desnivell, "A14", true);       
        llistaAccessos_.afegirAcces(Acc14);
        
        /* Vies */
        llistaVies_.buidar();
        Via V1  = new Via("V1", "Iberica", 2, true, "100%"); llistaVies_.afegirVia(V1);
        Via V2  = new Via("V2", "Iberica", 2, true, "100%"); llistaVies_.afegirVia(V2);
        Via V3  = new Via("V3", "Internacional", 2, true, "100%"); llistaVies_.afegirVia(V3);
        Via V4  = new Via("V4", "Iberica", 1, true, "100%"); llistaVies_.afegirVia(V4);
        Via V5  = new Via("V5", "Iberica", 1, true, "100%"); llistaVies_.afegirVia(V5);
        Via V6  = new Via("V6", "Estreta", 1, true, "100%"); llistaVies_.afegirVia(V6);
        Via V7  = new Via("V7", "Estreta", 1, true, "100%"); llistaVies_.afegirVia(V7);
         
        /* Accés */
        Acc1.afegirVia(V1);
        Acc2.afegirVia(V1); 
        Acc3.afegirVia(V2);
        Acc4.afegirVia(V2);
        Acc5.afegirVia(V3);
        Acc6.afegirVia(V3);
        Acc7.afegirVia(V4); Acc7.afegirVia(V6);
        Acc8.afegirVia(V4); Acc8.afegirVia(V6);
        Acc9.afegirVia(V5); Acc9.afegirVia(V7);
        Acc10.afegirVia(V5); Acc10.afegirVia(V7);
        Acc11.afegirVia(V6);
        Acc12.afegirVia(V6);
        Acc13.afegirVia(V7);
        Acc14.afegirVia(V7);
        
    }
    
    
    /**
     * Guarda les dades de l'estació en un fitxer al disc.
     * @param camiDesti String
     * @throws ExcepcioEstacio 
     */
    public void guardar(String camiDesti) throws ExcepcioEstacio {
        // Obtenim la ruta cap al fitxer
        File file = new File(camiDesti);
        
        // Declarem un Stream de sortida cap al fitxer 
        FileOutputStream fout = null;
        
        try {
            // Obrim l'Stream de sortida cap al fitxer 
            fout = new FileOutputStream(file);

            // Creem un ObjectOutputStream 
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            // Escrivim l'estació al fitxer
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            throw new ExcepcioEstacio(e.getMessage()); 
        } catch (IOException e) {
            throw new ExcepcioEstacio(e.getMessage()); 
        } finally {
            try {
                // Tanquem l’Stream de sortida cap al fitxer 
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                throw new ExcepcioEstacio(e.getMessage()); 
            }
        } 
    }
    
    
    /**
     * Carrega les dades d'una estació d'un fitxer del disc.
     * @param camiOrigen String
     * @return
     * @throws ExcepcioEstacio 
     */
    public static EstacioTren carregar(String camiOrigen) throws ExcepcioEstacio {
        // Creem una estació de tren buida
        EstacioTren estacioTren = null;
        
        // Obtenim la ruta cap al fitxer
        File fitxer = new File(camiOrigen);
        
        // Declarem un Stream d'entrada des del fitxer 
        FileInputStream fin = null;
        
        try {
            // Obrim l'Stream d’entrada des del fitxer 
            fin = new FileInputStream(fitxer);
            
            // Creem un ObjectInputStream
            ObjectInputStream ois = new ObjectInputStream(fin);
            
            // Llegim l'estació del fitxer
            estacioTren = (EstacioTren)ois.readObject();
        } catch (FileNotFoundException e) {
            throw new ExcepcioEstacio(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ExcepcioEstacio(e.getMessage()); 
        } catch (IOException e) {
            throw new ExcepcioEstacio(e.getMessage()); 
        } finally {
            try {
                // Tanquem l’Stream d’entrada des del fitxer 
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                throw new ExcepcioEstacio(e.getMessage()); 
            }
        }
        
        return estacioTren;
    }
    
    
    /**
     * Retorna un String amb la informació de totes les vies amb l'estat rebut per paràmetre.
     * Invoca al mètode llistarVies de la classe LlistaVies
     * @param estat String
     * @return String
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode llança una excepció en cas que no hi hagi vies en l'estat passat com a parametre.
     */
    public String llistarVies(String estat) throws ExcepcioEstacio {
        return llistaVies_.llistarVies(estat);
    }
    
    
    /**
     * Retorna un String amb la informació de tots els accessos amb l'estat rebut per paràmetre. 
     * Invoca al mètode llistarAccessos de la classe LlistaAccessos
     * @param estat boolean
     * @return String
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode llança una excepció en cas que no hi hagi accessos en l'estat passat com a parametre.
     * 
     */
    public String llistarAccessos(boolean estat) throws ExcepcioEstacio {
        return llistaAccessos_.llistarAccessos(estat);
    }
    
    
    /**
     * Retorna un String amb la informació de totes les incidències. 
     * Invoca al mètode llistarIncidencies de la classe LlistaIncidencies.
     * @return String
     * @throws ExcepcioEstacio 
     */
    public String llistarIncidencies() throws ExcepcioEstacio {
        return llistaIncidencies_.llistarIncidencies();
    }
    
    
    /**
     * Retorna el número d'accessos amb accessibilitat. Invoca al mètode de mateix nom de la classe LlistaAccessos.
     * @return int
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    public int calculaAccessosAccessibles() throws ExcepcioEstacio {
        return llistaAccessos_.calculaAccessosAccessibles();
    }
     
    
    /**
     * Retorna la longitud total dels accessos a nivell. Invoca al mètode de mateix nom de la classe LlistaAccessos.
     * @return float 
     * @throws prog2.vista.ExcepcioEstacio Aquest mètode podria llançar una excepció si fos necessari.
     */
    public float calculaLongitudAccessosNivell() throws ExcepcioEstacio {
        return llistaAccessos_.calculaLongitudAccessosNivell();
    }
}
