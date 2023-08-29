package uz.coder.doriapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.coder.doriapp.model.DoriModel

@Database(entities = [DoriModel::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun doridoa(): DoriDAO

    companion object {
        var database: uz.coder.doriapp.Database? = null
        @Synchronized
        fun getInstance(context: Context): uz.coder.doriapp.Database {
            if (database == null) {
                database =
                    Room.databaseBuilder(context, uz.coder.doriapp.Database::class.java, "dori")
                        .allowMainThreadQueries()
                        .build()
            }
            return database!!
        }
    }
}