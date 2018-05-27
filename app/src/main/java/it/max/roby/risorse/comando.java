package it.max.roby.risorse;

import java.util.ArrayList;

public class comando {

    public String input_utente;
    public ArrayList<String> comandiPossibili;
    public ArrayList<String> comandiIgnoti;
    public ArrayList<String> comandiParolaccie;
    public ArrayList<String> comandiScartati;
    public ArrayList<String> comandi;

    public comando(String text_input) {
        this.comandiScartati = new ArrayList();
        this.comandiIgnoti = new ArrayList();
        this.comandiPossibili = new ArrayList();
        this.comandiParolaccie = new ArrayList();
        this.comandi = new ArrayList();
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
                    case 2: // parole NON contenute nei comandi di Roby
                        this.comandiIgnoti.add(word_check);
                        break;
                    default:
                        break;
                }
            }
        }


    }

    public static int verificaTipoParola(String parola) {
        int tipo = 2;
        parola = parola.trim();

        if (parola.length() < 2) {
            tipo = 0;
        }
        if (parola.length() > 2) // confrontare con Comandi xml
        {
            tipo = 1;
        }
        return tipo;
    }

}