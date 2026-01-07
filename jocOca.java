import java.util.InputMismatchException;
import java.util.Scanner;

public class jocOca {
    static Scanner sc = new Scanner(System.in);
    int numJugadors = 0;
    int[] casellaJugadors;
    String[] nomJugadors;
    int[] tornsBloquejats;
    int[] ordreJugadors;
    
    public static void main(String[] args) {
        jocOca joc = new jocOca();
        joc.jocPrincipal();
    }
    
    public void jocPrincipal() {
        System.out.println("=========================================");
        System.out.println("       Benvinguts al joc de l'Oca!       ");
        System.out.println("=========================================");
        System.out.println();
        
        do {
            System.out.println("Quants jugadors hi ha? (2-4)");
            numJugadors = llegirEnter();
        } while (numJugadors < 2 || numJugadors > 4);
        
        casellaJugadors = new int[numJugadors];
        nomJugadors = new String[numJugadors];
        tornsBloquejats = new int[numJugadors];
        ordreJugadors = new int[numJugadors];
        
        for (int i = 0; i < numJugadors; i++) {
            casellaJugadors[i] = 0;
            tornsBloquejats[i] = 0;
        }
        
        for (int i = 0; i < numJugadors; i++) {
            System.out.println("Nom del jugador " + (i + 1));
            nomJugadors[i] = llegirLletres();
        }
        
        System.out.println("\nComença el joc!");
        determinarOrdre();
        
        boolean finalJoc = false;

        do {
            for (int i = 0; i < numJugadors; i++) {
                int jugadorActual = ordreJugadors[i];
            
                System.out.println("\n__________________________________________");
                System.out.println("És el torn del jugador " + (jugadorActual + 1) + ", " + nomJugadors[jugadorActual] + ".");
                
                if (tornsBloquejats[jugadorActual] > 0) {
                    System.out.println("Estàs bloquejat. Et queden " + tornsBloquejats[jugadorActual] + " torns sense jugar.");
                    tornsBloquejats[jugadorActual]--;
                } 

                else {
                    boolean tiradaValida = false;

                    while (!tiradaValida) {
                        System.out.print(">> ");
                        String entrada = sc.nextLine().trim();
                        
                        if (entrada.equalsIgnoreCase("tiro")) {
                            tiradaValida = true;
                        } else {
                            System.out.println("Has d'escriure 'tiro' per tirar els daus.");
                        }
                    }

                    int dau1, dau2, suma;

                    // A partir de la casella 60, només es fa servir un dau
                    if (casellaJugadors[jugadorActual] >= 60) {
                        dau1 = tirarDau();
                        dau2 = 0;
                        suma = dau1;
                        System.out.println("Has obtingut un " + dau1 + " = " + suma + ".");
                    } else {
                        dau1 = tirarDau();
                        dau2 = tirarDau();
                        suma = dau1 + dau2;
                        System.out.println("Has obtingut un " + dau1 + " i " + dau2 + " = " + suma + ".");
                    }

                    
                    if (casellaJugadors[jugadorActual] == 0) {
                        // Cas especial: primera tirada amb 3-6 (1+2)
                        if ((dau1 == 1 && dau2 == 2) || (dau1 == 2 && dau2 == 1)) {
                            casellaJugadors[jugadorActual] = 26;
                            System.out.println("De dado a dado y tiro porque me ha tocado.");
                            System.out.println("Avances fins la casella 26.");
                            boolean tornExtra = processarCasella(jugadorActual);
                            if (tornExtra) {
                                i--;
                            }
                        } 
                        // Cas especial: primera tirada amb 4-5 (2+3)
                        else if ((dau1 == 2 && dau2 == 3) || (dau1 == 3 && dau2 == 2)) {
                            casellaJugadors[jugadorActual] = 53;
                            System.out.println("De dado a dado y tiro porque me ha tocado.");
                            System.out.println("Avances fins la casella 53.");
                            boolean tornExtra = processarCasella(jugadorActual);
                            if (tornExtra) {
                                i--;
                            }
                        } else {
                            // Moviment normal
                            int novaCasella = casellaJugadors[jugadorActual] + suma;
                            
                            if (novaCasella > 63) {
                                novaCasella = 63 - (novaCasella - 63);
                            }
                            
                            casellaJugadors[jugadorActual] = novaCasella;
                            
                            if (novaCasella == 63) {
                                System.out.println("Avances fins la casella 63!");
                                finalDelJoc(jugadorActual);
                                finalJoc = true;
                            } else {
                                System.out.println("Avances fins la casella " + novaCasella + ".");
                                boolean tornExtra = processarCasella(jugadorActual);
                                if (tornExtra) {
                                    i--;
                                }
                            }
                        }
                    } else {
                        // Moviment normal per jugadors que ja no estan a la casella 0
                        int novaCasella = casellaJugadors[jugadorActual] + suma;
                        
                        if (novaCasella > 63) {
                            novaCasella = 63 - (novaCasella - 63);
                        }
                        
                        casellaJugadors[jugadorActual] = novaCasella;
                        
                        if (novaCasella == 63) {
                            System.out.println("Avances fins la casella 63!");
                            finalDelJoc(jugadorActual);
                            finalJoc = true;
                        } else {
                            System.out.println("Avances fins la casella " + novaCasella + ".");
                            boolean tornExtra = processarCasella(jugadorActual);
                            if (tornExtra) {
                                i--;
                            }
                        }
                    }
                }
                    
                
            }
        } while (!finalJoc);
        
        sc.close();
    }
    public void determinarOrdre() {
        System.out.println("\nDeterminem l'ordre dels jugadors llençant el dau...");
        int[] tirades = new int[numJugadors];
        
        for (int i = 0; i < numJugadors; i++) {
            tirades[i] = tirarDau();
            ordreJugadors[i] = i;
            System.out.println("El jugador " + nomJugadors[i] + " ha tret un " + tirades[i]);
            System.out.println("Prem enter per continuar...");
            sc.nextLine();
        }
        
        for (int i = 0; i < numJugadors - 1; i++) {
            for (int j = i + 1; j < numJugadors; j++) {
                if (tirades[ordreJugadors[j]] > tirades[ordreJugadors[i]]) {
                    int ordreTemp = ordreJugadors[i];
                    ordreJugadors[i] = ordreJugadors[j];
                    ordreJugadors[j] = ordreTemp;
                }
            }
        }
        
        System.out.println("\nOrdre de joc:");
        for (int i = 0; i < numJugadors; i++) {
            System.out.println((i + 1) + ". " + nomJugadors[ordreJugadors[i]]);
        }
        System.out.println("Prem enter per continuar...");
        sc.nextLine();
    }  

    
    public int tirarDau() {
        int tirada = (int)(Math.random() * 6) + 1;
        return tirada;
    }
    public void finalDelJoc(int guanyador) {
        System.out.println("Has guanyat al joc de l'Oca!");
        System.out.println("___\r\n" + //
                        "           , \" \"  .\r\n" + //
                        "         , '      e  ) `- ._\r\n" + //
                        "        /   ,  -._<.===-'\r\n" + //
                        "       /  /\r\n" + //
                        "      /  ;\r\n" + //
                        "     /  ;\r\n" + //
                        "    (  ._    _.-\"\" \"\"--..__, '    |\r\n" + //
                        "    <_    -\"\"                     \\\r\n" + //
                        "     < -                          :\r\n" + //
                        "      (__   <__.                  ;\r\n" + //
                        "        ._ ._  _                 /\r\n" + //
                        "          \\ ._  \\          _ . '\r\n" + //
                        "           \\ ._  \\_.-,   ,  '\r\n" + //
                        "            ._     / _, - '\r\n" + //
                        "             \"\"  ._,   '< <\r\n" + //
                        "                   | |   - -..._\r\n" + //
                        "                   | |      . - '\r\n" + //
                        "                   ; |__      \\ - .\r\n" + //
                        "                   \\   --<\r\n" + //
                        "                    ' . .<\r\n" + //
                        "                      ' -'\r\n" + //
                        "\r\n" + //
                        "\r\n" + //
                        "\r\n" + //
                        "              ");
                        System.out.println("Gràcies per jugar!");
    }
    public int llegirEnter() {
        boolean entradaCorrecta = false;
        int entrada = 0;
        while (!entradaCorrecta) {
            try {
                entrada = sc.nextInt();
                entradaCorrecta = true;

            } catch (InputMismatchException e) {
                System.out.println("Entrada no vàlida. Si us plau, introdueix un nombre enter.");
            }
            catch (Exception e) {
                System.out.println("S'ha produït un error inesperat. Si us plau, torna-ho a intentar.");
            } finally {
                sc.nextLine();
            }
        }
        return entrada;
    }
   public String llegirLletres() {
        boolean entradaCorrecta = false;
        String entrada = "";

        while (!entradaCorrecta) {
            entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("(!) Error: no es permet deixar el camp buit.");
            }
            else if (!entrada.matches("[a-zA-Z]+")) {
                System.out.println("(!) Error: només es permeten lletres, sense números ni espais.");
            }
            else {
                entradaCorrecta = true;
            }
        }

