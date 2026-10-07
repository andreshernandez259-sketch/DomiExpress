package domiexpress.servicio;

import domiexpress.notificaciones.Notificador;
import domiexpress.pago.MetodoPago;
import domiexpress.pago.PasarelaPago;
import domiexpress.pedido.EstadoPedido;
import domiexpress.pedido.Pedido;
import domiexpress.persistencia.RepositorioPedidos;
import java.util.Optional;

/** DIP: recibe todo por constructor y como interfaces; nunca hace new de infraestructura. */
public class ServicioPedidos {
  private final RepositorioPedidos repositorio;
  private final Notificador notificador;
  private final PasarelaPago pasarela;

  public ServicioPedidos(
      RepositorioPedidos repositorio, Notificador notificador, PasarelaPago pasarela) {
    this.repositorio = repositorio;
    this.notificador = notificador;
    this.pasarela = pasarela;
  }

  public void registrar(Pedido pedido) {
    repositorio.guardar(pedido);
    avisarEstado(pedido);
  }

  /** Devuelve true si el pago fue aprobado. Se puede reintentar con otro medio. */
  public boolean pagar(Pedido pedido, MetodoPago metodo) {
    boolean aprobado = pasarela.cobrar(metodo, pedido.calcularTotal());
    if (aprobado) {
      cambiarEstado(pedido, EstadoPedido.PAGADO);
    } else {
      notificador.notificar(pedido.getCliente().getContacto(),
          "No pudimos cobrar el pedido " + pedido.getId() + " con " + metodo.getNombre());
    }
    return aprobado;
  }

  public void cambiarEstado(Pedido pedido, EstadoPedido nuevoEstado) {
    pedido.cambiarEstado(nuevoEstado);
    repositorio.guardar(pedido);
    avisarEstado(pedido);
  }

  public Optional<Pedido> buscar(String id) {
    return repositorio.buscarPorId(id);
  }

  private void avisarEstado(Pedido pedido) {
    notificador.notificar(pedido.getCliente().getContacto(),
        "Tu pedido " + pedido.getId() + " " + pedido.getEstado().getMensaje());
  }
}
