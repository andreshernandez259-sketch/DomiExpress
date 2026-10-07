package domiexpress.pruebas;

import domiexpress.entrega.Domicilio;
import domiexpress.entrega.RecogerEnTienda;
import domiexpress.notificaciones.Notificador;
import domiexpress.pago.PagoEfectivo;
import domiexpress.pago.PagoTarjeta;
import domiexpress.pago.PasarelaPago;
import domiexpress.pedido.EstadoPedido;
import domiexpress.pedido.Pedido;
import domiexpress.persistencia.RepositorioEnMemoria;
import domiexpress.productos.Bebida;
import domiexpress.productos.Comida;
import domiexpress.productos.TamanoBebida;
import domiexpress.promociones.PromoDosPorUno;
import domiexpress.servicio.ServicioPedidos;
import domiexpress.usuarios.Cliente;
import java.util.ArrayList;
import java.util.List;

/** Pruebas sin framework. Gracias a DIP se usa un Notificador falso que no envía nada real. */
public class PruebasServicio {
  private static int fallos = 0;

  /** Notificador falso: solo guarda los mensajes en una lista. */
  private static class NotificadorFalso implements Notificador {
    private final List<String> mensajes = new ArrayList<>();

    @Override
    public void notificar(String destinatario, String mensaje) {
      mensajes.add(destinatario + ": " + mensaje);
    }
  }

  public static void main(String[] args) {
    totalSeCalculaPorPolimorfismo();
    dosPorUnoDescuentaElMasBarato();
    pagoAprobadoCambiaEstadoYNotifica();
    pagoRechazadoNoCambiaEstado();
    domicilioExigeDireccion();
    System.out.println(fallos == 0 ? "Todas las pruebas pasaron" : "Fallaron " + fallos);
    if (fallos > 0) {
      System.exit(1);
    }
  }

  private static Pedido pedidoBase(String id) {
    Cliente cliente = new Cliente("Test", "test@correo.com");
    Pedido pedido = cliente.crearPedido(id, new RecogerEnTienda("Centro"));
    pedido.agregarProducto(new Comida("Pizza", 10_000));
    pedido.agregarProducto(new Bebida("Jugo", 5_000, TamanoBebida.PEQUENO));
    return pedido;
  }

  private static void totalSeCalculaPorPolimorfismo() {
    verificar(pedidoBase("T1").calcularTotal() == 15_000, "total polimórfico");
  }

  private static void dosPorUnoDescuentaElMasBarato() {
    Pedido pedido = pedidoBase("T2");
    pedido.agregarProducto(new Comida("Pasta", 6_000));
    pedido.aplicarPromocion(new PromoDosPorUno());
    verificar(pedido.calcularDescuento() == 6_000, "2x1 descuenta el más barato de cada pareja");
  }

  private static void pagoAprobadoCambiaEstadoYNotifica() {
    NotificadorFalso notificador = new NotificadorFalso();
    PasarelaPago pasarela = (metodo, monto) -> metodo.autorizar(monto);
    ServicioPedidos servicio =
        new ServicioPedidos(new RepositorioEnMemoria(), notificador, pasarela);
    Pedido pedido = pedidoBase("T3");
    servicio.registrar(pedido);
    boolean ok = servicio.pagar(pedido, new PagoEfectivo());
    verificar(ok && pedido.getEstado() == EstadoPedido.PAGADO, "pago aprobado cambia estado");
    verificar(notificador.mensajes.size() == 2, "se notificó creación y pago");
    verificar(servicio.buscar("T3").isPresent(), "el pedido quedó en el repositorio");
  }

  private static void pagoRechazadoNoCambiaEstado() {
    NotificadorFalso notificador = new NotificadorFalso();
    PasarelaPago pasarela = (metodo, monto) -> metodo.autorizar(monto);
    ServicioPedidos servicio =
        new ServicioPedidos(new RepositorioEnMemoria(), notificador, pasarela);
    Pedido pedido = pedidoBase("T4");
    boolean ok = servicio.pagar(pedido, new PagoTarjeta("0000", 100));
    verificar(!ok && pedido.getEstado() == EstadoPedido.CREADO, "pago rechazado no cambia estado");
  }

  private static void domicilioExigeDireccion() {
    try {
      new Domicilio(" ", 5_000);
      verificar(false, "domicilio sin dirección debe fallar");
    } catch (IllegalArgumentException esperado) {
      verificar(true, "domicilio sin dirección falla");
    }
  }

  private static void verificar(boolean condicion, String descripcion) {
    if (!condicion) {
      fallos++;
    }
    System.out.println((condicion ? "OK    " : "FALLO ") + descripcion);
  }
}
