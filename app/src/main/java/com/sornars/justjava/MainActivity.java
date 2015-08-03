package com.sornars.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox topping1CheckBox = (CheckBox) findViewById(R.id.topping1_checkbox);
        CheckBox topping2CheckBox = (CheckBox) findViewById(R.id.topping2_checkbox);
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        Boolean topping1 = topping1CheckBox.isChecked();
        Boolean topping2 = topping2CheckBox.isChecked();
        String name = nameInput.getText().toString();
        int price = calculatePrice(topping1, topping2);
        String message = createOrderSummary(name, price, topping1, topping2);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity++;
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Cannot order more than 100 coffees!", duration);
            toast.show();
        }

        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, "Cannot order less than 1 coffee!", duration);
            toast.show();
        }

        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @param topping1 has whipped cream
     * @param topping2 has chocolate
     * @return total price
     */
    private int calculatePrice(boolean topping1, boolean topping2) {
        int price = 5;
        if (topping1 == true) {
            price += 1;
        }

        if (topping2 == true) {
            price += 2;
        }

        return price * quantity;
    }

    /**
     * Create summary of the order.
     *
     * @param name     of the person making the order
     * @param price    of the order
     * @param topping1 is whether or not the user wants whipped cream topping
     * @param topping2 is whether or not the user wants chocolate topping
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean topping1, boolean topping2) {
        String orderSummaryMessage = "Name: " + name;
        orderSummaryMessage += "\nHas Whipped Cream: " + topping1;
        orderSummaryMessage += "\nHas Chocolate: " + topping2;
        orderSummaryMessage += "\nQuantity: " + quantity;
        orderSummaryMessage += "\nTotal $: " + price;
        orderSummaryMessage += "\nThank you!";
        return orderSummaryMessage;
    }
}