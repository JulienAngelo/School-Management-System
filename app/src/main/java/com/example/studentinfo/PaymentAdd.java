package com.example.studentinfo;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.studentinfo.constants.CommonConstants;
import com.example.studentinfo.domain.Admin;
import com.example.studentinfo.domain.Payment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.math.BigDecimal;
public class PaymentAdd extends AppCompatActivity {
    EditText txtPayStudentId, txtTerm, txtPayment;
    ImageView submitBtn, menuBtn;
    DatabaseReference dbRef;
    Payment payment;
    private void clearControls() {
        txtPayStudentId.setText("");
        txtTerm.setText("");
        txtPayment.setText(""); }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_add);
        txtPayStudentId = findViewById(R.id.payStudentId);
        txtTerm = findViewById(R.id.payTermId);
        txtPayment = findViewById(R.id.payPaymentId);
        submitBtn = findViewById(R.id.paymentSubmit);
        payment = new Payment();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");
                try {
                    if(TextUtils.isEmpty(txtPayStudentId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Student Id is required!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtTerm.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Term is required!", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtPayment.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Payment is required!", Toast.LENGTH_SHORT).show();
                    else {
                        payment.setStudentId(CommonConstants.STUDENT_ID_PREFIX+txtPayStudentId.getText().toString().trim());
                     //   payment.setStudentId(txtPayStudentId.getText().toString().trim());
                        payment.setTerm(txtTerm.getText().toString().trim());
                        payment.setPayment(Float.parseFloat(txtPayment.getText().toString().trim()));
                        dbRef.child(payment.getStudentId()).setValue(payment);
                        Toast.makeText(getApplicationContext(), "Payment added successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Payment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}