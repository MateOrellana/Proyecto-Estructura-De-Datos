/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.contactos.telefonicos;

import java.util.Scanner;

/**
 *
 * @author mateo
 */
public class ArbolContactos {
    private Nodo raiz;

    public void agregarContacto(Contacto nuevoContacto) {
        raiz = agregarContactoRecursivo(raiz, nuevoContacto);
    }

    private Nodo agregarContactoRecursivo(Nodo nodoActual, Contacto nuevoContacto) {
        if (nodoActual == null) {
            return new Nodo(nuevoContacto);
        }

        int comparacion = nuevoContacto.getNombre().compareToIgnoreCase(nodoActual.getContacto().getNombre());

        if (comparacion < 0) {
            nodoActual.setIzquierdo(agregarContactoRecursivo(nodoActual.getIzquierdo(), nuevoContacto));
        } else if (comparacion > 0) {
            nodoActual.setDerecho(agregarContactoRecursivo(nodoActual.getDerecho(), nuevoContacto));
        } else {
            // Si el nombre ya existe, se le pregunta al usuario si quiere actualizar el numero
            System.out.println("El nombre ya existe en la lista. ¿Desea actualizar el número? ");
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) {
                nodoActual.getContacto().setNumero(nuevoContacto.getNumero());
            }
        }

        return nodoActual;
    }

    public Contacto buscarContacto(String nombre) {
        return buscarContactoRecursivo(raiz, nombre);
    }

    private Contacto buscarContactoRecursivo(Nodo nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(nodoActual.getContacto().getNombre());

        if (comparacion == 0) {
            return nodoActual.getContacto();
        } else if (comparacion < 0) {
            return buscarContactoRecursivo(nodoActual.getIzquierdo(), nombre);
        } else {
            return buscarContactoRecursivo(nodoActual.getDerecho(), nombre);
        }
    }

    public void eliminarContacto(String nombre) {
        raiz = eliminarContactoRecursivo(raiz, nombre);
    }

    private Nodo eliminarContactoRecursivo(Nodo nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(nodoActual.getContacto().getNombre());

        if (comparacion < 0) {
            nodoActual.setIzquierdo(eliminarContactoRecursivo(nodoActual.getIzquierdo(), nombre));
        } else if (comparacion > 0) {
            nodoActual.setDerecho(eliminarContactoRecursivo(nodoActual.getDerecho(), nombre));
        } else {
            // Nodo encontrado, proceder con la eliminación.
            if (nodoActual.getIzquierdo() == null) {
                return nodoActual.getDerecho();
            } else if (nodoActual.getDerecho() == null) {
                return nodoActual.getIzquierdo();
            } else {
                Nodo sucesor = encontrarMinimo(nodoActual.getDerecho());
                nodoActual.setContacto(sucesor.getContacto());
                nodoActual.setDerecho(eliminarContactoRecursivo(nodoActual.getDerecho(), sucesor.getContacto().getNombre()));
            }
        }

        return nodoActual;
    }

    private Nodo encontrarMinimo(Nodo nodoActual) {
        while (nodoActual.getIzquierdo() != null) {
            nodoActual = nodoActual.getIzquierdo();
        }
        return nodoActual;
    }

    // Métodos de recorrido

    public void preorden() {
        preordenRecursivo(raiz);
    }

    private void preordenRecursivo(Nodo nodoActual) {
        if (nodoActual != null) {
            System.out.println(nodoActual.getContacto());
            preordenRecursivo(nodoActual.getIzquierdo());
            preordenRecursivo(nodoActual.getDerecho());
        }
    }

    public void inorden() {
        inordenRecursivo(raiz);
    }

    private void inordenRecursivo(Nodo nodoActual) {
        if (nodoActual != null) {
            inordenRecursivo(nodoActual.getIzquierdo());
            System.out.println(nodoActual.getContacto());
            inordenRecursivo(nodoActual.getDerecho());
        }
    }

    public void postorden() {
        postordenRecursivo(raiz);
    }

    private void postordenRecursivo(Nodo nodoActual) {
        if (nodoActual != null) {
            postordenRecursivo(nodoActual.getIzquierdo());
            postordenRecursivo(nodoActual.getDerecho());
            System.out.println(nodoActual.getContacto());
        }
    }
}
