package org.grupo.Presentacion.Vista.Controlador;

import org.grupo.Datos.XMLDom.DAO.ManejotablaEmpl;
import org.grupo.Datos.XMLDom.DAO.conexion;
import org.grupo.Negocio.*;
import org.grupo.Datos.XMLDom.*;
import org.grupo.Presentacion.Vista.Vista.MapaVista;
import org.grupo.Presentacion.Vista.Vista.PantallaPrincipal;
import org.grupo.Negocio.PdfSucursal;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Double.parseDouble;

public class Controlador {
    static ModeloTableModelSucursal modeloSuc = new ModeloTableModelSucursal();
    static ModeloTableModelEmpleado modelo_empleado = new ModeloTableModelEmpleado();
    static PantallaPrincipal view;
    static ModeloImagen gestorImagenes;
    public static conexion co = new conexion();

    public Controlador() {
        gestorImagenes = new ModeloImagen();
    }

    public static void reestart() {
        view.setVisible(false);
        gestorImagenes.setListaDeImagenes(gestorImagenes.CargaImagenes(modeloSuc.getListaDeSucursales()));
        view = new PantallaPrincipal();
    }

    public static ModeloImagen getImaModel() {
        return gestorImagenes;
    }

    public static ModeloTableModelSucursal getModeloSuc() {
        return modeloSuc;
    }

    public static ModeloTableModelEmpleado getModeloEm() {
        return modelo_empleado;
    }

    public void startApplication() throws IOException, ParserConfigurationException, SAXException {
        // View the application's GUI

      co.conect();
            modeloSuc.setListaDeSucursales(co.MostrarLista());
        try {
            modelo_empleado.setListaDeEmpleados(co.muestraempl());
        } catch (RuntimeException e) {
            modelo_empleado = new ModeloTableModelEmpleado();
        }
        // aca carga los xml

        gestorImagenes.setListaDeImagenes(gestorImagenes.CargaImagenes(modeloSuc.getListaDeSucursales()));

        view = new PantallaPrincipal();
        view.setVisible(true);
        try {
            co.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //preguntar como hacer el close
    }

    public static boolean guardaXMLEmpleados(TableModel model) {
        CrearXMLEmpleado.creaXML((ModeloTableModelEmpleado) model);
        return true;

    }

    public static boolean guardaXMLSucursal(TableModel model) {
        CrearXMLSucursales.creaXML((ModeloTableModelSucursal) model);
        return true;
    }

    public static void generarPdfSucursal() throws URISyntaxException, IOException, TransformerException, SAXException {
        PdfSucursal.convertToFO();
        PdfSucursal.convertToPDF();
        abrirArchivo("Sucursales.pdf");
    }

    public static void generarPdfEmpleado() throws URISyntaxException, IOException, TransformerException, SAXException {
        PdfEmpleado.convertToFO();
        PdfEmpleado.convertToPDF();
        abrirArchivo("Empleados.pdf");
    }

    //tomado de: https://codigosparadesarrolladores.blogspot.com/2014/06/codigo-java-abrir-archivo-de-cualquier-tipo.html
    public static void abrirArchivo(String archivo){
        try {
            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

        }catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void buscar(String nombre, JTable tabla) {
        ManejotablaEmpl m = new ManejotablaEmpl();

        ModeloTableModelEmpleado resultado = new ModeloTableModelEmpleado();

        HashMap<String, Empleado> dos = modelo_empleado.getListaDeEmpleados();
        if (!nombre.isEmpty()) {
            m.Buscar(tabla,nombre);
        } else {
            resultado = modelo_empleado;
            tabla.setModel(resultado);
        }
    }

    //Action Listeners de Empleado V;
    public static void AccionTablaEmp(JTable table, JPanel mapamain) {
        int fila = table.getSelectedRow();
        String cedu = (String) table.getValueAt(fila, 0);
        Empleado emple = null;
        emple = modelo_empleado.getListaDeEmpleados().get(cedu);
        JFrame Agregar = new JFrame();
        Agregar.setResizable(false);
        Agregar.setSize(1200, 400);
        Agregar.setLayout(new GridLayout(1, 2));
        JPanel Datos = new JPanel();

        Image markload = new ImageIcon("lugarRojo.png").getImage();
        Datos.setLayout(new GridLayout(6, 2));
        JTextField Nom = new JTextField();
        Nom.setText(emple.getNombre());
        JTextField tel = new JTextField();
        tel.setText(emple.getTelefono());
        JTextField salBase = new JTextField();
        salBase.setText(String.valueOf(emple.getSalario_base()));
        JTextField Suc = new JTextField(emple.getSucursal().getCodigo());


        JLabel codi = new JLabel("Cedula del Empleado: ");
        JLabel nomb = new JLabel("Nombre del Empleado: ");
        JLabel direcc = new JLabel("Numero de Telefono: ");
        JLabel porc = new JLabel("Salario Base: ");
        JLabel sucu = new JLabel("Codigo de sucursal: ");
        JLabel Codi = new JLabel(emple.getCedula());
        JButton Guard = new JButton("Guardar");
        JButton Cancel = new JButton("Cancelar");
        Cancel.setBackground(Color.RED);
        Guard.setBackground(Color.GREEN);
        Datos.add(codi);
        Datos.add(Codi);
        Datos.add(nomb);
        Datos.add(Nom);
        Datos.add(direcc);
        Datos.add(tel);
        Datos.add(porc);
        Datos.add(salBase);
        Datos.add(sucu);
        Datos.add(Suc);
        Datos.add(Guard);
        Datos.add(Cancel);

        Empleado finalEmple = emple;
        //Image imag = new ImageIcon(Controlador.class.getResource("/Imagen/lugarRojo.png")).getImage();

        //mapamain.getGraphics().drawImage(imag,emple.getSucursal().getCoordenada().getX(),emple.getSucursal().getCoordenada().getY(),20,20,mapamain);

        Agregar.add(Datos);
        Agregar.add(mapamain);
        Agregar.setVisible(true);

        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar.setVisible(false);
            }
        });

