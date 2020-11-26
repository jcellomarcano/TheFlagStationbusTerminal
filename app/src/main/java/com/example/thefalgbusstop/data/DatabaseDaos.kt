package com.example.thefalgbusstop.data

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface DatabaseDaos {
    @Query("SELECT * FROM Chofer")
    fun getAllFavoriteChofers(): Flowable<List<ChoferEntity>>

    @Query("SELECT * FROM Chofer WHERE chofer_id = :id")
    fun getChoferById(id: Int): Maybe<ChoferEntity>

    @Insert
    fun insertChofer(choferEntity: ChoferEntity)

    @Delete
    fun deleteChofer(choferEntity: ChoferEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateChofer(choferEntity: ChoferEntity)
}