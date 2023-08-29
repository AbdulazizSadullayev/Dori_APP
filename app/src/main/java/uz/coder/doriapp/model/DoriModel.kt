package uz.coder.doriapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dori")
data class DoriModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var doriNomi:String,
    var muddati:String,
    var qolgan_dori:String
) {


}