package org.grupo.Negocio;

import org.grupo.Negocio.Empleado;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModeloTableModelEmpleado extends AbstractTableModel {
    private HashMap<String, Empleado> ListaDeEmpleados;
    private int numeroEmpleados;
    private LinkedList Listen;

    public ModeloTableModelEmpleado() {
        super();
        ListaDeEmpleados = new HashMap<String, Empleado>();
        numeroEmpleados = ListaDeEmpleados.size();
        Listen = new LinkedList<>();
    }

    public void addEmpleado(Empleado em) {
        String cedu = em.getCedula();
        ListaDeEmpleados.put(cedu, em);
        //crea un evento en esa fila Cual? el de insertar empleado
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

        int i;

        for (i = 0; i < Listen.size(); i++)
            ((TableModelListener) Listen.get(i)).tableChanged(evento); //table change notifica a la lista que algo paso y eso es el insert.
    }

    public Empleado buscar(String cedula) { //Busca el empleado por cedula o key y regresa el empleado
        Empleado value = new Empleado();
        for (Map.Entry<String, Empleado> entry : ListaDeEmpleados.entrySet()) {
            String key = entry.getKey();
            if (key == cedula) {
                value = entry.getValue();
            } else {
                value = new Empleado();
            }
        }
        return value;
    }

    public void editar(Empleado emp) { // el parametro es el empleado nuevo los datos nuevos
        Empleado aux = buscar(emp.getCedula());
        aux.setCedula(emp.getCedula());
        aux.setNombre(emp.getNombre());
        aux.setTelefono(emp.getTelefono());
        aux.setSalario_base(emp.getSalario_base());
        aux.setSucursal(emp.getSucursal());
    }
    public void borrarEmpleado(String key) {
        for (Map.Entry<String, Empleado> em : ListaDeEmpleados.entrySet()) {
            if (em.getKey() == key) {
                ListaDeEmpleados.remove(key);
            }
        }
    }

    public void addTableModelListener(TableModelListener l) {
        Listen.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        Listen.remove(l);
    }

    public HashMap<String, Empleado> getListaDeEmpleados() {
        return ListaDeEmpleados;
    }

    public void setListaDeEmpleados(HashMap<String, Empleado> listaDeEmpleados) {
        ListaDeEmpleados = listaDeEmpleados;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    @Override
    public int getRowCount() {
        return ListaDeEmpleados.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    public String getColumnName(int columnIndex) {
        // Devuelve el nombre de cada columna. Este texto aparecerá en la
        // cabecera de la tabla.
        switch (columnIndex) {
            case 0:
                return "Cedula";
            case 1:
                return "Nombre";
            case 2:
                return "Telefono";
            case 3:
                return "Salario";
            case 4:
                return "Sucursal";
            case 5:
                return "% Zonaje";
            case 6:
                return "Sal.total";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado empleadoAux;
        empleadoAux = (Empleado) this.ListaDeEmpleados.values().toArray()[rowIndex]; //Hago un casteo para guardar en el empleado lo que hay en ese index

        switch (columnIndex) {// tengo los datos de la fila y va por columna trae los datos
            case 0:
                return empleadoAux.getCedula();
            case 1:
                return empleadoAux.getNombre();
            case 2:
                return empleadoAux.getTelefono();
            case 3:
                return empleadoAux.getSalario_base();
            case 4:
                return empleadoAux.getSucursal();
            case 5:
                return empleadoAux.getSucursal().getPorcentajeDeZonaje();
            case 6:
                return empleadoAux.Salario_Total();
        }
        return null;
    }

    public void setValueAt(Object empleado, int rowIndex, int columnIndex) {
        Empleado empleadoAux = (Empleado) (ListaDeEmpleados.get(rowIndex)); // Toma los datos de la lista para que se pinten en la tabla
        switch (columnIndex) {
            case 0:
                empleadoAux.setCedula((empleadoAux).getCedula());
            case 1:
                empleadoAux.setNombre((empleadoAux).getNombre());
            case 2:
                empleadoAux.setTelefono((empleadoAux).getTelefono());
            case 3:
                empleadoAux.setSalario_base((empleadoAux).getSalario_base());
            case 4:
                empleadoAux.setSucursal((empleadoAux).getSucursal());
            case 5:
            case 6:
        }
    }

}
