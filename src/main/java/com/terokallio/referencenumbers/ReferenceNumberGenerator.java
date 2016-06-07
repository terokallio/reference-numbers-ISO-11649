package com.terokallio.referencenumbers;

import org.apache.commons.lang3.math.NumberUtils;

import java.security.InvalidAlgorithmParameterException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;


/**
 * Bank Reference Number Generator for Invoices
 *
 * Created by tero.kallio on 06/06/16.
 */
public final class ReferenceNumberGenerator {

    private ReferenceNumberGenerator() {
        // this is a static class and it makes no sense even try to instantiate this.
    }

    /**
     * Generates a valid reference numbers for a given base String,
     * by creating a valid control number for its last digit.
     *
     * For multiple reference numbers, the numerical value of base number is incremented by one for each
     * reference number created.
     *
     * The minimum length of a reference number is four characters (basis 3 characters + control number)
     * and the maximum length is 20 characters (19 + 1).
     *
     * @param base String reference number base
     * @param amount int amount of reference numbers
     * @return List<String> referencenumbers
     */
    public static List<String> generate(String base, int amount) throws InvalidAlgorithmParameterException {

        validateBaseFormat(base);

        if (Objects.isNull(amount)) {
            throw new InvalidAlgorithmParameterException("amount MUST be given");
        }

        long baseDigit = Long.parseLong(base);

        List<String> referenceNumbers = new ArrayList<String>();

        for (int i = 0; i < amount; i++) {
            referenceNumbers.add(String.valueOf(baseDigit) + calculateCheckSum(Long.toString(baseDigit)));
            baseDigit++;
        }

        return referenceNumbers;
    }

    private static int calculateCheckSum(String base) throws InvalidAlgorithmParameterException {

        Integer[] multipliers = new Integer[] { 7, 3, 1 };
        int multiplierIndex = 0;
        int sum = 0;

        // multiplication from right to left
        for (int i = base.length() - 1; i >= 0; i--) {

            if (multiplierIndex > 2) {
                multiplierIndex = 0;
            }

            char c = base.charAt(i);

            if (!Character.isDigit(c)) {
                throw new InvalidAlgorithmParameterException("base MUST contain only digits.");
            }

            int value = Character.getNumericValue(c);
            sum += value * multipliers[multiplierIndex];
            multiplierIndex++;
        }

        return calculateCheckDigit(sum);
    }

    private static int calculateCheckDigit(int sum) {
        int checkDigit;

        checkDigit = 10 - sum % 10;

        if (checkDigit == 10)
        {
            checkDigit = 0;
        }

        return checkDigit;
    }

    private static boolean validateBaseFormat(String base) throws InvalidAlgorithmParameterException {
        if (Objects.isNull(base) || base.trim().isEmpty()) {
            throw new InvalidAlgorithmParameterException("base length MUST be between 3 and 19 characters");
        }

        if (base.length() < 3 || base.length() > 19) {
            throw new InvalidAlgorithmParameterException("base length MUST be between 3 and 19 characters");
        }

        if (!NumberUtils.isNumber(base)) {
            throw new InvalidAlgorithmParameterException("base MUST be a number");
        }

        return true;
    }
}

