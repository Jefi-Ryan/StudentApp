package com.example.myapplication.Utils

import android.app.Application
import android.content.Context
import com.example.myapplication.Database.AppDatabase
import com.example.myapplication.Database.Dao
import com.example.myapplication.Database.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent :: class)
object AppModule {

    @Provides
    @Singleton
    @Named("StudentDao")
    fun provideDao(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context).getDao()

    @Provides
    @Singleton
    @Named("StudentRepository")
    fun provideRepository (@Named("StudentDao") dao : Dao) = StudentRepository(dao)

    @Provides
    @Singleton
    @Named("Application")
    fun provideApplication (@ApplicationContext context: Context) = context as Application

}