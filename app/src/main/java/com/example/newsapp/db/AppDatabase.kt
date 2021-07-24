package com.example.newsapp.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.model.Article
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.util.Constants.Companion.DATABASE_VERSION


@Database(entities = [Article::class],version = DATABASE_VERSION)

@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: NewsActivity) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "article_db.db"
            ).build()
    }
}

//    companion object DatabaseBuilder{
//        fun getInstance(context: Context): AppDatabase {
//            return Room.databaseBuilder(
//                context,
//                AppDatabase::class.java,
//                DATABASE_NAME
//            ).build()
//        }
//    }
