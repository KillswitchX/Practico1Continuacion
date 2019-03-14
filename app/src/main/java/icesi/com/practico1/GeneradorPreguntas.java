package icesi.com.practico1;

import java.util.ArrayList;

public class GeneradorPreguntas {

    public static String FACIL="FACIL";

    public static String DIFICIL="DIFICIL";

    private String tipoDePregunta;

    private ArrayList<String> operadores;

    public GeneradorPreguntas() {
        operadores = new ArrayList<>();

        operadores.add("+");
        operadores.add("-");
        operadores.add("*");
        operadores.add("/");
    }

    public int aleatorioFacil(){
        int numero = (int) (Math.random() * 9) + 1;
        return  numero;
    }

    public int aleatorioDificilLejano(){
        int num = (int) (Math.random() * 99) + 1;
        return num;
    }

    public int aleatorioDificilCercano1(int resultado){
        int num = resultado+1;
        return num;
    }

    public int aleatorioDificilCercano2(int resultado){
        int num = resultado-1;
        return num;
    }

    public String operandoAlAzar(){
        int i = (int) (Math.random() * 3);
        return  operadores.get(i);
    }

    public int aleatorioMultiplicacion(){
        int num = (int) (Math.random() * 81) + 1;
        return num;
    }

    public int aleatorioSuma(){
        int n = (int) (Math.random() * 18) + 1;
        return n;
    }

    public int aleatorioRestaoDivision(){
        int num = (int) (Math.random() * 9) + 1;
        return num;
    }


    public Question generarPreguntaFacil(){
        int a = aleatorioFacil();
        int b = aleatorioFacil();
        String op = operandoAlAzar();
        int resultado = 0;
        int x = 0;
        int y=0;
        int z=0;

        switch (op){
            case "*":
                resultado = (a*b);
                x = aleatorioMultiplicacion();
                y = aleatorioMultiplicacion();
                z = aleatorioMultiplicacion();
                break;

            case "+":
                resultado = (a+b);
                x =  aleatorioSuma();
                y =  aleatorioSuma();
                z =  aleatorioSuma();
                break;

            case "-":
                if(a>=b){
                    resultado = (a-b);
                }
                else{
                    resultado = (b-a);
                }
                x = aleatorioRestaoDivision();
                y = aleatorioRestaoDivision();
                z = aleatorioRestaoDivision();

                break;

            case "/":
                if(a>=b){
                    resultado = (int) (a/b);
                }
                else{
                    resultado = (int) (b/a);
                }
                x = aleatorioRestaoDivision();
                y = aleatorioRestaoDivision();
                z = aleatorioRestaoDivision();
                break;
        }

        Question q = new Question(a,b,op);
        q.agregarOpcion(resultado);
        q.agregarOpcion(x);
        q.agregarOpcion(y);
        q.agregarOpcion(z);

        return q;
    }



}
