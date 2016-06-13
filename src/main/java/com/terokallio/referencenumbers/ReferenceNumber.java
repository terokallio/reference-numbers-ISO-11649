package com.terokallio.referencenumbers;

import org.apache.commons.lang3.math.NumberUtils;

import java.security.InvalidAlgorithmParameterException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;


/**
 * Bank Reference Number Generator
 *
 * Created by tero.kallio on 06/06/16.
 */
public final class ReferenceNumber {

    private ReferenceNumber() {
        // it makes no sense to instantiate this.
    }

    /**
     * The Finnish creditor reference is used in domestic payment traffic.
     * (See {@link RFCreditorReference} for cross-border reference numbers)
     *
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
     * @return List<String> reference numbers
     */
    public static List<String> generate(String base, int amount) throws InvalidAlgorithmParameterException {

        validateBaseFormat(base);

        long baseDigit = Long.parseLong(base);

        List<String> referenceNumbers = new ArrayList<String>();

        for (int i = 0; i < amount; i++) {
            referenceNumbers.add(String.valueOf(baseDigit) + calculateCheckSum(Long.toString(baseDigit)));
            baseDigit++;
        }

        return referenceNumbers;
    }

    private static int calculateCheckSum(String base) {

        Integer[] multipliers = new Integer[] { 7, 3, 1 };
        int multiplierIndex = 0;
        int sum = 0;

        // multiplication from right to left
        for (int i = base.length() - 1; i >= 0; i--) {

            if (multiplierIndex > 2) {
                multiplierIndex = 0;
            }

            int value = Character.getNumericValue(base.charAt(i));
            sum += value * multipliers[multiplierIndex];
            multiplierIndex++;
        }

        return calculateCheckDigit(sum);
    }

    private static int calculateCheckDigit(int sum) {

        int checkDigit = 10 - sum % 10;

        if (checkDigit == 10)
        {
            checkDigit = 0;
        }

        return checkDigit;
    }

    private static void validateBaseFormat(String base) throws InvalidAlgorithmParameterException {
        if (Objects.isNull(base) || base.trim().isEmpty()) {
            throw new InvalidAlgorithmParameterException("base length MUST be between 3 and 19 characters");
        }

        if (base.length() < 3 || base.length() > 19) {
            throw new InvalidAlgorithmParameterException("base length MUST be between 3 and 19 characters");
        }

        if (!NumberUtils.isNumber(base)) {
            throw new InvalidAlgorithmParameterException("base MUST be a number");
        }
    }
}

