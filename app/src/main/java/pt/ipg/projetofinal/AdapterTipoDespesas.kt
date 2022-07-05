package pt.ipg.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import pt.ipg.projetofinal.ui.tipo_despesa.TipoDespesaFragment
import pt.ipg.projetofinal.ui.tipo_despesa.TipoDespesaFragmentDirections
import pt.ipg.projetofinal.ui.utilizadores.UtilizadoresFragmentDirections

class AdapterTipoDespesas(val fragment: TipoDespesaFragment) : RecyclerView.Adapter<AdapterTipoDespesas.ViewHolderTipoDespesas>() {

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderTipoDespesas? = null

    inner class ViewHolderTipoDespesas(itemTipoDespesa: View) : RecyclerView.ViewHolder(itemTipoDespesa), View.OnClickListener, View.OnLongClickListener {

        val textViewTipoDespesa = itemTipoDespesa.findViewById<TextView>(R.id.textViewNomeTipoDespesa)

        init {
            itemTipoDespesa.setOnClickListener(this)
            itemTipoDespesa.setOnLongClickListener(this)
        }

        var tipoDespesas : TipoDespesa? = null
            get() = field
            set(value: TipoDespesa?) {
                field = value

                textViewTipoDespesa.text = tipoDespesas?.nome ?:""
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
            navController!!.navigate(R.id.action_navigation_tipo_despesa_to_navigation_despesas)
        }

        private fun SelecionaDelete(){
            viewHolderSelecionado = this
            fragment.tipoDespesaSelecionado = tipoDespesas
            val acao = TipoDespesaFragmentDirections.actionNavigationTipoDespesaToEliminarTipoDespesa(fragment.tipoDespesaSelecionado!!)
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTipoDespesas {
        val itemTipoDespesas = fragment.layoutInflater.inflate(R.layout.item_tipo_despesa, parent, false)
        return ViewHolderTipoDespesas(itemTipoDespesas)
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
    override fun onBindViewHolder(holder: ViewHolderTipoDespesas, position: Int) {
        cursor!!.moveToPosition(position)
        holder.tipoDespesas = TipoDespesa.fromCursor(cursor!!)
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