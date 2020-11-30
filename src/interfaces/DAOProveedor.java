package interfaces;

import pojo.Proveedor;
import java.util.List;

public interface DAOProveedor {
    public List<Proveedor> listar ();
    public Proveedor buscarProv(int id);
    public void registrar(Proveedor e);
    public void actualizar(Proveedor p);
    public void eliminar(Proveedor p);
}
