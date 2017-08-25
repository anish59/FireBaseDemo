package com.example.anish.firebasedemo.fireBaseTuts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anish.firebasedemo.R;
import com.example.anish.firebasedemo.fireBaseTuts.model.Info;
import com.example.anish.firebasedemo.fireBaseTuts.model.Result;
import com.example.anish.firebasedemo.fireBaseTuts.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterStudentDetailsActivity extends AppCompatActivity {

    private android.widget.EditText txtName;
    private android.widget.EditText txtRollNo;
    private android.widget.TextView txtMarks;
    private android.view.View viewLine;
    private android.widget.EditText edtM1;
    private android.widget.EditText edtM2;
    private EditText edtName;
    private EditText edtRollNo;
    private android.widget.Button btnSubmit;
    private DatabaseReference studentRef, resultRef, infoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();
        initFireBaseDatabase();
    }

    private void initFireBaseDatabase() {
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        studentRef = database.getReference(Student.class.getSimpleName());
        resultRef = database.getReference(Result.class.getSimpleName());
        infoRef = database.getReference(Info.class.getSimpleName());

    }

    private void initListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String rollNo = edtRollNo.getText().toString();
                String m1 = edtM1.getText().toString();
                String m2 = edtM2.getText().toString();
                String resultId = resultRef.push().getKey();
                String infoId = infoRef.push().getKey();
                Student student = new Student(new Result(m1, m2, resultId), new Info(rollNo, name, infoId));
                String studentId = studentRef.push().getKey();
                studentRef.child(studentId).setValue(student);
            }
        });
    }

    private void init() {
        setContentView(R.layout.activity_enter_student_details);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        edtRollNo = (EditText) findViewById(R.id.edtRollNo);
        edtName = (EditText) findViewById(R.id.edtName);
        edtM2 = (EditText) findViewById(R.id.edtM2);
        edtM1 = (EditText) findViewById(R.id.edtM1);
        viewLine = findViewById(R.id.viewLine);
        txtMarks = (TextView) findViewById(R.id.txtMarks);
    }
}
