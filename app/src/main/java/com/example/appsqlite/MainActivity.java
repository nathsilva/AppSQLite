package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtNome, txtCpf, txtRg, txtTelefone, txtEmail, txtSenha, txtConfirmaSenha;
    Button btnRegistar, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtNome = findViewById(R.id.idNome);
        txtCpf = findViewById(R.id.idCpf);
        txtRg = findViewById(R.id.idRg);
        txtTelefone = findViewById(R.id.idTelefone);
        txtEmail = findViewById(R.id.idEmail);
        txtSenha = findViewById(R.id.idSenha);
        txtConfirmaSenha = findViewById(R.id.idConfirmaSenha);

        btnLogin = findViewById(R.id.idBtnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistar = findViewById(R.id.idBtnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, cpf, rg, telefone, email, senha, confirmaSenha;

                nome = txtNome.getText().toString();
                cpf = txtRg.getText().toString();
                rg = txtRg.getText().toString();
                telefone = txtTelefone.getText().toString();
                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();
                confirmaSenha = txtConfirmaSenha.getText().toString();

                if (nome.equals("") ||cpf.equals("") ||rg.equals("") ||telefone.equals("") ||email.equals("") || senha.equals("") || confirmaSenha.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor inserir valores!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmaSenha)) {
                        Boolean checharEmail = db.validarEmail(email);
                        if (checharEmail == true) {
                            Boolean inserir = db.insert(email, senha);
                            if (inserir == true) {
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email inserido já existe!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Senha não confere!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
