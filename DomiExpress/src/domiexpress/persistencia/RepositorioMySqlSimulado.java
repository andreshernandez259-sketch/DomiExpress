package domiexpress.persistencia;

import domiexpress.pedido.Pedido;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Simula MySQL (bonus). Cambiarlo en Main es una sola línea. */
public class RepositorioMySqlSimulado implements RepositorioPedidos {
  private final Map<String, Pedido> tabla = new LinkedHashMap<>();

  @Override
  public void guardar(Pedido pedido) {
    String operacion = tabla.containsKey(pedido.getId()) ? "UPDATE" : "INSERT";
    tabla.put(pedido.getId(), pedido);
    System.out.printf("[MySQL] %s pedidos id=%s estado=%s%n",
        operacion, pedido.getId(), pedido.getEstado());
  }

  @Override
  public Optional<Pedido> buscarPorId(String id) {
    return Optional.ofNullable(tabla.get(id));
  }

  @Override
  public List<Pedido> listarTodos() {
    return new ArrayList<>(tabla.values());
  }
}
