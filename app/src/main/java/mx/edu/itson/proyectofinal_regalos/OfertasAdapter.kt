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
        val oferta = getItem(position) as oferta
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_ofertas, parent, false)
            viewHolder = ViewHolder(
                view.findViewById(R.id.imageView2),
                view.findViewById(R.id.nombre),
                view.findViewById(R.id.precioOriginal),
                view.findViewById(R.id.precioOferta)
            )
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.image.setImageResource(oferta.imagen)
        viewHolder.nombre.text = oferta.nombre
        viewHolder.precioOriginal.text = "$${oferta.precioOriginal}"
        viewHolder.precioOferta.text = "$${oferta.precioOferta}"

        return view
    }

    private class ViewHolder(
        val image: ImageView,
        val nombre: TextView,
        val precioOriginal: TextView,
        val precioOferta: TextView
    )


}