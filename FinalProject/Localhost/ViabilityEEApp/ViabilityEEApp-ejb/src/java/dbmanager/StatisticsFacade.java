/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmanager;

import entity.Statistics;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alessio
 */
@Stateless
public class StatisticsFacade extends AbstractFacade<Statistics> implements StatisticsFacadeLocal {
    @PersistenceContext(unitName = "ViabilityEEApp-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatisticsFacade() {
        super(Statistics.class);
    }
    
}
