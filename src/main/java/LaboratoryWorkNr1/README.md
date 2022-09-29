# Laboratory work nr 1, Clasical Ciphers

### Course: Cryptography & Security
### Author: Grosu Damian

----

## Theory
In cryptography, a cipher (or cypher) is an algorithm for performing encryption or decryption — a series of well-defined 
steps that can be followed as a procedure.
Some There are a variety of different types of encryption. Algorithms used earlier in the history 
of cryptography, which are called classical ciphers, are substantially different from modern methods, and modern ciphers can be classified
according to how they operate and whether they use one or two keys.
Some classical cipher which were implemented in this laboratory work are: Caesar substitution cipher,
Affine cipher, Vigenre cipher and Playfiar cipher.<br />
<br />
In cryptography, a substitution cipher is a method of encrypting in which units of plaintext are replaced with the 
ciphertext, in a defined manner, with the help of a key; the "units" may be single letters (the most common), pairs of 
letters, triplets of letters, mixtures of the above, and so forth. The receiver deciphers the text by performing the inverse 
substitution process to extract the original message.<br />
<br />
The affine cipher is a type of monoalphabetic substitution cipher, where each letter in an alphabet is mapped to its 
numeric equivalent, encrypted using a simple mathematical function, and converted back to a letter. The formula used means 
that each letter encrypts to one other letter, and back again, meaning the cipher is essentially a standard substitution 
cipher with a rule governing which letter goes to which. As such, it has the weaknesses of all substitution ciphers. 
Each letter is enciphered with the function (ax + b) mod 26, where b is the magnitude of the shift.<br />
<br />
The Vigenère cipher is a method of encrypting alphabetic text by using a series of interwoven Caesar ciphers, based on 
the letters of a keyword. It employs a form of polyalphabetic substitution.<br />
<br />
The Playfair cipher was the first practical digraph substitution cipher. The scheme was invented in 1854 by Charles 
Wheatstone but was named after Lord Playfair who promoted the use of the cipher. In playfair cipher unlike traditional 
cipher we encrypt a pair of alphabets(digraphs) instead of a single alphabet.



## Objectives:

* Get familiar with the basics of cryptography and classical ciphers.
* Implement 4 types of the classical ciphers:
    - Caesar cipher with one key used for substitution,
    - Affine cipher,
    - Vigenere cipher,
    - Playfair cipher.


## Implementation description

* Caesar Cipher with substitution<br/>

**Encryption** in this type of ciphers is realised really simple, basically we are shifting letters of the plaint text 
with a fixed number of places down the alphabet, and this number is our key of the encryption. So here in the code we are
firstly changing all letters in lower case, and after we are using formula, (key + position) % 26, to get the position
of shifted letter.
```
public String encrypt(String inputMessage) {

        inputMessage = inputMessage.toLowerCase(Locale.ROOT);
        StringBuilder encryptedMessage = new StringBuilder();

        for (Character letter : inputMessage.toCharArray()) {
            int pos = ALPHABET.indexOf(letter);
            int encryptedPos = (key + pos)%LETTERS_OF_ALPHABET;
            char encryptedLetter = ALPHABET.charAt(encryptedPos);

            encryptedMessage.append(encryptedLetter);
        }

        return encryptedMessage.toString().toUpperCase();
    }
```
For the **Decryption** we have basically the same, but process is inverted, we are shifting letters to the left, and here 
is a case if we will get a negative sign of the position, it will be added to alphabet length to get the positive position. 
```
public String decrypt(String inputMessage) {

        inputMessage = inputMessage.toLowerCase(Locale.ROOT);
        StringBuilder decryptedMessage = new StringBuilder();

        for (Character letter : inputMessage.toCharArray()) {
            int pos = ALPHABET.indexOf(letter);
            int decryptedPos = (pos-key)%LETTERS_OF_ALPHABET;

            if (decryptedPos < 0){
                decryptedPos = ALPHABET.length() + decryptedPos;
            }

            char decryptedLetter = ALPHABET.charAt(decryptedPos);
            decryptedMessage.append(decryptedLetter);
        }

        return decryptedMessage.toString().toUpperCase(Locale.ROOT);
    }
```
* Affine Cipher<br />

So here for **Encryption** of the Affine cipher we use modular arithmetic to transform the integer that each plaintext 
letter corresponds to into another integer that correspond to a ciphertext letter.
The encryption function for a single letter looks like this: E ( x ) = ( key1 x + key2 ) mod alphabet_size, and also
key 1 and 2 must be coprime, in code it looks like this:
```
public String encrypt(String message) {

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' '){
                ciphertext.append((char) ((((key1 * (message.charAt(i) - 'A')) + key2) % 26) + 'A'));
            } else {
                ciphertext.append(message.charAt(i));
            }
        }

        return ciphertext.toString();
    }
```
For the **Decryption** we must perform the opposite (or inverse) functions on the ciphertext to retrieve the plaintext. 
Once again, the first step is to convert each of the ciphertext letters into their integer values. The decryption function 
looks like this: D ( x ) = a ^ (-1) (x - key2) mod alphabet_size, a^-1 is the multiplicative inverse, to find, we need to 
find the number x such that the equation is true,  then x is the inverse of a. The easiest way to solve this equation is 
to search each of the numbers 1 to 25, and see which one satisfies the equation.
In the code it looks like this:
```
public String decrypt(String message) {

        StringBuilder encryptedmessage = new StringBuilder();
        int a_inv = 0;
        int flag = 0;

        for (int i = 0; i < 26; i++) {
            flag = (key1 * i) % 26;
            if (flag == 1) {
                a_inv = i;
            }
        }

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' ') {
                encryptedmessage.append( (char) (((a_inv * ((message.charAt(i) + 'A' - key2)) % 26)) + 'A') );
            } else {
                encryptedmessage.append(message.charAt(i));
            }
        }

        return encryptedmessage.toString();
    }
```
* Vigenere Cipher<br />

