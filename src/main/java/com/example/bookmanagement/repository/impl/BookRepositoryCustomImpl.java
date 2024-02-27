package com.example.bookmanagement.repository.impl;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.model.FilterParam;
import com.example.bookmanagement.repository.BookRepositoryCustom;
import com.example.bookmanagement.utils.PaginationUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll(FilterParam filterParam) {
        Map<String, String> paramms = new HashMap<>();
        StringBuilder queryString = new StringBuilder(GET_ALL_QUERY);

        /*
         * SELECT * FROM book b
         * WHERE 1 = 1 AND b.author LIKE :author
         * ORDER BY b.id
         * LIMIT 10
         * OFFSET 1;
         */

        if(filterParam.getAuthor() != null && filterParam.getAuthor().isPresent()) {
            queryString.append(" AND b.author LIKE :author");
            paramms.put("author", "%" + filterParam.getAuthor().get() + "%");
        }
        queryString.append(" ORDER BY b.id");
        queryString.append(" LIMIT :size");
        paramms.put("size", String.valueOf(filterParam.getSize()));
        queryString.append(" OFFSET :offset");
        paramms.put("offset",
                PaginationUtils.getOffset(filterParam.getPage(), filterParam.getSize()));

        Query query = entityManager.createQuery(queryString.toString(), Book.class);

        for(Map.Entry<String, String> param : paramms.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        return query.getResultList();

    }
}
