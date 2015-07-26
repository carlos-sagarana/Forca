package alura.com.br.forca;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private Button btJogar;
    private Button btPlay;
    private EditText etLetra;
    private ForcaView forcaView;
    private ForcaController forcaController;

    private String[] palavras = new String[]{"Alura", "Caelum"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btJogar = (Button) findViewById(R.id.btJogar);
        btPlay = (Button) findViewById(R.id.btPlay);
        etLetra = (EditText) findViewById(R.id.etLetra);

        forcaView = (ForcaView) findViewById(R.id.fvJogo);

        init();
    }

    private void init() {

        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etLetra.getText().toString().trim().length() == 0) {
                    return;
                }

                getForcaController().joga(etLetra.getText().toString().trim().charAt(0));
                forcaView.invalidate();
                etLetra.getText().clear();

                if(getForcaController().isTerminou()) {
                    btJogar.setEnabled(false);
                    btPlay.setEnabled(true);
                    if (getForcaController().isMorreu()) {
                        Toast.makeText(getApplicationContext(), "Ops, você perdeu!", Toast.LENGTH_LONG).show();
                    } else if (getForcaController().isGanhou()) {
                        Toast.makeText(getApplicationContext(), "Parabéns, você ganhou!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForcaController(new ForcaController(palavras[new Random().nextInt(palavras.length)]));
                forcaView.setForcaController(getForcaController());

                etLetra.getText().clear();
                etLetra.setEnabled(true);
                btJogar.setEnabled(true);
                btPlay.setEnabled(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ForcaController getForcaController() {
        return forcaController;
    }

    public void setForcaController(ForcaController forcaController) {
        this.forcaController = forcaController;
    }
}
