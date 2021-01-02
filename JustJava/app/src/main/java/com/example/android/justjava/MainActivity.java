package com.example.android.justjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numberOfCoffee = 2;
    boolean cream_added = false;
    boolean choco_added = false;
    int price = 0;
    String printmssg = "";
    @SuppressLint("QueryPermissionsNeeded")
    public void submitOrder(View view)
    {
        EditText text = (EditText)findViewById(R.id.name_field);
        String name = text.getText().toString();
        price = numberOfCoffee * 5;
        if(cream_added)
        {
            price += 1;
        }
        if(choco_added)
        {
            price += 2;
        }
        printmssg = " Hello! " + name;
        printmssg +="\n Total: $" + price;
        if(cream_added)
        {
            printmssg = printmssg + "\n Whipped cream is added!";
        }
        if(choco_added)
        {
            printmssg = printmssg + "\n Chocolate Topping is added!";
        }
        printmssg = printmssg + "\n Thank You";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, printmssg);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }
    public void increment(View view)
    {
        if(numberOfCoffee == 100)
        {
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffee++;
        display(numberOfCoffee);
    }
    public void cream_added(View view)
    {
        cream_added = !cream_added;
    }
    public void choco_added(View view)
    {
        choco_added = !choco_added;
    }
    public void decrement(View view)
    {
        if(numberOfCoffee == 1)
        {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffee--;
        display(numberOfCoffee);
    }
    private void display(int number)
    {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
        public void sendSMS (View view){
            EditText text = (EditText)findViewById(R.id.name_field);
            String name = text.getText().toString();
            EditText mob = (EditText)findViewById(R.id.phone_field);
            String mobile = mob.getText().toString();
        price = numberOfCoffee * 5;
        if (cream_added) {
            price += 1;
        }
        if (choco_added) {
            price += 2;
        }
        printmssg = " Hello! "+name;
        printmssg += "\n Total: $" + price;
        if (cream_added) {
            printmssg = printmssg + "\n Whipped cream is added!";
        }
        if (choco_added) {
            printmssg = printmssg + "\n Chocolate Topping is added!";
        }
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address"  , mobile);
            smsIntent.putExtra("sms_body"  , printmssg);
            startActivity(smsIntent);
    }
}