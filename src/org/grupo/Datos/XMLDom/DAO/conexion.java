package org.grupo.Datos.XMLDom.DAO;
import org.grupo.Negocio.*;

import javax.swing.*;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

public class conexion {

    Connection cn;

    public ManejotablaEmpl getEm() {
        return em;
    }

    public void setEm(ManejotablaEmpl em) {
        this.em = em;
    }

    ManejotablaEmpl em;
    public Connection conect() {
        em= new ManejotablaEmpl();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto?useSSL=false", "root", "andres4646");
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return cn;
    }
    public void close() throws SQLException {
        cn.close();
    }
    public HashMap<String, Empleado> muestraempl(){

       return em.MostrarListaEmplea();
    }
    public void Buscar(JTable tab, String Bus){
        ModeloTableModelSucursal mod = new ModeloTableModelSucursal();
        String sql = "select * from sucursales where concat(codigo,canton,distrito,provincia,nombre_referencia) like '%"+Bus+"%'";
        try {
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    int x = Integer.parseInt(rs.getString("coordenada_x"));
                    int y = Integer.parseInt(rs.getString("coordenada_y"));
                    Sucursal s = new Sucursal(rs.getString("codigo"),rs.getString("nombre_referencia"),
                            rs.getString("direccion"),new Coordenada(x, y),rs.getString("canton"),
                            rs.getString("distrito"),rs.getString("provincia"));
                    mod.addSucursal(s);
                }

                tab.setModel(mod);

        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }

    }

     public void BorrarSucursal(String cod){
        String sql = "delete from sucursales where codigo = ?";
        try {
            PreparedStatement statement = cn.prepareStatement(sql);
            //setting parameter values
            statement.setString(1, cod);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
               JOptionPane.showMessageDialog(null,"A new user was inserted successfully!");
           }
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }

    }

    public void InsertaSucursal(String n, String cod, String dir, String Distrito, String Provincia, String Canton, Coordenada co) {

        //sql statement for inserting record
        String sql = "INSERT INTO sucursales " +
                "(codigo, coordenada_x,coordenada_y,distrito,canton,provincia,nombre_referencia,direccion) " +
                "VALUES " +
                "(?,?,?,?,?,?,?,?)";
        //getting input from user

        try {
            PreparedStatement statement = cn.prepareStatement(sql);
            //setting parameter values
            statement.setString(1, cod);
            statement.setString(2, String.valueOf(co.getX()));
            statement.setString(3, String.valueOf(co.getY()));
            statement.setString(4, Distrito);
            statement.setString(5, Canton);
            statement.setString(6, Provincia);
            statement.setString(7, n);
            statement.setString(8, dir);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
               JOptionPane.showMessageDialog(null,"A new user was inserted successfully!");
            }
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
    }

    public HashMap<String, Sucursal> MostrarLista(){ //las coordenadas no pueden estar en 0 porque no las muestra
        HashMap<String, Sucursal> mod = new HashMap<String, Sucursal>();
        String sql = "SELECT * FROM sucursales";
        try {
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Sucursal s = new Sucursal();
                    s.setCodigo(rs.getString("codigo"));
                    int x = Integer.parseInt(rs.getString("coordenada_x"));
                    int y = Integer.parseInt(rs.getString("coordenada_y"));
                    s.setCoordenada(new Coordenada(x, y));
                    s.setDistrito(rs.getString("distrito"));
                    s.setCanton(rs.getString("canton"));
                    s.setProvincia(rs.getString("provincia"));
                    s.setNombreCorto(rs.getString("nombre_referencia"));
                    s.setDireccion(rs.getString("direccion"));
                    s.setPorcentajeDeZonaje(getZonajeDeProvincia(s.getProvincia()));
                    mod.put(s.getCodigo(), s);
                }

        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
        return mod;
    }
    public static Sucursal getSucursal(String id){ //las coordenadas no pueden estar en 0 porque no las muestra
        HashMap<String, Sucursal> mod = new HashMap<String, Sucursal>();
        String sql = "SELECT * FROM sucursales where codigo = " + id;
        conexion conn = new conexion();
        conn.conect();
        try {
            Statement st;
            st = conn.cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    //rs.first();
                    Sucursal s = new Sucursal();
                    s.setCodigo(rs.getString("codigo"));
                    int x = Integer.parseInt(rs.getString("coordenada_x"));
                    int y = Integer.parseInt(rs.getString("coordenada_y"));
                    s.setCoordenada(new Coordenada(x, y));
                    s.setDistrito(rs.getString("distrito"));
                    s.setCanton(rs.getString("canton"));
                    s.setProvincia(rs.getString("provincia"));
                    s.setNombreCorto(rs.getString("nombre_referencia"));
                    s.setDireccion(rs.getString("direccion"));
                    s.setPorcentajeDeZonaje(conn.getZonajeDeProvincia((s.getProvincia())));
                    conn.close();
                    return s;
                }
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());

        }
