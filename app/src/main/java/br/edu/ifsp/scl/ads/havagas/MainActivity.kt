package br.edu.ifsp.scl.ads.havagas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        val formacoes = resources.getStringArray(R.array.education_levels)
        val formacaoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formacoes)
        formacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        amb.spFormacao.adapter = formacaoAdapter

        amb.spFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0, 1 -> {
                        amb.etAnoConclusao.visibility = View.VISIBLE
                        amb.etInstituicao.visibility = View.GONE
                        amb.etTituloMonografia.visibility = View.GONE
                        amb.etOrientador.visibility = View.GONE
                    }
                    2, 3 -> {
                        amb.etAnoConclusao.visibility = View.VISIBLE
                        amb.etInstituicao.visibility = View.VISIBLE
                        amb.etTituloMonografia.visibility = View.GONE
                        amb.etOrientador.visibility = View.GONE
                    }
                    4, 5 -> {
                        amb.etAnoConclusao.visibility = View.VISIBLE
                        amb.etInstituicao.visibility = View.VISIBLE
                        amb.etTituloMonografia.visibility = View.VISIBLE
                        amb.etOrientador.visibility = View.VISIBLE
                    }
                    else -> {
                        amb.etAnoConclusao.visibility = View.GONE
                        amb.etInstituicao.visibility = View.GONE
                        amb.etTituloMonografia.visibility = View.GONE
                        amb.etOrientador.visibility = View.GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //NSA
            }
        }

        amb.cbAdicionarCelular.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                amb.etCelular.visibility = View.VISIBLE
            } else {
                amb.etCelular.visibility = View.GONE
            }
        }

        amb.btnSalvar.setOnClickListener {
            val nomeCompleto = amb.etNomeCompleto.text.toString()
            val email = amb.etEmail.text.toString()
            val desejaReceberEmails = amb.cbReceberEmails.isChecked
            val telefone = amb.etTelefone.text.toString()
            val tipoTelefone = when (amb.rgTipoTelefone.checkedRadioButtonId) {
                amb.rbComercial.id -> "Comercial"
                amb.rbResidencial.id -> "Residencial"
                else -> "Não especificado"
            }
            val celular = if (amb.cbAdicionarCelular.isChecked) amb.etCelular.text.toString() else "Não informado"
            val sexo = when (amb.rgSexo.checkedRadioButtonId) {
                amb.rbMasculino.id -> "Masculino"
                amb.rbFeminino.id -> "Feminino"
                amb.rbOutro.id -> "Outro"
                else -> "Não especificado"
            }
            val dataNascimento = amb.etDataNascimento.text.toString()
            val formacao = amb.spFormacao.selectedItem.toString()
            val anoConclusao = amb.etAnoConclusao.text.toString()
            val instituicao = amb.etInstituicao.text.toString()
            val tituloMonografia = amb.etTituloMonografia.text.toString()
            val orientador = amb.etOrientador.text.toString()
            val vagasInteresse = amb.etVagasInteresse.text.toString()

            val mensagem = StringBuilder()
            mensagem.append("Nome Completo: $nomeCompleto\n")
            mensagem.append("E-mail: $email\n")
            mensagem.append("Deseja receber e-mails: ${if (desejaReceberEmails) "Sim" else "Não"}\n")
            mensagem.append("Telefone: $telefone ($tipoTelefone)\n")
            mensagem.append("Celular: $celular\n")
            mensagem.append("Sexo: $sexo\n")
            mensagem.append("Data de Nascimento: $dataNascimento\n")
            mensagem.append("Formação: $formacao\n")
            if (anoConclusao.isNotEmpty()) mensagem.append("Ano de Conclusão: $anoConclusao\n")
            if (instituicao.isNotEmpty()) mensagem.append("Instituição: $instituicao\n")
            if (tituloMonografia.isNotEmpty()) mensagem.append("Título da Monografia: $tituloMonografia\n")
            if (orientador.isNotEmpty()) mensagem.append("Orientador: $orientador\n")
            mensagem.append("Vagas de Interesse: $vagasInteresse\n")

            Toast.makeText(this, mensagem.toString(), Toast.LENGTH_LONG).show()
        }

        amb.btnLimpar.setOnClickListener {
            amb.etNomeCompleto.text.clear()
            amb.etEmail.text.clear()
            amb.cbReceberEmails.isChecked = false
            amb.etTelefone.text.clear()
            amb.rgTipoTelefone.clearCheck()
            amb.cbAdicionarCelular.isChecked = false
            amb.etCelular.text.clear()
            amb.rgSexo.clearCheck()
            amb.etDataNascimento.text.clear()
            amb.spFormacao.setSelection(0)
            amb.etAnoConclusao.text.clear()
            amb.etInstituicao.text.clear()
            amb.etTituloMonografia.text.clear()
            amb.etOrientador.text.clear()
            amb.etVagasInteresse.text.clear()
        }
    }
}