        Guard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Sucursal x = null;
                try {
                    for (Map.Entry<String, Sucursal> d : modeloSuc.getListaDeSucursales().entrySet()) {
                        x = d.getValue();
                        if (Objects.equals(d.getValue().getCodigo(), Suc.getText())) {

                            finalEmple.setSucursal(x);
                            finalEmple.setNombre(Nom.getText());
                            finalEmple.setTelefono(tel.getText());
                            finalEmple.setSalario_base(Double.parseDouble(salBase.getText()));
                            ManejotablaEmpl.UpdateEmp(finalEmple);
                            Agregar.setVisible(false);
                            Controlador.reestart();
                            break;

                        }
                        x = null;
                    }
                    if (x == null) {
                        JOptionPane.showMessageDialog(Agregar, "No se encuentra la sucursal");
                    }
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(Agregar, "Debe Agregar una cifra en el salario base");
                }
            }

        });


    }

    public static JFrame ValidacionIngresoEmpleado() {
        JFrame Agregar = new JFrame("Agregar Empleado");
        Agregar.setSize(600, 400);
        Agregar.setLayout(new GridLayout(6, 2));
        JTextField Ced = new JTextField();
        JTextField Nom = new JTextField();
        JTextField tel = new JTextField();
        JTextField salBase = new JTextField();
        JTextField Zonaje = new JTextField();

        JLabel codi = new JLabel("Escriba la cedula del empleado: ");
        JLabel nomb = new JLabel("Escriba el nombre del empleado: ");
        JLabel direcc = new JLabel("Escriba el numero de telefono del empleado: ");
        JLabel porc = new JLabel("Escriba el salario base del empleado: ");
        JLabel Zonaj = new JLabel("Escriba el codigo de la sucursal a la que pertenecera el empleado: ");

        JButton Guard = new JButton("Guardar");
        Guard.setBackground(Color.GREEN);
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.setBackground(Color.RED);
        Agregar.add(nomb);
        Agregar.add(Nom);
        Agregar.add(codi);
        Agregar.add(Ced);
        Agregar.add(direcc);
        Agregar.add(tel);
        Agregar.add(porc);
        Agregar.add(salBase);
        Agregar.add(Zonaj);
        Agregar.add(Zonaje);
        Agregar.add(Guard);
        Agregar.add(Cancelar);

        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar.setVisible(false);
            }
        });

        Guard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sucursal x = new Sucursal();
                boolean bandera = true;
                for (Map.Entry<String, Empleado> g : (modelo_empleado).getListaDeEmpleados().entrySet()) {
                    if (Objects.equals(Ced.getText(), g.getValue().getCedula())) {
                        bandera = false;
                    }
                }
                try {
                    double num = Double.parseDouble(salBase.getText());
                    if (bandera) {
                        for (Map.Entry<String, Sucursal> d : (modeloSuc).getListaDeSucursales().entrySet()) {
                            x = d.getValue();
                            if (Objects.equals(d.getValue().getCodigo(), Zonaje.getText())) {

                                Controlador.AgregarEmpleado(Ced.getText(), Nom.getText(), tel.getText(), parseDouble(salBase.getText()), d.getValue());
                                Agregar.setVisible(false);
                                break;

                            }
                        }
                        if (!(Objects.equals(x.getCodigo(), Zonaje.getText())))
                            JOptionPane.showMessageDialog(Agregar, "No se encuentra la sucursal");
                    } else {
                        JOptionPane.showMessageDialog(Agregar, "Cedula Repetida");
                    }
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(Agregar, "Invalido!, debe agregar un numero en el apartado de salario base");
                }


            }
        });
        return Agregar;
    }

    public static void AgregarEmpleado(String cedula, String nombre, String telefono, double salario_base, Sucursal s) {
        Empleado emp = new Empleado(cedula, nombre, telefono, salario_base, s);
        ManejotablaEmpl.InsertaEmp(emp);
        modelo_empleado.addEmpleado(emp);
        view.setVisible(false);
        view = new PantallaPrincipal();
        view.setVisible(true);
        //CrearXMLEmpleado.creaXML(modelo_empleado);
    }

    public static void buscarSucur(String cod, JTable tabla) {
        co.conect();
        ModeloTableModelSucursal resultado = new ModeloTableModelSucursal();

        HashMap<String, Sucursal> dos = modeloSuc.getListaDeSucursales();
        if (!cod.isEmpty()) {
            co.Buscar(tabla,cod);
        } else {
            resultado = modeloSuc;
            tabla.setModel(resultado);
        }
        try {
            co.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void AgregarSucursal(String Cod, String Nom, String Dir, Coordenada coor,String Can,String Pro,String Dis) {
        Sucursal dep = new Sucursal(Cod, Nom, Dir,coor,Can,Dis,Pro);
        dep.setPorcentajeDeZonaje(co.getZonajeDeProvincia(Pro));
        modeloSuc.addSucursal(dep);
        gestorImagenes.addImagen(new Imagen("/IconoPlace.png", dep, false));
        view.setVisible(false);
        view = new PantallaPrincipal();
        view.setVisible(true);
    }

    //ActionListeners Sucursal
    public static JFrame ValidarIngresoSucursal() {
        JPanel p2 = new JPanel();
        JFrame Agregar = new JFrame("Agregar Sucursal");
        Agregar.setSize(1200, 400);
        Agregar.setLayout(new GridLayout(1, 2));
        p2.setLayout(new GridLayout(9, 2));
        Agregar.add(p2);
        MapaVista mapa = new MapaVista();
        JPanel mapap = mapa.getF();

        Agregar.add(mapap, BorderLayout.CENTER);
        JTextField Codi = new JTextField();
        JTextField Nom = new JTextField();
        JTextField Direcc = new JTextField();
        JTextField Porc = new JTextField();
        JTextField canton = new JTextField();
        JTextField dist = new JTextField();

        JLabel codi = new JLabel("Escriba el codigo de la sucursal: ");
        JLabel Tex = new JLabel("SELECCIONE LA UBICACION  DE LA SUCURSAL EN EL MAPA");
        Tex.setFont(new Font(Font.SERIF, Font.BOLD, 10));
        JLabel nomb = new JLabel("Escriba el nombre corto de la sucursal: ");
        JLabel direcc = new JLabel("Escriba la direccion de la sucursal: ");
        JLabel porc = new JLabel("Escriba el nombre de la provincia donde se encuentra: ");
        //VALIDACION DE PROVINCIA DIGITADA
        JLabel CANT = new JLabel("Escriba el nombre del canton donde se encuentra: ");
        //validacion de canton que se encuentre en la provincia
        JLabel DIST = new JLabel("Escriba el nombre del distrito donde se encuentra: ");
        //validacion de distrito en el canton
        JButton Guard = new JButton("Guardar");
        Guard.setBackground(Color.GREEN);
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.setBackground(Color.RED);

        p2.add(nomb);
        p2.add(Codi);
        p2.add(codi);
        p2.add(Nom);
        p2.add(direcc);
        p2.add(Direcc);
        p2.add(porc);
        p2.add(Porc);
        p2.add(CANT);
        p2.add(canton);
        p2.add(DIST);
        p2.add(dist);
        p2.add(Guard);
        p2.add(Cancelar);
        p2.add(Tex);

        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar.setVisible(false);
            }
        });

        Guard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co.conect();
                try {
                    boolean bandera = co.ExisteCodigo(Integer.valueOf(Nom.getText()));
                    if (bandera) {
                        if (co.ValidacionProvincia(Porc.getText())) {
                            if (co.ValidacionCanton(Porc.getText(), canton.getText())) {
                                if (co.ValidacionDistrito(canton.getText(), dist.getText())) {
                                    int x = mapa.getF().x;
                                    int y = mapa.getF().y;
                                    Coordenada coor = new Coordenada(x, y);
                                    co.InsertaSucursal(Codi.getText(), Nom.getText(), Direcc.getText(), dist.getText(), Porc.getText(), canton.getText(), coor);
                                    Agregar.setVisible(false);
                                    AgregarSucursal(Nom.getText(),Codi.getText(), Direcc.getText(),coor,canton.getText(), Porc.getText(),dist.getText());
                                    co.close();
                                } else {
                                    JOptionPane.showMessageDialog(Agregar, "Distrito Incorrecto");
                                }
                            } else {
                                JOptionPane.showMessageDialog(Agregar, "Canton Incorrecto");
                            }
                        } else {
                            JOptionPane.showMessageDialog(Agregar, "Provincia Incorrecta");
                        }
                    } else {
                        JOptionPane.showMessageDialog(Agregar, "Invalido, Codigo Repetido");

                    }
                    co.close();
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(Agregar, "Codigo debe ser un numero");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(Agregar, ex.getMessage());
                }


            }


        });
        return Agregar;
    }

    public static void AccionTablaSuc(JTable tabla, JPanel mapamain) {
        Image im = new ImageIcon("lugarRojo.png").getImage();
        Image im2 = new ImageIcon("IconoPlace.png").getImage();
        for (Imagen x : gestorImagenes.getListaDeImagenes()) {

            mapamain.getGraphics().drawImage(im2, x.getSucur().getCoordenada().getX() - 95, x.getSucur().getCoordenada().getY() - 10, 20, 20, mapamain);


        }

        int fila = tabla.getSelectedRow();

        JLabel imag = new JLabel(new ImageIcon("lugarRojo.png"));


        String cod = (String) tabla.getValueAt(fila, 0);

        for (Imagen x : gestorImagenes.getListaDeImagenes()) {
            if (x.getSucur().getCodigo() == cod) {

                mapamain.getGraphics().drawImage(im, x.getSucur().getCoordenada().getX() - 95, x.getSucur().getCoordenada().getY() - 10, 20, 20, mapamain);
            }

        }

    }

    public static void EliminarElemento(@NotNull JTable tabla, char t) {
        co.conect();
        int fila = tabla.getSelectedRow();
        try {
            String cod = (String) tabla.getValueAt(fila, 0);
            if (t == 's') {
                boolean b = true;
               if(co.empleadosRelacionadosAsucursal(cod)) {
                   b = false;
                   JOptionPane.showMessageDialog(null,"La sucursal cuenta con empleados relacionados");
               }
                if (b) {
                    modeloSuc.getListaDeSucursales().remove(cod);


                    co.BorrarSucursal(cod);
                    co.close();

                    reestart();

                    guardaXMLEmpleados(modelo_empleado);
                    guardaXMLSucursal(modeloSuc);
                }


            }
            if (t == 'e') {
                modelo_empleado.getListaDeEmpleados().remove(cod);
                ManejotablaEmpl.DeleteEmp(cod);
               // guardaXMLEmpleados(modelo_empleado);
                reestart();
            }

        } catch (ArrayIndexOutOfBoundsException x) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un espacio de la tabla para poder eliminarlo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            co.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}





