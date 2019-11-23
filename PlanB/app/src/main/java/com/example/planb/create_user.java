package com.example.planb;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class create_user extends AppCompatActivity {

    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 이메일, 비밀번호, 전화번호, 성별, 생년월일, 소개글
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private RadioButton editTextGenderMale;
    private RadioButton editTextGenderFemale;
    private EditText editTextDob;
    private EditText editTextIntroduce;

    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.emailCreateUser);
        editTextPassword = findViewById(R.id.passwordCreateUser);
        editTextPhone = findViewById(R.id.phoneCreateUser);
        editTextGenderMale = findViewById(R.id.male);
        editTextGenderFemale = findViewById(R.id.female);
        editTextDob = findViewById(R.id.passwordCreateUser);
        editTextIntroduce = findViewById(R.id.passwordCreateUser);
    }

    public void singUp(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();



        if(isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    // 이메일 유효성 검사
    private boolean isValidEmail() {
        if (email.isEmpty()) {
            // 이메일 공백
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            // 비밀번호 공백
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            Toast.makeText(create_user.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // 회원가입 실패
                            Toast.makeText(create_user.this, "회원가입 실패..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onGenderRadioClicked(View view) {
        switch(view.getId()) {

        }
    }
}
