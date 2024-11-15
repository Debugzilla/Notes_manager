import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NoteManagerApp {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NoteManager manager = new NoteManager();
        boolean noteManager = false;
        boolean exit = false;
        int opcion = 0;

        System.out.print("Bienvenido al gestor de notas");
        System.out.println(" ");
        System.out.println(" --------------------------------------");

        do {
            DisplayMenu();
            System.out.println();
            System.out.print("Selecciona una de las opciones posibles: ");

            opcion = scanner.nextInt();
            System.out.println();

            switch (opcion){
                case 1:

                    System.out.println("Has seleccionado añadir notas");
                    NoteManager.addNote(scanner);
                    break;
                case 2:
                    System.out.println("2. Has seleccionado editar notas");
                    NoteManager.editNote(scanner);
                    break;
                case 3:
                    System.out.println("3. Has seleccionado eliminar notas");
                    NoteManager.deleteNote(scanner);
                    break;
                case 4:
                    System.out.println("4. Has seleccionado ver notas");
                    NoteManager.viewNotes(scanner);
                    break;
                case 5:
                    System.out.println("5. Has selecionado ver notas por categoria");
                    NoteManager.viewNotesByCategory(scanner);
                    break;
                case 6:
                    System.out.println("6. Has seleccionado guardar a la base de datos");
                    NoteManager.saveToDatabase(scanner);
                    break;
                case 7:
                    System.out.println("7. Has seleccionado cargar de la base de datos");
                    NoteManager.loadFromDatabase(scanner);
                    break;
                case 0:
                    System.out.println("0. Saliendo del programa....");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");

            }

        }while (opcion != 0);

    }

//Metodo que muestra el menu principal
    public static void DisplayMenu() {
        System.out.println();
        System.out.println("Puedes realizar las siguientes operaciones:");
        System.out.println("1. Añadir notas");
        System.out.println("2. Editar notas");
        System.out.println("3. Eliminar notas");
        System.out.println("4. Ver notas");
        System.out.println("5. Ver notas por categoría");
        System.out.println("6. Guardar a la base de datos");
        System.out.println("7. Cargar de la base de datos");
        System.out.println("0. Salir del programa");


        }

    }


