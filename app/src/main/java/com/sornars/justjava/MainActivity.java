package com.sornars.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

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
        displayMessage(message);
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
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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