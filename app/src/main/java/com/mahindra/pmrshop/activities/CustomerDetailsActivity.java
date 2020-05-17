package com.mahindra.pmrshop.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mahindra.pmrshop.R;
import com.mahindra.pmrshop.databinding.ActivityComplaintBinding;
import com.mahindra.pmrshop.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsActivity extends AppCompatActivity {

    private ActivityComplaintBinding binding;
    List<String> typeOfService = new ArrayList<>();
    StringBuilder serviceType = new StringBuilder();
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_complaint);
        builder = new AlertDialog.Builder(this);
        typeOfService = CommonUtils.getInstance().getSelectedComplaintList();
        if (typeOfService.isEmpty()) {
            serviceType.append("Not Selected !!");
        } else {
            for (int i = 0; i < typeOfService.size(); i++) {
                serviceType.append(typeOfService.get(i));
                serviceType.append("\n");
            }
        }
        binding.phoneNum.setOnClickListener(v -> {
            String number = binding.phoneNum.getText().toString();
            if (number != null) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9952218678"));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Number is not valid. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnSubmit.setOnClickListener(v -> {

            if (binding.tvName.getText().toString().isEmpty()) {
                binding.tvName.setError("Enter your name");
            }
            if (binding.tvNumber.getText().toString().isEmpty()) {
                binding.tvNumber.setError("Enter your number");
            }
            if (binding.tvNumber.getText().toString().length() != 10) {
                binding.tvNumber.setError("Enter 10 digits number");
            }
            if (binding.tvAddress.getText().toString().isEmpty()) {
                binding.tvAddress.setError("Enter your address");
            }
            if (binding.tvCity.getText().toString().isEmpty()) {

                binding.tvCity.setError("Enter your city");
            }

            if (!binding.tvName.getText().toString().isEmpty() &&
                    !binding.tvNumber.getText().toString().isEmpty() &&
                    binding.tvNumber.getText().toString().length() == 10 &&
                    !binding.tvAddress.getText().toString().isEmpty() &&
                    !binding.tvCity.getText().toString().isEmpty()) {

                String name = binding.tvName.getText().toString();
                String number = binding.tvNumber.getText().toString();
                String address = binding.tvAddress.getText().toString();
                String city = binding.tvCity.getText().toString();

                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkPermission()) {
                        Log.e("permission", "Permission already granted.");
                    } else {
                        requestPermission();
                    }
                }

                if (checkPermission()) {
            /*        Uri uri = Uri.parse("smsto:8667808632");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", " Name :-  " + name + "\n" + " Number :-  " + number + "\n" + " Address :-  " + address + "\n" + " City :-  " + city + "\n" + " Service Type :-  " + serviceType);
                    startActivity(intent);*/
                    // Intent intent = new Intent(this, MainActivity.class);
                    // PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    String msgBody = " Name :-  " + name + "\n" + " Number :-  " + number + "\n" + " Address :-  " + address + "\n" + " City :-  " + city + "\n" + " Service Type :-  " + serviceType;
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("9952218678", null, msgBody, null, null);
                    createDialog();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                //finish();
                CommonUtils.getInstance().getSelectedComplaintList().clear();
                // startActivity(new Intent(this, MainActivity.class));

            }
        });

    }

    private void createDialog() {
        builder.setMessage("Your Service request has been sent successfully !!")
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, id) -> {
                    startActivity(new Intent(this, MainActivity.class));
                });
               /* .setNegativeButton("No", (dialog, id) -> {
                    //  Action for 'NO' Button
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                            Toast.LENGTH_SHORT).show();
                });*/
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Service Request");
        alert.show();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                  /*  Button sendSMS = (Button) findViewById(R.id.sendSMS);
                    sendSMS.setEnabled(false);*/

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
