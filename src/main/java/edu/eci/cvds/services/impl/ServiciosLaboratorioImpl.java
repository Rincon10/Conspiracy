package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Equipo;
import edu.eci.cvds.entities.Laboratorio;
import edu.eci.cvds.entities.Novedad;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatis.dao.LaboratorioDAO;
import edu.eci.cvds.services.HistorialEquiposException;
import edu.eci.cvds.services.ServiciosEquipo;
import edu.eci.cvds.services.ServiciosLaboratorio;
import edu.eci.cvds.services.ServiciosNovedad;
import javax.inject.Inject;
import java.util.List;
/**
 * @author Ana Gabriela Silva
 * @author Juan Andrés Pico
 * @author Leonardo Galeano
 * @author Iván Camilo Rincón Saavedra
 * @version 11/12/2020
 */
public class ServiciosLaboratorioImpl implements ServiciosLaboratorio {
    @Inject
    private LaboratorioDAO laboratorioDAO;

    @Inject
    private ServiciosEquipo serviciosEquipo;

    @Inject
    private ServiciosNovedad serviciosNovedad;

    /*
    SELECT
     */
    @Override
    public List<Laboratorio> consultarLaboratorios() throws HistorialEquiposException {
        try{
            return laboratorioDAO.consultarLaboratorios();
        }
        catch (PersistenceException persistenceException){
            throw new HistorialEquiposException(persistenceException.getMessage(),persistenceException );
        }
    }

    @Override
    public Laboratorio consultarLaboratorio(int idLab) throws HistorialEquiposException{
        try{
            return laboratorioDAO.consultarLaboratorio(idLab);
        }
        catch (PersistenceException persistenceException){
            throw new HistorialEquiposException(persistenceException.getMessage(),persistenceException );
        }
    }

    /*
    INSERT
     */
    @Override
    public void registrarLaboratorio(int idUsuario,int idLab, String nombreLab, List<Equipo> aAsociar) throws HistorialEquiposException {
        try{
            laboratorioDAO.registrarLaboratorio(idLab, nombreLab);
            for (Equipo equipo: aAsociar){
                serviciosEquipo.asociarEquipo(idLab, equipo.getIdEquipo());
                serviciosNovedad.insertarNovedad(new Novedad("Asociación de equipo a laboratorio","El equipo con id "+idLab+" fue asociado al laboratorio "+nombreLab+" con id "+idLab,idUsuario,null,equipo.getIdEquipo()));
            }
        }
        catch (PersistenceException persistenceException){
            throw new HistorialEquiposException(persistenceException.getMessage(),persistenceException );
        }
    }

    @Override
    public void cambiarEstado(int idUsuario, Laboratorio laboratorioSeleccionado) throws HistorialEquiposException {
        try{
            laboratorioDAO.cambiarEstado(laboratorioSeleccionado);
        }
        catch (PersistenceException persistenceException){
            throw new HistorialEquiposException(persistenceException.getMessage(),persistenceException );
        }
    }

}
