# reference-numbers

A utility to create and calculate reference numbers to your invoices.

As the basis of the reference number you can use the customer number or the invoice number. 

The minimum length of a reference number is four characters (basis 3 characters + control number) and the maximum length is 20 characters (19 + 1).

## Usage

```java
  List<String> referenceNumbers = new ArrayList<String>();
  try {
    // base for refnum: 999111, amount: 1
    referenceNumbers = ReferenceNumberGenerator.generate("999111", 1);
  } catch (InvalidAlgorithmParameterException e) {
    // Do error handling. Input parameter error.
  }
```
#### Improvements
If you have a specific feature request or if you found a bug, please use GitHub issues. Fork these docs and send a pull request with improvements.

###### Fast, Reliable, and Tested

