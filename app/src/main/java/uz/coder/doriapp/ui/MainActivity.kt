package uz.coder.doriapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import uz.coder.doriapp.Database
import uz.coder.doriapp.model.DoriModel
import uz.coder.doriapp.MyAdapter
import uz.coder.doriapp.databinding.ActivityMainBinding
import uz.coder.doriapp.databinding.DialogMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val db by lazy {
        Database.getInstance(this@MainActivity)
    }
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list by lazy {
            ArrayList(db.doridoa().getAllist())
        }

        adapter = MyAdapter(this,list,{position ->
            val intent = Intent(this@MainActivity,SecondActivity::class.java)
            intent.putExtra("id",db.doridoa().getAllist()[position].id)
            startActivity(intent)
            finish()
        },{ position ->
            val dialogBinding =
                DialogMainBinding.inflate(LayoutInflater.from(this@MainActivity), null, false)
            val dialog = AlertDialog.Builder(this@MainActivity)
            dialog.create()
            dialog.setView(dialogBinding.root)
            dialogBinding.name.setText(db.doridoa().getAllist().get(position).doriNomi)
            dialogBinding.number.setText(db.doridoa().getAllist().get(position).qolgan_dori)
            dialogBinding.muddat.setText(db.doridoa().getAllist().get(position).muddati)

            dialog.setPositiveButton("Saqlash") { _, _ ->
                val dori =db.doridoa().getAllist().get(position)
                val dori_nomi = dialogBinding.name.text.toString()
                val muddat = dialogBinding.muddat.text.toString()
                val soni = dialogBinding.number.text.toString()
                dori.doriNomi = dori_nomi
                dori.muddati = muddat
                dori.qolgan_dori = soni
                list.removeAt(position)
                list.add(position,dori)
                db.doridoa().update(dori)
                adapter.notifyItemChanged(position)
            }
            dialog.show()
        },{ position ->

            val get = db.doridoa().getAllist()[position]
            db.doridoa().delete(get)
            list.removeAt(position)
            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position,list.size)
        })
        binding.apply {
            /*qidirish.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(Search: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (Search?.length !=-1){
                        val search1 =Search.toString()
                        val doriModelList = db.doridoa().search(search1)
                        adapter.filter(doriModelList)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })*/
            rec.adapter = adapter
            fab.setOnClickListener{
                val dialogBinding =
                    DialogMainBinding.inflate(LayoutInflater.from(this@MainActivity), null, false)
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.create()
                dialog.setView(dialogBinding.root)
                dialog.setPositiveButton("Saqlash"){_,_->
                    val name = dialogBinding.name.text.toString()
                    val muddat = dialogBinding.muddat.text.toString()
                    val soni = dialogBinding.number.text.toString()
                    list.add(DoriModel(doriNomi = name, muddati = muddat, qolgan_dori = soni))
                    db.doridoa().add(
                        DoriModel(
                            doriNomi = name,
                            muddati = muddat,
                            qolgan_dori = soni
                        )
                    )
                    adapter.notifyItemInserted(list.size)
                }
                dialog.show()
            }
        }
    }

        }







    fun MyAdapter.filter(list: List<DoriModel>){
        this.list = list
        this.notifyDataSetChanged()
    }
