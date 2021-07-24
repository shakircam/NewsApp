package com.example.newsapp.repository

import com.example.newsapp.db.AppDatabase
import com.example.newsapp.network.ApiService

class NewsRepository(val db: AppDatabase) {

     suspend fun getBreakingNews(countryCode: String,pageNumber:Int)=
         ApiService.api.getBreakingNews(countryCode, pageNumber)

}