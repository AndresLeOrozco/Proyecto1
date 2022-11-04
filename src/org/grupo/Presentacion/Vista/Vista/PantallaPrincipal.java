package org.grupo.Presentacion.Vista.Vista;

import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JFrame {

    private JPanel empleado;

    public PantallaPrincipal() {

        // Create and set up the window.
        super("Proyecto I - Programacion 3 - UNA");
        // Display the window.
        this.setSize(900, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // set grid layout for the frame
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        // add tab with title
        tabbedPane.addTab("Empleados", new EmpleadoV());

        // add tab with title and icon
        Icon icon = new ImageIcon("icon.gif");
        tabbedPane.addTab("Sucursales", new SucursalV());
        // add tab with title, icon and tooltip
        JPanel mainAD = new JPanel();
        mainAD.setLayout(new BorderLayout());
        mainAD.setSize(950, 510);
        JPanel imagenI = new JPanel();
        JPanel nombCom = new JPanel();
        JLabel nomC = new JLabel("Jennifer, Andrés, Idany. TecSolutions S.A,   Tel: 25252424");
        nomC.setVerticalTextPosition(JLabel.CENTER);
        nomC.setHorizontalTextPosition(JLabel.CENTER);
        nomC.setFont(new Font("MV Boli", Font.BOLD, 15));
        nombCom.add(nomC);
        ImageIcon imag = new ImageIcon("ImagCompania.jpg");
        JLabel j = new JLabel(imag);
        imagenI.add(j);
        mainAD.add(imagenI, BorderLayout.CENTER);
        mainAD.add(nombCom, BorderLayout.SOUTH);
        tabbedPane.addTab("Acerca de:", mainAD);

        this.add(tabbedPane);
    }
}