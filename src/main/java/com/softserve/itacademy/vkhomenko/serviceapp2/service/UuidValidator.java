package com.softserve.itacademy.vkhomenko.serviceapp2.service;

import com.softserve.itacademy.vkhomenko.serviceapp2.exception.InvalidUuidException;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UuidValidator {

    public static boolean isValid(String uuid) {
        Pattern pattern = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");
        Matcher matcher = pattern.matcher(uuid);
        return matcher.matches();
    }

    public static UUID validAndGetUuid(String uuid) {
        if (!isValid(uuid)) {
            throw new InvalidUuidException("Invalid UUID");
        }

        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException("Invalid UUID");
        }
    }

}
