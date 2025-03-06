package com.example.examenjulianjpa.impl;

import com.example.examenjulianjpa.domain.Product;
import com.example.examenjulianjpa.repository.ProductCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductCustomRespositoryImpl implements ProductCustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Product> findByColVal(String[] colVal) {
        String[] campos = {"name", "descrip", "image", "price", "quantity", "id_category"};
        List<String> camposList = Arrays.asList(campos);

        if (colVal[0] == null || !camposList.contains(colVal[0])) {
            colVal[0] = "name";
        }

        String col = colVal[0];
        String val = colVal[1];

        String queryString = "SELECT p FROM Product p WHERE p." + col + " LIKE :value";

        Query query = em.createQuery(queryString);
        query.setParameter("value", "%" + val + "%");

        return query.getResultList();
    }

    @Override
    public List<Product> ordenar(String[] colSentido) {
        String[] ordenes = {"name", "price"};
        List<String> ordenesList = Arrays.asList(ordenes);
        String[] sentido = {"asc", "desc"};
        List<String> sentidosList = Arrays.asList(sentido);

        if (colSentido[0] == null || !ordenesList.contains(colSentido[0])) {
            colSentido[0] = "name";
        }

        if (colSentido[1] == null || !sentidosList.contains(colSentido[1])) {
            colSentido[1] = "asc";
        }

        String col = colSentido[0];
        String val = colSentido[1];

        String queryString = "SELECT p FROM Product p ORDER BY p." + col + " " + val;

        Query query = em.createQuery(queryString);

        return query.getResultList();
    }
}
