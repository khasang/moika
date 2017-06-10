package io.khasang.moika.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity()
@Table(name = "addr")
public class WashAddr extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_addr", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_city", insertable=false, updatable=false)
    private int idCity ;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city", referencedColumnName = "id_city")
    private City city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @Column(name = "letter")
    private String letter;

    @Embedded
    Coordinate coordinate;

    public WashAddr() {
    }

    public WashAddr(BigDecimal latitude, BigDecimal longitude) {
        this.coordinate.setLat(latitude);
        this.coordinate.setLon(longitude);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "WashAddr{" +
                "id=" + id +
                ", city='" + ((city == null)? "-" : city.getName())  + '\'' +
                ", street='" + ((street ==null) ? "-" : street) + '\'' +
                ", building='" + building + '\'' +
                ", letter='" +((letter == null) ? "" : letter) + '\'' +
                ", latitude=" + ((coordinate == null) ? "" : coordinate.getLat().toString()) +
                ", longitude=" + ((coordinate == null) ? "" : coordinate.getLon().toString()) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashAddr)) return false;

        WashAddr washAddr = (WashAddr) o;

        if (getId() != washAddr.getId()) return false;
        if (!coordinate.getLat().equals(washAddr.coordinate.getLat())) return false;
        return coordinate.getLon().equals(washAddr.coordinate.getLon());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + coordinate.getLat().hashCode();
        result = 31 * result + coordinate.getLon().hashCode();
        return result;
    }

}