package com.example.appdrivinglience.core

import android.content.Context
import android.content.res.AssetManager
import com.example.appdrivinglience.database.AppDatabase
import com.example.appdrivinglience.database.dao.QuestionDao
import com.example.appdrivinglience.database.dao.TrickDao
import com.example.appdrivinglience.database.model.Question
import com.example.appdrivinglience.feature.trick_screen.TrickModel
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class ReadFileUtils @Inject constructor() {
    @Inject
    lateinit var trickDao: TrickDao
    @Inject
    lateinit var questionDao: QuestionDao

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

    fun readVHTheoryFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("VANHOA_DAODUC/vanhoa_daoduc.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,3)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 3)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }
    fun readCTSCTheoryFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("CAUTAO_SUACHUA/cautao_suachua.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,5)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 5)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }

    fun readNVVTTheoryFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("NGHIEPVU_VANTAI/nghiepvu_vantai.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,4)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 4)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }

    fun readKNQTFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("KHAI_NIEM_QUY_TAC/khainiemquytac.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,2)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 2)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }

    fun readDLFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("DIEM_LIET/diemliet.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,1)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 1)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }
    fun readSHFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("SA_HINH/sahinh.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,7)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 7)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }

    fun readBBFile(context: Context){
        val bufferReader = BufferedReader(InputStreamReader(context.assets.open("BIEN_BAO/bienbao.txt")));
        var data : String? = bufferReader.readLine()

        val dataList = data?.split("@@")?.toList() ?: mutableListOf();
        val questionData = getQuestion(dataList,6)

        questionData?.let {
            questionDao.insertQuestion(it);
        }

        while (data?.isNotEmpty() == true) {
            runCatching {
                data = bufferReader.readLine()
                if (data == null) return
                val dataTmpList = data?.split("@@")?.toList() ?: mutableListOf()
                getQuestion(dataTmpList, 6)?.let {
                    questionDao.insertQuestion(it)
                }

            }.onFailure {
                println("error = ${it.message}")
                return
            }
        }
    }
    private fun getQuestion(dataList: List<String>, idCategoryQuestion: Long) : Question? {
        var dataQuestion : Question?= null
        println(dataList)
         if (dataList.size >= 7 ){
            dataQuestion =  Question(
                idCategoryQuestion = idCategoryQuestion,
                question = dataList[0],
                ansA = dataList[1],
                ansB = dataList[2],
                ansC = dataList[3],
                ansD = dataList[4],
                correctAns = getAnswerQuestion(dataList[1], dataList[2], dataList[3], dataList[4], dataList[5]),
                analysis = dataList[6],
                isImportant = dataList[0][0] == '*'
            )
        }else if(dataList.size == 6){
            dataQuestion =  Question(
                idCategoryQuestion = idCategoryQuestion,
                question = dataList[0],
                ansA = dataList[1],
                ansB = dataList[2],
                ansC = dataList[3],
                correctAns = getAnswerQuestion(a= dataList[1], b=dataList[2], c=dataList[3],d = null, ans = dataList[4]),
                analysis = dataList[5],
                isImportant = dataList[0][0] == '*'
            )
        }else if(dataList.size == 5) {
             dataQuestion =  Question(
                 idCategoryQuestion= idCategoryQuestion,
                question = dataList[0],
                ansA = dataList[1],
                ansB = dataList[2],
                correctAns = getAnswerQuestion(dataList[1], dataList[2], c= null, d = null, dataList[3]),
                analysis = dataList[4],
                isImportant = dataList[0][0] == '*'
            )
        }else {
             print("size = ${dataList.size}")
        }
        return  dataQuestion
    }

    private fun getAnswerQuestion(a: String, b : String, c : String?, d: String?, ans: String): Int {
        return if (a == ans) {
            0
        }else if (b == ans){
            1
        }else if(c == ans) {
            2
        }else{
            3
        }
    }

}