/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.ups.contactos.telefonicos;

import java.util.Scanner;

/**
 *
 * @author mateo
 */
public class ContactosTelefonicos {

    public static void main(String[] args) {
        ArbolContactos arbol = new ArbolContactos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú :");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Mostrar contactos (inorden)");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el número de teléfono: ");
                    String numero = scanner.nextLine();
                    Contacto nuevoContacto = new Contacto(nombre, numero);
                    arbol.agregarContacto(nuevoContacto);
                    System.out.println("Contacto agregado correctamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del contacto a buscar: ");
                    String nombreBuscado = scanner.nextLine();
                    Contacto contactoEncontrado = arbol.buscarContacto(nombreBuscado);
                    if (contactoEncontrado != null) {
                        System.out.println("Contacto encontrado: " + contactoEncontrado);
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del contacto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    arbol.eliminarContacto(nombreEliminar);
                    System.out.println("Contacto eliminado correctamente.");
                    break;
                case 4:
                    System.out.println("Lista de contactos (orden inorden):");
                    arbol.inorden();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
