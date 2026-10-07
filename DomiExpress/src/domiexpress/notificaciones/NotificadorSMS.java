package domiexpress.notificaciones;

public class NotificadorSMS implements Notificador {
  @Override
  public void notificar(String destinatario, String mensaje) {
    System.out.printf("[SMS -> %s] %s%n", destinatario, mensaje);
  }
}
