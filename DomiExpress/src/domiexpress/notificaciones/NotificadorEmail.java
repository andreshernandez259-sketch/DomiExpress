package domiexpress.notificaciones;

public class NotificadorEmail implements Notificador {
  @Override
  public void notificar(String destinatario, String mensaje) {
    System.out.printf("[EMAIL -> %s] %s%n", destinatario, mensaje);
  }
}
