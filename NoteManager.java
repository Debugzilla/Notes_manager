import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


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

    public static void saveToDatabase(Scanner scanner) {
        Connection connection = null;

        try {
            // Registrar el controlador JDBC para SQLite
            Class.forName("org.sqlite.JDBC"); // Carga el controlador JDBC de SQLite

            // URL de conexión a la base de datos SQLite
            String url = "jdbc:sqlite:notas.db"; // Asegúrate de que el prefijo sea "jdbc:sqlite:"

            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false); // Deshabilitar autocommit
            System.out.println("Conectando a la base de datos en: " + url);

            // Imprimir el tamaño de la lista de notas
            System.out.println("Número de notas a insertar: " + notes.size());

            // Preparar la sentencia SQL parametrizada para insertar notas
            String InsertSQL = "INSERT INTO notes (title, content, category) VALUES (?, ?, ?);";

            // Preparar la sentencia SQL utilizando la conexión a la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(InsertSQL);

            // Iterar sobre las notas que se van a insertar
            for (Note note : notes) {
                // Verifica que los valores no sean null
                if (note.getTitle() == null || note.getContent() == null || note.getCategory() == null) {
                    System.out.println("Algunos valores son NULL. No se insertará esta nota.");
                    continue;
                }

                // Asignar valores a los parámetros de la sentencia
                preparedStatement.setString(1, note.getTitle());  // Parámetro 1: Título
                preparedStatement.setString(2, note.getContent()); // Parámetro 2: Contenido
                preparedStatement.setString(3, note.getCategory()); // Parámetro 3: Categoría
                System.out.println("Insertando nota: " + note.getTitle() + ", " + note.getContent() + ", " + note.getCategory());

                // Ejecutar la sentencia SQL para insertar la nota
                preparedStatement.executeUpdate();
            }

            // Commit después de todas las inserciones
            connection.commit();
            System.out.println("Notas guardadas correctamente en la base de datos.");

            // Cerrar el objeto PreparedStatement después de su uso
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println("Error al guardar la nota en la base de datos: " + e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();  // Revertir cambios si hay error
                    System.out.println("Rollback realizado.");
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Error al hacer rollback: " + rollbackEx.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }


}

