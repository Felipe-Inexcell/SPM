package felipe.cl.spm.actividades;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import felipe.cl.spm.R;
import felipe.cl.spm.actividades.extras.Constantes;
import felipe.cl.spm.actividades.extras.CustomStateDialog;
import felipe.cl.spm.actividades.extras.TransparentProgressDialog;

public class Principal extends Activity implements View.OnClickListener {
    ImageButton btn_search;
    TransparentProgressDialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_rut);

        btn_search = (ImageButton) findViewById(R.id.search);
        btn_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search) {
            d = new TransparentProgressDialog(this, R.drawable.progress_img, "BUSCANDO");

            new AlertDialog.Builder(this)
                    .setItems(new CharSequence[]{"CLIENTE NUEVO", "CLIENTE EXISTENTE", "CLIENTE EN MORA"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            d.show();
                            TimeoutOperation t = new TimeoutOperation(Principal.this,d,which, Constantes.TIME_SHORT);
                            t.execute();
                        }
                    }).show();

        }
    }

    private class TimeoutOperation extends AsyncTask<String, Void, Void> {
        Context context;
        int caso;
        int duration;
        Dialog dialog;

        public TimeoutOperation(Context context, Dialog d, int caso, int duration) {
            this.context = context;
            this.duration = duration;
            this.dialog = d;
            this.caso = caso;
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            if(caso == 0){
                startActivity(new Intent(Principal.this, Form_Cliente.class));
            }else if(caso == 1){
                startActivity(new Intent(Principal.this, DetalleCliente.class));
            }else if(caso == 2){
                CustomStateDialog di = new CustomStateDialog(Principal.this, R.drawable.ic_state_nok, "Cliente no puede contratar servicios", Constantes.STATE_NOK);
                di.show();
                TimeoutOperation t = new TimeoutOperation(context,di,-1, Constantes.TIME_LONG);
                t.execute();
            }
        }
    }

}
