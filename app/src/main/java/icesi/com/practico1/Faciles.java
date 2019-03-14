package icesi.com.practico1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Faciles extends AppCompatActivity {

    public EditText pregunta;

    public RadioButton opcion1;

    public RadioButton opcion2;

    public RadioButton opcion3;

    public RadioButton opcion4;

    public Button boton;

    public GeneradorPreguntas generador;

    public Question question;


    public String respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faciles);

        generador = new GeneradorPreguntas();

        pregunta = findViewById(R.id.et_preguntaFacil);
        opcion1 = findViewById(R.id.rb_opcion1_Facil);
        opcion2 = findViewById(R.id.rb_opcion2_Facil);
        opcion3 = findViewById(R.id.rb_opcion3_Facil);
        opcion4 = findViewById(R.id.rb_opcion4_Facil);
        boton = findViewById(R.id.btn_aceptar_facil);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void crearPreguntaFacil(){
        question = generador.generarPreguntaFacil();
        respuesta = question.getOpciones().get(0) +"";

        String pre = question.getOpeA() + question.getOperando() + question.getOpeB();

        ArrayList<Integer> opciones = question.getOpciones();

        Random random = new Random();
        Collections.shuffle(opciones, random);

        String op1 = opciones.get(0) +"";
        String op2 = opciones.get(1) +"";
        String op3 = opciones.get(2) +"";
        String op4 = opciones.get(3) +"";

        opcion1.setText(op1);
        opcion2.setText(op2);
        opcion3.setText(op3);
        opcion4.setText(op4);




    }
}
