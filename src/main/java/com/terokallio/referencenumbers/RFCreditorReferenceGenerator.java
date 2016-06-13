package com.terokallio.referencenumbers;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * The new global Structured Creditor Reference (ISO 11649) may be used on cross-border invoices
 * and domestic invoices. The RF Creditor reference is based on the Finnish creditor reference.
 *
 * The structure of the RF Creditor reference is as follows: RFXX1234561, where
 * RF is the identifier of the RF Creditor reference, XX are the two check digits
 *
 * 1234561 is the Finnish creditor reference, which consists of numerical digits only and which
 * the creditor may create freely within the limits of the maximum reference length of 20 digits.
 * The last digit is a check digit calculated using the check digit algorithm for the Finnish creditor
 * reference (see the Federation of Finnish Financial Services publication Reference number
 * and check digit.)
 *
 * Created by tero.kallio on 10/06/16.
 */
public final class RFCreditorReferenceGenerator {

    private final static String RF = "RF";
    private final static String RF_NUM_VALUE = "2715";
    private final static String DOUBLE_ZERO = "00";
    private final static String ZERO = "0";

    private RFCreditorReferenceGenerator() {
        // it makes no sense to instantiate this.
    }

    /**
     * The new global Structured Creditor Reference (ISO 11649) may be used on cross-border invoices
     * and domestic invoices in Finland.
     *
     * The RF Creditor reference includes two check digits, which are calculated on the basis of the
     * Finnish creditor reference as follows:
     *
     * Add the number string “2715” (which corresponds to the letters ‘RF’) and “00” at the end of the
     * Finnish creditor reference.
     *
     * Calculate modulo 97, and subtract the remainder from 98 to obtain the check digits.
     *
     * Add the letters ‘RF’ and the check digits before the basic creditor reference element
     * to obtain the RF Creditor reference such as: RF332348236 (machine-readable format)
     *
     * @param base
     * @param amount
     * @return List<String> RFCreditorReferences;
     * @throws InvalidAlgorithmParameterException
     */
    public static List<String> generate(String base, int amount) throws InvalidAlgorithmParameterException {

        List<String> referenceNumbers = ReferenceNumberGenerator.generate(base, amount);
        List<String> rfCreditorReferences = new ArrayList<String>();

        for (int i = 0; i < referenceNumbers.size(); i++) {
            //Add the number string “2715” (which corresponds to the letters ‘RF’) and “00” at the
            //end of the Finnish creditor reference
            String RFRef = referenceNumbers.get(i) + RF_NUM_VALUE + DOUBLE_ZERO;
            rfCreditorReferences.add(i, RF + getCheckDigits(RFRef) + referenceNumbers.get(i));
        }
        return rfCreditorReferences;
    }

    /**
     * Calculate modulo 97, i.e. the remainder obtained when dividing the number RFRef by 97.
     * Subtract the remainder from 98 to obtain the check digits. If the difference is less
     * than ten, add a leading zero before it
     *
     * @param RFRef String
     * @return String checkDigits
     */
    private static String getCheckDigits(String RFRef) {

        BigInteger modulo =  new BigInteger(RFRef).remainder(BigInteger.valueOf(97L));

        int checkDigits =  98 - modulo.intValue();

        if (checkDigits < 10) {
            return ZERO + String.valueOf(checkDigits);
        }

        return String.valueOf(checkDigits);
    }
}
