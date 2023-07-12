package cadastro.usuario.model

class Cadastros {

    var id = 0 // id serial PRIMARY KEY NOT NULL,

    @JvmField
    var nome: String? = null // nome varchar(120) NOT NULL,
    @JvmField
    var cpf: String? = null // cpf char(11) UNIQUE NOT NULL,
    @JvmField
    var endereco: String? = null // endereco varchar(150) NULL,
    @JvmField
    var telefone: String? = null // telefone varchar(11) null
    @JvmField
    var idUsuario: String? = null // telefone varchar(11) null


    constructor()

    constructor(id: Int, nome: String?, cpf: String?, endereco: String?, telefone: String?, idUsuario: String?) {
        this.id = id
        this.nome = nome
        this.cpf = cpf
        this.endereco = endereco
        this.telefone = telefone
        this.idUsuario = idUsuario
    }
}