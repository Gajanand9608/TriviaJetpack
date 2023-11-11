package com.example.jettrivia.repository

import android.util.Log
import com.example.jettrivia.data.DataOrException
import com.example.jettrivia.model.QuestionItem
import com.example.jettrivia.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionAPI: QuestionAPI) {
    private val questionItemListOrException =
        DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            questionItemListOrException.loading = true
            val response = questionAPI.getAllQuestions()
            questionItemListOrException.data = response
            if (questionItemListOrException.data.toString()
                    .isNotEmpty()
            ) questionItemListOrException.loading = false

        } catch (exception: Exception) {
            questionItemListOrException.e = exception
            Log.d("TAG", "getAllQuestions: ${questionItemListOrException.e!!.localizedMessage} ")
        }
        return questionItemListOrException
    }
}