package com.example.android.myapplication;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    boolean chocolate = false;
    boolean whippedcream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        tryCheckBoxChocolate();
        tryCheckBoxCream();
        int price = calculatePrice(quantity, 5);
        createOrderSummary(price, quantity);

    }

    /*
    This method is called when the minus button is clicked
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        }
    }

    /*
   This method is called when the plus button is clicked
    */
    public void increment(View view) {
        if(quantity < 100)
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int prize) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + prize);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(int quantity, int DrinkCost) {

        if(chocolate == true)
            DrinkCost = DrinkCost +2;
        if(whippedcream == true)
            DrinkCost++;
        int price = quantity * DrinkCost;
        return price;
    }

    /*
     * This method creates order summary
     * @param price of total order
     * @param quantity of order
     * @param name of person
     *
     * */
    private void createOrderSummary(int price, int quantity) {
        String name = LookForName();
        String subject = "JustJava " +getString(R.string.order_for,name);
        String pricemessage = getString(R.string.MainName,name) +
                              "\n"+getString(R.string.add) + " " + getString(R.string.whipped_cream) + " ?";
        if (whippedcream == true)
            pricemessage += "true";
        else
            pricemessage += "false";
        if (chocolate == true)
            pricemessage += "\n"+getString(R.string.add) + " " + getString(R.string.chocolate) + " ? true" ;
        else
            pricemessage += "\n"+getString(R.string.add) + " " + getString(R.string.chocolate) + " ? false" ;

        pricemessage += "\n"+getString(R.string.quantity)+":" + quantity + "" +
                         "\n"+getString(R.string.total)+": $" + price +
                         "\n" + getString(R.string.thank_you) + "!";

        composeEmail(pricemessage,subject);
    }

    private void tryCheckBoxCream() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if (checkBox.isChecked() == true)
            whippedcream = true;
        else
            whippedcream = false;
    }

    private void tryCheckBoxChocolate() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (checkBox.isChecked() == true)
            chocolate = true;
        else
            chocolate = false;
    }

    private String LookForName() {
        EditText editText = (EditText) findViewById(R.id.Name_EditText_View);
        return editText.getText().toString();
    }
    public void composeEmail(String Text, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, Text);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
