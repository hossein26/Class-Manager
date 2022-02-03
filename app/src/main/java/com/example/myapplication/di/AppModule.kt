package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.db.StudentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideStudentDatabase(
        @ApplicationContext context: Context
    ) =
        Room.databaseBuilder(
            context,
            StudentDatabase::class.java,
            "student-db"
        ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideStudentDao(db: StudentDatabase) = db.studentDao()

}