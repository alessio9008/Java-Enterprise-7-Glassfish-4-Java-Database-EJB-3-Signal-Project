/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmanager;

import entity.Segnalazione;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alessio
 */
@Local
public interface SegnalazioneFacadeLocal {

    public void create(Segnalazione segnalazione);

    public void edit(Segnalazione segnalazione);

    public void remove(Segnalazione segnalazione);

    public boolean contains(Segnalazione entity);
    
    public Segnalazione find(Object id);

    public List<Segnalazione> findAll();

    public List<Segnalazione> findRange(int[] range);

    public int count();
    
}
