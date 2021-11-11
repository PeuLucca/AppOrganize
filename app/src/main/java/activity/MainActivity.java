package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organize.R;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import activity.CadastroActivity;
import activity.LoginActivity;
import conf.ConfiguracaoFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build() );

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build() );

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build() );

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build() );

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build() );

    }

    public void btnEntrar(View view){

        startActivity( new Intent(this, LoginActivity.class) );

    }

    public void btnCadastrar(View view){
        startActivity( new Intent(this, CadastroActivity.class) );
    }

    public void verificarUsuarioLogado(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();
        if ( autenticacao.getCurrentUser() != null ){

            startActivity( new Intent( getApplicationContext(),Principal_activity.class ));

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }
}