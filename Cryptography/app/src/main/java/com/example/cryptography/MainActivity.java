package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/* This program generates a Cryptography convertor APP, (that can be used on android applications),
 * and allows for the user to convert texts between 3 different types of cryptography (Scytale, Caesar, Vigenere).
 *
 * The first edit text box is where the user types in their message, then the user selects the type of cipher...
 * and then enters in the respective value needed for conversion. Afterwards, encrypt of decrypt is selected.
 *
 * The final result comes out in the bottom text view.
 *
 * The 3 different types of cryptography are displayed as radio buttons for users to select the version before converting
 * Additionally there's a convertor button beneath the radio buttons (encrypt or decrypt)
 *
 * Helen Wang
 * March 17, 2022
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // set initial values
    RadioButton scytale;
    RadioButton caesar;
    RadioButton vigenere;
    RadioGroup unselect;

    Button encrypt;
    Button decrypt;
    EditText enterMessage;
    TextView getMessage;

    // store given values
    int columnValue;
    int shiftValue;
    String keyValue;
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // components from activity_main.xml
        scytale = this.findViewById(R.id.button1);
        scytale.setOnClickListener(this);
        caesar = this.findViewById(R.id.button2);
        caesar.setOnClickListener(this);
        vigenere = this.findViewById(R.id.button3);
        vigenere.setOnClickListener(this);

        unselect = this.findViewById(R.id.rg1);
        unselect.setOnClickListener(this);

        encrypt = this.findViewById(R.id.button4);
        encrypt.setOnClickListener(this);
        encrypt.setEnabled(true);
        decrypt = this.findViewById(R.id.button5);
        decrypt.setOnClickListener(this);
        decrypt.setEnabled(true);

        enterMessage = this.findViewById(R.id.editText1);
        enterMessage.setHint("Enter message here");

        // makes keyboard go down after entering in a value
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(enterMessage.getWindowToken(), 0);

        getMessage = this.findViewById(R.id.textView3);
        getMessage.setHint("Message will come out here");

    }


    @Override
    // when buttons are clicked

    public void onClick(View v) {

        // ENCRYPT
        if (v.equals(encrypt)) {

            if (scytale.isChecked()) {  // if scytale is selected
                // encrypt using scytale
                EditText enterMessage = findViewById(R.id.editText1);
                String messageBox = enterMessage.getText().toString();
                // get rid of spaces
                messageBox = messageBox.replaceAll("\\s", "");
                System.out.println("get rid of spaces: " + messageBox);

                getMessage.setText(encryptScytale(messageBox, columnValue));

            } else if (caesar.isChecked()) {    // if caesar is selected
                // encrypt using caesar
                EditText enterMessage = findViewById(R.id.editText1);
                String messageBox = enterMessage.getText().toString();
                messageBox = messageBox.toUpperCase();
                // get rid of spaces
                messageBox = messageBox.replaceAll("\\s", "");
                System.out.println("get rid of spaces: " + messageBox);

                getMessage.setText(encryptCaesar(messageBox, shiftValue));

            } else if (vigenere.isChecked()) {  // if vigenere is selected
                // encrypt using vigenere
                EditText enterMessage = findViewById(R.id.editText1);
                String messageBox = enterMessage.getText().toString();
                // get rid of spaces
                messageBox = messageBox.replaceAll("\\s", "");
                System.out.println("get rid of spaces: " + messageBox);

                getMessage.setText(encryptVigenere(messageBox, keyValue));
            }

        }

        // DECRYPT
        else if (v.equals(decrypt)) { // if user wants to decrypt the message

            if (scytale.isChecked()) {  // if scytale is selected
                // decrypt using scytale
                EditText enterMessage = findViewById(R.id.editText1);
                String messageBox = enterMessage.getText().toString();
                messageBox = messageBox.toUpperCase();
                // get rid of spaces
                messageBox = messageBox.replaceAll("\\s", "");
                System.out.println("get rid of spaces: " + messageBox);

                getMessage.setText(decryptScytale(messageBox, columnValue));

            } else if (caesar.isChecked()) {    // if caesar is selected
                // decrypt using caesar
                EditText enterMessage = findViewById(R.id.editText1);
                String messageBox = enterMessage.getText().toString();
                messageBox = messageBox.toUpperCase();
                // get rid of spaces
                messageBox = messageBox.replaceAll("\\s", "");
                System.out.println("get rid of spaces: " + messageBox);

                getMessage.setText(decryptCaesar(messageBox, shiftValue));

            } else if (vigenere.isChecked()) {  // if vigenere is selected
                // decrypt using vigenere
                EditText enterMessage = findViewById(R.id.editText1);
                String messageBox = enterMessage.getText().toString();
                messageBox = messageBox.toUpperCase();
                // get rid of spaces
                messageBox = messageBox.replaceAll("\\s", "");
                System.out.println("get rid of spaces: " + messageBox);

                getMessage.setText(decryptVigenere(messageBox, keyValue));
            }
        }


        if (v.equals(scytale)) {    // if user selected scytale

            // build an alert for # of columns
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Columns");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("How many columns?");
            alertDialogBuilder
                    .setMessage("Please input how many columns you want...")
                    .setView(input)
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                        dialog.dismiss();
                        String value = String.valueOf(input.getText());
                        columnValue = Integer.parseInt(value);
                        System.out.println("scytale column value set to: " + columnValue);
                    });
            alertDialogBuilder
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (v.equals(caesar)) {    // if user selected caesar

            // build an alert for # it'll shift by
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Shift");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("By how many letters do you want to offset your message?");
            alertDialogBuilder
                    .setMessage("Please input how much you want to shift by...")
                    .setView(input)
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                        dialog.dismiss();
                        String value = String.valueOf(input.getText());
                        shiftValue = Integer.parseInt(value);
                        System.out.println("caesar offset set to: " + shiftValue);
                    });
            alertDialogBuilder
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (v.equals(vigenere)) {    // if user selected vigenere

            // build an alert for the key
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Key");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("What do you want the key to be?");
            alertDialogBuilder
                    .setMessage("Please input the key value...")
                    .setView(input)
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                        dialog.dismiss();
                        keyValue = (String.valueOf(input.getText()));
                        System.out.println("vigenere key set to: " + keyValue);
                    });
            alertDialogBuilder
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    // encode scytale: https://codereview.stackexchange.com/questions/249051/implementation-of-scytale-cipher-encryption-and-decryption
    public static String encryptScytale(String inputText, int numOfRows) {
        int textLength = inputText.length();
        String givenText = "";

        inputText = inputText.toUpperCase();

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; i + j < textLength; j += numOfRows) {
                givenText += String.valueOf(inputText.charAt(i + j));
            }
        }
        System.out.println("printer" + givenText);
        return givenText;
    }

    // decrypt scytale
    public static String decryptScytale(String inputText, int shift) {
        String givenText = "";
        givenText = givenText.toUpperCase();

        // every "_"th letter starting as the first letter

        for (int i = 0; i < shift; i++) {
            for (int j = i; j < inputText.length(); j += shift) {
                givenText += inputText.charAt(j);
            }

        }
        return givenText;
    }

    // encrypt caesar
    private static StringBuilder encryptCaesar(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'A';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('A' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result;
    }

    // decrypt caesar
    public static String decryptCaesar(String cipherText, int shiftKey) {
        cipherText = cipherText.toUpperCase();
        String message = "";
        for (int ii = 0; ii < cipherText.length(); ii++) {
            int charPosition = ALPHABET.indexOf(cipherText.charAt(ii));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0) {
                keyVal = ALPHABET.length() + keyVal;
            }
            char replaceVal = ALPHABET.charAt(keyVal);
            message += replaceVal;
        }
        return message;
    }

    // encrypt vigenere
    public static String encryptVigenere(String str, String keyword) {
        //convert to capital letters
        keyword = keyword.toUpperCase();
        str = str.toUpperCase();

        String result = "";
        int offset = 0;
        //repeat the keyword until you can't anymore
        //ex:
        // hello keyword: hi
        // hihih
        String repeatedKeyWord = "";
        int number = str.length() / keyword.length() + 1;
        for (int i = 0; i < number; i++) {
            repeatedKeyWord = repeatedKeyWord + keyword;
        }

        //convert input to char array
        char[] strArray = str.toCharArray();
        // use ASCII
        // starts at A (65)
        for (int i = 0; i < strArray.length; i++) {
            offset = repeatedKeyWord.charAt(i) - 65;
            if (strArray[i] + offset > 90) { //Loop back to start
                strArray[i] = (char) (65 + ((strArray[i] + offset) - 90) - 1);
            } else {
                strArray[i] = (char) (strArray[i] + offset);
            }
            result = result + strArray[i];
        }
        return result;
    }

    // decrypt vigenere
    public static String decryptVigenere(String str, String keyword) {
        //convert to capital letters
        keyword = keyword.toUpperCase();
        str = str.toUpperCase();
        char[] array = str.toCharArray();

        String result = "";
        int offset = 0;

        String repeatedKeyWord = "";
        int number = str.length() / keyword.length() + 1;
        for (int i = 0; i < number; i++) {
            repeatedKeyWord = repeatedKeyWord + keyword;
        }

        for (int i = 0; i < array.length; i++) {
            offset = repeatedKeyWord.charAt(i) - 65;
            if (array[i] - offset < 65) { //Loop back to 'A'
                array[i] = (char) (90 - (65 - (array[i] - offset)) + 1);
            } else {
                array[i] = (char) (array[i] - offset);
            }
            result = result + array[i];
        }
        return result;
    }
}