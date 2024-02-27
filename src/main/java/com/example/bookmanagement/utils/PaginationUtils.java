package com.example.bookmanagement.utils;

import com.example.bookmanagement.exception.InvalidParamException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class PaginationUtils {
    public static String getOffset(int page, int size) {
        if(page == 1) {
            return "0";
        } else {
            return String.valueOf(page * size - size);
        }
    }

    public static void validatePageAndSize(int page, int size) {
        if(page <= 0) {
            throw new InvalidParamException("page is invalid " + page);
        }
        if(size <= 0) {
            throw new InvalidParamException("size is invalid " + page);
        }
    }
}
