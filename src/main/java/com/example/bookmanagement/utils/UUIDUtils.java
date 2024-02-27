package com.example.bookmanagement.utils;

import com.example.bookmanagement.exception.InvalidIdException;

import java.util.UUID;

public class UUIDUtils {

    public static UUID validateUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            throw new InvalidIdException("Id is invalid " + uuid);
        }
    }
}
