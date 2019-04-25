package com.hpdev.smartthermostat.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hpdev.smartthermostat.models.AQARA_NETWORK_MESSAGES_TABLE
import com.hpdev.smartthermostat.models.AqaraMessage

@Dao
interface AqaraMessageDAO {

    @Insert
    suspend fun insertMessage(data: AqaraMessage)

    @Query("SELECT * FROM $AQARA_NETWORK_MESSAGES_TABLE")
    suspend fun getAllMessages(): List<AqaraMessage>
}