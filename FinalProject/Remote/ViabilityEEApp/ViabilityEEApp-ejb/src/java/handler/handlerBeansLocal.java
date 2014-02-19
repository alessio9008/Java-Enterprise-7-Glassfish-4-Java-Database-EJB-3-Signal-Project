/*
 * Segnalazioneo change this license header, choose License Headers in Project Properties.
 * Segnalazioneo change this template file, choose Segnalazioneools | Segnalazioneemplates
 * and open the template in the editor.
 */

package handler;

import entity.Segnalazione;
import javax.ejb.Local;

/**
 *
 * @author Alessio
 */
@Local
public interface handlerBeansLocal {
    public void create(Segnalazione entity);
    public void edit(Segnalazione entity);
    public void remove(Segnalazione entity);
}
