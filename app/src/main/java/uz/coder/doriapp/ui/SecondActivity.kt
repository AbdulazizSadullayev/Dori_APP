package uz.coder.doriapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.coder.doriapp.Database
import uz.coder.doriapp.R
import uz.coder.doriapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var db:Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Database.getInstance(this@SecondActivity)
        val id = intent.getIntExtra("id",0)
        val a = db.doridoa().getbyId(id)
        binding.apply {
            dona.text = "Qolgan dori: " + db.doridoa().getbyId(id).qolgan_dori
            name.text = "Dorini nomi: " + a.doriNomi
            dona.text = "Dorini muddati: " + a.muddati
            muddat.text = "Qolgan dori: " + a.qolgan_dori + " ta"
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@SecondActivity, MainActivity::class.java))
        finish()
    }
}