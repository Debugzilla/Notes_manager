import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteManager {
    //Esta clase será responsable de gestionar las notas.

    private static List<Note> notes; // Lista para almacenar las notas

    //constructor
    public NoteManager() {
        //Inicializacion de la lista de notas
        notes = new ArrayList<>();
    }


    public static void addNote(Scanner scanner) {
        // Solicitar el título de la nota:
        System.out.println("Por favor, introduzca el título de la nota: ");

        // Leer la entrada del usuario y guardarla en una variable title.
        // Es importante usar nextLine() aquí para que lea toda la línea de texto
        scanner.nextLine(); //limpiamos el buffer
        String title = scanner.nextLine();


        // Solicitar el contenido de la nota:
        System.out.println("Por favor, introduzca el contenido de la nota: ");
        // Leer el contenido de la nota
        String content = scanner.nextLine();


        // Solicitar la categoría de la nota:
        System.out.println("Agrega una categoría: ");
        String category = scanner.nextLine();

        // Si el usuario no proporciona una categoría, se puede dejar como null
        if (category.isEmpty()) {
            category = null; // O asignar un valor por defecto
        }

        // Obtener la fecha actual:
        LocalDate date = LocalDate.now();

        // Crear una nueva instancia de Note:
        Note newNote = new Note(title, content, category, date);

        // Agregar la nota a la lista de notas
        notes.add(newNote);

        System.out.println("Nota agregada correctamente.");
    }

    // Método para editar una nota existente
    public static void editNote(Scanner scanner) {
        // Pedir al usuario que seleccione la nota a editar mediante su título.
        System.out.println("Introduce el título de la nota a modificar: ");
        scanner.nextLine();
        String title = scanner.nextLine();

        // Buscar la nota por el título
        Note noteToEdit = findNoteByTitle(title);

        if (noteToEdit != null) {
            // Si la nota fue encontrada, pedir al usuario que modifique los campos
            System.out.println("Nota encontrada. ¿Qué te gustaría cambiar?");
            System.out.println("1. Título");
            System.out.println("2. Contenido");
            System.out.println("3. Categoría");
            System.out.println("4. Cancelar");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Introduce el nuevo título: ");
                    noteToEdit.setTitle(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Introduce el nuevo contenido: ");
                    noteToEdit.setContent(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Introduce la nueva categoría: ");
                    String category = scanner.nextLine();
                    if (!category.isEmpty()) {
                        noteToEdit.setCategory(category);
                    } else {
                        noteToEdit.setCategory(null); // Si el campo está vacío, asignamos null
                    }
                    break;
                case 4:
                    System.out.println("Edición cancelada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            // Actualizar la fecha de modificación
            //noteToEdit.setDate(LocalDate.now());

            System.out.println("Nota modificada correctamente.");
        } else {
            System.out.println("No se encontró una nota con ese título.");
        }
    }

    // Método para buscar una nota por su título
    private static Note findNoteByTitle(String title) {
        for (Note note : notes) {
            if (note.getTitle().equalsIgnoreCase(title)) {
                return note;
            }
        }
        return null; // Retorna null si no encuentra la nota

    }

    public static void deleteNote(Scanner scanner){
        //Método para eliminar una nota en NoteManager
        //Eliminar la nota seleccionada de la lista notes.
        // Pedir al usuario que seleccione la nota a eliminar mediante su título.
        System.out.println("Introduce el título de la nota a eliminar: ");
        scanner.nextLine();
        String title = scanner.nextLine();


        // Buscar la nota por el título
        Note noteToDelete = findNoteByTitle(title);

        if(noteToDelete != null){
            notes.remove(noteToDelete);
            System.out.println("Nota eliminada correctamente.");
        } else{
            System.out.println("No se encontró una nota con este titulo");
        }


    }
    public static void viewNotes(Scanner scanner){


        //Si la lista de notas está vacía:
        if(notes != null && !notes.isEmpty()){
            for(Note nota : notes){
                System.out.println("Titulo: "+nota.getTitle());
                System.out.println("Contenido: "+nota.getContent());
                System.out.println("Categoria: "+nota.getCategory());


            }

        }else{
        System.out.println("No hay notas disponibles");
        }

    }

    // Método para buscar una nota por su título
    private static Note findNoteByCategory(String category) {
        for (Note note : notes) {
            if (note.getCategory().equalsIgnoreCase(category)) {
                return note;
            }
        }
        return null; // Retorna null si no encuentra la nota

    }



    public static void viewNotesByCategory(Scanner scanner){
        //Método para ver notas por categoría en NoteManager (opcional)
        boolean found = false;

        //Pedir al usuario una categoría.
        System.out.println("Introduce la categoria: ");
        scanner.nextLine();
        String category = scanner.nextLine();

        //Si la lista de notas está vacía:
        if(notes != null && !notes.isEmpty()) {
            for (Note nota : notes) {
                if (nota.getCategory().equals(category)) {
                    System.out.println("Las notas coincidentes con la categoría " + category + ":");
                    System.out.println(nota);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No se encontraron notas con la categoría " + category);
            }
        }else{
            System.out.println("La lista de notas está vacía");

        }


    }

}

