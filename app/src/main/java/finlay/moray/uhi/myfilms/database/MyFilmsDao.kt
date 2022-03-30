package finlay.moray.uhi.myfilms.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MyFilmsDao {

    @Insert
    fun insertNewCinema(NewCinema: Cinema)

}

