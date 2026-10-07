package domiexpress.persistencia;

import domiexpress.pedido.Pedido;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositorioEnMemoria implements RepositorioPedidos {
  private final Map<String, Pedido> pedidos = new LinkedHashMap<>();

  @Override
  public void guardar(Pedido pedido) {
    pedidos.put(pedido.getId(), pedido);
  }

  @Override
  public Optional<Pedido> buscarPorId(String id) {
    return Optional.ofNullable(pedidos.get(id));
  }

  @Override
  public List<Pedido> listarTodos() {
    return new ArrayList<>(pedidos.values());
  }
}
