package cadastro.usuario.model

class Usuarios {
    var id = 0 // id serial PRIMARY KEY NOT NULL,
    @JvmField
    var nomeUsuario: String? = null // nome varchar(120) NOT NULL,
    @JvmField
    var senha: String? = null // telefone varchar(11) null

    constructor()

    constructor(id: Int, nomeUsuarios: String?, senha: String?) {
        this.id = id
        this.nomeUsuario = nomeUsuarios
        this.senha = senha

    }
}