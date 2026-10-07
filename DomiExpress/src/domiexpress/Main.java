package domiexpress;

import domiexpress.entrega.Domicilio;
import domiexpress.entrega.RecogerEnTienda;
import domiexpress.facturacion.GeneradorFactura;
import domiexpress.notificaciones.Notificador;
import domiexpress.notificaciones.NotificadorEmail;
import domiexpress.notificaciones.NotificadorSMS;
import domiexpress.pago.MetodoPago;
import domiexpress.pago.PagoNequi;
import domiexpress.pago.PagoTarjeta;
import domiexpress.pago.PasarelaPago;
import domiexpress.pago.PasarelaSimulada;
import domiexpress.pedido.EstadoPedido;
import domiexpress.pedido.Pedido;
import domiexpress.persistencia.RepositorioEnMemoria;
import domiexpress.persistencia.RepositorioMySqlSimulado;
import domiexpress.persistencia.RepositorioPedidos;
import domiexpress.productos.Bebida;
import domiexpress.productos.Combo;
import domiexpress.productos.Comida;
import domiexpress.productos.TamanoBebida;
import domiexpress.promociones.PromoCompuesta;
import domiexpress.promociones.PromoDosPorUno;
import domiexpress.promociones.PromoEnvioGratis;
import domiexpress.promociones.PromoPorcentaje;
import domiexpress.servicio.ServicioPedidos;
import domiexpress.usuarios.Cliente;
import domiexpress.usuarios.Repartidor;
import domiexpress.usuarios.Restaurante;
import java.util.List;

/** Demo. Es el único lugar (raíz de composición) donde se hace new de infraestructura. */
public class Main {
  private static final GeneradorFactura FACTURAS = new GeneradorFactura();

  public static void main(String[] args) {
    Restaurante restaurante = new Restaurante("La Parrilla");
    Repartidor repartidor = new Repartidor("Juan");
    Cliente camila = new Cliente("Camila", "camila@correo.com");
    Cliente diego = new Cliente("Diego", "3001234567");

    Comida hamburguesa = new Comida("Hamburguesa", 18_000);
    Comida papas = new Comida("Papas", 7_000);
    Bebida gaseosa = new Bebida("Gaseosa", 4_500, TamanoBebida.MEDIANO);
    Bebida limonada = new Bebida("Limonada", 6_000, TamanoBebida.GRANDE);
    Combo combo = new Combo("Combo Clásico", List.of(hamburguesa, papas, gaseosa), 0.15);
    restaurante.publicar(hamburguesa);
    restaurante.publicar(limonada);
    restaurante.publicar(combo);

    // Escenario 1: Domicilio + promoción + pago rechazado y luego cambio de medio de pago.
    titulo("Escenario 1: domicilio, promoción y cambio de medio de pago");
    RepositorioPedidos repositorio = new RepositorioEnMemoria();
    Notificador email = new NotificadorEmail();
    PasarelaPago pasarela = new PasarelaSimulada();
    ServicioPedidos servicio = new ServicioPedidos(repositorio, email, pasarela);

    Pedido pedido1 = camila.crearPedido("P-001", new Domicilio("Calle 10 #20-30", 5_000));
    pedido1.agregarProducto(hamburguesa);
    pedido1.agregarProducto(limonada);
    pedido1.agregarProducto(combo);
    pedido1.aplicarPromocion(new PromoPorcentaje(10));
    servicio.registrar(pedido1);
    System.out.println(FACTURAS.generar(pedido1));

    MetodoPago tarjeta = new PagoTarjeta("4417", 20_000);
    if (!servicio.pagar(pedido1, tarjeta)) {
      servicio.pagar(pedido1, new PagoNequi("3001112233", 200_000));
    }
    restaurante.prepararPedido(pedido1);
    servicio.cambiarEstado(pedido1, EstadoPedido.EN_PREPARACION);
    servicio.cambiarEstado(pedido1, EstadoPedido.EN_CAMINO);
    repartidor.entregar(pedido1);
    servicio.cambiarEstado(pedido1, EstadoPedido.ENTREGADO);

    // Escenario 2: Recoger en tienda, 2x1, y se cambian repositorio y notificador.
    titulo("Escenario 2: recoger en tienda, 2x1, otro repositorio y otro canal");
    RepositorioPedidos repositorioSql = new RepositorioMySqlSimulado(); // <- una línea
    Notificador sms = new NotificadorSMS();                             // <- una línea
    ServicioPedidos servicioSql = new ServicioPedidos(repositorioSql, sms, pasarela);

    Pedido pedido2 = diego.crearPedido("P-002", new RecogerEnTienda("Centro"));
    pedido2.agregarProducto(hamburguesa);
    pedido2.agregarProducto(papas);
    pedido2.agregarProducto(limonada);
    pedido2.aplicarPromocion(new PromoDosPorUno());
    servicioSql.registrar(pedido2);
    System.out.println(FACTURAS.generar(pedido2));
    servicioSql.pagar(pedido2, new PagoNequi("3009998877", 100_000));

    // Escenario 3: extensibilidad. PagoPuntos y la promo compuesta se agregan sin editar
    // ninguna clase existente (Pedido, ServicioPedidos, MetodoPago, Promocion).
    titulo("Escenario 3: medio de pago y promoción nuevos sin tocar lo existente");
    Pedido pedido3 = camila.crearPedido("P-003", new Domicilio("Carrera 5 #8-12", 6_000));
    pedido3.agregarProducto(combo);
    pedido3.agregarProducto(limonada);
    pedido3.aplicarPromocion(
        new PromoCompuesta(List.of(new PromoPorcentaje(5), new PromoEnvioGratis())));
    servicio.registrar(pedido3);
    System.out.println(FACTURAS.generar(pedido3));
    servicio.pagar(pedido3, new PagoPuntos(50_000));

    System.out.println("\nPedidos guardados: " + repositorio.listarTodos().size());
  }

  private static void titulo(String texto) {
    System.out.println("\n##### " + texto + " #####");
  }

  /** Medio de pago nuevo, definido sin modificar ninguna clase existente. */
  private static class PagoPuntos implements MetodoPago {
    private final double puntos;

    PagoPuntos(double puntos) {
      this.puntos = puntos;
    }

    @Override
    public String getNombre() {
      return "Puntos DomiExpress";
    }

    @Override
    public boolean autorizar(double monto) {
      return monto <= puntos;
    }
  }
}
