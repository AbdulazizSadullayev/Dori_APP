package uz.coder.doriapp

import androidx.room.*
import uz.coder.doriapp.model.DoriModel

@Dao
interface DoriDAO {
    @Insert
    fun add(doriModel: DoriModel)

    @Update
    fun update(doriModel: DoriModel)

    @Delete
    fun delete(doriModel: DoriModel)

    @Query("Select * from dori")
    fun getAllist():List<DoriModel>

    @Query("Select * from dori where id=:id")
    fun getbyId(id:Int): DoriModel


    /*@Query("select * from dori where name like '%' || :ism || '%'")
    fun search(ism:String):List<DoriModel>*/
}