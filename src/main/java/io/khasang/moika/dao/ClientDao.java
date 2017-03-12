package io.khasang.moika.dao;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Интерфейс DAO для услуг клиентов
 * @author Skvortsov Pavel
 *
 */
public interface ClientDao extends IMoikaDaoCrud <Client>{
    List<Client> getClientByName(String firstName, String middelName, String lastName);
    Client getClientByTel(String tel);
    List<Car> getCarsByClient(Client client);
    List<Client> getClientsListByLastDateWash(Date dateStart, Date dateEnd);
    List<Client> getClientListByStatus(int status);
}