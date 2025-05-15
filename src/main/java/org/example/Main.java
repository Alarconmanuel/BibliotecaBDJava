package org.example;

import org.example.dao.*;
import org.example.model.Autor;
import org.example.model.Libro;
import org.example.model.Miembro;
import org.example.model.Prestamo;
import org.example.utils.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            AutorDao autor = new AutorDaoImpl(connection);
            LibroDao libro = new LibroDaoImpl(connection);
            MiembroDao miembro = new MiembroDaoImpl(connection);
            PrestamoDao prestamo = new PrestamoDaoImpl(connection);

            Scanner teclado = new Scanner(System.in);
            int opcionPrincipal, op;

            do {
                System.out.println("BIBLIOTECA");
                System.out.println("¿A qué tabla desea acceder?");
                System.out.println("""
                1. AUTORES
                2. LIBROS
                3. MIEMBROS
                4. PRÉSTAMOS
                0. SALIR
                """);

                opcionPrincipal = teclado.nextInt();

                switch (opcionPrincipal) {
                    case 1 -> {
                        do {
                            System.out.println("GESTIONAR AUTORES");
                            System.out.println("""
                                    1. AGREGAR
                                    2. CONSULTAR
                                    3. EDITAR
                                    4. BORRAR
                                    5. VER TODOS
                                    0. VOLVER
                                    """);
                            op = teclado.nextInt();
                            switch (op) {
                                case 1 -> {
                                    System.out.print("ID: "); int id = teclado.nextInt();
                                    System.out.print("Nombre: "); String nombre = teclado.next();
                                    System.out.print("Apellido: "); String apellido = teclado.next();
                                    System.out.print("Nacionalidad: "); String nacion = teclado.next();
                                    autor.agregarAutor(new Autor(id, nombre, apellido, nacion));
                                    System.out.println("Autor agregado.");
                                }
                                case 2 -> {
                                    System.out.print("ID: "); Autor a = autor.obtenerAutor(teclado.nextInt());
                                    System.out.println(a != null ? a : "No encontrado.");
                                }
                                case 3 -> {
                                    System.out.print("ID: "); int id = teclado.nextInt(); teclado.next();
                                    Autor a = autor.obtenerAutor(id);
                                    if (a != null) {
                                        System.out.print("Nuevo nombre: "); a.setNombre(teclado.next());
                                        System.out.print("Nuevo apellido: "); a.setApellido(teclado.next());
                                        System.out.print("Nueva nacionalidad: "); a.setNacionalidad(teclado.next());
                                        autor.actualizarAutor(a); System.out.println("Actualizado.");
                                    } else System.out.println("No encontrado.");
                                }
                                case 4 -> {
                                    System.out.print("ID: "); autor.eliminarAutor(teclado.nextInt()); System.out.println("Eliminado.");
                                }
                                case 5 -> autor.listarAutores().forEach(System.out::println);
                            }
                        } while (op != 0);
                    }

                    case 2 -> {
                        int oplb;
                        do {
                            System.out.println("GESTIONAR LIBROS");
                            System.out.println("""
                                    1. AGREGAR
                                    2. CONSULTAR
                                    3. EDITAR
                                    4. BORRAR
                                    5. VER TODOS
                                    0. VOLVER
                                    """);
                            oplb = teclado.nextInt();
                            switch (oplb) {
                                case 1 -> {
                                    System.out.print("ID: ");
                                    int id = teclado.nextInt();
                                    teclado.nextLine();
                                    System.out.print("Título: ");
                                    String titulo = teclado.nextLine();
                                    System.out.print("Género: ");
                                    String genero = teclado.nextLine();
                                    System.out.print("ID autor: ");
                                    int autorId = teclado.nextInt();
                                    teclado.nextLine();
                                    libro.agregarLibro(new Libro(id, titulo, genero, autorId));
                                    System.out.println("Libro registrado");
                                }

                                case 2 -> {
                                    System.out.print("ID: "); Libro l = libro.leer(teclado.nextInt());
                                    System.out.println(l != null ? l : "No encontrado");
                                }
                                case 3 -> {
                                    System.out.print("ID: "); int id = teclado.nextInt();
                                    Libro l = libro.leer(id);
                                    if (l != null) {
                                        System.out.print("Nuevo título: "); l.setTitulo(teclado.next());
                                        System.out.print("Nuevo género: "); l.setGenero(teclado.next());
                                        System.out.print("Nuevo ID autor: "); l.setAutorId(teclado.nextInt()); teclado.next();
                                        libro.actualizarLibro(l); System.out.println("Libro actualizado");
                                    } else System.out.println("No encontrado");
                                }

                                case 4 -> {
                                    System.out.print("ID: "); libro.eliminarLibro(teclado.nextInt()); System.out.println("Eliminado");
                                }
                                case 5 -> libro.listarLibro().forEach(System.out::println);

                                default -> System.out.println("Opción inválida");
                            }
                        } while (oplb != 0);
                    }

                    case 3 -> {
                        int opm;
                        do {
                            System.out.println("GESTIONAR MIEMBROS");
                            System.out.println("""
                                     1. AGREGAR
                                     2. CONSULTAR
                                     3. EDITAR
                                     4. BORRAR
                                     5. VER TODOS
                                     0. VOLVER
                                    """);
                            opm = teclado.nextInt();
                            switch (opm) {
                                case 1 -> {
                                    System.out.print("ID: ");
                                    int id = teclado.nextInt();
                                    System.out.print("Nombre: ");
                                    String nombre = teclado.next();
                                    System.out.print("Apellido: ");
                                    String apellido = teclado.next();
                                    System.out.print("Fecha inscripción (YYYY-MM-DD): ");
                                    LocalDate fecha = LocalDate.parse(teclado.next());

                                    miembro.agregarMiembro(new Miembro(id, nombre, apellido, fecha));
                                    System.out.println("Miembro agregado");
                                }
                                case 2 -> {
                                    System.out.print("ID: ");
                                    Miembro m = miembro.obtenerMiembro(teclado.nextInt());
                                    System.out.println(m != null ? m : "No encontrado");
                                }
                                case 3 -> {
                                    System.out.print("ID: ");
                                    int id = teclado.nextInt();
                                    Miembro m = miembro.obtenerMiembro(id);
                                    if (m != null) {
                                        System.out.print("Nuevo nombre: ");
                                        m.setNombre(teclado.next());
                                        System.out.print("Nuevo apellido: ");
                                        m.setApellido(teclado.next());
                                        System.out.print("Nueva fecha (YYYY-MM-DD): ");
                                        m.setFechaInscripcion(LocalDate.parse(teclado.next()));

                                        miembro.actualizarMiembro(m);
                                        System.out.println("→ Actualizado.");
                                    } else {
                                        System.out.println("No encontrado.");
                                    }
                                }
                                case 4 -> {
                                    System.out.print("ID a borrar: ");
                                    miembro.eliminarMiembro(teclado.nextInt());
                                    System.out.println("Eliminado");
                                }
                                case 5 -> miembro.listarMiembros().forEach(System.out::println);

                                default -> System.out.println("Opción inválida");
                            }
                        } while (opm != 0);
                    }

                    case 4 -> {
                        int opp;
                        do {
                            System.out.println("GESTIONAR PRESTAMOS");
                            System.out.println("""
                                    1. AGREGAR
                                    2. CONSULTAR
                                    3. EDITAR
                                    4. BORRAR
                                    5. VER TODOS
                                    0. VOLVER
                                    """);
                            opp = teclado.nextInt();
                            switch (opp) {
                                case 1 -> {
                                    System.out.print("ID: ");
                                    int id = teclado.nextInt();
                                    System.out.print("ID libro: ");
                                    int libroId = teclado.nextInt();
                                    System.out.print("ID miembro: ");
                                    int miembroId = teclado.nextInt();
                                    System.out.print("Fecha préstamo (YYYY-MM-DD): ");
                                    LocalDate f1 = LocalDate.parse(teclado.next());
                                    System.out.print("Fecha devolución (YYYY-MM-DD): ");
                                    LocalDate f2 = LocalDate.parse(teclado.next());
                                    prestamo.agregarPrestamo(new Prestamo(id, java.sql.Date.valueOf(f1), java.sql.Date.valueOf(f2), libroId, miembroId));
                                    System.out.println("Préstamo agregado");
                                }
                                case 2 -> {
                                    System.out.print("ID a buscar: ");
                                    Prestamo p = prestamo.obtenerPrestamo(teclado.nextInt());
                                    System.out.println(p != null ? p : "No encontrado.");
                                }
                                case 3 -> {
                                    System.out.print("ID a editar: ");
                                    int id = teclado.nextInt();
                                    Prestamo p = prestamo.obtenerPrestamo(id);

                                    if (p != null) {
                                        System.out.print("Nuevo ID libro: ");
                                        p.setLibroId(teclado.nextInt());

                                        System.out.print("Nuevo ID miembro: ");
                                        p.setMiembroId(teclado.nextInt());
                                        System.out.print("Nueva fecha préstamo: ");
                                        p.setFechaprestamo(java.sql.Date.valueOf(LocalDate.parse(teclado.next())));
                                        System.out.print("Nueva fecha devolución: ");
                                        p.setFechaDevolucion(java.sql.Date.valueOf(LocalDate.parse(teclado.next())));
                                        prestamo.actualizarPrestamo(p);
                                        System.out.println("Actualizado");
                                    } else {
                                        System.out.println("No encontrado");
                                    }
                                }
                                case 4 -> {
                                    System.out.print("ID a borrar: ");
                                    prestamo.eliminarPrestamo(teclado.nextInt());
                                    System.out.println("Eliminado");
                                }
                                case 5 -> {
                                    List<Prestamo> prestamos = prestamo.listarPrestamos();
                                    prestamos.forEach(System.out::println);
                                }
                                default -> System.out.println("Opción inválida");
                            }
                        } while (opp != 0);
                    }

                    case 0 -> System.out.println("Cerrando sistema");
                    default -> System.out.println("Opción inválida");
                }
            } while (opcionPrincipal != 0);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

    }
}
