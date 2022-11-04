package org.grupo.Negocio;

import org.grupo.Negocio.Sucursal;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModeloTableModelSucursal extends AbstractTableModel {
    private HashMap<String, Sucursal> ListaDeSucursales;
    private int numeroSucursales;
    private LinkedList Listen;

    public ModeloTableModelSucursal() {
        super();
        ListaDeSucursales = new HashMap<String, Sucursal>();
        numeroSucursales = ListaDeSucursales.size();
        Listen = new LinkedList<>();
    }

    public void addSucursal(Sucursal su) {
        String cod = su.getCodigo();
        ListaDeSucursales.put(cod, su);
        //crea un evento en esa fila Cual? el de insertar empleado
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        int i;

        for (i = 0; i < Listen.size(); i++)
            ((TableModelListener) Listen.get(i)).tableChanged(evento); //table change notifica a la lista que algo paso y eso es el insert.
    }

    public Sucursal buscar(String codigo) { //Busca el empleado por codigo o key y regresa el Sucursal
        Sucursal value = new Sucursal();
        for (Map.Entry<String, Sucursal> entry : ListaDeSucursales.entrySet()) { //itere este EntrySet
            String key = entry.getKey();
            if (key == codigo) {
                value = entry.getValue();
            } else {
                value = new Sucursal();
            }
        }
        return value;
    }

    public void editar(Sucursal emp) { // el parametro es la Sucursal nuevo los datos nuevos
        Sucursal aux = buscar(emp.getCodigo());
        aux.setNombreCorto(emp.getNombreCorto());
        aux.setDireccion(emp.getDireccion());
        aux.setPorcentajeDeZonaje(emp.getPorcentajeDeZonaje());
        aux.setCoordenada(emp.getCoordenada());
    }

    public void borrarEmpleado(String key) {
        for (Map.Entry<String, Sucursal> em : ListaDeSucursales.entrySet()) {
            if (em.getKey() == key) {
                ListaDeSucursales.remove(key);
            }
        }
    }

    public void addTableModelListener(TableModelListener l) {
        Listen.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        Listen.remove(l);
    }

    public HashMap<String, Sucursal> getListaDeSucursales() {
        return ListaDeSucursales;
    }

    public void setListaDeSucursales(HashMap<String, Sucursal> listaDeSucursales) {
        ListaDeSucursales = listaDeSucursales;
    }
   public  Sucursal getSucXCod(int inds){
        return ListaDeSucursales.get(inds);
   }
    public int getNumeroSucursales() {
        return numeroSucursales;
    }

    public void setNumeroSucursales(int numeroSucursales) {
        this.numeroSucursales = numeroSucursales;
    }

    @Override
    public int getRowCount() {
        return ListaDeSucursales.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public String getColumnName(int columnIndex) {
        // Devuelve el nombre de cada columna. Este texto aparecerá en la
        // cabecera de la tabla.
        switch (columnIndex) {
            case 0:
                return "Codigo";
            case 1:
                return "Referencia";
            case 2:
                return "Dirección";
            case 3:
                return "% Zonaje";
            case 4:
                return "Coordenadas";
            case 5:
                return "Provincia";


            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sucursal sucursalAux;
        sucursalAux = (Sucursal) this.ListaDeSucursales.values().toArray()[rowIndex]; //Hago un casteo para guardar en el empleado lo que hay en ese index
        switch (columnIndex) {// tengo los datos de la fila y va por columna trae los datos
            case 0:
                return sucursalAux.getCodigo();
            case 1:
                return sucursalAux.getNombreCorto();
            case 2:
                return sucursalAux.getDireccion();
            case 3:
                return sucursalAux.getPorcentajeDeZonaje();
            case 4:
                return sucursalAux.getCoordenada();
            case 5:
                return sucursalAux.getProvincia();
        }
        return null;
    }

    public void setValueAt(Object sucursal, int rowIndex, int columnIndex) {

        Sucursal sucursalAux = (Sucursal) this.ListaDeSucursales.values().toArray()[rowIndex];
        ; // Toma los datos de la lista para que se pinten en la tabla
        switch (columnIndex) {
            case 0:
                sucursalAux.setCodigo((sucursalAux).getCodigo());
            case 1:
                sucursalAux.setNombreCorto((sucursalAux).getNombreCorto());
            case 2:
                sucursalAux.setDireccion((sucursalAux).getDireccion());
            case 3:
                sucursalAux.setPorcentajeDeZonaje((sucursalAux).getPorcentajeDeZonaje());
            case 4:
                sucursalAux.setCoordenada((sucursalAux).getCoordenada());
            case 5:
                sucursalAux.setProvincia((sucursalAux).getProvincia());

        }
    }

}
