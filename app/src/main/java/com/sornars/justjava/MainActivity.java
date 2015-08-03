package com.sornars.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
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
        CheckBox topping1CheckBox = (CheckBox) findViewById(R.id.topping1_check_box);
        Boolean topping1 = topping1CheckBox.isChecked();
        int price = calculatePrice();
        String message = createOrderSummary(price, topping1);
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
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Creates a summary of the order
     *
     * @param price total price
     * @return String order summary
     */
    private String createOrderSummary(int price, boolean topping1) {
        String orderSummaryMessage = "Name: Kaptain Kunal";
        orderSummaryMessage += "\nHas Whipped Cream: " + topping1;
        orderSummaryMessage += "\nQuantity: " + quantity;
        orderSummaryMessage += "\nTotal $: " + price;
        orderSummaryMessage += "\nThank you!";
        return orderSummaryMessage;
    }
}