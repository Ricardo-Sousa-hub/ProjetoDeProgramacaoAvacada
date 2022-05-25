package pt.ipg.projetofinal

data class Despesa(
    var id: Long,
    var tipo_despesa: String,
    var data_despesa: String,
    var valor_despesa: Float,
    var id_carro: Long
    ) {

}