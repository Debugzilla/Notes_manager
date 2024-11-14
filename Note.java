import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Note { // Creamos la clase Note
    String title;
    String content;
    String category;
    LocalDate date;


    // Definimos el constructor para inicializar una nota
    public Note(String title, String content, String category, LocalDate date) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
    }

    //Definir métodos para obtener y establecer valores (getters y setters):
    //Para title: Crear getTitle y setTitle.
    // Getter y setter para 'nombre'
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.date = date;
    }
    //Para content: Crear getContent y setContent.

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.date = date;
    }
    //Para category: Crear getCategory y setCategory.

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.date = date;
    }
    //En cada método setter, actualizar la propiedad date a la fecha actual para registrar la modificación.

    //Definir un método privado updateDate:
    private void updateDate(LocalDate fecha) {
        this.date = fecha;

    }
    //Asignar la fecha actual a la propiedad date.

    //Definir el método toString para representar la nota como texto:

    public void showNote() {
        System.out.println("Título: " + title);
        System.out.println("Contenido: " + content);
        System.out.println("Categoría: " + category);
        System.out.println("Fecha: " + date);


    }

    // Método para representar la nota como una cadena de texto
    public String toString() {
        return "Título: " + title + "\n" +
                "Contenido: " + content + "\n" +
                "Categoría: " + category + "\n" +
                "Fecha: " + date;
        //Devolver un formato de texto que incluya title, content, category (si existe) y date.


    }
}