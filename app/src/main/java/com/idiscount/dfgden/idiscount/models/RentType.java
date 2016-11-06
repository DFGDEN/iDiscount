package com.idiscount.dfgden.idiscount.models;

import com.google.gson.annotations.SerializedName;


public enum RentType {

    @SerializedName("room")
    ROOM("rent_type_room"),
    @SerializedName("1_room")
    ROOM_1("rent_type_room_1"),
    @SerializedName("2_rooms")
    ROOM_2("rent_type_room_2"),
    @SerializedName("3_rooms")
    ROOM_3("rent_type_room_3"),
    @SerializedName("4_rooms")
    ROOM_4("rent_type_room_4"),
    @SerializedName("5_rooms")
    ROOM_5("rent_type_room_5"),
    @SerializedName("6_rooms")
    ROOM_6("rent_type_room_6"),
    @SerializedName("7_rooms")
    ROOM_7("rent_type_room_7"),
    @SerializedName("8_rooms")
    ROOM_8("rent_type_room_8"),
    @SerializedName("9_rooms")
    ROOM_9("rent_type_room_9");

    private String countRooms;

    RentType(String countRooms) {
        this.countRooms = countRooms;
    }

    public String getSortType() {
        return countRooms;
    }
}
