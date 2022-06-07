/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.japo.java.entities.Carrito;
import org.japo.java.entities.Categoria;
import org.japo.java.entities.Compra;
import org.japo.java.entities.Desarrolladora;
import org.japo.java.entities.Favorito;
import org.japo.java.entities.Grupo;
import org.japo.java.entities.Producto;
import org.japo.java.entities.Transaccion;
import org.japo.java.entities.Usuario;
import org.japo.java.libraries.IdDescription;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class MainDAO {

    // Propiedades del Servido
    private Properties prp;

    public MainDAO(Properties prp) {
        this.prp = prp;
    }

    // <editor-fold defaultstate="collapsed" desc="Producto">
    public List<Producto> listarProductos() {
        // SQL
        final String SQL
                = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p "
                + "LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "ORDER BY lanzamiento ASC";

        // Lista de Productos vacía
        List<Producto> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        Producto p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista
                        lista.add(p);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public List<Producto> listarProductosPagina(int offset, int limit, int cat, int des, int tip, int min, int max) {
        // SQL

        String sql
                = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p "
                + "LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "WHERE precio - (precio*descuento/100) BETWEEN ? AND ?";

        if (cat > 0) {
            sql += " AND categoria = " + cat;
        }
        if (des > 0) {
            sql += " AND desarrolladora = " + des;
        }
        if (tip < 2) {
            sql += " AND tipo = " + tip;
        }

        sql += "  ORDER BY lanzamiento ASC LIMIT ?,?";

        // Lista de Productos vacía
        List<Producto> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, min);
                ps.setDouble(2, max);
                ps.setInt(3, offset);
                ps.setInt(4, limit);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        Producto p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista
                        lista.add(p);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("xd");
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Producto
        return lista;
    }

    public Producto obtenerProducto(int _id) {
        // SQL
        final String SQL = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p"
                + " LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "WHERE p.id = ?";

        // Lista de Productos vacía
        Producto p = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista

                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return p;
    }

    public Producto obtenerProductoNombre(String _nombre) {
        // SQL
        final String SQL = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p"
                + " LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "WHERE p.nombre = ?";

        // Lista de Productos vacía
        Producto p = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setString(1, _nombre.trim());
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista

                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return p;
    }

    public int contarProcutos(int cat, int des, int tipo, double min, double max) {
        // SQL
        String sql = "SELECT * FROM productos WHERE precio - (precio*descuento/100) BETWEEN ? AND ?";

        if (cat > 0) {
            sql += " AND categoria = " + cat;
        }
        if (des > 0) {
            sql += " AND desarrolladora = " + des;
        }
        if (tipo < 2) {
            sql += " AND tipo = " + tipo;
        }
        // Lista de Productos vacía
        int productos = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, min);
                ps.setDouble(2, max);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        productos++;
                        // Producto > Lista

                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return productos;
    }

    public List<Producto> filtrarProductos(String name) {
        // SQL
        final String SQL
                = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p "
                + "LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "WHERE p.nombre LIKE ?"
                + " LIMIT 5";

        // Lista de Productos vacía
        List<Producto> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                ps.setString(1, "%" + name + "%");
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        Producto p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista
                        lista.add(p);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;

    }

    public List<Producto> obtenerProductosCarrito(int _id) {
        // SQL
        final String SQL
                = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p "
                + "LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "WHERE p.id IN (SELECT producto FROM carrito WHERE usuario = ?)";

        // Lista de Productos vacía
        List<Producto> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        Producto p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista
                        lista.add(p);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;

    }

    public boolean insertarProducto(String nombre, double precio, int tipo, int descuento, int categoria, int desarrolladora, String descripcion, String img, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        final String SQL = "INSERT INTO productos "
                + "(nombre,precio,descuento,tipo,categoria,desarrolladora,descripcion,imagen,lanzamiento) "
                + "VALUES ('" + nombre + "'," + precio + "," + descuento + "," + (tipo == 1 ? true : false) + "," + categoria + "," + desarrolladora + ",'" + descripcion + "','" + img + "','" + date + "');";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarProducto(int id, String nombre, double precio, int descuento, int tipo, int categoria, int desarrolladora, String descripcion, String img, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        final String SQL = "UPDATE productos "
                + "SET nombre=?, precio=?, descuento=?, tipo=?, categoria=?, desarrolladora=?, descripcion=?, imagen=?, lanzamiento=? "
                + "WHERE id = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, nombre);
                ps.setDouble(2, precio);
                ps.setInt(3, descuento);
                ps.setBoolean(4, tipo == 1 ? true : false);
                ps.setInt(5, categoria);
                ps.setInt(6, desarrolladora);
                ps.setString(7, descripcion);
                ps.setString(8, img);
                ps.setString(9, date);
                ps.setInt(10, id);
                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public int contarProductosNombre(String nombre) {
        // SQL
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";

        // Lista de Productos vacía
        int productos = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + nombre + "%");
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        productos++;
                        // Producto > Lista

                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return productos;
    }

    public List<Producto> listarProductosPaginadosNombre(int offset, int limit, String _nombre) {
        // SQL
        String sql
                = "SELECT p.id as id,"
                + "p.nombre as nombre,"
                + "p.descripcion as descripcion,"
                + "p.precio as precio,"
                + "p.tipo as tipo,"
                + "p.categoria as categoriaId,"
                + "c.nombre as categoriaDescription,"
                + "p.desarrolladora as desarrolladoraId,"
                + "d.nombre as desarrolladoraDescription,"
                + "p.lanzamiento as lanzamiento,"
                + "p.imagen as imagen,"
                + "p.descuento as descuento"
                + " FROM productos p "
                + "LEFT JOIN categorias c on c.id = p.categoria"
                + " LEFT JOIN desarrolladoras d on d.id = p.desarrolladora "
                + "WHERE p.nombre LIKE ? ORDER BY lanzamiento ASC LIMIT ?,?";

        // Lista de Productos vacía
        List<Producto> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + _nombre + "%");
                ps.setInt(2, offset);
                ps.setInt(3, limit);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        boolean tipo = rs.getInt("tipo") == 1 ? true : false;
                        IdDescription categoria = new IdDescription(rs.getInt("categoriaId"), rs.getString("categoriaDescription"));
                        IdDescription desarrolladora = new IdDescription(rs.getInt("desarrolladoraId"), rs.getString("desarrolladoraDescription"));
                        Date lanzamiento = rs.getDate("lanzamiento");
                        String imagen = rs.getString("imagen");
                        int descuento = rs.getInt("descuento");

                        // Instanciar Producto
                        Producto p = new Producto(id, nombre, precio, tipo,
                                categoria, desarrolladora, descripcion,
                                lanzamiento, imagen, descuento);
                        // Producto > Lista
                        lista.add(p);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        // Devolder la lista de Producto
        return lista;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Usuario">
    public Usuario obtenerUsuarioUs(String _usuario) {
        // SQL
        final String SQL = "SELECT * FROM usuarios WHERE user=?";

        // Referencia de Entidad
        Usuario u = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial Nombrado JNDI
            Context iniCtx = new InitialContext();

            // Situar Contexto Inicial
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Contexto Inicial > DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, _usuario);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {

                        // Registro Actual > Campos
                        int id = rs.getInt("id");
                        String usuario = rs.getString("user");
                        String pass = rs.getString("password");
                        String email = rs.getString("email");
                        String avatar = rs.getString("avatar");
                        int grupo = rs.getInt("grupo");

                        // Campos > Entidad
                        u = new Usuario(id, usuario, pass, email, avatar, grupo);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Retorno Entidad
        return u;
    }

    public Usuario obtenerUsuarioEmail(String _email) {
        // SQL
        final String SQL = "SELECT * FROM usuarios WHERE email=?";

        // Referencia de Entidad
        Usuario u = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial Nombrado JNDI
            Context iniCtx = new InitialContext();

            // Situar Contexto Inicial
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Contexto Inicial > DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, _email);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {

                        // Registro Actual > Campos
                        int id = rs.getInt("id");
                        String usuario = rs.getString("user");
                        String pass = rs.getString("password");
                        String email = rs.getString("email");
                        String avatar = rs.getString("avatar");
                        int grupo = rs.getInt("grupo");

                        // Campos > Entidad
                        u = new Usuario(id, usuario, pass, email, avatar, grupo);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Retorno Entidad
        return u;
    }

    public Usuario obtenerUsuarioId(int _id) {
        // SQL
        final String SQL = "SELECT * FROM usuarios WHERE id=?";

        // Referencia de Entidad
        Usuario u = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial Nombrado JNDI
            Context iniCtx = new InitialContext();

            // Situar Contexto Inicial
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Contexto Inicial > DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, _id);

                // BD > Entidad
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {

                        // Registro Actual > Campos
                        int id = rs.getInt("id");
                        String usuario = rs.getString("usuario");
                        String pass = rs.getString("password");
                        String email = rs.getString("email");
                        String avatar = rs.getString("avatar");
                        int grupo = rs.getInt("grupo");

                        // Campos > Entidad
                        u = new Usuario(id, usuario, pass, email, avatar, grupo);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Retorno Entidad
        return u;
    }

    public List<Usuario> obtenerListaUsuario() {
        // SQL
        final String SQL = "SELECT * FROM usuarios";

        // Lista de Productos vacía
        List<Usuario> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String usuario = rs.getString("usuario");
                        String pass = rs.getString("password");
                        String email = rs.getString("email");
                        String avatar = rs.getString("avatar");
                        int grupo = rs.getInt("grupo");

                        // Campos > Entidad
                        Usuario u = new Usuario(id, usuario, pass, email, avatar, grupo);

                        // Producto > Lista
                        lista.add(u);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public boolean insertarUsuario(Usuario u) {
        final String SQL = "INSERT INTO usuarios "
                + "(user,password,grupo,avatar,email) "
                + "VALUES (?,?,?,?,?);";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, u.getUser());
                ps.setString(2, u.getPassword());
                ps.setString(3, u.getGrupo() + "");
                ps.setString(4, u.getAvatar());
                ps.setString(5, u.getEmail());

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarUsuario(int id, String user, String email) {
        String SQL = "UPDATE usuarios "
                + "SET user = '" + user + "', email='" + email + "'";

        SQL += " WHERE id =" + id;

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarUsuarioPass(int id, String user, String email, String pass) {
        String SQL = "UPDATE usuarios "
                + "SET user = '" + user + "', email='" + email + "',password='" + pass + "'";

        SQL += " WHERE id =" + id;

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarUsuarioImg(int id, String user, String email, String img) {
        String SQL = "UPDATE usuarios "
                + "SET user = '" + user + "', email='" + email + "',avatar='" + img + "'";

        SQL += " WHERE id =" + id;

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarUsuarioAll(int id, String user, String email, String pass, String img) {
        String SQL = "UPDATE usuarios "
                + "SET user = '" + user + "', email='" + email + "',avatar='" + img + "',password='" + pass + "'";

        SQL += " WHERE id =" + id;

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Carrito">
    public List<Carrito> listarCarrito(int _id) {
        // SQL
        final String SQL = "SELECT * FROM carrito WHERE usuario = ?";

        // Lista de Productos vacía
        List<Carrito> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {

                ps.setInt(1, _id);

                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int producto = rs.getInt("producto");
                        int usuario = rs.getInt("usuario");

                        // Instanciar Producto
                        Producto p = this.obtenerProducto(producto);
                        Carrito c = new Carrito(id, p, usuario);
                        // Producto > Lista
                        lista.add(c);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public Carrito obtenerCarrito(int userId, int prodId) {
        // SQL
        final String SQL = "SELECT * FROM carrito WHERE usuario = ? and producto = ?";

        // Lista de Productos vacía
        Carrito f = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, userId);
                ps.setInt(2, prodId);
                System.out.println("xd");
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int producto = rs.getInt("producto");
                        int usuario = rs.getInt("usuario");

                        f = new Carrito(id, producto, usuario);

                        // Producto > Lista
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return f;
    }

    public boolean añadirCarrito(int prod, int user) {
        final String SQL = "INSERT INTO carrito "
                + "(producto,usuario) "
                + "VALUES (?,?);";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, prod);
                ps.setInt(2, user);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean eliminarProductoCarrito(int userId, int prodId) {
        // SQL
        final String SQL = "DELETE FROM carrito WHERE producto = ? and usuario = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, prodId);
                ps.setInt(2, userId);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        System.out.println(numReg);
        return numReg == 1;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Categoria">
    public List<Categoria> listarCategorias() {
        // SQL
        final String SQL = "SELECT * FROM categorias";

        // Lista de Productos vacía
        List<Categoria> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");

                        // Instanciar Producto
                        Categoria c = new Categoria(id, nombre, descripcion);
                        // Producto > Lista
                        lista.add(c);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public Categoria obtenerCategorias(int _id) {
        // SQL
        final String SQL = "SELECT * FROM categorias WHERE id=?";

        // Lista de Productos vacía
        Categoria c = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");

                        // Instanciar Producto
                        c = new Categoria(id, nombre, descripcion);
                        // Producto > Lista
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return c;
    }

    public boolean añadirCategoria(String nombre) {
        final String SQL = "INSERT INTO categorias "
                + "(nombre,descripcion) "
                + "VALUES (?,'');";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, nombre);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarCategoria(int id, String nombre) {
        final String SQL = "UPDATE categorias "
                + " SET nombre = ? WHERE id = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, nombre);
                ps.setInt(2, id);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean borrarCategoria(int id) {
        // SQL
        final String SQL = "DELETE FROM categorias WHERE id = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        System.out.println(numReg);
        return numReg == 1;

    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Transaccion">
    public List<Transaccion> listarTransacciones(int _id) {
        // SQL
        final String SQL = "SELECT * FROM transacciones WHERE usuario = ? ORDER BY fecha DESC";

        // Lista de Productos vacía
        List<Transaccion> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {

                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int usuario = rs.getInt("usuario");
                        double precio = rs.getDouble("importe");
                        Date fecha = rs.getDate("fecha");

                        // Instanciar Producto
                        Transaccion t = new Transaccion(id, usuario, fecha, precio);
                        // Producto > Lista
                        lista.add(t);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public Transaccion obtenerTransaccion(int _id) {
        // SQL
        final String SQL = "SELECT * FROM transacciones WHERE id = ?";

        // Lista de Productos vacía
        Transaccion t = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {

                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int usuario = rs.getInt("usuario");
                        double precio = rs.getDouble("precio");
                        Date fecha = rs.getDate("fecha");

                        // Instanciar Producto
                        t = new Transaccion(id, usuario, fecha, precio);
                        // Producto > Lista
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return t;
    }

    public boolean insertarTransaccion(int id, int user, Date d, double importe) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        final String SQL = "INSERT INTO transacciones "
                + "(id,usuario,fecha,importe) "
                + "VALUES (?,?,?,?);";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);
                ps.setInt(2, user);
                ps.setString(3, sdf.format(d));
                ps.setDouble(4, importe);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Compra">
    public List<Compra> listarCompras(int _id) {

        // SQL
        final String SQL = "SELECT * FROM compras WHERE transaccion = ?";

        // Lista de Productos vacía
        List<Compra> lista = new ArrayList<>();
        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int transaccion = rs.getInt("transaccion");
                        int producto = rs.getInt("producto");
                        double precio = rs.getDouble("precio");

                        // Instanciar Producto
                        Producto p = this.obtenerProducto(producto);
                        Compra c = new Compra(id, transaccion, p, precio);
                        // Producto > Lista
                        lista.add(c);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
//        }
        // Devolder la lista de Productos
        return lista;
    }

    public List<Producto> listarComprasUsuario(int _id, int offset,int limit) {

        // SQL
        final String SQL = "SELECT * from productos WHERE id IN(SELECT producto FROM `compras` WHERE transaccion IN (SELECT id FROM transacciones WHERE usuario = ?)) LIMIT ?,?";

        // Lista de Productos vacía
        List<Producto> lista = new ArrayList<>();
        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                ps.setInt(2, offset);
                ps.setInt(3, limit);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int producto = rs.getInt("id");

                        // Instanciar Producto
                        Producto p = this.obtenerProducto(producto);
                        // Producto > Lista
                        lista.add(p);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
//        }
        // Devolder la lista de Productos
        return lista;
    }

    public Compra obtenerCompra(int _id) {
        // SQL
        final String SQL = "SELECT * FROM compras WHERE user = ?";

        // Lista de Productos vacía
        Compra c = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        double precio = rs.getDouble("precio");
                        int transaccion = rs.getInt("transaccion");
                        int producto = rs.getInt("producto");

                        // Instanciar Producto
                        Producto p = this.obtenerProducto(producto);
                        c = new Compra(id, transaccion, p, precio);
                        // Producto > Lista
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return c;
    }

    public boolean insertarCompra(int id, int prod, double precio) {
        final String SQL = "INSERT INTO compras "
                + "(transaccion,producto,precio) "
                + "VALUES (?,?,?);";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);
                ps.setInt(2, prod);
                ps.setDouble(3, precio);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Favorito">
    public List<Favorito> listarFavoritos(int _id, int offset, int limit) {
        // SQL
        final String SQL = "SELECT * FROM favoritos WHERE usuario = ? LIMIT ?,?";

        // Lista de Productos vacía
        List<Favorito> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                ps.setInt(1, _id);
                ps.setInt(2, offset);
                ps.setInt(3, limit);
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int producto = rs.getInt("producto");
                        int usuario = rs.getInt("usuario");

                        // Instanciar Producto
                        Producto p = this.obtenerProducto(producto);
                        Favorito f = new Favorito(id, p, usuario);
                        // Producto > Lista
                        lista.add(f);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista.isEmpty() ? null : lista;
    }

    public Favorito obtenerFavorito(int userId, int prodId) {
        // SQL
        final String SQL = "SELECT * FROM favoritos WHERE usuario = ? and producto = ?";

        // Lista de Productos vacía
        Favorito f = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, userId);
                ps.setInt(2, prodId);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        int producto = rs.getInt("producto");
                        int usuario = rs.getInt("usuario");

                        // Instanciar Producto
                        Producto p = this.obtenerProducto(producto);
                        f = new Favorito(id, p, usuario);
                        // Producto > Lista
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return f;
    }

    public boolean quitarFavorito(int prod, int user) {
        // SQL
        final String SQL = "DELETE FROM favoritos WHERE producto = ? and usuario = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, prod);
                ps.setInt(2, user);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean añadirFavorito(int prod, int user) {
        final String SQL = "INSERT INTO favoritos "
                + "(producto,usuario) "
                + "VALUES (?,?);";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, prod);
                ps.setInt(2, user);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Desarrolladora">
    public List<Desarrolladora> listarDesarrolladoras() {
        // SQL
        final String SQL = "SELECT * FROM desarrolladoras";

        // Lista de Productos vacía
        List<Desarrolladora> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");

                        // Instanciar Producto
                        Desarrolladora d = new Desarrolladora(id, nombre);
                        // Producto > Lista
                        lista.add(d);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public Desarrolladora obtenerDesarrolladora(int _id) {
        // SQL
        final String SQL = "SELECT * FROM desarrolladoras WHERE id = ?";

        // Lista de Productos vacía
        Desarrolladora d = null;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, _id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");

                        // Instanciar Producto
                        d = new Desarrolladora(id, nombre);
                        // Producto > Lista
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return d;
    }

    public boolean añadirDesarrolladora(String nombre) {
        final String SQL = "INSERT INTO desarrolladoras "
                + "(nombre,director) "
                + "VALUES (?,'');";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, nombre);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean modificarDesarrolladora(int id, String nombre) {
        final String SQL = "UPDATE desarrolladoras "
                + " SET nombre = ? WHERE id = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setString(1, nombre);
                ps.setInt(2, id);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return numReg == 1;
    }

    public boolean borrarDesarrolladora(int id) {
        // SQL
        final String SQL = "DELETE FROM desarrolladoras WHERE id = ?";

        // Número de registros afectados
        int numReg = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Parametrizar Sentencia
                ps.setInt(1, id);

                // Borrar el Producto
                numReg = ps.executeUpdate();
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        System.out.println(numReg);
        return numReg == 1;

    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Grupo">
    public List<Grupo> listarGrupos() {
        // SQL
        final String SQL = "SELECT * FROM productos";

        // Lista de Productos vacía
        List<Grupo> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");

                        // Instanciar Producto
                        Grupo g = new Grupo(id, nombre, descripcion);
                        // Producto > Lista
                        lista.add(g);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }
    //</editor-fold>

    public long contarCategorias() {
        // SQL
        final String SQL = "SELECT * FROM categorias";

        // Lista de Productos vacía
        long filas = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        if (rs.next()) {
                            filas = rs.getLong(1);
                        }
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return filas;
    }

    public long contarDesarrolladoras() {
        // SQL
        final String SQL = "SELECT * FROM desarrolladoras";

        // Lista de Productos vacía
        long filas = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        if (rs.next()) {
                            filas = rs.getLong(1);
                        }
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return filas;
    }

    public List<Categoria> listarCategoriaPagina(int offset, int limit) {
        // SQL
        final String SQL = "SELECT * FROM categorias LIMIT ?,?";

        // Lista de Productos vacía
        List<Categoria> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                // Obtener Productos
                ps.setInt(1, offset);
                ps.setInt(2, limit);
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");

                        // Instanciar Producto
                        Categoria c = new Categoria(id, nombre, descripcion);
                        // Producto > Lista
                        lista.add(c);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public List<Desarrolladora> listarDesarrolladorasPagina(int offset, int limit) {
        // SQL
        final String SQL = "SELECT * FROM desarrolladoras LIMIT ?,?";

        // Lista de Productos vacía
        List<Desarrolladora> lista = new ArrayList<>();

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, offset);
                ps.setInt(2, limit);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");

                        // Instanciar Producto
                        Desarrolladora d = new Desarrolladora(id, nombre);
                        // Producto > Lista
                        lista.add(d);
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return lista;
    }

    public long contarFavoritos(int id) {
        // SQL
        final String SQL = "SELECT * FROM favoritos WHERE usuario = ?";

        // Lista de Productos vacía
        long filas = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
//                        if (rs.next()) {
                            filas ++;
//                        } 
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return filas ;
    }

    public long contarBiblioteca(int id) {
        // SQL
        final String SQL = "SELECT * from productos WHERE id IN(SELECT producto FROM `compras` WHERE transaccion IN (SELECT id FROM transacciones WHERE usuario = ?))";
        // Lista de Productos vacía
        long filas = 0;

        // Obtención del Contexto
        try {
            // Contexto Inicial para operaciones de nombrado JNDI
            Context iniCtx = new InitialContext();

            // Contextualizar el contexto
            Context envCtx = (Context) iniCtx.lookup("java:/comp/env");

            // Acceso al recurso DataSource
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ohmygames");

            try (
                     Connection conn = ds.getConnection();  PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setInt(1, id);
                // Obtener Productos
                try ( ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Obtener Campos
//                        if (rs.next()) {
                            filas ++;
//                        }
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // Devolder la lista de Productos
        return filas;
    }

}
