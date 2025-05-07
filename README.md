# 🚗🛒✨ **[rebUeats - Aplicativo de Delivery](https://backend-desafio-3.onrender.com/)** ✨🛒🚗

---

## 🌟 Descrição

O **rebUeats** é um aplicativo de delivery de alimentos criado para tornar a rotina dos usuários mais simples oferecendo um cardápio variado de refeições práticas e sofisticadas de diversos restaurantes parceiros. A plataforma é moderna, intuitiva e proporciona uma navegação fluida e acessível. Oferece ainda um diferencial: uma funcionalidade exclusiva que recomenda apenas produtos saudáveis, ajudando os usuários a fazerem escolhas mais conscientes e equilibradas! 
Temos um suporte especializado 24 horas para atender nossos clientes e parceiros com qualidade e competência: permitindo explorar diferentes opções de pratos, fazer pedidos de forma rápida e acompanhar as entregas em tempo real, oferecendo toda assistência colaborativa que nossos parceiros precisam!​

[Link Deploy](https://backend-desafio-3.onrender.com/)

> 💡 **"Pedidos rápidos, escolhas conscientes."!**

---
![Cópia de Projeto Integrador 3 - rebuEats (1)](https://github.com/user-attachments/assets/929dc24b-1647-40f2-8935-a6e9e1e667d4)
---

## 🧑‍🤝‍🧑 **Integrantes**

- [Guilherme Lima](https://github.com/Guilhermelso0905)  
- [Maria de Fátima](https://github.com/fatima330)
- [Leonardo de Paula](https://github.com/leodipaula)  
- [Caio Nascimento](https://github.com/Caiosn098)
- [Laura Nery](https://github.com/LauNery)
- [Luana Cardoso](https://github.com/luacrds)

---

## 🛠️ Tecnologias Utilizadas

### Backend
- 🍃 **Spring Boot**
  - Spring Boot Web
  - Spring Boot DevTools
  - Spring Boot Data JPA
  - Spring Boot Validation
  - Spring Security
  -  JWT (JSON Web Token)

### Banco de Dados
- 🐬 **MySQL**

### Deploy
- 🌐 **Render**
- 📘 **Swagger**

---

## 📋 Entidades e Atributos

### 👤 Usuários (`Usuario`)
- `id`
- `nome`
- `email` 
- `senha`
- `endereco`
- `numero` 
- `listaProdutos` (associação com `Produto`)

### 🛒 Produtos (`Produto`)
- `id`
- `nome`
- `descricao`
- `preco`
- `foto`
- `listaUsuarios` (associação com `Usuario`)
- `listaCategorias` (associação com `Categoria`)

### 🏷️ Categoria (`Categoria`)
- `id`
- `nome`
- `descricao`
- `saudavel`
- `imagemUrl`
- `listaProdutos` (associação com `produto`)

---

## ⚙️ Funcionalidades Principais (CRUD)

### 👤 Usuário
✅ Cadastrar novo Usuário  
🔑 Login seguro com JWT  
📋 Listar todos os Usuários  
🆔 Buscar usuário por ID  
✏️ Atualizar dados do Usuário  
🗑️ Deletar Usuário  

### 🛒 Produtos
✅ Cadastrar novo Produto  
📋 Listar todos os Produtos  
🆔 Buscar Produto por ID  
🔍 Buscar Produto por Nome  
✏️ Atualizar informações do Produto  
🗑️ Deletar Produto  

### 🏷️ Categorias
✅ Cadastrar nova Categoria  
📋 Listar todas as Categorias  
🆔 Buscar Categoria por ID  
🔍 Buscar Categoria por Nome  
🍉 Buscar todos os Produtos da Categoria Saudável 
✏️ Atualizar informações da Categoria
🗑️ Deletar Categoria  

---

# 🎨✨ Diagrama ✨🎨

![tabela de classes desafio 3](https://github.com/user-attachments/assets/433e32ff-e941-46f7-8bf5-e5e36753e1aa)

✨ Obrigado por visitar! ✨
