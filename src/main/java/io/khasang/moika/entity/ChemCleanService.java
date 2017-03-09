package io.khasang.moika.entity;

import javax.persistence.*;


@Entity(name= "chem_clean_services")
@PrimaryKeyJoinColumn(name="id_service")
public class ChemCleanService extends ABaseMoikaServiceAdditionalInfo   {

    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable=false, updatable=false )
    private DirtType dirtTypeEntity;

    @Column(name = "id_material")
    private int idMaterial;
    @ManyToOne
    @JoinColumn(name = "id_material", foreignKey = @ForeignKey(name = "fk_salon_materials"), insertable=false, updatable=false )
    private SalonMaterial salonMaterial;

    public ChemCleanService(){}

    public int getidDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(Short idDirtType) {
        this.idDirtType = idDirtType;
    }

    public String getDirtTypeCode() {
        return this.dirtTypeEntity.getTypeCode();
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Short idMaterial) {
        this.idMaterial = idMaterial;
    }

    public SalonMaterial getSalonMaterial() {
        return salonMaterial;
    }

    public void setSalonMaterial(SalonMaterial salonMaterial) {
        this.salonMaterial = salonMaterial;
    }

}
