package felipe.cl.spm.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import felipe.cl.spm.R;
import felipe.cl.spm.actividades.extras.CustomStateToast;
import felipe.cl.spm.actividades.extras.TransparentProgressDialog;

public class DetalleCliente extends Activity implements View.OnClickListener {
    ImageButton btn_ok, btn_nok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_detalle_cliente);

        btn_ok = (ImageButton) findViewById(R.id.ib_ok);
        btn_nok = (ImageButton) findViewById(R.id.ib_nok);
        btn_ok.setOnClickListener(this);
        btn_nok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_ok) {
            /*TransparentProgressDialog d = new TransparentProgressDialog(this, R.drawable.progress_img, "BUSCANDO");
            d.show();*/

            startActivity(new Intent(this, Servicios.class).putExtra("CASO", 1));

            /*CustomStateToast d = new CustomStateToast(this, R.drawable.progress_img, "REGISTRO INGRESADO EXISTOSAMENTE");
            d.show();*/
        }
        if (v.getId() == R.id.ib_nok) {
            finish();
        }
    }
}
