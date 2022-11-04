package org.grupo.Presentacion.Vista.Vista;

import org.grupo.Presentacion.Vista.Controlador.Controlador;
import org.grupo.Presentacion.Vista.Vista.MapaVista;
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

public class SucursalV extends JPanel {
    public SucursalV() {

        this.setLayout(new BorderLayout());

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new GridLayout(2, 4));
        JLabel nom = new JLabel("Filtrar: ");
        JTextField ingreso = new JTextField();
        ingreso.setText(null);
        ingreso.setPreferredSize(new Dimension(40, 50));
        JButton buscar = new JButton("Buscar");
        JButton save = new JButton("Guardar");
        JButton agregar = new JButton("Agregar");
        JButton Borrar = new JButton("Borrar");

        MapaVista.FondoPanel mapamain = new MapaVista().getF();

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controlador.guardaXMLSucursal(Controlador.getModeloSuc());
            }
        });

        JFrame Agregar = Controlador.ValidarIngresoSucursal();
        Agregar.setResizable(false);
        Agregar.setSize(1200, 400);
        Agregar.setLayout(new GridLayout(1, 2));
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar.setVisible(true);
            }
        });

        JButton pdf = new JButton("Generar PDF");

        p1.add(nom);
        p1.add(ingreso);
        p1.add(buscar);
        p1.add(new JLabel());
        p1.add(agregar);
        p1.add(save);
        p1.add(Borrar);
        p1.add(pdf);

        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controlador.generarPdfSucursal();
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

        JTable table = new JTable(Controlador.getModeloSuc());

        Borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controlador.EliminarElemento(table, 's');
            }
        });

        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Controlador.AccionTablaSuc(table, mapamain);
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

        this.add(p1, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.WEST);
        this.add(mapamain, BorderLayout.CENTER);

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controlador.buscarSucur(ingreso.getText(), table);
            }
        });

    }
}
