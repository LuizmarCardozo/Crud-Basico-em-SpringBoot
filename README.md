# 🧑‍💼 Projeto CRUD de Usuários - Spring Boot

Este é um projeto simples de CRUD (Create, Read, Update, Delete) para gerenciamento de usuários, desenvolvido com **Spring Boot**. O sistema oferece uma API REST e páginas HTML para visualização e manipulação de dados.

---

## 🚀 Funcionalidades

- ✅ Listagem de usuários
- ➕ Cadastro de novos usuários
- ✏️ Edição de usuários existentes
- ❌ Exclusão de usuários
- 🧭 Navegação por rotas de visualização (`/` e `/crud`)

---

## 🛠️ Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- HTML/CSS/JavaScript (ou Thymeleaf, se preferir)
- H2 Database (ou outro banco, se configurado)
- Maven

---

---

## 📌 Endpoints da API

| Método | Rota             | Descrição                  |
|--------|------------------|----------------------------|
| GET    | /usuarios        | Lista todos os usuários    |
| GET    | /usuarios/{id}   | Retorna usuário por ID     |
| POST   | /usuarios        | Cria um novo usuário       |
| PUT    | /usuarios/{id}   | Atualiza um usuário        |
| DELETE | /usuarios/{id}   | Remove um usuário          |

---
## 🧪 Executando o projeto

1. Clone o repositório:
   ```bash
   https://github.com/LuizmarCardozo/Crud-Basico-em-SpringBoot.git
   
2. Abra o projeto em sua IDE favorita (IntelliJ, VSCode, Eclipse).
3. Execute a classe principal:
  CrudUsuariosApplication.java
4. Acesse no navegador:
  http://localhost:8080/ → Página inicial

🔐 Login e Perfis de Acesso
Ao iniciar a aplicação, o sistema possui dois usuários em memória com perfis distintos:
| Usuário | Senha | Perfil | Redirecionado para | 
| admin | 123qwe!@# | ADMIN | /crud (cadastro/edição) | 
| usuario | 123qwe123 | USER | / (somente visualização) | 


- O login é feito automaticamente pelo Spring Security.
- Cada perfil tem acesso a rotas diferentes:
- ADMIN: pode cadastrar, editar e excluir usuários.
- USER: apenas visualizar os usuários cadastrados.
- Após o cadastro de um novo usuário, o sistema retorna automaticamente para a tela de login.
Caso deseje trocar de perfil, basta clicar em "Trocar de Usuário", que faz o logout e redireciona para a tela de login novamente.


