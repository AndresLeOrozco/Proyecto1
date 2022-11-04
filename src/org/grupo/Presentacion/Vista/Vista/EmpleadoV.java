package org.grupo.Presentacion.Vista.Vista;

import org.grupo.Presentacion.Vista.Controlador.Controlador;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class EmpleadoV extends JPanel {

    public EmpleadoV() {

        JPanel datos = new JPanel();
        datos.add(new JLabel("Cedula: "));
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(40, 50));
        datos.add(text);

        JButton search = new JButton("Buscar");
        datos.add(search);
        datos.add(new JLabel());
        JButton add = new JButton("Agregar");
        datos.add(add);
        JButton save = new JButton("Guardar");
        datos.add(save);
        JButton Borrar = new JButton("Borrar");
        datos.add(Borrar);
        datos.setLayout(new GridLayout(2, 5));
        this.setLayout(new BorderLayout());
        JButton pdf = new JButton("Generar PDF");
        datos.add(pdf);

        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controlador.generarPdfEmpleado();
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (TransformerException ex) {
                    throw new RuntimeException(ex);
                } catch (SAXException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JTable table = new JTable(Controlador.getModeloEm());
        MapaVista.FondoPanel mapamain = new MapaVista().getF();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    Controlador.AccionTablaEmp(table, mapamain);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.add(datos, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Controlador.buscar(text.getText(), table);
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Controlador.guardaXMLEmpleados(table.getModel());
            }
        });

        Borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controlador.EliminarElemento(table, 'e');
            }
        });

        JFrame Agregar = Controlador.ValidacionIngresoEmpleado();
        Agregar.setResizable(false);
        Agregar.setSize(600, 400);
        Agregar.setLayout(new GridLayout(6, 2));

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar.setVisible(true);
            }
        });

    }
}
