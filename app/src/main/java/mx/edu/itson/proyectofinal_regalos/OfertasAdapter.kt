package mx.edu.itson.proyectofinal_regalos

import android.content.Context
import android.view.View
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class OfertasAdapter(private val context: Context, private val ofertas: List<oferta>) : BaseAdapter() {

    override fun getCount(): Int {
        return ofertas.size
    }

    override fun getItem(position: Int): Any {
        return ofertas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val oferta = this.getItem(position) as oferta

        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var ofertaView = inflator.inflate(R.layout.activity_ofertas, null)
        ofertaView.findViewById<ImageView>(R.id.image).setImageResource(oferta.imagen)
        ofertaView.findViewById<TextView>(R.id.nombre).text = oferta.nombre
        ofertaView.findViewById<TextView>(R.id.precioOriginal).text = "$${oferta.precioOriginal}"
        ofertaView.findViewById<TextView>(R.id.precioOferta).text = "$${oferta.precioOferta}"

        return ofertaView
    }

}