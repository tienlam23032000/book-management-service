package com.example.bookmanagement.controller.base;

import org.springframework.http.ResponseEntity;

public interface BaseController<T, C, P, L, ID> {

    ResponseEntity<T> getById(ID id);

    ResponseEntity<T> create(C t);

    ResponseEntity<T> update(ID id, T t);

    ResponseEntity<T> updatePartial(ID id, P partial);

    void delete(ID id);

}
