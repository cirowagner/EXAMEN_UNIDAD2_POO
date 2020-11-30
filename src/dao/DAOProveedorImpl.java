package dao;

import interfaces.DAOProveedor;
import pojo.Proveedor;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOProveedorImpl extends Conexion implements DAOProveedor {
    @Override
    public void registrar(Proveedor e) {
        try{
            PreparedStatement pstmt = connect().prepareStatement("INSERT INTO proveedor " +
                    "(NOMBRE_Prov, DOC_Prov, DIRECTION_Prov, CORREO_Prov, TELEFONO) VALUES " +
                    "(?,?,?,?,?)");
            pstmt.setString(1, e.getName_prov());
            pstmt.setString(2, e.getDoc_prov());
            pstmt.setString(3, e.getDirection_prov());
            pstmt.setString(4, e.getCorreo_prov());
            pstmt.setInt(5, e.getTelefono());
            pstmt.executeUpdate();
        }catch (Exception er){
            er.getMessage();
        }
    }

    @Override
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            PreparedStatement pstmt = connect().prepareStatement("SELECT * FROM proveedor");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Proveedor pvr = new Proveedor();
                pvr.setId_prov(rs.getInt(1));
                pvr.setName_prov(rs.getString(2));
                pvr.setDoc_prov(rs.getString(3));
                pvr.setDirection_prov(rs.getString(4));
                pvr.setCorreo_prov(rs.getString(5));
                pvr.setTelefono(rs.getInt(6));
                lista.add(pvr);
            }
        } catch (Exception er) {
            er.getMessage();
        }
        return lista;
    }

    @Override
    public Proveedor buscarProv(int id) {
        Proveedor pvr = new Proveedor();
        try {
            PreparedStatement pstmt = connect().prepareStatement("SELECT * FROM proveedor WHERE ID_Prov = "+id+"");
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                pvr = new Proveedor();
                pvr.setId_prov(rs.getInt(1));
                pvr.setName_prov(rs.getString(2));
                System.out.println("NAMEEEEE: "+ pvr.getName_prov());
                pvr.setDoc_prov(rs.getString(3));
                pvr.setDirection_prov(rs.getString(4));
                pvr.setCorreo_prov(rs.getString(5));
                pvr.setTelefono(rs.getInt(6));
            }else {
                pvr.setId_prov(0);
            }
            connect().close();
            pstmt.close();
        }catch (Exception er){
            er.getMessage();
        }
        return pvr;
    }

    @Override
    public void actualizar(Proveedor e) {
        try{
            PreparedStatement pStmt = connect().prepareStatement("UPDATE proveedor SET" +
                    " NOMBRE_Prov = ?, " +
                    " DOC_Prov = ?, " +
                    " DIRECTION_Prov = ?, " +
                    " CORREO_Prov = ?, " +
                    " TELEFONO = ? " +
                    " WHERE ID_Prov = ?");
            pStmt.setString(1,e.getName_prov());
            pStmt.setString(2,e.getDoc_prov());
            pStmt.setString(3,e.getDirection_prov());
            pStmt.setString(4,e.getCorreo_prov());
            pStmt.setInt(5,e.getTelefono());
            pStmt.setInt(6,e.getId_prov());
            pStmt.executeUpdate();
        }catch (Exception er){
            er.getMessage();
        }
    }
    @Override
    public void eliminar(Proveedor e) {
        try{
            PreparedStatement pstmt = connect().prepareStatement("DELETE FROM proveedor WHERE ID_Prov = ?");
            pstmt.setInt(1,e.getId_prov());
            pstmt.executeUpdate();
        }catch (Exception er){
            er.getMessage();
        }
    }
}
