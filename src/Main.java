import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creo una lista vacía donde voy a guardar todos los pilotos
        ArrayList<Piloto> pilotos = new ArrayList<>();
        
        // Llamo al método que lee el archivo y mete los pilotos en la lista
        cargarDatos(pilotos);
        
        // Llamo al método para que el usuario pueda añadir un piloto nuevo
        aniadirPiloto(pilotos);
        
        // Muestro todos los pilotos que hay en la lista
        mostrarDatos(pilotos);
        
        // Busco un piloto por su nombre
        buscarPiloto(pilotos);
        
        // Guardo todos los datos en un archivo nuevo
        guardarDatos(pilotos);
    }
    
    // Este método lee el archivo "pilotos_f1.txt" y guarda los pilotos en la lista
    public static void cargarDatos(ArrayList<Piloto> pilotos) {
        // Intento abrir el archivo para leerlo
        try (BufferedReader reader = new BufferedReader(new FileReader("pilotos_f1.txt"))) {
            String linea;
            
            // Voy leyendo línea por línea hasta que no queden más
            while ((linea = reader.readLine()) != null) {
                // Cada línea tiene el formato: nombre;escuderia;puntos
                // Con split separo los tres datos usando el punto y coma
                String[] campos = linea.split(";");
                
                // Creo un nuevo piloto con los datos que he sacado de la línea
                // campos[0] = nombre, campos[1] = escudería, campos[2] = puntos
                // Ojo: convierto campos[2] a número entero porque en el archivo viene como texto
                Piloto piloto = new Piloto(campos[0], campos[1], Integer.parseInt(campos[2]));
                
                // Añado el piloto a la lista
                pilotos.add(piloto);
            }
        } catch (IOException e) {
            // Si hay algún error al leer el archivo, lo digo por pantalla
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    // Este método muestra por pantalla todos los pilotos que hay en la lista
    public static void mostrarDatos(ArrayList<Piloto> pilotos) {
        // Recorro la lista entera con un for-each
        for (Piloto piloto : pilotos) {
            // Voy mostrando cada dato del piloto
            System.out.println(piloto.getNombre());
            System.out.println(piloto.getEscuderia());
            System.out.println(piloto.getPuntos());
            System.out.println(" "); // Dejo una línea en blanco entre piloto y piloto
        }
    }

    // Este método mira si un piloto ya está en la lista
    // Devuelve true si lo encuentra, false si no
    public static boolean comprobarPiloto(ArrayList<Piloto> pilotos, String piloto) {
        boolean encontrado = false; // Empiezo suponiendo que no está
        
        // Recorro todos los pilotos de la lista
        for (Piloto p : pilotos) {
            // Si el nombre del piloto de la lista es igual al que estoy buscando
            if (p.getNombre().equals(piloto)) {
                encontrado = true; // Marco que lo he encontrado
                // Podría poner un break aquí para no seguir buscando, pero así también funciona
            }
        }
        return encontrado; // Devuelvo si lo encontré o no
    }

    // Este método pide datos al usuario para añadir un piloto nuevo
    public static void aniadirPiloto(ArrayList<Piloto> pilotos) {
        Scanner sc = new Scanner(System.in);
        
        // Pido el nombre
        System.out.println("Escribe el nombre del piloto");
        String nombreP = sc.nextLine();
        
        // Antes de añadirlo, compruebo si ya existe
        if (comprobarPiloto(pilotos, nombreP)) {
            System.out.println("El piloto ya existe"); // Si existe, no lo añado
        } else {
            // Si no existe, pido los demás datos
            System.out.println("Escribe la escuderia del piloto");
            String escuderiaP = sc.nextLine();
            
            System.out.println("Escribe los puntos del piloto");
            int puntosP = sc.nextInt();
            
            // Creo el nuevo piloto y lo meto en la lista
            pilotos.add(new Piloto(nombreP, escuderiaP, puntosP));
        }
    }

    // Este método busca un piloto y dice si existe o no
    public static void buscarPiloto(ArrayList<Piloto> pilotos) {
        Scanner sc = new Scanner(System.in);
        
        // Pido el nombre del piloto a buscar
        System.out.println("Escribe el nombre del piloto");
        String nombreP = sc.nextLine();
        
        // Compruebo si existe usando el método que ya tenía
        if (comprobarPiloto(pilotos, nombreP)) {
            System.out.println("El piloto " + nombreP + " existe");
        } else {
            System.out.println("No existe el piloto");
        }
    }

    // Este método guarda todos los pilotos en un archivo nuevo
    public static void guardarDatos(ArrayList<Piloto> pilotos) {
        // Intento crear/abrir el archivo para escribir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pilotos_f1_nuevo.txt"))) {
            
            // Recorro todos los pilotos de la lista
            for (Piloto piloto : pilotos) {
                // Escribo una línea con el formato nombre;escuderia;puntos
                writer.write(piloto.getNombre() + ";" + piloto.getEscuderia() + ";" + piloto.getPuntos());
                writer.newLine(); // Salto de línea después de cada piloto
            }
            
        } catch (IOException e) {
            // Si hay error al guardar, lo digo
            System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
        }
    }
}
