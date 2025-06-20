const API_URL = "http://localhost:8080/usuarios";

// ========== Eventos ==========
document.addEventListener('DOMContentLoaded', () => {
    listarUsuarios();
    document.getElementById('formUsuario')?.addEventListener('submit', salvarUsuario);
    adicionarTelefone(); // Inicial com 1
    adicionarEmail();
});

// ========== Listagem ==========
async function listarUsuarios() {
    const response = await fetch(API_URL);
    const usuarios = await response.json();

    const lista = document.getElementById("usuarios") || document.getElementById("listaUsuarios");
    const isCrudPage = !!document.getElementById("formUsuario");

    lista.innerHTML = usuarios.map(usuario => `
        <div class="usuario-item">
            <strong>${usuario.nome}</strong> - ${usuario.cpf}
            ${isCrudPage ? `
                <button onclick="editarUsuario(${usuario.id})">Editar</button>
                <button onclick="deletarUsuario(${usuario.id})">Excluir</button>
            ` : ''}
        </div>
    `).join("");
}

// ========== Adicionar Telefones e E-mails ==========
function adicionarTelefone(tipo = '', numero = '') {
    const div = document.createElement('div');
    div.className = 'telefone-item';
    div.innerHTML = `
        <select name="tipo" required>
            <option value="">Tipo</option>
            <option value="residencial" ${tipo === 'residencial' ? 'selected' : ''}>Residencial</option>
            <option value="comercial" ${tipo === 'comercial' ? 'selected' : ''}>Comercial</option>
            <option value="celular" ${tipo === 'celular' ? 'selected' : ''}>Celular</option>
        </select>
        <input name="numero" type="text" required placeholder="Telefone" value="${numero}">
        <button type="button" onclick="this.parentElement.remove()">🗑️</button>
    `;
    document.getElementById("telefones").appendChild(div);
}

function adicionarEmail(email = '') {
    const div = document.createElement('div');
    div.className = 'email-item';
    div.innerHTML = `
        <input type="email" name="email" required placeholder="email@exemplo.com" value="${email}">
        <button type="button" onclick="this.parentElement.remove()">🗑️</button>
    `;
    document.getElementById("emails").appendChild(div);
}

// ========== Salvar/Atualizar ==========
async function salvarUsuario(e) {
    e.preventDefault();

    const id = document.getElementById("id").value;

    const usuario = {
        nome: document.getElementById("nome").value,
        cpf: document.getElementById("cpf").value.replace(/\D/g, ''),
        cep: document.getElementById("cep").value.replace(/\D/g, ''),
        logradouro: document.getElementById("logradouro").value,
        bairro: document.getElementById("bairro").value,
        cidade: document.getElementById("cidade").value,
        uf: document.getElementById("uf").value,
        complemento: document.getElementById("complemento").value,
        telefones: [],
        emails: []
    };

    document.querySelectorAll("#telefones .telefone-item").forEach(item => {
        usuario.telefones.push({
            tipo: item.querySelector('select').value,
            numero: item.querySelector('input').value.replace(/\D/g, '')
        });
    });

    document.querySelectorAll("#emails .email-item").forEach(item => {
        usuario.emails.push(item.querySelector('input').value);
    });

    const url = id ? `${API_URL}/${id}` : API_URL;
    const method = id ? 'PUT' : 'POST';

    const response = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
    });

    if (response.ok) {
        alert("Usuário salvo com sucesso! Retornando para login...");
        window.location.href = "/logout";
    } else {
        alert("Erro ao salvar usuário");
    }
}

// ========== Editar ==========
async function editarUsuario(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const usuario = await response.json();

    document.getElementById("id").value = usuario.id;
    document.getElementById("nome").value = usuario.nome;
    document.getElementById("cpf").value = usuario.cpf;
    document.getElementById("cep").value = usuario.cep;
    document.getElementById("logradouro").value = usuario.logradouro;
    document.getElementById("bairro").value = usuario.bairro;
    document.getElementById("cidade").value = usuario.cidade;
    document.getElementById("uf").value = usuario.uf;
    document.getElementById("complemento").value = usuario.complemento;

    document.getElementById("telefones").innerHTML = '';
    usuario.telefones?.forEach(t => adicionarTelefone(t.tipo, t.numero));

    document.getElementById("emails").innerHTML = '';
    usuario.emails?.forEach(e => adicionarEmail(e));
}

// ========== Deletar ==========
async function deletarUsuario(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    listarUsuarios();
}