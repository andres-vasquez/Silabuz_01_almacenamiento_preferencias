package silabuz.com.almacenamientopreferencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mEnviarButton;
    private TextView mResultadosTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Buscamos el archivo de preferencias llamado MisPreferencias
        SharedPreferences prefs =
                getSharedPreferences(Constantes.PREFS_NAME, Context.MODE_PRIVATE);

        //Se busca los datos almacenados. SI no hay el resultado será no.
        String usuario_almacenado = prefs.getString(Constantes.PREFS_USUARIO,"no");
        String password_almacenado = prefs.getString(Constantes.PREFS_PASSWORD,"no");

        //Si hay datos de usuarios almacenados se irá a menu principal.
        if(usuario_almacenado.compareTo("no")!=0 && password_almacenado.compareTo("no")!=0)
        {
            Intent a=new Intent(getApplicationContext(),MenuPrincipal.class);
            finish();
            startActivity(a);
        }


        mUsernameEditText =(EditText)findViewById(R.id.username_edit_text);
        mPasswordEditText =(EditText)findViewById(R.id.password_edit_text);
        mEnviarButton =(Button)findViewById(R.id.enviar);
        mResultadosTextView =(TextView)findViewById(R.id.resultados);

        mEnviarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String nombre_ingresado= mUsernameEditText.getText().toString();
                String password_ingresado= mPasswordEditText.getText().toString();

                //Abrimos el archivo de preferencias
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

                //Editamos los campos existentes
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(Constantes.PREFS_USUARIO, nombre_ingresado);
                editor.putString(Constantes.PREFS_PASSWORD, password_ingresado);
                //Concretamos la edicion
                editor.apply();

                Toast.makeText(getApplicationContext(), "Usuario: " + nombre_ingresado + ", Password: " + password_ingresado, Toast.LENGTH_SHORT).show();

                Intent a=new Intent(getApplicationContext(),MenuPrincipal.class);
                startActivity(a);
                finish();
            }
        });
    }
}
