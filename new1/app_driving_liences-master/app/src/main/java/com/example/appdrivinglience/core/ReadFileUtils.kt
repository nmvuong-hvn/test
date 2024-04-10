package com.example.appdrivinglience.core

import android.content.Context
import android.content.res.AssetManager
import com.example.appdrivinglience.database.AppDatabase
import com.example.appdrivinglience.database.dao.TrickDao
import com.example.appdrivinglience.database.model.Question
import com.example.appdrivinglience.feature.trick_screen.TrickModel
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class ReadFileUtils @Inject constructor() {
    @Inject
    lateinit var trickDao: TrickDao
    fun readFilesFromAssets(context: Context) {
//
//        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("BANGA1/de1.txt")));
//        var data : String = bufferReader.readLine()
//
//        val question = Question(idCategory = 0, question = , ansA = , ansB = , ansC = , ansD = , correctAns = , analysis = , isImportant = )
//        val questionSplit = data.split(",");
//        if (questionSplit.size >= 7) { // 4 cau trả lời
//            val question = Question(idCategory = 0, question = questionSplit[0], ansA = questionSplit[1] , ansB = questionSplit[2], ansC =questionSplit[3] ,
//                ansD = questionSplit[4] , correctAns = getAnsQuestion(questionSplit[1], questionSplit[2],questionSplit[3] ,questionSplit[4],questionSplit[5])
//                , analysis =questionSplit[6] , isImportant = (questionSplit[0][0] == '*'))
//            AppDatabase.singletonAppDatabase(context).questionDao.insertQuestion(question)
//        } else if (questionSplit.size == 6) { // 3 cau trả lời
//            val question = Question(idCategory = 0, question =questionSplit[0] ,ansA = questionSplit[1], ansB = questionSplit[2], ansC =questionSplit[3] ,
//                correctAns = getAnsQuestion(questionSplit[1],questionSplit[2],questionSplit[3],null,questionSplit[4]), analysis = questionSplit[5], isImportant =(questionSplit[0][0] == '*') )
//            AppDatabase.singletonAppDatabase(context).questionDao.insertQuestion(question)
//
//        }else if(questionSplit.size == 4) { // 2 câu trả lời
//            val question = Question(idCategory = 0, question = questionSplit[0], ansA = questionSplit[1], ansB = questionSplit[2] , correctAns =
//                getAnsQuestion(questionSplit[1],questionSplit[2],null,null,questionSplit[3])
//                , analysis = questionSplit[4] ,
//                isImportant =(questionSplit[0][0] == '*') )
//            AppDatabase.singletonAppDatabase(context).questionDao.insertQuestion(question)
//
//        }
//
//        while (data.isNotEmpty()) {
//            data = bufferReader.readLine();
//            val questionSplit = data.split(",");
//            if (questionSplit.size >= 7) { // 4 cau trả lời
//                val question = Question(idCategory = 0, question = questionSplit[0], ansA = questionSplit[1] , ansB = questionSplit[2], ansC =questionSplit[3] ,
//                    ansD = questionSplit[4] , correctAns = getAnsQuestion(questionSplit[1], questionSplit[2],questionSplit[3] ,questionSplit[4],questionSplit[5])
//                    , analysis =questionSplit[6] , isImportant = (questionSplit[0][0] == '*'))
//                AppDatabase.singletonAppDatabase(context).questionDao().insertQuestion(question)
//            } else if (questionSplit.size == 6) { // 3 cau trả lời
//                val question = Question(idCategory = 0, question =questionSplit[0] ,ansA = questionSplit[1], ansB = questionSplit[2], ansC =questionSplit[3] ,
//                    correctAns = getAnsQuestion(questionSplit[1],questionSplit[2],questionSplit[3],null,questionSplit[4]), analysis = questionSplit[5], isImportant =(questionSplit[0][0] == '*') )
//                AppDatabase.singletonAppDatabase(context).questionDao().insertQuestion(question)
//
//            }else if(questionSplit.size == 4) { // 2 câu trả lời
//                val question = Question(idCategory = 0, question = questionSplit[0], ansA = questionSplit[1], ansB = questionSplit[2] , correctAns =
//                getAnsQuestion(questionSplit[1],questionSplit[2],null,null,questionSplit[3])
//                    , analysis = questionSplit[4] ,
//                    isImportant =(questionSplit[0][0] == '*') )
//                AppDatabase.singletonAppDatabase(context).questionDao().insertQuestion(question)
//
//            }
//
//        }
    }
//    private fun getAnsQuestion(a: String, b: String, c : String?, d : String?, ans : String ):Int {
//        return if (a == ans) 0
//        else if (b== ans) 1
//        else if (c == ans) 2
//        else 3
//    }

    fun readTrickFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("TRICK/meothi.txt")));
        var data : String = bufferReader.readLine()
        var title : String = data
        var content: String = ""
        while (data.isNotEmpty()) {
            runCatching {
                data = bufferReader.readLine()
                if (data[0] == '*'){
                    val trickModel = TrickModel(title = title, content =  content)
                    println("trickModel = $trickModel")
                    trickDao.insertTrick(trickModel)
                    title = data
                    content = ""
                }else {
                    content += data+"\n"
                }
            }.onFailure {
                return
            }

        }
    }
}