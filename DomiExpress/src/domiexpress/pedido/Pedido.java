package domiexpress.pedido;

import domiexpress.entrega.TipoEntrega;
import domiexpress.productos.Producto;
import domiexpress.promociones.Promocion;
import domiexpress.usuarios.Cliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SRP: solo modela el pedido y calcula montos. No factura, no notifica, no guarda.
 * Polimorfismo: el total se calcula llamando calcularPrecio() sin conocer el tipo.
 */
public class Pedido {
  private final String id;
  private final Cliente cliente;
  private final TipoEntrega entrega;
  private final List<Producto> productos = new ArrayList<>();
  private final List<Promocion> promociones = new ArrayList<>();
  private EstadoPedido estado = EstadoPedido.CREADO;

  public Pedido(String id, Cliente cliente, TipoEntrega entrega) {
    this.id = id;
    this.cliente = cliente;
    this.entrega = entrega;
  }

  public void agregarProducto(Producto producto) {
    productos.add(producto);
  }

  public void aplicarPromocion(Promocion promocion) {
    promociones.add(promocion);
  }

  public void cambiarEstado(EstadoPedido nuevoEstado) {
    this.estado = nuevoEstado;
  }

  public double calcularSubtotal() {
    double subtotal = 0;
    for (Producto producto : productos) {
      subtotal += producto.calcularPrecio();
    }
    return subtotal;
  }

  public double calcularCostoEnvio() {
    return entrega.calcularCostoEnvio();
  }

  public double calcularDescuento() {
    double descuento = 0;
    for (Promocion promocion : promociones) {
      descuento += promocion.calcularDescuento(this);
    }
    return Math.min(descuento, calcularSubtotal() + calcularCostoEnvio());
  }

  public double calcularTotal() {
    return calcularSubtotal() + calcularCostoEnvio() - calcularDescuento();
  }

  public String getId() {
    return id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public TipoEntrega getEntrega() {
    return entrega;
  }

  public EstadoPedido getEstado() {
    return estado;
  }

  public List<Producto> getProductos() {
    return Collections.unmodifiableList(productos);
  }

  public List<Promocion> getPromociones() {
    return Collections.unmodifiableList(promociones);
  }
}
