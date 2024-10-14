package br.edu.ifsp.scl.ads.havagas

import android.app.AlertDialog
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

        val formacoes = resources.getStringArray(R.array.formacao)
        val formacaoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, formacoes)
        formacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        amb.formacaoSp.adapter = formacaoAdapter

        amb.formacaoSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0, 1 -> {
                        amb.anoconclusaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.GONE
                        amb.titulomonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    2, 3 -> {
                        amb.anoconclusaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.titulomonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    4, 5 -> {
                        amb.anoconclusaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.titulomonografiaEt.visibility = View.VISIBLE
                        amb.orientadorEt.visibility = View.VISIBLE
                    }
                    else -> {
                        amb.anoconclusaoEt.visibility = View.GONE
                        amb.instituicaoEt.visibility = View.GONE
                        amb.titulomonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //NSA
            }
        }
        //anotação de teste
        amb.addcelularCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                amb.celularEt.visibility = View.VISIBLE
            } else {
                amb.celularEt.visibility = View.GONE
            }
        }

        amb.salvarBt.setOnClickListener {
            val nomeCompleto = amb.nomecompletoEt.text.toString()
            val email = amb.emailEt.text.toString()
            val desejaReceberEmails = amb.receberemailCb.isChecked
            val telefone = amb.telefoneEt.text.toString()
            val tipoTelefone = when (amb.tipotelefoneRg.checkedRadioButtonId) {
                amb.comercialRb.id -> "Comercial"
                amb.residencialRb.id -> "Residencial"
                else -> "Não especificado"
            }
            val celular = if (amb.addcelularCb.isChecked) amb.celularEt.text.toString() else "Não informado"
            val sexo = when (amb.sexoRg.checkedRadioButtonId) {
                amb.masculinoRb.id -> "Masculino"
                amb.femininoRb.id -> "Feminino"
                amb.outroRb.id -> "Outro"
                else -> "Não especificado"
            }
            val dataNascimento = amb.datanascimentoEt.text.toString()
            val formacao = amb.formacaoSp.selectedItem.toString()
            val anoConclusao = amb.anoconclusaoEt.text.toString()
            val instituicao = amb.instituicaoEt.text.toString()
            val tituloMonografia = amb.titulomonografiaEt.text.toString()
            val orientador = amb.orientadorEt.text.toString()
            val vagasInteresse = amb.vagainteresseEt.text.toString()

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

            //Toast.makeText(this, mensagem.toString(), Toast.LENGTH_LONG).show()

            AlertDialog.Builder(this)
                .setTitle("Confirmação de Dados")
                .setMessage(mensagem.toString())
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        amb.limparBt.setOnClickListener {
            amb.nomecompletoEt.text.clear()
            amb.emailEt.text.clear()
            amb.receberemailCb.isChecked = false
            amb.telefoneEt.text.clear()
            amb.tipotelefoneRg.clearCheck()
            amb.addcelularCb.isChecked = false
            amb.celularEt.text.clear()
            amb.sexoRg.clearCheck()
            amb.datanascimentoEt.text.clear()
            amb.formacaoSp.setSelection(0)
            amb.anoconclusaoEt.text.clear()
            amb.instituicaoEt.text.clear()
            amb.titulomonografiaEt.text.clear()
            amb.orientadorEt.text.clear()
            amb.vagainteresseEt.text.clear()
        }
    }
}
