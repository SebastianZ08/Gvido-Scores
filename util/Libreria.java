package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Libreria {

    public static String pedirString(Scanner sc, String palabra1, String palabra2) {
        String palabra;
        boolean validado = false;
        do{
            try {
                System.out.println("Dime el/la " + palabra1 + " de " + palabra2);
        palabra = sc.nextLine();
        if(!palabra.isEmpty()){
            validado = true;
        }
            } catch (InputMismatchException e) {
                palabra = null;
                sc.nextLine();
                System.out.println("Error");
            }
        }while(!validado);
        return palabra;
    }

    

    /**
     * Asks the user to enter an integer with a descriptive prompt.
     *
     * @param sc       Scanner used for input
     * @param palabra1 The type of data being requested
     * @param palabra2 Additional context for the prompt
     * @return The integer entered by the user
     */
    public static int pedirInt(Scanner sc, String palabra1, String palabra2) {
        int numero = 0;
        do {
            try {
                System.out.println("Dime el/la " + palabra1 + " de " + palabra2);
                numero = sc.nextInt();
            } catch (InputMismatchException e) {
                numero = 0;
                sc.nextLine();
                System.out.println("Debes introducir un número entero");
            }
        } while (numero <= 0);
        sc.nextLine();

        return numero;
    }

    /**
     * Asks the user to enter a double with a descriptive prompt.
     *
     * @param sc       Scanner used for input
     * @param palabra1 The type of data being requested
     * @param palabra2 Additional context for the prompt
     * @return The double entered by the user
     */
    public static double pedirDouble(Scanner sc, String palabra1, String palabra2) {
        double numero = 0.0;
        do {
            try {
                System.out.println("Dime el/la " + palabra1 + " de " + palabra2);
                numero = sc.nextDouble();
            } catch (InputMismatchException e) {
                numero = 0.0;
                sc.nextLine();
                System.out.println("Introduce un número de tipo double");
            }

        } while (numero <= 0);
        sc.nextLine();
        return numero;
    }

    /**
     * Validates whether a given email string matches a standard email format
     * using a regular expression.
     *
     * @param email the email string to validate
     * @return true if the email format is valid, false otherwise
     * 
     * @author Sebastián Zuluaga
     */
    public static boolean comprobarEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (email.matches(emailRegex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates whether a given dni string matches a standard dni format
     * using a regular expression.
     *
     * @param dni the dni string to validate
     * @return true if the dni format is valid, false otherwise
     * 
     * @author Sebastián Zuluaga
     */
    public static boolean comprobarDNI(String dni) {
        String dniRegex = "^[0-9]{8}[A-Z]$";
        if (dni.matches(dniRegex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates whether a given name string matches a standard name format
     * using a regular expression.
     *
     * @param nombre the nombre string to validate
     * @return true if the dni format is valid, false otherwise
     * 
     * @author Sebastián Zuluaga
     */
    public static boolean comprobarNombre(String nombre){
        String nombreRegex = "^[A-Za-z]{1,}$";
        if(nombre.matches(nombreRegex)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Validates whether a given telephone number string matches a standard number format
     * using a regular expression.
     *
     * @param telefono the telephone string to validate
     * @return true if the telephone number format is valid, false otherwise
     * 
     * @author Sebastián Zuluaga
     */
    public static boolean comprobarTelefono(String telefono){
        String telefonoRegex = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$";
        if(telefono.matches(telefonoRegex)){
            return true;
        }else{
            return false;
        }
    }
}