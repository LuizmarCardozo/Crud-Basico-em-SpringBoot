const API_URL = "http://localhost:8080/usuarios";

// Listar usuários (GET)
async function listarUsuarios() {
    const response = await fetch(API_URL);
    const usuarios = await response.json();
    
    const lista = document.getElementById("usuarios") || document.getElementById("listaUsuarios");
    
    // Verifica se está na página de CRUD (crud.html)
    const isCrudPage = window.location.pathname.includes("crud") || 
                      document.getElementById("formUsuario") !== null;

    lista.innerHTML = usuarios.map(usuario => `
        <div class="usuario-item">
            <strong>${usuario.nome}</strong> - ${usuario.email}
            ${isCrudPage ? `
                <button onclick="editarUsuario(${usuario.id})">Editar</button>
                <button onclick="deletarUsuario(${usuario.id})">Excluir</button>
            ` : ''}
        </div>
    `).join("");
}
// Criar/Atualizar usuário (POST/PUT)
document.getElementById("formUsuario")?.addEventListener("submit", async (e) => {
    e.preventDefault();
    
    const id = document.getElementById("id").value;
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    
    const metodo = id ? "PUT" : "POST";
    const url = id ? `${API_URL}/${id}` : API_URL;
    
    await fetch(url, {
        method: metodo,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email })
    });
    
    listarUsuarios();
    document.getElementById("formUsuario").reset();
});

// Editar usuário (Preenche o formulário)
async function editarUsuario(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const usuario = await response.json();
    
    document.getElementById("id").value = usuario.id;
    document.getElementById("nome").value = usuario.nome;
    document.getElementById("email").value = usuario.email;
}

// Deletar usuário (DELETE)
async function deletarUsuario(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    listarUsuarios();
}

// Carrega a lista ao abrir a página
if (document.getElementById("usuarios") || document.getElementById("listaUsuarios")) {
    listarUsuarios();
}