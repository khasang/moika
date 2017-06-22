package io.khasang.moika.entity;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * Сужность составного первичного ключа для расписаний
 */
@Embeddable
public class TimeTablePk implements Serializable{

    protected int id;
    @Temporal(TemporalType.DATE)
    protected Date dateX;
    protected LocalTime timeOnStarts;


    public TimeTablePk() {
    }

    public TimeTablePk(int id, Date dateX, LocalTime timeOnStarts) {
        this.id = id;
        this.dateX = dateX;
        this.timeOnStarts = timeOnStarts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void settimeOnStarts(LocalTime timeOnStarts) {
        this.timeOnStarts = timeOnStarts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTablePk that = (TimeTablePk) o;
        return id == that.id &&
                Objects.equals(dateX, that.dateX) &&
                Objects.equals(timeOnStarts, that.timeOnStarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateX, timeOnStarts);
    }
}
