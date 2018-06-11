package it.max.roby.risorse;

import java.util.ArrayList;
import java.util.Collections;


public class comando {

    public String input_utente;
    public ArrayList<String> comandiPossibili;
    public ArrayList<String> comandiParolaccie;
    public ArrayList<String> comandiScartati;
    public char [] comandi;
    public ArrayList<String> comandoRoby;

    public comando(String text_input, ArrayList<String> comandoRoby ) {
        this.comandiScartati = new ArrayList();
        this.comandiPossibili = new ArrayList();
        this.comandiParolaccie = new ArrayList();
        this.comandoRoby=comandoRoby;
        this.comandi = new char [2];
        // m1>muovi_avanti m2>muovi_indietro
        // m3>muovi_destra m4>muovi_sinistra
        // i?>nonmovimento
        this.input_utente = text_input;

        String word_temp = text_input.toLowerCase().replace("6", "sei");
        String[] words = word_temp.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word_check = words[i];
            word_check = Utility.removeUselessChars(words[i]);
            if (word_check.trim().length() >= 1) {
                switch (verificaTipoParola(word_check)) {
                    case 0: // < di 2 caratteri
                        this.comandiScartati.add(word_check);
                        break;
                    case 1: // parole contenute nei comandi di Roby
                        if (!this.comandiPossibili.contains(word_check)){
                        this.comandiPossibili.add(word_check);}
                        break;
                    default:
                        break;
                }
            }
        }

        this.comandi=verificaMovimento(this.comandoRoby,this.comandiPossibili);


    }

    public static int verificaTipoParola(String parola) {
        int tipo = 1;
        parola = parola.trim();

        if (parola.length() < 2) {
            tipo = 0;
        }
        return tipo;
    }

    public static char [] verificaMovimento(ArrayList <String> paroleComandoMovimento, ArrayList <String> paroleAscolto){


        char [] idcom= new char[2];

            if (paroleAscolto.contains(paroleComandoMovimento)){
                idcom[0]='m';

            } else {
                idcom[0]='i';
            }


        idcom[1]='0';

        for (String list : paroleAscolto){
            switch (list){
                case "avanti": idcom[1]='1';
                    break;
                case "indietro": idcom[1]='2';
                    break;
                case "destra": idcom[1]='3';
                    break;
                case "sinistra": idcom[1]='4';
                    break;
            }

        }

        return idcom;


    }

    public static boolean equalList(ArrayList<String> a, ArrayList<String> b){
        // Check for sizes and nulls

        // Sort and compare the two lists
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }

}