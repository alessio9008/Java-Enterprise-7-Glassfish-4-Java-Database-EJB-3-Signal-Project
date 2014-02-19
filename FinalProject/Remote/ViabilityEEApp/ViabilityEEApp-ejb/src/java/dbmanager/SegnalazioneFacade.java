/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmanager;

import entity.Segnalazione;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alessio
 */
@Stateless
public class SegnalazioneFacade extends AbstractFacade<Segnalazione> implements SegnalazioneFacadeLocal {
    @PersistenceContext(unitName = "ViabilityEEApp-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegnalazioneFacade() {
        super(Segnalazione.class);
    }
    
}
