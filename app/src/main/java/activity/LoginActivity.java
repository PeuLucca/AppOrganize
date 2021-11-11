package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organize.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import conf.ConfiguracaoFirebase;
import model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText email,senha;
    private Button botaoEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edit_Email);
        senha = findViewById(R.id.edit_Senha);
        botaoEntrar = findViewById(R.id.btnEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoEmail = email.getText().toString();
                String textoSenha = senha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !textoSenha.isEmpty() ){

                        usuario = new Usuario();
                        usuario.setEmail( textoEmail );
                        usuario.setSenha( textoSenha );

                        validarLogin();

                    }else {
                        Toast.makeText(getApplicationContext(),"Preencha a senha",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Preencha o email",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword( usuario.getEmail(), usuario.getSenha() )
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){

                    startActivity( new Intent(getApplicationContext(),Principal_activity.class));
                    finish();

                }else{

                    String excecao="";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Senha incorreta";
                    }catch (Exception e){
                        excecao = "Erro ao fazer login" + e.getMessage();
                    }

                    Toast.makeText(getApplicationContext(),excecao,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}