package icesi.com.practico1;

import java.util.ArrayList;

public class Question {

    private int opeA;

    private int opeB;

    private String operando;

    //La opciòn correcta siempre va a ser la primera
    private ArrayList<Integer> opciones;




    public Question(int a, int b, String o)
    {
        opeA=a;
        opeB=b;
        operando=o;
        opciones= new ArrayList<>();

    }


    public void setOpeA(int opeA) {
        this.opeA = opeA;
    }

    public void setOpeB(int opeB) {
        this.opeB = opeB;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }

    public void setOpciones(ArrayList<Integer> opciones) {
        this.opciones = opciones;
    }

    public int getOpeA() {
        return opeA;
    }

    public int getOpeB() {
        return opeB;
    }

    public String getOperando() {
        return operando;
    }

    public ArrayList<Integer> getOpciones() {
        return opciones;
    }

    public void agregarOpcion(int opcion) {
        opciones.add(opcion);
    }

}
