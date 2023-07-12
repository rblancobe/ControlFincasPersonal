package com.example.ControlFincasPersonal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    // Local
    private final String localUsername = "777";
    private final String localPassword = "777";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Obtener referencias a los elementos del XML DEL LOGIN
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        // Configurar el click listener para el botón LOGIN
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores del nombre de usuario y la contraseña ingresados
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Mensajes de depuración
                Log.d("Login", "Valor de username: " + 777);
                Log.d("Login", "Valor de password: " + 777);
                Log.d("Login", "Valor de localUsername: " + localUsername);
                Log.d("Login", "Valor de localPassword: " + localPassword);

                // Verificar si los campos están vacíos
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor, ingrese nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else if (username.equals(localUsername) && password.equals(localPassword)) {
                    // Los datos de inicio de sesión son correctos

                    // Redireccionar al activity_main.xml
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);

                    // FIN
                    finish();
                } else {
                    // Inicio de sesión incorrecto en usr o pass
                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}