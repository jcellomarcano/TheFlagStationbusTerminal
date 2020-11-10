package com.example.thefalgbusstop.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chofer")
data class ChoferEntity(
    @PrimaryKey @ColumnInfo(name = "chofer_id") var id: Int,
    @ColumnInfo(name = "chofer_name") var name: String,
    @ColumnInfo(name = "chofer_lastname") var lastname: String,
    @ColumnInfo(name = "chofer_rut") var rut: String,
)
@Entity(tableName = "passenger")
data class PassengerEntity(
    @PrimaryKey @ColumnInfo(name = "passenger_id") var id: Int,
    @ColumnInfo(name = "passenger_name") var name: String,
    @ColumnInfo(name = "passenger_lastname") var lastname: String,
    @ColumnInfo(name = "passenger_rut") var rut: String,
)
@Entity(tableName = "bus")
data class BusEntity(
    @PrimaryKey @ColumnInfo(name = "bus_id") var id: Int,
    @ColumnInfo(name = "bus_patente") var patente: String,
    @ColumnInfo(name = "bus_marca") var marca: String,
    @ColumnInfo(name = "bus_chofer_id") var chofer_id: String,
)
@Entity(tableName = "route")
data class RouteEntity(
    @PrimaryKey @ColumnInfo(name = "route_id") var id: Int,
    @ColumnInfo(name = "route_ida") var ida: String,
    @ColumnInfo(name = "route_vuelta") var vuelta: String,
    @ColumnInfo(name = "route_terminal") var terminal: String,
)
@Entity(tableName = "sit")
data class SitEntity(
    @PrimaryKey @ColumnInfo(name = "sit_id") var id: Int,
    @ColumnInfo(name = "sit_number") var numAsiento: Int,
    @ColumnInfo(name = "sit_id_bus") var idBus: Int,
    @ColumnInfo(name = "sit_id_passenger") var idPasenger: Int,
)
@Entity(tableName = "hours")
data class HoursEntity(
    @PrimaryKey @ColumnInfo(name = "hour_id") var id: Int,
    @ColumnInfo(name = "hour_fecha") var fecha: String,
    @ColumnInfo(name = "hour_hora") var hora: String,
    @ColumnInfo(name = "hours_id_trayecto") var idTrayecto: Int,
    @ColumnInfo(name = "hours_id_bus") var idBus: Int,
)
