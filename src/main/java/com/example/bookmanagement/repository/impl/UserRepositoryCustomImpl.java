package com.example.bookmanagement.repository.impl;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.model.FilterParam;
import com.example.bookmanagement.model.UserFilterParam;
import com.example.bookmanagement.repository.BookRepositoryCustom;
import com.example.bookmanagement.repository.UserRepositoryCustom;
import com.example.bookmanagement.utils.PaginationUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(UserFilterParam filterParam) {
        Map<String, String> paramms = new HashMap<>();
        StringBuilder queryString = new StringBuilder(GET_ALL_QUERY);

        queryString.append(" ORDER BY u.id");
        queryString.append(" LIMIT :size");
        paramms.put("size", String.valueOf(filterParam.getSize()));
        queryString.append(" OFFSET :offset");
        paramms.put("offset",
                PaginationUtils.getOffset(filterParam.getPage(), filterParam.getSize()));

        Query query = entityManager.createQuery(queryString.toString(), User.class);

        for(Map.Entry<String, String> param : paramms.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        return query.getResultList();

    }
}
