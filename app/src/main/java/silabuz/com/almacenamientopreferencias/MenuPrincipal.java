package silabuz.com.almacenamientopreferencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    private TextView titulo;
    private Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titulo=(TextView)findViewById(R.id.titulo_text_view);
        cerrar=(Button)findViewById(R.id.cerrar_button);

        SharedPreferences prefs =
                getSharedPreferences(Constantes.PREFS_NAME, Context.MODE_PRIVATE);

        //Se busca los datos almacenados. SI no hay el resultado será no.
        String usuario_almacenado = prefs.getString(Constantes.PREFS_USUARIO,"no");

        titulo.setText("Bienvenido: "+usuario_almacenado);

        cerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Cerrando sesión.", Toast.LENGTH_SHORT).show();

                SharedPreferences prefs =
                        getSharedPreferences(Constantes.PREFS_NAME, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(a);
            }
        });
    }

}
