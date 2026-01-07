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
                System.out.println("\n En procés...");
                    
                
            }
        } while (!finalJoc);
        
        finalDelJoc();
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
    }  

    
    public int tirarDau() {
        int tirada = (int)(Math.random() * 6) + 1;
        return tirada;
    }
    public void finalDelJoc() {
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
}
