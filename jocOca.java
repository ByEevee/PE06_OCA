import java.util.InputMismatchException;
import java.util.Scanner;

public class jocOca {
    static Scanner sc = new Scanner(System.in);
    int numJugadors = 0;
    int [] casellaJugadors = new int [numJugadors];
    String [] nomJugadors = new String [numJugadors];
    int [] ordreTirades = new int [numJugadors];
    
    public static void main(String[] args) {
        jocOca joc = new jocOca();
        joc.jocPrincipal();
    }
    public void jocPrincipal() {
        System.out.println("=========================================");
        System.out.println("       Benvinguts al joc de l'Oca!       ");
        System.out.println("=========================================");
        System.out.println();
        boolean finalJoc = false;
        do {
            System.out.println("Quants jugadors hi ha?");
            numJugadors = llegirEnter();
            
            for (int i = 0; i < numJugadors; i++) {
                //Inicialitzem totes les posicions dels jugadors a la casella 0
                casellaJugadors[i] = 0;
            }
            for (int i = 0; i < numJugadors; i++) {
                //Posem nom a tots els jugadors
                System.out.println("Nom del jugador " + (i + 1));
                nomJugadors[i] = llegirLletres();
            }
            System.out.println("Comença el joc!");
            System.out.println("Determinem el ordre dels jugadors llençant el dau...");
            ordreJugadors();
            
        } while (finalJoc);
        
        

        finalDelJoc();
    }
    public void ordreJugadors() {
        System.out.println("Tirant el dau per determinar l'ordre dels jugadors...");
        int [] resultatTirades = new int [numJugadors];
        for (int i = 0; i < numJugadors; i++) {
            int tirada = tirarDau();
            System.out.println("El jugador " + nomJugadors[i] + " ha tret un " + tirada);
            resultatTirades[i] = tirada;
        }
        for (int i = 0; i < numJugadors; i++) {
            if (resultatTirades[i]>resultatTirades[i+1]|| resultatTirades [i] > resultatTirades[ i+2]) {
                ordreTirades[i]=1;
            }
            else if (resultatTirades [i] < resultatTirades[ i-1] || resultatTirades [i] < resultatTirades[ i-2]) {
                ordreTirades[i]=1;
            }
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
            try {
                entrada = sc.nextLine().trim();
                if (!entrada.matches("[a-zA-Z]+")) {
                    throw new InputMismatchException();
                }
                else if (entrada.equals("")) {
                    System.out.println("No es permeten espais buits. Si us plau, introdueix un nom vàlid.");
                }
                else{
                    entradaCorrecta = true;
                }
                
            }catch (Exception e) {
                System.out.println("S'ha produït un error inesperat. Si us plau, torna-ho a intentar.");
            } finally {
                sc.nextLine();
            }
        }
        return entrada;
    
    }
}
