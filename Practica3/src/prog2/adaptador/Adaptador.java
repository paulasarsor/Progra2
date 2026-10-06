
package prog2.adaptador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import prog2.model.Bitacola;
import prog2.model.Dades;
import prog2.model.PaginaEstat;
import prog2.model.PaginaIncidencies;
import prog2.model.Reactor;
import prog2.model.SistemaRefrigeracio;
import prog2.vista.CentralUBException;

/**
 * Classe que funciona com a mediador entre el model i la vista.
 * 
 * @author paula
 */
public class Adaptador implements Serializable {
    
    public Dades dades; // Dades de la central

    /**
     * Constructor per defecte. Inicialitza les dades.
     */
    public Adaptador() {
        dades = new Dades();
    }
    
    
    /**
     * Retorna el grau d'inserció de les barres de control en percentatge.
     * @return float 
     */
    public float getInsercioBarres() {
        return dades.getInsercioBarres();
    } 
    
    
    /**
     * Estableix el grau d'inserció de les barres de control en percentatge. 
     * @param insercioBarres Percentatge d'inserció de les barres de control
     * @throws prog2.vista.CentralUBException 
     */
    public void setInsercioBarres(float insercioBarres) throws CentralUBException {
        dades.setInsercioBarres(insercioBarres);
    }
    
    
    /**
     * Activa el reactor de la central.
     * @throws prog2.vista.CentralUBException
     */
    public void activaReactor() throws CentralUBException {
        dades.activaReactor();
    }

    
    /**
     * Desactiva el reactor de la central.
     */
    public void desactivaReactor() {
        dades.desactivaReactor();
    }
    
    
    /**
     * Retorna un String indicant si el reactor està activat i la seva temperatura.
     * @return String 
     */
    public String mostraReactor() {
        Reactor reactor = dades.mostraReactor();
        
        return "El reactor està " + (reactor.isActivat() ? "activat" : "desactivat") 
                + " i la seva temperatura és de " + reactor.getTemperatura() + " graus.";
    }
    
    
    /**
     * Activa una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar
     * @throws prog2.vista.CentralUBException
     */
    public void activaBomba(int id) throws CentralUBException {
        // Comprovem que l'identificador és correcte
        if (id < 0 || id > 3) throw new CentralUBException("L'identificador proporcionat no és correcte.");
        
        dades.activaBomba(id);
    }
    
    
    /**
     * Desactiva una bomba refrigerant amb Id donat com a paràmetre.
     * @param id Identificador de la bomba que es vol activar
     */
    public void desactivaBomba(int id) {
        dades.desactivaBomba(id);
    }
    
    
    /**
     * Retorna un String amb la informació de l'estat del sistema de refrigeració.
     * @return String 
     */
    public String mostraSistemaRefrigeracio() {
        SistemaRefrigeracio sistemaRefrigeracio = dades.mostraSistemaRefrigeracio();
        
        return sistemaRefrigeracio.mostraEstatBombes();
    }
    
    
    /**
     * Retorna la pàgina d'estat corresponent al dia actual en forma de String.
     * @param demandaPotencia Demanda de potència del dia actual
     * @return String 
     */
    public String mostraEstatCentral(float demandaPotencia) {
        PaginaEstat pag = dades.mostraEstat(demandaPotencia);
        
        return pag.toString();
    }
    
    
    /**
     * Retorna la bitàcola sencera en forma de String.
     * @return String 
     */
    public String mostraBitacola() {
        Bitacola bitacola = dades.mostraBitacola();
        
        return bitacola.toString();
    }
    
    
    /**
     * Retorna totes les pàgines d'incidències en forma de String.
     * @return String 
     */
    public String mostraIncidencies() {
        List<PaginaIncidencies> llista = dades.mostraIncidencies();
        
        return llista.toString();
    }
    
    
    /**
     * Retorna la bitàcola al finalitzar el dia en forma de String.
     * @param demandaPotencia Demanda de potència del dia actual
     * @return String 
     */
    public String finalitzaDia(float demandaPotencia) {
        return dades.finalitzaDia(demandaPotencia).toString();
    }
    
    
    /**
     * Guarda les dades de l'aplicació en un fitxer amb el nom proporcionat.
     * @param camiDesti Nom del fitxer on guardem les dades
     * @throws prog2.vista.CentralUBException
     */
    public void guardaDades(String camiDesti) throws CentralUBException {
        // Obtenim la ruta cap al fitxer
        File file = new File(camiDesti);
        
        // Declarem un Stream de sortida cap al fitxer 
        FileOutputStream fout = null;
        
        try {
            // Obrim l'Stream de sortida cap al fitxer 
            fout = new FileOutputStream(file);

            // Creem un ObjectOutputStream 
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            // Escrivim les dades al fitxer
            oos.writeObject(dades);
        } catch (FileNotFoundException e) {
            throw new CentralUBException(e.getMessage()); 
        } catch (IOException e) {
            throw new CentralUBException(e.getMessage()); 
        } finally {
            try {
                // Tanquem l’Stream de sortida cap al fitxer 
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                throw new CentralUBException(e.getMessage()); 
            }
        } 
    }

    
    /**
     * Carrega les dades de l'aplicació des d'un fitxer amb el nom proporcionat.
     * @param camiOrigen Nom del fitxer des d'on carreguem les dades
     * @throws prog2.vista.CentralUBException 
     */
    public void carregaDades(String camiOrigen) throws CentralUBException {
        // Obtenim la ruta cap al fitxer
        File fitxer = new File(camiOrigen);
        
        // Declarem un Stream d'entrada des del fitxer 
        FileInputStream fin = null;
        
        try {
            // Obrim l'Stream d’entrada des del fitxer 
            fin = new FileInputStream(fitxer);
            
            // Creem un ObjectInputStream
            ObjectInputStream ois = new ObjectInputStream(fin);
            
            // Llegim les dades del fitxer
            dades = (Dades)ois.readObject();
        } catch (FileNotFoundException e) {
            throw new CentralUBException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new CentralUBException(e.getMessage()); 
        } catch (IOException e) {
            throw new CentralUBException(e.getMessage()); 
        } finally {
            try {
                // Tanquem l’Stream d’entrada des del fitxer 
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                throw new CentralUBException(e.getMessage()); 
            }
        }
    }
}
