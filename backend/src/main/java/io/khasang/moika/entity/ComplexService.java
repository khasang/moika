package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * Сущность описывающая комплексные услуги (т.е. набор услуг),
 * например, мойка и чиска салона, мойка, чиска салона и полировка корпуса
 */
@Entity(name = "complex_services")
@Inheritance(strategy = InheritanceType.JOINED)
public class ComplexService extends MoikaService {

    @Id
    @Column(name = "id_service")
    private int id;

    public int getIdService() {
        return id;
    }

    public void setIdService(int id) {
        this.id = id;
    }

    @Column(name = "add_info")
    private String addInfo;

    public ComplexService() {

    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }


}
