package gui;

import dao.DAOProveedorImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import pojo.Proveedor;

    public class ListarProveedor extends JFrame implements MouseListener, ActionListener {

        public ListarProveedor() {
            setSize(700, 500);
            initComponents();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        JLabel lbBuscar = new JLabel(new ImageIcon());
        JLabel lb1 = new JLabel("NOMBRE");
        JLabel lb2 = new JLabel("DOCUMENTO");
        JLabel lb3 = new JLabel("DIRECCIÓN");
        JLabel lb4 = new JLabel("CORREO");
        JLabel lb5 = new JLabel("TELEFONO");

        JTextField tfBuscar = new JTextField();
        JTextField tf1 = new JTextField();
        JTextField tf2 = new JTextField();
        JTextField tf3 = new JTextField();
        JTextField tf4 = new JTextField();
        JTextField tf5 = new JTextField();


        JButton btBuscar = new JButton("Buscar");
        JButton btlistar = new JButton("listar");
        JButton btActualizar = new JButton("Actualizar");
        JButton btEliminar = new JButton("Eliminar");
        JButton btRegistrar = new JButton("Registrar");

        public void initComponents () {
            JPanel pnlU = new JPanel(new GridLayout(2, 1));

            JPanel pnl1 = new JPanel(new GridLayout(6, 2));
            pnl1.setBackground(new Color(42, 0, 0));
            pnl1.add(tfBuscar);
            pnl1.add(btBuscar);
            btBuscar.addActionListener(this);
            pnl1.add(lb1);
            pnl1.add(tf1);
            tf1.setBackground(new Color(2,11,0));
            tf1.setForeground(new Color(255,225,225));
            pnl1.add(lb2);
            pnl1.add(tf2);
            pnl1.add(lb3);
            pnl1.add(tf3);
            pnl1.add(lb4);
            pnl1.add(tf4);
            pnl1.add(lb5);
            pnl1.add(tf5);


            JPanel pnl2 = new JPanel(new BorderLayout());
            pnl2.setBackground(new Color(0, 23, 0));
            pnl2.add(btRegistrar, BorderLayout.NORTH);
            btRegistrar.setBackground(new Color(12,0,0));
            btRegistrar.setForeground(new Color(255,255,255));
            btRegistrar.addActionListener(this);
            pnl2.add(btlistar, BorderLayout.SOUTH);
            btlistar.addActionListener(this);
            pnl2.add(btActualizar, BorderLayout.WEST);
            btActualizar.addActionListener(this);
            pnl2.add(btEliminar, BorderLayout.EAST);
            btEliminar.addActionListener(this);



            tabla.setModel(new DefaultTableModel(
                    new Object[][]{},
                    new String[]{"ID","NOMBRE","DOCUMENTO","DIRECCIÓN","CORREO","TELEFONO"}));
            ScrollPane scroll = new ScrollPane();
            scroll.add(tabla);
            scroll.validate();
            //tabla.setBorder(new TitledBorder("tabla"));
            pnl2.add(scroll, BorderLayout.CENTER);
            pnlU.add(pnl1);
            pnlU.add(pnl2);
            //pnlU.add(scroll);
            add(pnlU);
        }
        JTable tabla = new JTable();
        interfaces.DAOProveedor dao = new DAOProveedorImpl();
        Proveedor pvr = new Proveedor();

        public void vaciar(){
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
            tf5.setText("");
        }

        @Override
        public void actionPerformed (ActionEvent e){
            if(e.getSource() == btlistar){
                List<Proveedor> lista = dao.listar();
                Object [][] datos = new Object[lista.size()][6];
                for (int i = 0; i < lista.size(); i++) {
                    datos[i][0] = lista.get(i).getId_prov();
                    datos[i][1] = lista.get(i).getName_prov();
                    datos[i][2] = lista.get(i).getDoc_prov();
                    datos[i][3] = lista.get(i).getDirection_prov();
                    datos[i][4] = lista.get(i).getCorreo_prov();
                    datos[i][5] = lista.get(i).getTelefono();
                    System.out.println(datos[i][2]);
                }

                tabla.setModel(new DefaultTableModel(datos,new String[]{"ID","NOMBRE","DOCUMENTO","DIRECCIÓN","CORREO","TELEFONO"}));
            }
            if(e.getSource() == btRegistrar){
                pvr.setName_prov(tf1.getText());
                pvr.setDoc_prov(tf2.getText());
                pvr.setDirection_prov(tf3.getText());
                pvr.setCorreo_prov(tf4.getText());
                pvr.setTelefono(Integer.parseInt(tf5.getText()));
                dao.registrar(pvr);
                vaciar();
            }

            if(e.getSource() == btActualizar){
                pvr.setName_prov(tf1.getText());
                pvr.setDoc_prov(tf2.getText());
                pvr.setDirection_prov(tf3.getText());
                pvr.setCorreo_prov(tf4.getText());
                pvr.setTelefono(Integer.parseInt(tf5.getText()));
                pvr.setId_prov(Integer.parseInt(tfBuscar.getText()));
                dao.actualizar(pvr);
            }
            if(e.getSource() == btEliminar){
                pvr.setId_prov(Integer.parseInt(tfBuscar.getText()));
                dao.eliminar(pvr);
                vaciar();
            }
            if(e.getSource() == btBuscar){
                pvr = dao.buscarProv(Integer.parseInt(tfBuscar.getText()));
                if(pvr.getId_prov() == (0)){
                    System.out.println(pvr.getName_prov()+"d");
                    vaciar();
                    JOptionPane.showMessageDialog(null,"Proveedor no encotrado","Error",JOptionPane.ERROR_MESSAGE);
                }else {
                    tf1.setText(pvr.getName_prov());
                    tf2.setText(pvr.getDoc_prov());
                    tf3.setText(pvr.getDirection_prov());
                    tf4.setText(pvr.getCorreo_prov());
                    tf5.setText("" + pvr.getTelefono());
                    tabla.setModel(
                            new DefaultTableModel(new Object[][]{{pvr.getId_prov() ,pvr.getName_prov(), pvr.getDoc_prov(), pvr.getDirection_prov(),pvr.getCorreo_prov(), pvr.getTelefono()}},
                                    new String[]{"ID","NOMBRE","DOCUMENTO","DIRECCIÓN","CORREO","TELEFONO"}));
                }
            }
        }

        @Override
        public void mouseClicked (MouseEvent e){

        }

        @Override
        public void mousePressed (MouseEvent e){

        }

        @Override
        public void mouseReleased (MouseEvent e){

        }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }
    }
