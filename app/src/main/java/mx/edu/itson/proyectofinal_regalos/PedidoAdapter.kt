package mx.edu.itson.proyectofinal_regalos

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class PedidoAdapter : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    private var pedidos = mutableListOf<Pedido>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    fun setPedidos(pedidos: List<Pedido>) {
        this.pedidos = pedidos.toMutableList()
        notifyDataSetChanged()
    }

    class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewPedido: TextView = itemView.findViewById(R.id.textViewPedido)

        fun bind(pedido: Pedido) {
            // Personaliza la visualización del pedido según tus necesidades
            val formattedText = SpannableStringBuilder()

            // ID del pedido en rosa y negrita con tipo de letra Jomhuria
            val idPedidoText = SpannableString("ID: ${pedido.id}\n")
            idPedidoText.setSpan(ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.rosa)), 0, 4, 0)
            formattedText.append(idPedidoText)

            // Estado del pedido en rosa y negrita con tipo de letra Jomhuria
            val estadoPedidoText = SpannableString("Estado: ${pedido.Estado}\n")
            estadoPedidoText.setSpan(ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.rosa)), 0, 8, 0)
            formattedText.append(estadoPedidoText)

            // Dirección en rosa
            val direccionText = SpannableString("Dirección: ${pedido.direccion}\n")
            direccionText.setSpan(ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.rosa)), 0, 10, 0)
            formattedText.append(direccionText)

            // Últimos Cuatro Dígitos Tarjeta en rosa
            val ultimosDigitosText = SpannableString("Últimos Cuatro Dígitos Tarjeta: ${pedido.ultimosCuatroDigitosTarjeta}\n")
            ultimosDigitosText.setSpan(ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.rosa)), 0, 30, 0)
            formattedText.append(ultimosDigitosText)

            // Aplica estilos de texto según tus necesidades
            textViewPedido.text = formattedText
            textViewPedido.setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
            textViewPedido.textSize = 40f
        }
    }

}
