

package com.company.data;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Flat implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private int area;
    private Long numberOfRooms;
    private Boolean furniture;
    private Long timeToMetroOnFoot;
    private View view;
    private House house;
    private String user;

    public Flat(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, int area, Long numberOfRooms, Boolean furniture, Long timeToMetroOnFoot, View view, House house, String user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furniture = furniture;
        this.timeToMetroOnFoot = timeToMetroOnFoot;
        this.view = view;
        this.house = house;
        this.user = user;
    }

    public Integer setID(int i) {
        this.id = i;
        return null;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
    }

    public int getArea() {
        return this.area;
    }

    public Long getNumberOfRooms() {
        return this.numberOfRooms;
    }

    public Boolean getFurniture() {
        return this.furniture;
    }

    public Long getTimeToMetroOnFoot() {
        return this.timeToMetroOnFoot;
    }

    public View getView() {
        return this.view;
    }

    public House getHouse() {
        return this.house;
    }

    public String getUser() {
        return this.user;
    }

    public String toString() {
        String result = String.format("Id: %d\nName: %s\nCoordinates: {x: %d, y: %d}\nCreation Time: %s\nArea: %d\nNumberOfRooms: %d\nFurniture: %b\nTimeToMetroOnFoot: %d\nView: %s\nName: %s\nYear: %d\nNumberOfFlatsOnFloor: %d\nUser: %s\n", this.getId(), this.getName(), this.getCoordinates().getX(), this.getCoordinates().getY(), this.getCreationDate(), this.getArea(), this.getNumberOfRooms(), this.getFurniture(), this.getTimeToMetroOnFoot(), this.getView(), this.getHouse().getName(), this.getHouse().getYear(), this.getHouse().getNumberOfFlatsOnFloor(), this.getUser());
        return result;
    }

    public String[] toStringForSave() {
        String[] array = new String[]{String.valueOf(this.getId()), this.getName(), String.valueOf(this.getCoordinates().getX()), String.valueOf(this.getCoordinates().getY()), String.valueOf(this.getCreationDate()), String.valueOf(this.getArea()), String.valueOf(this.getNumberOfRooms()), String.valueOf(this.getFurniture()), String.valueOf(this.getTimeToMetroOnFoot()), String.valueOf(this.getView()), this.getHouse().getName(), String.valueOf(this.getHouse().getYear()), String.valueOf(this.getHouse().getNumberOfFlatsOnFloor())};
        return array;
    }
}
