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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Preguntas extends AppCompatActivity {

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
        setContentView(R.layout.activity_preguntas);

        generador = new GeneradorPreguntas();
        dialogCorrecto = new Dialog(this);
        dialogIncorrecto = new Dialog(this);
        pregunta = findViewById(R.id.et_pregunta_Dificil);
        radioGroup = findViewById(R.id.rg_dificiles);
        opcion1 = findViewById(R.id.rb_opcion1_Dificil);
        opcion2 = findViewById(R.id.rb_opcion2_Dificil);
        opcion3 = findViewById(R.id.rb_opcion3_Dificil);
        opcion4 = findViewById(R.id.rb_opcion4_Dificil);
        boton = findViewById(R.id.btn_aceptar_dificil);
        puntaje = findViewById(R.id.puntaje_dificil);
        volver = findViewById(R.id.btn_volver_dificil);


        //------------------------
        if(getIntent()==null){
            puntos=0;
        }
        else{
            puntos = getIntent().getIntExtra("puntos", 0);
        }
        puntaje.setText("Puntaje = " + puntos);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton r= (RadioButton) findViewById(selectedId);

                String seleccionado = r.getText().toString();

                if(seleccionado.equals(respuesta)){
                    inicializarDialogoCorrecto();
                    puntos+=10;
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
                Intent i = new Intent(Preguntas.this, MapsActivity.class);
                i.putExtra("puntos", puntos);
                startActivity(i);
                Preguntas.this.finish();
            }
        });


        crearPreguntaDificil();
    }


    public void crearPreguntaDificil(){
        question = generador.generarPreguntaDificil();
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

                crearPreguntaDificil();
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

                crearPreguntaDificil();
            }
        });
        indicador.setText("Incorrecta! :( ");

        dialogIncorrecto.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogIncorrecto.show();

    }

}
