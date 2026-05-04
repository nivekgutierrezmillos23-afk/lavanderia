package org.example;

import org.example.model.*;
import org.example.util.ConexionBD;
import org.example.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {

            Scanner scanner = new Scanner(System.in);
            int op, op1, op2, op3, op4;

            do {
                System.out.println("--- LAVADOKELG ---");
                System.out.println("1. Registros de lavado");
                System.out.println("2. Seccion de Clientes");
                System.out.println("3. Menu de Vehiculos");
                System.out.println("4. Gestion de Servicios");
                System.out.println("5. Salir del programa");
                op = scanner.nextInt();

                switch (op) {
                    case 1:
                        RegistroLavadoDAO registroDAO = new RegistrosLavadoDAOimpl(connection);
                        do {
                            System.out.println("PANEL DE CONTROL - LAVADOS");
                            System.out.println("1. Generar nuevo registro");
                            System.out.println("2. Consultar por ID");
                            System.out.println("3. Eliminar registro");
                            System.out.println("4. Ver historial completo");
                            System.out.println("5. Menu anterior");
                            op4 = scanner.nextInt();

                            switch (op4) {
                                case 1:
                                    System.out.print("ID Vehiculo: ");
                                    int idv = scanner.nextInt();
                                    System.out.print("ID Servicio: ");
                                    int ids = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Fecha (YYYY-MM-DD): ");
                                    LocalDate fecha = LocalDate.parse(scanner.nextLine());
                                    System.out.print("Hora inicio (HH:MM): ");
                                    LocalTime hi = LocalTime.parse(scanner.nextLine());
                                    System.out.print("Hora fin (HH:MM): ");
                                    LocalTime hf = LocalTime.parse(scanner.nextLine());
                                    System.out.print("Precio: ");
                                    double p = scanner.nextDouble();
                                    registroDAO.crear(new Registroslavado(idv, ids, fecha, hi, hf, p));
                                    break;
                                case 2:
                                    System.out.print("ID: ");
                                    System.out.println(registroDAO.leer(scanner.nextInt()));
                                    break;
                                case 3:
                                    System.out.print("ID: ");
                                    registroDAO.eliminar(scanner.nextInt());
                                    break;
                                case 4:
                                    registroDAO.listar().forEach(System.out::println);
                                    break;
                            }
                        } while (op4 != 5);
                        break;

                    case 2:
                        ClientesDAO clientesDAO = new ClientesDAOimpl(connection);
                        do {
                            System.out.println("ADMINISTRACION DE CLIENTES");
                            System.out.println("1. Ver lista de abonados");
                            System.out.println("2. Registrar nuevo ingreso");
                            System.out.println("3. Buscar ficha por ID");
                            System.out.println("4. Corregir datos existentes");
                            System.out.println("5. Dar de baja (Eliminar)");
                            System.out.println("6. Volver");
                            op1 = scanner.nextInt();

                            switch (op1) {
                                case 1:
                                    clientesDAO.listar().forEach(System.out::println);
                                    break;
                                case 2:
                                    scanner.nextLine();
                                    System.out.print("Nombres: "); String n = scanner.nextLine();
                                    System.out.print("Apellidos: "); String a = scanner.nextLine();
                                    System.out.print("Celular: "); String t = scanner.nextLine();
                                    System.out.print("Correo: "); String e = scanner.nextLine();
                                    System.out.print("Direccion: "); String d = scanner.nextLine();
                                    clientesDAO.crear(new Clientes(n, a, t, e, d));
                                    break;
                                case 3:
                                    System.out.print("ID: ");
                                    Clientes cId = clientesDAO.leer(scanner.nextInt());
                                    System.out.println(cId != null ? cId : "No se encontro.");
                                    break;
                                case 4:
                                    System.out.print("ID a editar: ");
                                    Clientes cEdit = clientesDAO.leer(scanner.nextInt());
                                    if (cEdit != null) {
                                        scanner.nextLine();
                                        System.out.print("Nuevo nombre: "); cEdit.setNombre(scanner.nextLine());
                                        clientesDAO.actualizar(cEdit);
                                    }
                                    break;
                                case 5:
                                    System.out.print("ID a borrar: ");
                                    clientesDAO.eliminar(scanner.nextInt());
                                    break;
                            }
                        } while (op1 != 6);
                        break;

                    case 3:
                        VehiculosDAO vehiculosDAO = new VehiculosDAOimpl(connection);
                        do {
                            System.out.println("CONTROL DE VEHICULOS");
                            System.out.println("1. Listado general");
                            System.out.println("2. Añadir vehiculo");
                            System.out.println("3. Consultar datos");
                            System.out.println("4. Borrar de la base");
                            System.out.println("5. Atras");
                            op3 = scanner.nextInt();
                            switch (op3) {
                                case 1:
                                    vehiculosDAO.listar().forEach(System.out::println);
                                    break;
                                case 2:
                                    System.out.print("ID Cliente: "); int idc = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Placa: "); String pl = scanner.nextLine();
                                    vehiculosDAO.crear(new Vehiculos(idc, "", "", pl, "", ""));
                                    break;
                                case 3:
                                    System.out.print("ID: ");
                                    System.out.println(vehiculosDAO.leer(scanner.nextInt()));
                                    break;
                                case 4:
                                    vehiculosDAO.eliminar(scanner.nextInt());
                                    break;
                            }
                        } while (op3 != 5);
                        break;

                    case 4:
                        ServiciosDAO serviciosDAO = new ServiciosDAOimpl(connection);
                        do {
                            System.out.println("MODULO DE SERVICIOS");
                            System.out.println("1. Consultar catalogo");
                            System.out.println("2. Nuevo tipo de servicio");
                            System.out.println("3. Quitar del catalogo");
                            System.out.println("4. Volver");
                            op2 = scanner.nextInt();
                            switch (op2) {
                                case 1:
                                    serviciosDAO.listar().forEach(System.out::println);
                                    break;
                                case 2:
                                    scanner.nextLine();
                                    System.out.print("Nombre: "); String nomS = scanner.nextLine();
                                    System.out.print("Precio: "); double preS = scanner.nextDouble();
                                    serviciosDAO.crear(new Servicios(nomS, preS));
                                    break;
                                case 3:
                                    serviciosDAO.eliminar(scanner.nextInt());
                                    break;
                            }
                        } while (op2 != 4);
                        break;
                }
            } while (op != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}