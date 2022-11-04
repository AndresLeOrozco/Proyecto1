package org.grupo.Datos.XMLDom.DAO;

import org.grupo.Negocio.Coordenada;
import org.grupo.Negocio.Empleado;
import org.grupo.Negocio.Sucursal;

import javax.swing.*;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ManejotablaEmpl {
    conexion c = new conexion();

    public HashMap<String, Empleado> MostrarListaEmplea(){ //las coordenadas no pueden estar en 0 porque no las muestra
        HashMap<String, Empleado> mod = new HashMap<String, Empleado>();
        String sql = "SELECT * FROM empleados";
        try {
            Statement st;
            st = c.conect().createStatement();
            ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Empleado e = new Empleado();
                    e.setCedula(rs.getString("id_empleado"));
                    e.setNombre(rs.getString("nombre"));
                    e.setTelefono(rs.getString("tel"));
                    e.setSalario_base(Double.parseDouble(rs.getString("sal")));
                    String sus = rs.getString("sucursal_id");
                    Sucursal s = conexion.getSucursal(sus);
                    e.setSucursal(s);
                    e.getSucursal().setCodigo(rs.getString("sucursal_id"));
                    mod.put(e.getCedula(), e);
                }
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
        return mod;
    }
    public static void InsertaEmp(Empleado emp) {
        //sql statement for inserting record
        String sql = "insert into empleados (id_empleado,nombre, tel,sal,sucursal_id) values "+
                "(?,?,?,?,?)";
        try {
            conexion conn = new conexion();
            PreparedStatement statement = conn.conect().prepareStatement(sql);
            //setting parameter values
            statement.setString(1, emp.getCedula());
            statement.setString(2, emp.getNombre());
            statement.setString(3, emp.getTelefono());
            statement.setString(4, String.valueOf(emp.getSalario_base()));
            statement.setString(5, String.valueOf(emp.getSucursal().getCodigo()));
            //executing query which will return an integer value
            int rowsInserted = statement.executeUpdate();
            //if rowInserted is greater than 0 mean rows are inserted
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null,"A new user was inserted successfully!");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
    }

    public static void UpdateEmp(Empleado emp) {
        //sql statement for inserting record
        String sql = "update empleados set nombre = ?, tel = ?,sal = ?,sucursal_id = ? where id_empleado = "+ emp.getCedula();
        try {
            conexion conn = new conexion();
            PreparedStatement statement = conn.conect().prepareStatement(sql);
            //setting parameter values
            statement.setString(1, emp.getNombre());
            statement.setString(2, emp.getTelefono());
            statement.setString(3, String.valueOf(emp.getSalario_base()));
            statement.setString(4, String.valueOf(emp.getSucursal().getCodigo()));
            //executing query which will return an integer value
            int rowsInserted = statement.executeUpdate();
            //if rowInserted is greater than 0 mean rows are inserted
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null,"user was updated successfully!");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
    }
    public static void DeleteEmp(String ced) {
        //sql statement for inserting record
        String sql = "Delete from empleados where id_empleado = "+ ced;
        try {
            conexion conn = new conexion();
            PreparedStatement statement = conn.conect().prepareStatement(sql);
            //executing query which will return an integer value
            int rowsInserted = statement.executeUpdate();
            //if rowInserted is greater than 0 mean rows are inserted
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null,"user was deleted successfully!");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
    }
}