# reference-numbers

A algorithm to create and calculate reference numbers to your invoices.

Implementation of both domestic (ISO/IEC 7064 (MOD 97-10) and International cross border references (ISO 11649).

As the basis of the reference number you can use the customer number or the invoice number. 

The minimum length of a reference number is four characters (basis 3 characters + control number) and the maximum length is 20 characters (19 + 1).

## RF Creditor references (International, cross-border payments)

```java
  List<String> referenceNumbers = new ArrayList<String>();
  try {
    // base for refnum: 999111, amount: 1
    referenceNumbers = RFCreditorReferenceGenerator.generate("999111", 1);
  } catch (InvalidAlgorithmParameterException e) {
    // Do error handling. Input parameter error.
  }
  // this will return reference number: 9991110
```

## Creditor references (Finland, domestic)

```java
  List<String> referenceNumbers = new ArrayList<String>();
  try {
    // base for refnum: 999111, amount: 1
    referenceNumbers = ReferenceNumberGenerator.generate("999111", 1);
  } catch (InvalidAlgorithmParameterException e) {
    // Do error handling. Input parameter error.
  }
  // this will return RF Creditor reference number: RF419991110
```

## Details

Banks approve and validate both RF Creditor references and Finnish creditor references in all
payment channels where the payer or the bank’s employee may manually enter payments. Thus
any typing mistakes may be corrected at once.

If the payment is transferred between banks in the domestic payment system, the first four (4)
characters of the RF Creditor reference are omitted and the basic domestic reference element
only is forwarded with the payment. If the check digit is not correct, the reference is forwarded
in the message field in the domestic payment system.

If the payment is transferred as a cross-border payment, i.e. in the SWIFT system using an
MT103+ message, the RF Creditor reference is forwarded, unchanged, in field 70.

## Structure of the global Structured Creditor Reference (ISO 11649) and calculation of the check digits

A creditor reference allows a company to identify customer invoices. Payments with a creditor reference are forwarded to the beneficiary in accordance with what has been agreed on the
remittance of reference payments.
The Finnish creditor reference (see the Federation of Finnish Financial Services publication Reference number and check digit) is used in domestic payment traffic.
The new global Structured Creditor Reference (ISO 11649, hereinafter ’RF Creditor reference’) may be used on cross-border invoices and domestic invoices in Finland as of 1 December 2010.

### The RF Creditor reference is based on the Finnish creditor reference

In Finland, the RF Creditor reference is based on the Finnish creditor reference
The structure of the RF Creditor reference is as follows:
RFXX1234561, where
• RF is the identifier of the RF Creditor reference
• XX are the two check digits
1234561 is the Finnish creditor reference, which consists of numerical digits only and which
the creditor may create freely within the limits of the maximum reference length of 20 digits.
The last digit is a check digit calculated using the check digit algorithm for the Finnish creditor
reference (see the Federation of Finnish Financial Services publication publication Reference
number and check digit.)

### A short creditor reference is the best choice
In order to avoid typing errors, a creditor reference should be short. The purpose of the check
digits is to prevent erroneous data from being recorded.

#### Improvements
If you have a specific feature request or if you found a bug, please use GitHub issues. Fork these docs and send a pull request with improvements.

###### Fast, Robust, and Tested

