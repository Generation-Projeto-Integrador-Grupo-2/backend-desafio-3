CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE tb_usuarios (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    numero VARCHAR(15) NOT NULL
);

CREATE TABLE tb_categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    saudavel BOOLEAN,
    imagem_url VARCHAR(255)
);

CREATE TABLE tb_empresas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cnpj VARCHAR(30) NOT NULL UNIQUE,
    telefone VARCHAR(30) NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE tb_motoboys (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    modelo_moto VARCHAR(100) NOT NULL,
    placa VARCHAR(20) NOT NULL,
    cnh VARCHAR(30) NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE tb_entregas (
    id BIGSERIAL PRIMARY KEY,
    data TIMESTAMP,
    usuario_id BIGINT REFERENCES tb_usuarios(id),
    motoboy_id BIGINT REFERENCES tb_motoboys(id),
    empresa_id BIGINT REFERENCES tb_empresas(id)
);

CREATE TABLE tb_produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    foto VARCHAR(255) NOT NULL,
    embedding vector(384),
    categoria_id BIGINT REFERENCES tb_categorias(id),
    usuario_id BIGINT REFERENCES tb_usuarios(id),
    empresa_id BIGINT REFERENCES tb_empresas(id),
    entrega_id BIGINT REFERENCES tb_entregas(id)
);

CREATE TABLE tb_pedidos (
    id BIGSERIAL PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'AGUARDANDO_CONFIRMACAO',
    usuario_id BIGINT REFERENCES tb_usuarios(id)
);

-- Tabela de junção: Pedido <-> Produto (N:N)
CREATE TABLE tb_pedido_produto (
    pedido_id BIGINT REFERENCES tb_pedidos(id) ON DELETE CASCADE,
    produto_id BIGINT REFERENCES tb_produtos(id) ON DELETE CASCADE,
    PRIMARY KEY (pedido_id, produto_id)
);

CREATE TABLE tb_avaliacoes (
    id BIGSERIAL PRIMARY KEY,
    nota INT CHECK (nota >= 1 AND nota <= 5),
    comentario VARCHAR(500),
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    empresa_id BIGINT REFERENCES tb_empresas(id),
    usuario_id BIGINT REFERENCES tb_usuarios(id)
);

INSERT INTO tb_usuarios (name, email, senha, endereco, numero) VALUES
  ('Lanchonete Fit&Sabor', 'contato@fitsabor.com', '12345678', 'Rua das Laranjeiras', '123456789');

INSERT INTO tb_categorias (nome, descricao, saudavel, imagem_url) VALUES
  ('Doces', 'Produtos açucarados e saborosos', false, 'https://img.exemplo.com/categoria-doces.jpg'),
  ('Lanches', 'Comidas rápidas e práticas', false, 'https://img.exemplo.com/categoria-lanches.jpg'),
  ('Saudáveis', 'Produtos leves e nutritivos', true, 'https://img.exemplo.com/categoria-saudaveis.jpg'),
  ('Bebidas', 'Bebidas geladas, energéticas ou naturais', false, 'https://img.exemplo.com/categoria-bebidas.jpg'),
  ('Veganos', 'Sem ingredientes de origem animal', true, 'https://img.exemplo.com/categoria-veganos.jpg'),
  ('Fitness', 'Para quem cuida do corpo e da saúde', true, 'https://img.exemplo.com/categoria-fitness.jpg');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id) VALUES
  ('Barra de Cereal', 'Barra integral com castanhas e mel', 4.50, 'https://img.exemplo.com/produtos/barra.jpg', 3, 1),
  ('Refrigerante Cola', 'Refrigerante tradicional de cola 350ml', 3.00, 'https://img.exemplo.com/produtos/cola.jpg', 4, 1),
  ('Sanduíche Natural', 'Pão integral com frango desfiado, alface e cenoura', 7.50, 'https://img.exemplo.com/produtos/sanduiche.jpg', 2, 1),
  ('Chocolate ao Leite', 'Tablete de chocolate ao leite 90g', 5.00, 'https://img.exemplo.com/produtos/chocolate.jpg', 1, 1),
  ('Água Mineral', 'Água mineral sem gás 500ml', 2.00, 'https://img.exemplo.com/produtos/agua.jpg', 4, 1),
  ('Iogurte Natural', 'Iogurte sem açúcar com lactobacilos vivos', 3.80, 'https://img.exemplo.com/produtos/iogurte.jpg', 3, 1),
  ('Biscoito Recheado', 'Biscoito crocante recheado com chocolate', 2.50, 'https://img.exemplo.com/produtos/biscoito.jpg', 1, 1),
  ('Salada de Frutas', 'Frutas frescas cortadas em cubos, sem açúcar', 6.20, 'https://img.exemplo.com/produtos/salada.jpg', 3, 1),
  ('Suco Detox', 'Suco natural de couve, limão e gengibre', 5.50, 'https://img.exemplo.com/produtos/detox.jpg', 4, 1),
  ('Hambúrguer Artesanal', 'Hambúrguer artesanal com pão brioche e cheddar', 14.90, 'https://img.exemplo.com/produtos/hamburguer.jpg', 2, 1),
  ('Brownie Vegano', 'Brownie de cacau com nozes, sem leite nem ovos', 6.90, 'https://img.exemplo.com/produtos/brownie-vegano.jpg', 5, 1),
  ('Smoothie Proteico', 'Bebida cremosa com whey protein e frutas vermelhas', 9.50, 'https://img.exemplo.com/produtos/smoothie.jpg', 6, 1),
  ('Wrap de Tofu', 'Wrap integral com tofu grelhado e legumes', 8.70, 'https://img.exemplo.com/produtos/wrap.jpg', 5, 1),
  ('Chips de Batata Doce', 'Chips crocantes assados, sem conservantes', 4.90, 'https://img.exemplo.com/produtos/chips.jpg', 3, 1),
  ('Energy Drink', 'Bebida energética para quem precisa de energia extra', 7.00, 'https://img.exemplo.com/produtos/energy.jpg', 4, 1),
  ('Mousse de Maracujá', 'Mousse cremoso de maracujá com chantilly', 5.80, 'https://img.exemplo.com/produtos/mousse.jpg', 1, 1),
  ('Coxinha de Jaca', 'Coxinha vegana recheada com jaca desfiada temperada', 6.50, 'https://img.exemplo.com/produtos/coxinha-jaca.jpg', 5, 1),
  ('Panqueca Integral', 'Panqueca recheada com legumes e frango', 9.20, 'https://img.exemplo.com/produtos/panqueca.jpg', 3, 1),
  ('Torta de Frango', 'Torta salgada com recheio cremoso de frango e milho', 10.00, 'https://img.exemplo.com/produtos/torta.jpg', 2, 1),
  ('Salada Caesar', 'Alface, frango grelhado, parmesão e molho caesar', 8.90, 'https://img.exemplo.com/produtos/caesar.jpg', 6, 1);