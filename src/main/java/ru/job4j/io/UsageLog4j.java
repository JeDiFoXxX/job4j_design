package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte valueByte = 100;
        short valueShort = 123;
        int valueInt = 2127371;
        long valueLong = 996578354899455L;
        float valueFloat = 3.11231F;
        double valueDouble = 2.7;
        char valueChar = 'X';
        boolean valueBoolean = false;

        LOG.debug("byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, char: {}, boolean: {}",
                valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueChar, valueBoolean);
    }
}