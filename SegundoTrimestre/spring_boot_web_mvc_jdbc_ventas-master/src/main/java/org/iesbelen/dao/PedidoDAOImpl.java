package org.iesbelen.dao;

import java.util.List;

import org.iesbelen.modelo.Pedido;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class PedidoDAOImpl implements PedidoDAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pedido> getAll() {
//        List<Pedido> listPedido = jdbcTemplate.query(
//                "SELECT * FROM pedido",
//                (rs, rowNum) -> new Pedido(rs.getInt("id"),
//                        rs.getDouble("total"),
//                        rs.getDate("fecha").toLocalDate(),
//                        rs.getLong("id_cliente"),
//                        rs.getInt("id_comercial"))
//        );

        List<Pedido> listPedido = jdbcTemplate.query("SELECT * FROM pedido",
                (rs, rowNum) -> Pedido.builder()
                        .id(rs.getInt("id"))
                        .total(rs.getInt("total"))
                        .fecha(rs.getDate("fecha").toLocalDate())
                        .id_cliente(rs.getLong("id_cliente"))
                        .id_comercial(rs.getInt("id_comercial"))
                        .build());

        log.info("Devueltos {} registros.", listPedido.size());

        return listPedido;
    }

    @Override
    public List<Pedido> getAllByComercial(int idComercial) {
//        List<Pedido> listPedido = jdbcTemplate.query(
//                "SELECT * FROM pedido WHERE id_comercial = ?",
//                (rs, rowNum) -> new Pedido(rs.getInt("id"),
//                        rs.getDouble("total"),
//                        rs.getDate("fecha").toLocalDate(),
//                        rs.getLong("id_cliente"),
//                        rs.getInt("id_comercial"))
//                , idComercial
//        );

        List<Pedido> listPedido = jdbcTemplate.query("SELECT * FROM pedido WHERE id_comercial = ?",
                (rs, rowNum) -> Pedido.builder()
                        .id(rs.getInt("id"))
                        .total(rs.getDouble("total"))
                        .fecha(rs.getDate("fecha").toLocalDate())
                        .id_cliente(rs.getLong("id_cliente"))
                        .id_comercial(rs.getInt("id_comercial"))
                        .build()
        , idComercial);

        log.info("Devueltos {} pedidos del comercial con id {}.", listPedido.size(), idComercial);

        return listPedido;
    }

    @Override
    public List<Pedido> getAllByCliente(int idCliente) {

//        List<Pedido> listPedido = jdbcTemplate.query(
//                "SELECT * FROM pedido WHERE id_cliente = ?",
//                (rs, rowNum) -> new Pedido(rs.getInt("id"),
//                        rs.getDouble("total"),
//                        rs.getDate("fecha").toLocalDate(),
//                        rs.getLong("id_cliente"),
//                        rs.getInt("id_comercial"))
//                , idCliente
//        );

        List<Pedido> listPedido = jdbcTemplate.query("SELECT * FROM pedido WHERE id_cliente = ?",
                (rs, rowNum) -> Pedido.builder()
                        .id(rs.getInt("id"))
                        .total(rs.getDouble("total"))
                        .fecha(rs.getDate("fecha").toLocalDate())
                        .id_cliente(rs.getLong("id_cliente"))
                        .id_comercial(rs.getInt("id_comercial"))
                        .build()
        , idCliente);

        log.info("Devueltos {} pedidos del cliente con id {}.", listPedido.size(), idCliente);

        return listPedido;
    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        log.info("Delete de Pedido con {} registros eliminados.", rows);
    }

    @Override
    public int getTotalPedidos() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM pedido", Integer.class);
    }
}