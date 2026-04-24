package com.route.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsapp.data.api.model.SourceDM
import com.route.newsapp.data.api.model.SourcesResponse

@Dao
interface SourcesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(sources: List<SourceDM>)

    @Query("select * from SourceDM where category = :category")
    suspend fun getSources(category: String): List<SourceDM>
}