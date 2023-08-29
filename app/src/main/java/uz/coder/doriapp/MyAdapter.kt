package uz.coder.doriapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coder.doriapp.databinding.ItemAdapterBinding
import uz.coder.doriapp.model.DoriModel

class MyAdapter(var context: Context, var list: List<DoriModel>, val OnClick:(Int)->Unit, val Update:(Int)->Unit, val Delete:(Int)->Unit):RecyclerView.Adapter<MyAdapter.VH>(){

    inner class VH(val binding:ItemAdapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.doriNomi.text = list[position].doriNomi
        holder.binding.muddati.text = list[position].muddati
        holder.binding.doriSoni.text = list[position].qolgan_dori+" ta"
        holder.itemView.setOnClickListener { OnClick.invoke(position) }
        holder.binding.update.setOnClickListener { Update.invoke(position) }
        holder.binding.delete.setOnClickListener { Delete.invoke(position) }


    }
}