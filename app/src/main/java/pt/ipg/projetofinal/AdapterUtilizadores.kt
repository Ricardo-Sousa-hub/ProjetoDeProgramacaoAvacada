package pt.ipg.projetofinal

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projetofinal.ui.eliminarUtilizador.EliminarUtilizadorFragment
import pt.ipg.projetofinal.ui.utilizadores.UtilizadoresFragment
import pt.ipg.projetofinal.ui.utilizadores.UtilizadoresFragmentDirections


class AdapterUtilizadores(val fragment: UtilizadoresFragment) : RecyclerView.Adapter<AdapterUtilizadores.ViewHolderUtilizador>() {

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderUtilizador? = null

    inner class ViewHolderUtilizador(itemUtilizador: View) : RecyclerView.ViewHolder(itemUtilizador), View.OnClickListener, View.OnLongClickListener {

        val textViewUserName = itemUtilizador.findViewById<TextView>(R.id.textViewNomeUtilizador)

        init {
            itemUtilizador.setOnClickListener(this)
            itemUtilizador.setOnLongClickListener(this)
        }

        var utilizador : Utilizador? = null
            get() = field
            set(value: Utilizador?) {
                field = value

                textViewUserName.text = utilizador?.nome ?:""
            }

        override fun onClick(v: View?) {
            seleciona()
        }

        override fun onLongClick(p0: View?): Boolean {
            SelecionaDelete()
            return true
        }

        var navController: NavController? = null

        private fun seleciona() {
            viewHolderSelecionado = this
            navController = Navigation.findNavController(itemView)
            navController!!.navigate(R.id.action_navigation_utilizadores_to_navigation_carros)
        }

        private fun SelecionaDelete(){
            viewHolderSelecionado = this
            fragment.utilizadorSeleccionado =utilizador
            val acao = UtilizadoresFragmentDirections.actionNavigationUtilizadoresToNavigationEliminarUtilizador(fragment.utilizadorSeleccionado!!)
            Navigation.findNavController(itemView).navigate(acao)
        }
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUtilizador {
        val itemUtilizador = fragment.layoutInflater.inflate(R.layout.item_utilizador, parent, false)
        return ViewHolderUtilizador(itemUtilizador)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolderUtilizador, position: Int) {
        cursor!!.moveToPosition(position)
        holder.utilizador = Utilizador.fromCursor(cursor!!)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        if(cursor == null) return 0

        return cursor!!.count
    }



}