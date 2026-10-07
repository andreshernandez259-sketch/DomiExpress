package domiexpress.notificaciones;

/** DIP: el sistema de pedidos no conoce el canal concreto. */
public interface Notificador {
  void notificar(String destinatario, String mensaje);
}
