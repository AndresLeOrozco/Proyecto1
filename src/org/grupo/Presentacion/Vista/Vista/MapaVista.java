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
                            Agregar.setLayout(new GridLayout(5, 2));
                            JTextField Nom = new JTextField();
                            Nom.setText(ima.getSucur().getNombreCorto());
                            JTextField tel = new JTextField();
                            tel.setText(ima.getSucur().getDireccion());
                            JTextField salBase = new JTextField();
                            salBase.setText(String.valueOf(ima.getSucur().getPorcentajeDeZonaje()));

                            JLabel codi = new JLabel("Codigo de Sucursal: ");
                            JLabel nomb = new JLabel("Nombre de Sucursal: ");
                            JLabel direcc = new JLabel("Direccion: ");
                            JLabel porc = new JLabel("Porcentaje de Zonaje: ");
                            JLabel Codi = new JLabel(ima.getSucur().getCodigo());

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
                                    try {
                                        ima.getSucur().setNombreCorto(Nom.getText());
                                        ima.getSucur().setDireccion(tel.getText());
                                        ima.getSucur().setPorcentajeDeZonaje(Double.parseDouble(salBase.getText()));
                                        Agregar.setVisible(false);
                                        Controlador.reestart();
                                    } catch (NumberFormatException x) {
                                        JOptionPane.showMessageDialog(null, "Debe ingresar un numero en el apartado de Porcentaje Zonaje");
                                    }
                                }
                            });

                            Agregar.add(codi);
                            Agregar.add(Codi);
                            Agregar.add(nomb);
                            Agregar.add(Nom);
                            Agregar.add(direcc);
                            Agregar.add(tel);
                            Agregar.add(porc);
                            Agregar.add(salBase);
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
                            Agregar.setLayout(new GridLayout(5, 2));
                            JTextField Nom = new JTextField();
                            Nom.setText(ima.getSucur().getNombreCorto());
                            JTextField tel = new JTextField();
                            tel.setText(ima.getSucur().getDireccion());
                            JTextField salBase = new JTextField();
                            salBase.setText(String.valueOf(ima.getSucur().getPorcentajeDeZonaje()));

                            JLabel codi = new JLabel("Codigo de Sucursal: ");
                            JLabel nomb = new JLabel("Nombre de Sucursal: ");
                            JLabel direcc = new JLabel("Direccion: ");
                            JLabel porc = new JLabel("Porcentaje de Zonaje: ");
                            JLabel Codi = new JLabel(ima.getSucur().getCodigo());

                            JButton Guard = new JButton("Guardar");
                            Guard.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ima.getSucur().setNombreCorto(Nom.getText());
                                    ima.getSucur().setDireccion(tel.getText());
                                    ima.getSucur().setPorcentajeDeZonaje(Double.parseDouble(salBase.getText()));
                                    Agregar.setVisible(false);
                                    Controlador.reestart();
                                }
                            });

                            Agregar.add(codi);
                            Agregar.add(Codi);
                            Agregar.add(nomb);
                            Agregar.add(Nom);
                            Agregar.add(direcc);
                            Agregar.add(tel);
                            Agregar.add(porc);
                            Agregar.add(salBase);
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
                            Agregar.setLayout(new GridLayout(5, 2));
                            JTextField Nom = new JTextField();
                            Nom.setText(ima.getSucur().getNombreCorto());
                            JTextField tel = new JTextField();
                            tel.setText(ima.getSucur().getDireccion());
                            JTextField salBase = new JTextField();
                            salBase.setText(String.valueOf(ima.getSucur().getPorcentajeDeZonaje()));

                            JLabel codi = new JLabel("Codigo de Sucursal: ");
                            JLabel nomb = new JLabel("Nombre de Sucursal: ");
                            JLabel direcc = new JLabel("Direccion: ");
                            JLabel porc = new JLabel("Porcentaje de Zonaje: ");
                            JLabel Codi = new JLabel(ima.getSucur().getCodigo());

                            JButton Guard = new JButton("Guardar");
                            Guard.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ima.getSucur().setNombreCorto(Nom.getText());
                                    ima.getSucur().setDireccion(tel.getText());
                                    ima.getSucur().setPorcentajeDeZonaje(Double.parseDouble(salBase.getText()));
                                    Agregar.setVisible(false);
                                    Controlador.reestart();
                                }
                            });

                            Agregar.add(codi);
                            Agregar.add(Codi);
                            Agregar.add(nomb);
                            Agregar.add(Nom);
                            Agregar.add(direcc);
                            Agregar.add(tel);
                            Agregar.add(porc);
                            Agregar.add(salBase);
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

