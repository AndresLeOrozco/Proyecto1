package org.grupo.Presentacion.Vista.Vista;

import org.grupo.Presentacion.Vista.Controlador.Controlador;
import org.grupo.Negocio.Imagen;
import org.grupo.Negocio.ModeloImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class MapaVista extends JFrame implements MouseListener {
    private JLabel imag;

    public FondoPanel getF() {
        return f;
    }

    public void setF(FondoPanel f) {
        this.f = f;
    }

    FondoPanel f = new FondoPanel();

    public MapaVista() {
        this.setLayout(new FlowLayout());
        this.setContentPane(f);
        this.setSize(1000, 1000);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(this, "estoy hacieendo click");
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

    public class FondoPanel extends JPanel implements MouseListener {
        private Image imagenmark;
        public int x;
        public int y;

        public ModeloImagen molIma = Controlador.getImaModel();

        @Override
        public void paint(Graphics g) {
            Image imagenmap = new ImageIcon("MapaCr.jpg").getImage();
            g.drawImage(imagenmap, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            addMouseListener(this);
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            String title = topFrame.getTitle();

            if (title.equals("Proyecto I - Programacion 3 - UNA")) {
                for (Imagen ima : molIma.getListaDeImagenes()) {
                    Image markload = new ImageIcon("IconoPlace.png").getImage();
                    JLabel toolt = new JLabel();
                    toolt.setBounds(ima.getSucur().getCoordenada().getX() - 95, ima.getSucur().getCoordenada().getY() - 10, 30, 30);
                    toolt.setToolTipText(ima.toString());
                    toolt.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JFrame Agregar = new JFrame();
                            Agregar.setSize(900, 500);
                            Agregar.setLayout(new GridLayout(9, 2));
                            JTextField Nom = new JTextField();
                            Nom.setText(ima.getSucur().getNombreCorto());
                            JTextField tel = new JTextField();
                            tel.setText(ima.getSucur().getDireccion());
                            JTextField prov = new JTextField();
                            prov.setText(ima.getSucur().getProvincia());
                            JTextField cant = new JTextField();
                            cant.setText(ima.getSucur().getCanton());
                            JTextField dist = new JTextField();
                            dist.setText(ima.getSucur().getDistrito());

                            JLabel codi = new JLabel("Codigo de Sucursal: ");
                            JLabel nomb = new JLabel("Nombre de Sucursal: ");
                            JLabel direcc = new JLabel("Direccion: ");
                            JLabel Porc = new JLabel("Porcentaje de la zona: ");
                            JLabel PROV = new JLabel("Escriba el nombre de la provincia donde se encuentra: ");
                            //VALIDACION DE PROVINCIA DIGITADA
                            JLabel CANT = new JLabel("Escriba el nombre del canton donde se encuentra: ");
                            //validacion de canton que se encuentre en la provincia
                            JLabel DIST = new JLabel("Escriba el nombre del distrito donde se encuentra: ");
                            //validacion de distrito en el canton
                            JLabel Codi = new JLabel(ima.getSucur().getCodigo());
                            JLabel porc = new JLabel(String.valueOf(ima.getSucur().getPorcentajeDeZonaje()));


                            JButton Guard = new JButton("Guardar");
                            Guard.setBackground(Color.GREEN);

                            JButton Cancelar = new JButton("Cancelar");
                            Cancelar.setBackground(Color.RED);
                            Cancelar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Agregar.setVisible(false);
                                }
                            });

                            Guard.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Controlador.co.conect();
                                    try {
                                        if (Controlador.co.ValidacionProvincia(prov.getText())) {
                                            if (Controlador.co.ValidacionCanton(prov.getText(), cant.getText())) {
                                                if (Controlador.co.ValidacionDistrito(cant.getText(), dist.getText())) {
                                                    Controlador.co.editarSucursal(ima.getSucur().getCodigo(), Nom.getText(),tel.getText(), dist.getText(),prov.getText(),cant.getText());
                                                    ima.getSucur().setNombreCorto(Nom.getText());
                                                    ima.getSucur().setDireccion(tel.getText());
                                                    ima.getSucur().setProvincia(prov.getText());
                                                    ima.getSucur().setCanton(cant.getText());
                                                    ima.getSucur().setDistrito(dist.getText());
                                                    Agregar.setVisible(false);
                                                    Controlador.reestart();
                                                    Controlador.co.close();
                                                } else {
                                                    JOptionPane.showMessageDialog(Agregar, "Distrito Incorrecto");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(Agregar, "Canton Incorrecto");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(Agregar, "Provincia Incorrecta");
                                        }
                                    } catch (NumberFormatException x) {
                                        JOptionPane.showMessageDialog(null, "Debe ingresar un numero en el apartado de Porcentaje Zonaje");
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });

                            Agregar.add(codi);
                            Agregar.add(Codi);
                            Agregar.add(nomb);
                            Agregar.add(Nom);
                            Agregar.add(direcc);
                            Agregar.add(tel);
                            Agregar.add(Porc);
                            Agregar.add(porc);
                            Agregar.add(PROV);
                            Agregar.add(prov);
                            Agregar.add(CANT);
                            Agregar.add(cant);
                            Agregar.add(DIST);
                            Agregar.add(dist);
                            Agregar.add(Guard);
                            Agregar.add(Cancelar);
                            Agregar.setVisible(true);
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

                    this.add(toolt);
                    g.drawImage(markload, ima.getSucur().getCoordenada().getX() - 95, ima.getSucur().getCoordenada().getY() - 10, 20, 20, this);
                }
            } else {
                for (Imagen ima : molIma.getListaDeImagenes()) {
                    JLabel toolt = new JLabel();
                    toolt.setBounds(ima.getSucur().getCoordenada().getX() - 95, ima.getSucur().getCoordenada().getY() - 10, 30, 30);
                    toolt.setToolTipText(ima.toString());
                    toolt.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JFrame Agregar = new JFrame();
                            Agregar.setSize(900, 500);
                            Agregar.setLayout(new GridLayout(9, 2));
                            JTextField Nom = new JTextField();
                            Nom.setText(ima.getSucur().getNombreCorto());
                            JTextField tel = new JTextField();
                            tel.setText(ima.getSucur().getDireccion());
                            JTextField prov = new JTextField();
                            prov.setText(ima.getSucur().getProvincia());
                            JTextField cant = new JTextField();
                            cant.setText(ima.getSucur().getCanton());
                            JTextField dist = new JTextField();
                            dist.setText(ima.getSucur().getDistrito());

                            JLabel codi = new JLabel("Codigo de Sucursal: ");
                            JLabel nomb = new JLabel("Nombre de Sucursal: ");
                            JLabel direcc = new JLabel("Direccion: ");
                            JLabel Porc = new JLabel("Porcentaje de la zona: ");
                            JLabel PROV = new JLabel("Escriba el nombre de la provincia donde se encuentra: ");
                            //VALIDACION DE PROVINCIA DIGITADA
                            JLabel CANT = new JLabel("Escriba el nombre del canton donde se encuentra: ");
                            //validacion de canton que se encuentre en la provincia
                            JLabel DIST = new JLabel("Escriba el nombre del distrito donde se encuentra: ");
                            //validacion de distrito en el canton
                            JLabel Codi = new JLabel(ima.getSucur().getCodigo());
                            JLabel porc = new JLabel(String.valueOf(ima.getSucur().getPorcentajeDeZonaje()));

                            JButton Guard = new JButton("Guardar");
                            Guard.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Controlador.co.conect();
                                    try {
                                        if (Controlador.co.ValidacionProvincia(prov.getText())) {
                                            if (Controlador.co.ValidacionCanton(prov.getText(), cant.getText())) {
                                                if (Controlador.co.ValidacionDistrito(cant.getText(), dist.getText())) {
                                                    Controlador.co.editarSucursal(ima.getSucur().getCodigo(), Nom.getText(),tel.getText(), dist.getText(),prov.getText(),cant.getText());
                                                    ima.getSucur().setNombreCorto(Nom.getText());
                                                    ima.getSucur().setDireccion(tel.getText());
                                                    ima.getSucur().setProvincia(prov.getText());
                                                    ima.getSucur().setCanton(cant.getText());
                                                    ima.getSucur().setDistrito(dist.getText());
                                                    Agregar.setVisible(false);
                                                    Controlador.reestart();
                                                    Controlador.co.close();
                                                } else {
                                                    JOptionPane.showMessageDialog(Agregar, "Distrito Incorrecto");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(Agregar, "Canton Incorrecto");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(Agregar, "Provincia Incorrecta");
                                        }
                                    } catch (NumberFormatException x) {
                                        JOptionPane.showMessageDialog(null, "Debe ingresar un numero en el apartado de Porcentaje Zonaje");
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });

                            Agregar.add(codi);
                            Agregar.add(Codi);
                            Agregar.add(nomb);
                            Agregar.add(Nom);
                            Agregar.add(direcc);
                            Agregar.add(tel);
                            Agregar.add(Porc);
                            Agregar.add(porc);
                            Agregar.add(PROV);
                            Agregar.add(prov);
                            Agregar.add(CANT);
                            Agregar.add(cant);
                            Agregar.add(DIST);
                            Agregar.add(dist);
                            Agregar.add(Guard);
                            Agregar.setVisible(true);
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

                    this.add(toolt);
                    Image markload = new ImageIcon("IconoPlace.png").getImage();
                    g.drawImage(markload, ima.getSucur().getCoordenada().getX(), ima.getSucur().getCoordenada().getY(), 20, 20, this);
                }
            }
            super.paint(g);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            if (imagenmark == null) {
                imagenmark = new ImageIcon("IconoPlace.png").getImage();

                this.getGraphics().drawImage(imagenmark, x, y, 20, 20, this);

                JLabel toolt = new JLabel();
                toolt.setBounds(x, y, 30, 30);
                toolt.setToolTipText("ima.toString()");

                this.add(toolt);
                this.x = x;
                this.y = y;

            } else {
                Image imagenmap = new ImageIcon("MapaCr.jpg").getImage();
                this.getGraphics().drawImage(imagenmap, 0, 0, getWidth(), getHeight(), this);
                this.getGraphics().drawImage(imagenmark, x, y, 20, 20, this);
                this.x = x;
                this.y = y;

                for (Imagen ima : molIma.getListaDeImagenes()) {
                    JLabel toolt = new JLabel();
                    toolt.setBounds(ima.getSucur().getCoordenada().getX() - 95, ima.getSucur().getCoordenada().getY() - 10, 30, 30);
                    toolt.setToolTipText(ima.toString());
                    toolt.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JFrame Agregar = new JFrame();
                            Agregar.setSize(900, 500);
                            Agregar.setLayout(new GridLayout(9, 2));
                            JTextField Nom = new JTextField();
                            Nom.setText(ima.getSucur().getNombreCorto());
                            JTextField tel = new JTextField();
                            tel.setText(ima.getSucur().getDireccion());
                            JTextField prov = new JTextField();
                            prov.setText(ima.getSucur().getProvincia());
                            JTextField cant = new JTextField();
                            cant.setText(ima.getSucur().getCanton());
                            JTextField dist = new JTextField();
                            dist.setText(ima.getSucur().getDistrito());

                            JLabel codi = new JLabel("Codigo de Sucursal: ");
                            JLabel nomb = new JLabel("Nombre de Sucursal: ");
                            JLabel direcc = new JLabel("Direccion: ");
                            JLabel Porc = new JLabel("Porcentaje de la zona: ");
                            JLabel PROV = new JLabel("Escriba el nombre de la provincia donde se encuentra: ");
                            JLabel CANT = new JLabel("Escriba el nombre del canton donde se encuentra: ");
                            JLabel DIST = new JLabel("Escriba el nombre del distrito donde se encuentra: ");
                            JLabel Codi = new JLabel(ima.getSucur().getCodigo());
                            JLabel porc = new JLabel(String.valueOf(ima.getSucur().getPorcentajeDeZonaje()));

                            JButton Guard = new JButton("Guardar");
                            Guard.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Controlador.co.conect();
                                    try {
                                        if (Controlador.co.ValidacionProvincia(prov.getText())) {
                                            if (Controlador.co.ValidacionCanton(prov.getText(), cant.getText())) {
                                                if (Controlador.co.ValidacionDistrito(cant.getText(), dist.getText())) {
                                                    Controlador.co.editarSucursal(ima.getSucur().getCodigo(), Nom.getText(),tel.getText(), dist.getText(),prov.getText(),cant.getText());
                                                    ima.getSucur().setNombreCorto(Nom.getText());
                                                    ima.getSucur().setDireccion(tel.getText());
                                                    ima.getSucur().setProvincia(prov.getText());
                                                    ima.getSucur().setCanton(cant.getText());
                                                    ima.getSucur().setDistrito(dist.getText());
                                                    Agregar.setVisible(false);
                                                    Controlador.reestart();
                                                    Controlador.co.close();
                                                } else {
                                                    JOptionPane.showMessageDialog(Agregar, "Distrito Incorrecto");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(Agregar, "Canton Incorrecto");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(Agregar, "Provincia Incorrecta");
                                        }
                                    } catch (NumberFormatException x) {
                                        JOptionPane.showMessageDialog(null, "Debe ingresar un numero en el apartado de Porcentaje Zonaje");
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });

                            Agregar.add(codi);
                            Agregar.add(Codi);
                            Agregar.add(nomb);
                            Agregar.add(Nom);
                            Agregar.add(direcc);
                            Agregar.add(tel);
                            Agregar.add(Porc);
                            Agregar.add(porc);
                            Agregar.add(PROV);
                            Agregar.add(prov);
                            Agregar.add(CANT);
                            Agregar.add(cant);
                            Agregar.add(DIST);
                            Agregar.add(dist);
                            Agregar.add(Guard);
                            Agregar.setVisible(true);
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

                    this.add(toolt);
                    Image markload = new ImageIcon("IconoPlace.png").getImage();
                    this.getGraphics().drawImage(markload, ima.getSucur().getCoordenada().getX(), ima.getSucur().getCoordenada().getY(), 20, 20, this);
                }
            }
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

    }

}