return new Sucursal();
    }

    public int getZonajeDeProvincia(String nomPr) {
        String sql = "select * from provincia where nombre_provincia = "+"'"+nomPr+"'";
        //getting input from user
        Integer zonaje = 0;
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


        if(rs.next()){
            return rs.getInt("zonaje");
        }
        else
            System.out.println("no encontrado");
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
       return 0;
    }
    public boolean ExisteCodigo(Integer codSuc) {
        String sql = "select * from sucursales where codigo = "+codSuc;

        //getting input from user
        Integer IdPro = 0;
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            if(rs.next()){
                return false;
            }
            return true;


        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return false;
    }
    public int getIdProvincia(String nomPr) {
        String sql = "select * from provincia where nombre_provincia = "+"'"+nomPr+"'";
        //getting input from user
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            if(rs.next()){
                return rs.getInt("id_provincia");
            }
            else
                System.out.println("no encontrado");
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return 0;
    }
    public int getIdCanton(String NCanton) {
        String sql = "select * from canton where nombre_canton = "+"'"+NCanton+"'";
        //getting input from user
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            if(rs.next()){
                return rs.getInt("id_canton");
            }
            else
                System.out.println("no encontrado");
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return 0;
    }
    public int getIdDistrito(String NDistrito) {
        String sql = "select * from distrito where nombre_distrito = "+"'"+NDistrito+"'";
        //getting input from user
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            if(rs.next()){
                return rs.getInt("id_distrito");
            }
            else
                System.out.println("no encontrado");
        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return 0;
    }
    public boolean ValidacionProvincia(String nomPr) {
        String sql = "select * from provincia where nombre_provincia like "+"'"+nomPr+"'";

        //getting input from user
        Integer IdPro = 0;
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            while(rs.next()){
                return true;
            }
            return false;


        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return false;
    }
    public boolean ValidacionCanton(String nomPr,String nomCan) {
        String sql = "select * from canton where nombre_canton like "+"'"+nomCan+"'";

        //getting input from user
        Integer IdPro = 0;
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            while(rs.next()){
                IdPro = rs.getInt("id_provincia");
                if(IdPro == getIdProvincia(nomPr))
                    return true;
            }

        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return false;
    }
    public boolean ValidacionDistrito(String nomCa,String nomDis) {
        String sql = "select * from distrito where nombre_distrito like "+"'"+nomDis+"'";

        //getting input from user
        Integer IdPro = 0;
        try {

            //creating and executing our statement
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);


            while(rs.next()){
                IdPro = rs.getInt("id_canton");
                if(IdPro == getIdCanton(nomCa))
                    return true;
            }

        } catch (Exception e) {
            System.out.println("Exception in connection: " + e.toString());
        }
        return false;
    }

    public static void main (String[]args){
        conexion c = new conexion();
        c.conect();
//            c.getZonajeDeProvincia("'Cartago'");
//            System.out.println(c.getZonajeDeProvincia("'Heredia'"));
//            System.out.println(c.getIdProvincia("'Heredia'"));
//            System.out.println(c.getIdCanton("'Heredia'"));
//            System.out.println(c.getIdDistrito("'Orotina'"));
        //   System.out.println(c.ValidacionCanton("Heredia","Barva"));
//            System.out.println(c.ValidacionDistrito("'Desamparados'","'San Antonio'"));
        //System.out.println(c.ValidacionProvincia("Heredia"));
        //System.out.println(c.ExisteCodigo(4));

        try {
            c.cn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    }