        return entrada;
    }
    
    public boolean processarCasella(int jugador) {
        int casella = casellaJugadors[jugador];
        boolean tornExtra = false;
        
        if (casella == 5 || casella == 9 || casella == 14 || casella == 18 || 
            casella == 23 || casella == 27 || casella == 32 || casella == 36 || 
            casella == 41 || casella == 45 || casella == 50 || casella == 54 || casella == 59) // Caselles d'Oca
        {
            
            System.out.println("Casella nº " + casella + ": Oca. De oca en oca y tiro porque me toca.");
            
            int seguentOca = trobarSeguentOca(casella);
            if (seguentOca != -1) {
                casellaJugadors[jugador] = seguentOca;
                System.out.println("Avances fins la casella " + seguentOca + ".");
                tornExtra = processarCasella(jugador);
                tornExtra = true;
            }
        } else if (casella == 6) {
            System.out.println("Casella nº 6: Pont. De puente a puente y tiro porque me lleva la corriente.");
            casellaJugadors[jugador] = 12;
            System.out.println("Avances fins la casella 12.");
            tornExtra = processarCasella(jugador);
            tornExtra = true;
        } else if (casella == 12) {
            System.out.println("Casella nº 12: Pont. De puente a puente y tiro porque me lleva la corriente.");
            casellaJugadors[jugador] = 6;
            System.out.println("Avances fins la casella 6.");
            tornExtra = processarCasella(jugador);
            tornExtra = true;
        } else if (casella == 19) {
            System.out.println("Casella nº 19: Fonda. Una jugada sense moure's.");
            tornsBloquejats[jugador] = 1;
        } else if (casella == 31) {
            System.out.println("Casella nº 31: Pou. Dues jugades sense moure's.");
            tornsBloquejats[jugador] = 2;
        } else if (casella == 42) {
            System.out.println("Casella nº 42: Laberint. Tornes a la casella 39.");
            casellaJugadors[jugador] = 39;
        } else if (casella == 52) {
            System.out.println("Casella nº 52: Presó. Tres torns sense moure's.");
            tornsBloquejats[jugador] = 3;
        } else if (casella == 58) {
            System.out.println("Casella nº 58: La mort. Tornes al principi.");
            casellaJugadors[jugador] = 0;
        }
        
        return tornExtra;
    }

    public int trobarSeguentOca(int casellaActual) {
        int[] oques = {5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59};
        for (int oca : oques) {
            if (oca > casellaActual) {
                return oca;
            }
        }
        return -1;
    }
}