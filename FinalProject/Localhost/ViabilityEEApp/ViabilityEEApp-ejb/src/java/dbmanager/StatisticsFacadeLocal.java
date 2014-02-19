/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbmanager;

import entity.Statistics;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alessio
 */
@Local
public interface StatisticsFacadeLocal {

    public void create(Statistics statistics);

    public void edit(Statistics statistics);

    public void remove(Statistics statistics);
    
    public boolean contains(Statistics entity);

    public Statistics find(Object id);

    public List<Statistics> findAll();

    public List<Statistics> findRange(int[] range);

    public int count();
    
}
