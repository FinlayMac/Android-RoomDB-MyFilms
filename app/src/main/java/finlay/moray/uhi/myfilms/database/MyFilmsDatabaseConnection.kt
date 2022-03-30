package finlay.moray.uhi.myfilms.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Films::class, Cinema::class], version = 1, exportSchema = false)
abstract class MyFilmsDatabaseConnection: RoomDatabase() {

    abstract val databaseDAO: MyFilmsDao

    //allows clients to access methods for creating or getting the DB without instantiating the class
    companion object{

        //starts at null, once there is one, instance will keep track of it
        //Volatile variables will never be cached, changes by one thread are visible immediatly to all other threads
        @Volatile
        private var INSTANCE: MyFilmsDatabaseConnection? = null

        fun getInstance(context: Context): MyFilmsDatabaseConnection {
            //only one thread can open this DB at a time, makes it only initialised once
            println("Get Instance")
            synchronized(this){
                var instance = INSTANCE
                println("synchronized")

                if (instance == null) {
                    println("null instance")
                    //string is the database name

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyFilmsDatabaseConnection::class.java,
                        "my_films_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    println("instance 1")
                    //TODO .allowMainTread is a workaround as I diddnt want to do corountines
                    ///https://stackoverflow.com/questions/44167111/android-room-simple-select-query-cannot-access-database-on-the-main-thread/47773708

                    INSTANCE = instance
                }
                println("return instance")
                return instance
            }
        }
    }

}