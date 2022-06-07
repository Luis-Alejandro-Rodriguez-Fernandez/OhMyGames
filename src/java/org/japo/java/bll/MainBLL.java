/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.bll;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.japo.java.dao.MainDAO;
import org.japo.java.entities.Carrito;
import org.japo.java.entities.Categoria;
import org.japo.java.entities.Compra;
import org.japo.java.entities.Desarrolladora;
import org.japo.java.entities.Favorito;
import org.japo.java.entities.Producto;
import org.japo.java.entities.Transaccion;
import org.japo.java.entities.Usuario;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class MainBLL {
    // Propiedades del Servidor
    // Propiedades del Servidor

    private final Properties prp;

    // Capa de Datos
    private final MainDAO dao;

    public MainBLL(Properties prp) {
        this.prp = prp;

        // Instanciar Capa de Datos
        dao = new MainDAO(prp);
    }

    public List<Producto> listarProductos() {
        return dao.listarProductos();
    }

    public Usuario obtenerUsuarioUs(String user) {
        return dao.obtenerUsuarioUs(user);
    }

    public boolean insertarUsuario(Usuario u) {
        return dao.insertarUsuario(u);
    }

    public List<Usuario> obtenerListaUsuario() {
        return dao.obtenerListaUsuario();
    }

    public List<Producto> listarProductosPagina(int offset, int limit, int cat, int des, int tipo, int min, int max) {
        return dao.listarProductosPagina(offset, limit, cat, des, tipo, min, max);
    }

    public Producto obtenerProducto(int i) {
        return dao.obtenerProducto(i);
    }

    public int contarProductos(int cat, int des, int tipo, double min, double max) {
        return dao.contarProcutos(cat, des, tipo, min, max);
    }

    public List<Producto> filtrarProductos(String name) {
        return dao.filtrarProductos(name);
    }

    public List<Categoria> listarCategorias() {
        return dao.listarCategorias();
    }

    public List<Desarrolladora> listarDesarrolladoras() {
        return dao.listarDesarrolladoras();
    }

    public List<Favorito> listarFavorito(int id, int offset, int limit) {
        return dao.listarFavoritos(id, offset, limit);
    }

    public Favorito obtenerFavorito(int userId, int prodId) {
        return dao.obtenerFavorito(userId, prodId);
    }

    public Carrito obtenerCarrito(int userId, int prodId) {
        return dao.obtenerCarrito(userId, prodId);
    }

    public boolean quitarFavorito(int prod, int user) {
        return dao.quitarFavorito(prod, user);
    }

    public boolean añadirFavorito(int prod, int user) {
        return dao.añadirFavorito(prod, user);
    }

    public boolean añadirCarrito(int prod, int user) {
        return dao.añadirCarrito(prod, user);
    }

    public Usuario obtenerUsuarioEmail(String email) {
        return dao.obtenerUsuarioEmail(email);
    }

    public List<Producto> obtenerProductosCarrito(int id) {
        return dao.obtenerProductosCarrito(id);
    }

    public List<Carrito> listarCarrito(int id) {
        return dao.listarCarrito(id);
    }

    public Producto obtenerProductoNombre(String nombre) {
        return dao.obtenerProductoNombre(nombre);
    }

    public boolean eliminarProductoCarrito(int userId, int prodId) {
        return dao.eliminarProductoCarrito(userId, prodId);
    }

    public boolean insertarTransaccion(int rnd, int user, Date d, double importe) {
        return dao.insertarTransaccion(rnd, user, d, importe);
    }

    public boolean insertarCompra(int id, int prod, double precio) {
        return dao.insertarCompra(id, prod, precio);
    }

    public void eliminarCarrito(int prod, int user) {
        dao.eliminarProductoCarrito(prod, user);
    }

    public boolean modificarUsuario(int id, String user, String email) {
        return dao.modificarUsuario(id, user, email);
    }

    public boolean modificarUsuarioPass(int id, String user, String email, String pass) {
        return dao.modificarUsuarioPass(id, user, email, pass);
    }

    public boolean insertarProducto(String nombre, double precio, int descuento, int tipo, int categoria, int desarrolladora, String descripcion, String img, String date) {
        return dao.insertarProducto(nombre, precio, descuento, tipo, categoria, desarrolladora, descripcion, img, date);
    }

    public List<Transaccion> listarTransacciones(int id) {
        return dao.listarTransacciones(id);
    }

    public List<Producto> listarComprasUsuario(int id, int offset, int limit) {
        return dao.listarComprasUsuario(id,offset,limit);
    }

    public List<Compra> listarCompras(int id) {
        return dao.listarCompras(id);
    }

    public boolean modificarProducto(int id, String nombre, double precio, int descuento, int tipo, int categoria, int desarrolladora, String descripcion, String img, String date) {
        return dao.modificarProducto(id, nombre, precio, descuento, tipo, categoria, desarrolladora, descripcion, img, date);
    }

    public List<Producto> listarProductosPaginadosNombre(int offset, int limit, String nombre) {
        return dao.listarProductosPaginadosNombre(offset, limit, nombre);
    }

    public int contarProductosNombre(String nombre) {
        return dao.contarProductosNombre(nombre);
    }

    public boolean modificarUsuarioImg(int id, String user, String email, String pass) {
        return dao.modificarUsuarioImg(id, user, email, pass);
    }

    public boolean modificarUsuarioAll(int id, String user, String email, String pass, String img) {
        return dao.modificarUsuarioAll(id, user, email, pass, img);
    }

    public boolean añadirCategoria(String nombre) {
        return dao.añadirCategoria(nombre);
    }

    public Categoria obtenerCategoria(int id) {
        return dao.obtenerCategorias(id);
    }

    public boolean modificarCategoria(int id, String nombre) {
        return dao.modificarCategoria(id, nombre);
    }

    public boolean borrarCategoria(int id) {
        return dao.borrarCategoria(id);
    }

    public Desarrolladora obtenerDesarrolladora(int id) {
        return dao.obtenerDesarrolladora(id);
    }

    public boolean añadirDesarolladora(String nombre) {
        return dao.añadirDesarrolladora(nombre);
    }

    public boolean modificarDesarrolladora(int id, String nombre) {
        return dao.modificarDesarrolladora(id, nombre);
    }

    public boolean borrarDesarrolladora(int id) {
        return dao.borrarDesarrolladora(id);
    }

    public long contarCategorias() {
        return dao.contarCategorias();
    }

    public long contarDesarrolladoras() {
        return dao.contarDesarrolladoras();
    }

    public List<Categoria> listarCategoriasPagina(int offset, int limit) {
        return dao.listarCategoriaPagina(offset,limit);
    }
    public List<Desarrolladora> listarDesarrolladorasPagina(int offset, int limit) {
        return dao.listarDesarrolladorasPagina(offset,limit);
    }

    public long contarFavoritos(int id) {
        return dao.contarFavoritos(id);
    }

    public long contarBiblioteca(int id) {
        return dao.contarBiblioteca(id);
    }

}
