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
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Faciles extends AppCompatActivity {

    public EditText pregunta;

    public RadioGroup radioGroup;

    public RadioButton opcion1;

    public RadioButton opcion2;

    public RadioButton opcion3;

    public RadioButton opcion4;

    public Button boton;

    public GeneradorPreguntas generador;

    public Question question;

    public Dialog dialogCorrecto;

    public Dialog dialogIncorrecto;

    public String respuesta;

    public EditText puntaje;

    public Button volver;

    public int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faciles);

        generador = new GeneradorPreguntas();
        dialogCorrecto = new Dialog(this);
        dialogIncorrecto = new Dialog(this);
        pregunta = findViewById(R.id.et_preguntaFacil);
        radioGroup = findViewById(R.id.rg_faciles);
        opcion1 = findViewById(R.id.rb_opcion1_Facil);
        opcion2 = findViewById(R.id.rb_opcion2_Facil);
        opcion3 = findViewById(R.id.rb_opcion3_Facil);
        opcion4 = findViewById(R.id.rb_opcion4_Facil);
        boton = findViewById(R.id.btn_aceptar_facil);
        puntaje = findViewById(R.id.puntaje_facil);
        volver = findViewById(R.id.btn_volver_facil);


        //------------------------
        puntos=0;

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton r= (RadioButton) findViewById(selectedId);

                String seleccionado = r.getText().toString();

                if(seleccionado.equals(respuesta)){
                    inicializarDialogoCorrecto();
                    puntos+=5;
                    puntaje.setText("Puntaje = " + puntos);
                }
                else{
                    inicializarDialogoIncorrecto();
                    puntaje.setText("Puntaje = " + puntos);
                }

            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Faciles.this, MapsActivity.class);
                startActivity(i);
                Faciles.this.finish();
            }
        });


        crearPreguntaFacil();
    }


    public void crearPreguntaFacil(){
        question = generador.generarPreguntaFacil();
        respuesta = question.getOpciones().get(0) +"";

        String pre = question.getOpeA() + question.getOperando() + question.getOpeB();

        pregunta.setText(pre);

        ArrayList<Integer> opciones = question.getOpciones();

        Random random = new Random();
        Collections.shuffle(opciones, random);

        String op1 = opciones.get(0) +"";
        String op2 = opciones.get(1) +"";
        String op3 = opciones.get(2) +"";
        String op4 = opciones.get(3) +"";

        opcion1.refreshDrawableState();
        opcion1.setText(op1);
        opcion2.setText(op2);
        opcion3.setText(op3);
        opcion4.setText(op4);


    }

    public void inicializarDialogoCorrecto(){
        dialogCorrecto.setContentView(R.layout.epic_dialog);
        Button btn_dialogCorrecto = dialogCorrecto.findViewById(R.id.btn_dialog_correcto);
        TextView indicador = dialogCorrecto.findViewById(R.id.et_indicador);

        btn_dialogCorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCorrecto.dismiss();

                crearPreguntaFacil();
            }
        });
        btn_dialogCorrecto.setBackgroundColor(Color.parseColor("#0B6121"));
        indicador.setText("Correcta! :) ");

        dialogCorrecto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCorrecto.show();

    }

    public void inicializarDialogoIncorrecto(){
        dialogIncorrecto.setContentView(R.layout.epic_dialog);
        Button btn_dialogIncorrecto = dialogIncorrecto.findViewById(R.id.btn_dialog_correcto);
        TextView indicador = dialogIncorrecto.findViewById(R.id.et_indicador);
        btn_dialogIncorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogIncorrecto.dismiss();

                crearPreguntaFacil();
            }
        });
        indicador.setText("Incorrecta! :( ");

        dialogIncorrecto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogIncorrecto.show();

    }

}