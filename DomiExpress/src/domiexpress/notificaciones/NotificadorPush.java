package domiexpress.notificaciones;

public class NotificadorPush implements Notificador {
  @Override
  public void notificar(String destinatario, String mensaje) {
    System.out.printf("[PUSH -> %s] %s%n", destinatario, mensaje);
  }
}