The **Encryption** of Vigenere cipher consists in a table which has the alphabet diagonally and vertically, and pairing
letters of plaintext and key we get a ciphertext. For the implementation in code I used a easiest way, I mean by converting 
[A-Z] into numbers and using this formula: Ei = (Pi + Ki) mod 26, in code it looks like tihs:
```
public String encrypt(String message) {

        StringBuilder encryptedMessage = new StringBuilder();
        message = message.toUpperCase(Locale.ROOT);

        for (int i = 0, j =0; i < message.length(); i++) {

            char letter = message.charAt(i);
            if (letter < 'A' || letter > 'Z')
                continue;
            encryptedMessage.append((char) ((letter + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }

        return encryptedMessage.toString();
    }
```
For **Decryption** is realised the inverse method, and the formula for this is - Di = ( Ei - Ki + 26) mod 26, code looks 
like this:
```
public String decrypt(String message) {

        StringBuilder decryptedMessage = new StringBuilder();
        message = message.toUpperCase(Locale.ROOT);

        for (int i = 0, j =0; i < message.length(); i++) {

            char letter = message.charAt(i);
            if (letter < 'A' || letter > 'Z')
                continue;
            decryptedMessage.append((char) ((letter - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }

        return decryptedMessage.toString();
    }
```
* Playfair Cipher <br />
  
The Playfair algorithm is based on the use of a 5 * 5 matrix of letters constructed using a keyword.
This matrix is filled by keyword letters from left to right and then filling the reminders places in the matrix with 
the remaining letters in alphabetic order. Letters I and J are considered one letter.<br />
So for the **Encryption** we are starting by creating a matrix, I am using set as data structures in order not to repeat 
the letters :
```
public String createMatrix(String key) {
        
        String matrixstr = key + alphabet;
        StringBuilder matrix = new StringBuilder();
        Set<Character> set = new LinkedHashSet<>();

        for (char letter : matrixstr.toLowerCase().toCharArray()) {
            set.add(letter);
        }

        for (Object letter : set){
            matrix.append(letter);
        }

        return matrix.toString();
    }
```
After we are  pairing the letters and formatting this means that message is divided 
into pairs of letters, if a pair has same letter, separate between then by x or if message has an odd number of letters 
we are adding an x to get all paired.<br />
For formatting we are using this method:
```
public String formatMessage(String message){

        message = message.toLowerCase().replace(" ", "");
        StringBuilder mes = new StringBuilder(message);

        for (int i = 0; i < message.length() - 1; i += 2) {
            if (mes.charAt(i) == mes.charAt(i + 1)) {
                mes.insert(i + 1, "x");
            }
        }
        if (mes.length() % 2 == 1) {
            mes.append("x");
        }

        return mes.toString();
    }
```
And for pairing this one:
```
public String[] divideInPairs(String message){

        message = formatMessage(message);
        String[] pairs = new String[message.length() / 2];
        int j = 0;

        for (int i = 0; i < message.length(); i = i + 2) {
            pairs[j] = message.substring(i, i + 2);
            j++;
        }

        return pairs;
    }
```
So in result two plaintext letters that fall in the same column are each replaced by the letter beneath, with the top 
element of the column circularly following the last, otherwise, each plaintext letter in a pair is replaced by the letter 
that lies in its own row and the column occupied by the other plaintext letter.
```
public String encrypt(String[] pairs, String matrix){

        StringBuilder cipherText = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char letter1;
            char letter2;

            if (col1 == col2) {
                letter2 = matrix.charAt(((row2 + 1) % 5 * 5 + col2));
                letter1 = matrix.charAt(((row1 + 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                letter1 = matrix.charAt(row1 * 5 + ((col1 + 1) % 5));
                letter2 = matrix.charAt(row2 * 5 + ((col2 + 1) % 5));
            } else {
                letter1 = matrix.charAt(row1 * 5 + col2);
                letter2 = matrix.charAt(row2 * 5 + col1);
            }
            cipherText.append(Character.toString(letter1)).append(Character.toString(letter2));
        }
        return cipherText.toString();
    }
```
For the **Decryption** we are pairing the cipher text same as we did with plain text, code is above, and we are using basically 
same rules as in encryption ,but inverse, it means that wo ciphertext letters that fall in the same row of the matrix are 
each replaced by the letter to the left, not to the right.
```
public String decrypt(String[] pairs, String matrix){

        StringBuilder cipherText = new StringBuilder();

        for (String pair : pairs) {
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);

            char letter1;
            char letter2;

            if (col1 == col2) {
                letter2 = matrix.charAt(((row2 - 1) % 5 * 5 + col2));
                letter1 = matrix.charAt(((row1 - 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                letter1 = matrix.charAt(row1 * 5 + ((col1 - 1) % 5));
                letter2 = matrix.charAt(row2 * 5 + ((col2 - 1) % 5));
            } else {
                letter1 = matrix.charAt(row1 * 5 + col2);
                letter2 = matrix.charAt(row2 * 5 + col1);
            }
            cipherText.append(Character.toString(letter1)).append(Character.toString(letter2));
        }
        return cipherText.toString();
    }
```

## Conclusions and Results
The results of all ciphers are in the screenshot below: <br />

![Results](D:\HW University\Year3\CS-LABS\src\main\java\LaboratoryWorkNr1\images\results.png)

In conclusion, this laboratory work was elaborated successful, during the work on lab, a lot of information was 
found to be able to implement all the ciphers. All objectives that were proposed have been achieved, encrypting and decrypting
in all ciphers were realised correct, which can be confirmed by results.
