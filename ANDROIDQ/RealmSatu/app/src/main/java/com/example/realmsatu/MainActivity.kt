package com.example.realmsatu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realmsatu.adapter.UserAdapter
import com.example.realmsatu.model.User
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var useradapter : UserAdapter
    lateinit var realm : Realm
    val lm = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tambah()
        delete()
        update()
        initview()
        Showall.setOnClickListener {
            Intent(this, MainActivity2::class.java).also {

                startActivity(it)
            }
        }
    }
    private fun getAllUser(){
        realm.where(User::class.java).findAll().let {
            useradapter.setUser(it)
        }
    }
    private fun initview(){
        Showall.layoutManager = lm
        useradapter = UserAdapter(this)
        Showall.adapter = useradapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllUser()
    }
    fun tambah(){
        btnAdd.setOnClickListener {
            realm.beginTransaction()
            var count = 0
            realm.where(User::class.java).findAll().let {
                for (i in it){
                    count++
                }
            }
            try {
                var user = realm.createObject(User::class.java)
                user.setId(count+1)
                user.setNama(etNama.text.toString())
                user.setEmail(etEmail.text.toString())
                etId.setText("")
                etNama.setText("")
                etEmail.setText("")
                realm.commitTransaction()
                getAllUser()

            }catch (e:RealmException){

            }
        }
    }
    fun update(){
        btnUp.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id",etId.text.toString().toInt()).findFirst().let {
                it!!.setNama(etNama.text.toString())
                it!!.setEmail(etEmail.text.toString())
            }
            realm.commitTransaction()
            getAllUser()
        }
    }

    fun delete(){
        btnDel.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id",etId.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAllUser()
        }
    }

}