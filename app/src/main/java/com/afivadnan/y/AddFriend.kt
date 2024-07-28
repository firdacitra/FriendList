package com.afivadnan.y

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.afivadnan.y.databinding.ActivityAddFriendBinding
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AddFriend : AppCompatActivity() {

    private lateinit var viewModel: FriendViewModel
    private var friendId: Int? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        val factoryModel = Factory(this)
        viewModel = ViewModelProvider(this, factoryModel)[FriendViewModel::class.java]

        friendId = intent.getIntExtra("FRIEND_ID", -1)

        if (friendId != -1) {
            lifecycleScope.launch {
                viewModel.getFriend().collect { friends ->
                    val friend = friends.firstOrNull { it.id == friendId }
                    if (friend != null) {
                        findViewById<EditText>(R.id.ad_nama).setText(friend.name)
                        findViewById<EditText>(R.id.ad_sekolah).setText(friend.school)
                        findViewById<EditText>(R.id.ad_hobi).setText(friend.hobby)
                    }
                }
            }
            findViewById<Button>(R.id.btn_sv).visibility = View.GONE
            findViewById<Button>(R.id.update_button).visibility = View.VISIBLE
        } else {
            findViewById<Button>(R.id.btn_sv).visibility = View.VISIBLE
            findViewById<Button>(R.id.update_button).visibility = View.GONE
        }

        findViewById<Button>(R.id.btn_sv).setOnClickListener {
            saveFriend()
        }

        findViewById<Button>(R.id.update_button).setOnClickListener {
            updateFriend()
        }

        findViewById<Button>(R.id.delete_button).setOnClickListener {
            finish()
        }
    }

    private fun saveFriend() {
        val name = findViewById<EditText>(R.id.ad_nama).text.toString()
        val school = findViewById<EditText>(R.id.ad_sekolah).text.toString()
        val hobby = findViewById<EditText>(R.id.ad_hobi).text.toString()

        if (name.isBlank() || school.isBlank() || hobby.isBlank()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val friend = Friend(name = name, school = school, hobby = hobby)

        lifecycleScope.launch {
            viewModel.insertFriend(friend)
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun updateFriend() {
        val name = findViewById<EditText>(R.id.ad_nama).text.toString()
        val school = findViewById<EditText>(R.id.ad_sekolah).text.toString()
        val hobby = findViewById<EditText>(R.id.ad_hobi).text.toString()

        if (name.isBlank() || school.isBlank() || hobby.isBlank()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val friend = Friend(name = name, school = school, hobby = hobby).apply {
            id = friendId!!
        }
        lifecycleScope.launch {
            viewModel.updateFriend(friend)
            setResult(RESULT_OK)
            finish()
        }
    }
}
