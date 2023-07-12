package com.example.ControlFincasPersonal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.AsynchronousChannel;

import cz.msebera.android.httpclient.Header;


public class Login extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Context context = Login.this;
    private AsyncHttpClient client;
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
                //Log.d("Login", "Valor de username: " + 777);
                //Log.d("Login", "Valor de password: " + 777);
                //Log.d("Login", "Valor de localUsername: " + localUsername);
                //Log.d("Login", "Valor de localPassword: " + localPassword);

                // Verificar si los campos están vacíos
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Por favor, ingrese nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else if (username.equals(localUsername) && password.equals(localPassword)) {
                    // Los datos de inicio de sesión son correctos

                    // Redireccionar al activity_main.xml
                    RequestParams params = new RequestParams();
                    params.put("imei","");
                    params.put("usr",username);
                    params.put("pass",password);
                    client = new AsyncHttpClient();

                    client.post(utils.service(), params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                JSONObject response = new JSONObject(new String(responseBody).trim());
                                Toast.makeText(context,new String(responseBody),Toast.LENGTH_SHORT).show();
                                /*String id = response.getString("log");
                                JSONArray json = new JSONArray(id);
                                JSONObject obj = json.getJSONObject(0);
                                String valido = obj.getString("valido");

                                if(valido.equals("UN")){
                                    Toast.makeText(context,"El dispositivo utilizado no está autorizado\r\nContacte con el administrador",Toast.LENGTH_SHORT).show();
                                }else if(valido.equals("NO")){
                                    Toast.makeText(context,"Error en Usuario o contraseña",Toast.LENGTH_SHORT).show();
                                }else{
                                    goToMain();
                                }*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context,"Error JSON OBJ",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(context,"ERROR RESPONSE",Toast.LENGTH_SHORT).show();
                        }
                    });

                    // FIN
                    //finish();
                } else {
                    // Inicio de sesión incorrecto en usr o pass
                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void goToMain(){
        Toast.makeText(context,"Entro en GoToMain",Toast.LENGTH_SHORT).show();
     //   Intent intent = new Intent(Login.this, MainActivity.class);
     //   startActivity(intent);
    }
}