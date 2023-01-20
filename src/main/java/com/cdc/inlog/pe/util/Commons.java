package com.cdc.inlog.pe.util;

import static com.cdc.inlog.pe.util.Constants.CHARACTER_SPACE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;

public class Commons {

    private Commons() {

    }

    /*public static String getFullName(String firstName, String lastName, String secondLastName) {
        char[] characters = firstName.toLowerCase().toCharArray();
        for (int i = NUMBER_ZERO; i < firstName.length() - NUMBER_TWO; i++) {
            if (i == NUMBER_ZERO) {
                characters[i] = Character.toUpperCase(characters[i]);
            }
            if (characters[i] == ' ' || characters[i] == '.' || characters[i] == ',') {
                characters[i + NUMBER_ONE] = Character.toUpperCase(characters[i + NUMBER_ONE]);
            }
        }
        String newName = new String(characters);
        return secondLastName == null || secondLastName.isEmpty() ?
                newName.concat(CHARACTER_SPACE).concat(lastName) :
                newName.concat(CHARACTER_SPACE).concat(lastName)
                        .concat(CHARACTER_SPACE).concat(secondLastName);
    }*/

}