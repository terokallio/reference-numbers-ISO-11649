# reference-numbers

A utility to create and calculate reference numbers to your invoices.

As the basis of the reference number you can use the customer number or the invoice number. The only requirement is that it needs to be a String representation of a number.

The minimum length of a reference number is four characters (basis 3 characters + control number) and the maximum length is 20 characters (19 + 1).

## Usage (creates a single reference number)

```java
  List<String> referenceNumbers = new ArrayList<String>();
  try {
    referenceNumbers = ReferenceNumberGenerator.generate("999111", 1);
  } catch (InvalidAlgorithmParameterException e) {
    // Do error handling. Input parameter error.
  }
```
