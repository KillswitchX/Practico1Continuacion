package icesi.com.practico1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Canje extends AppCompatActivity {

    public RadioGroup radioGroup;

    public RadioButton opcion1;

    public RadioButton opcion2;

    public RadioButton opcion3;

    public RadioButton opcion4;

    public RadioButton opcion5;

    public Button boton;

    public Button volver;

    public EditText puntaje;

    public EditText canjeo;

    public Dialog dialogCorrecto;

    public int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canje);

        radioGroup = findViewById(R.id.rg_canje);
        opcion1 = findViewById(R.id.rb_opcion1_canje);
        opcion2 = findViewById(R.id.rb_opcion2_canje);
        opcion3 = findViewById(R.id.rb_opcion3_canje);
        opcion4 = findViewById(R.id.rb_opcion4_canje);
        opcion5 = findViewById(R.id.rb_opcion5_canje);
        puntaje = findViewById(R.id.puntaje_canje);
        boton = findViewById(R.id.btn_canjear);
        canjeo = findViewById(R.id.et_codigoCanjeo);
        dialogCorrecto = new Dialog(this);
        volver = findViewById(R.id.btn_volver_canje);

        if(getIntent()==null){
            puntos=0;
        }
        else{
            puntos = getIntent().getIntExtra("puntos", 0);
        }
        puntaje.setText("Puntaje = " + puntos);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Canje.this, MapsActivity.class);
                i.putExtra("puntos", puntos);
                startActivity(i);
                Canje.this.finish();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion1.isChecked()){
                    if(puntos>=20) {
                        puntos -= 20;
                        puntaje.setText("Puntaje = " + puntos);
                        generarCodigo();
                        Toast.makeText(getApplicationContext(), "Còdigo de canje generado correctamente", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No tiene suficientes puntos", Toast.LENGTH_LONG).show();
                    }
                }
                if(opcion2.isChecked()){
                    if(puntos>=30) {
                        puntos -= 30;
                        puntaje.setText("Puntaje = " + puntos);
                        generarCodigo();
                        Toast.makeText(getApplicationContext(), "Còdigo de canje generado correctamente", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No tiene suficientes puntos", Toast.LENGTH_LONG).show();
                    }
                }
                if(opcion3.isChecked()) {
                    if (puntos >= 40){
                        puntos -= 40;
                        puntaje.setText("Puntaje = " + puntos);
                        generarCodigo();
                        Toast.makeText(getApplicationContext(), "Còdigo de canje generado correctamente", Toast.LENGTH_LONG).show();
                    }
                    else{
                    Toast.makeText(getApplicationContext(), "No tiene suficientes puntos", Toast.LENGTH_LONG).show();
                    }
                }
                if(opcion4.isChecked()){
                    if(puntos>=80) {
                        puntos -= 80;
                        puntaje.setText("Puntaje = " + puntos);
                        generarCodigo();
                        Toast.makeText(getApplicationContext(), "Còdigo de canje generado correctamente", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No tiene suficientes puntos", Toast.LENGTH_LONG).show();
                    }
                }
                if(opcion5.isChecked()){
                    if(puntos>=100) {
                        puntos -= 100;
                        puntaje.setText("Puntaje = " + puntos);
                        generarCodigo();
                        Toast.makeText(getApplicationContext(), "Còdigo de canje generado correctamente", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "No tiene suficientes puntos", Toast.LENGTH_LONG).show();
                    }
                }
                }
        });

    }

    public void generarCodigo(){
        SecureRandom random = new SecureRandom();
        String txt = new BigInteger(130, random).toString(32);
        canjeo.setText(txt);
    }

    public void inicializarDialogoCorrecto(){
        dialogCorrecto.setContentView(R.layout.epic_dialog);
        Button btn_dialogCorrecto = dialogCorrecto.findViewById(R.id.btn_dialog_correcto);
        TextView indicador = dialogCorrecto.findViewById(R.id.et_indicador);


        btn_dialogCorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCorrecto.dismiss();

            }
        });
        btn_dialogCorrecto.setBackgroundColor(Color.parseColor("#0B6121"));
        indicador.setText("Còdigo de canjeo generado con èxito, lo puede copiar y pegar");

        dialogCorrecto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCorrecto.show();

    }
}
