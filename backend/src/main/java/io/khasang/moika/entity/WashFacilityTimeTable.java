package io.khasang.moika.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * FacilityTimeTable - Сущность описывающая расписание работы(нерабочие часы) конкретного автомойки,
 */
@Entity(name = "facility_time_table")
@IdClass(TimeTablePk.class)
public class WashFacilityTimeTable extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_fclt")
    protected int id;
    @Id
    @Column(name = "date_x")
    protected Date dateX;
    @Id
    @Column(name = "time_on_starts")
    protected LocalTime timeOnStarts;

    @Column(name = "time_on_ends")
    private LocalTime timeOnEnds;


    public WashFacilityTimeTable() {
    }

    public WashFacilityTimeTable(int idFacility, Date dateX, LocalTime timeOnStarts, LocalTime timeOnEnds) {
        this.id = idFacility;
        this.dateX = dateX;
        this.timeOnStarts = timeOnStarts;
        this.timeOnEnds = timeOnEnds;
    }


    public int getIdFaciilty() {
        return id;
    }

    public void setIdFacility(int idFclt) {
        this.id = idFclt;
    }

    public Date getDateX() {
        return dateX;
    }

    public void setdateX(Date dateX) {
        this.dateX = dateX;
    }

    public LocalTime getTimeOnStarts() {
        return timeOnStarts;
    }

    public void setTimeOnStarts(LocalTime timeOnStarts) {
        this.timeOnStarts = timeOnStarts;
    }

    public LocalTime getTimeOnEnds() {
        return timeOnEnds;
    }

    public void setTimeOnEnds(LocalTime timeOnEnds) {
        this.timeOnEnds = timeOnEnds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacilityTimeTable)) return false;

        WashFacilityTimeTable that = (WashFacilityTimeTable) o;

        if (id != that.getIdFaciilty()) return false;
        if (!getDateX().equals(that.getDateX())) return false;
        return getTimeOnStarts().equals(that.getTimeOnStarts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdFaciilty(), getDateX(), getTimeOnStarts() );
    }
}