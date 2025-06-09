INSERT INTO tb_usuarios (name, email, senha, endereco, numero)
SELECT 'Lanchonete Fit&Sabor', 'contato@fitsabor.com', '12345678', 'Rua das Laranjeiras', '123456789'
WHERE NOT EXISTS (
  SELECT 1 FROM tb_usuarios WHERE email = 'contato@fitsabor.com'
);


INSERT INTO tb_categorias (id, nome, descricao, saudavel, imagem_url)
SELECT 1, 'Doces', 'Produtos açucarados e saborosos', false, 'https://img.exemplo.com/categoria-doces.jpg'
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE id = 1);

INSERT INTO tb_categorias (id, nome, descricao, saudavel, imagem_url)
SELECT 2, 'Lanches', 'Comidas rápidas e práticas', false, 'https://img.exemplo.com/categoria-lanches.jpg'
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE id = 2);

INSERT INTO tb_categorias (id, nome, descricao, saudavel, imagem_url)
SELECT 3, 'Saudáveis', 'Produtos leves e nutritivos', true, 'https://img.exemplo.com/categoria-saudaveis.jpg'
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE id = 3);

INSERT INTO tb_categorias (id, nome, descricao, saudavel, imagem_url)
SELECT 4, 'Bebidas', 'Bebidas geladas, energéticas ou naturais', false, 'https://img.exemplo.com/categoria-bebidas.jpg'
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE id = 4);

INSERT INTO tb_categorias (id, nome, descricao, saudavel, imagem_url)
SELECT 5, 'Veganos', 'Sem ingredientes de origem animal', true, 'https://img.exemplo.com/categoria-veganos.jpg'
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE id = 5);

INSERT INTO tb_categorias (id, nome, descricao, saudavel, imagem_url)
SELECT 6, 'Fitness', 'Para quem cuida do corpo e da saúde', true, 'https://img.exemplo.com/categoria-fitness.jpg'
WHERE NOT EXISTS (SELECT 1 FROM tb_categorias WHERE id = 6);


INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Barra de Cereal', 'Barra integral com castanhas e mel', 4.50, 'https://img.exemplo.com/produtos/barra.jpg', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Barra de Cereal');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Refrigerante Cola', 'Refrigerante tradicional de cola 350ml', 3.00, 'https://img.exemplo.com/produtos/cola.jpg', 4, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Refrigerante Cola');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Sanduíche Natural', 'Pão integral com frango desfiado, alface e cenoura', 7.50, 'https://img.exemplo.com/produtos/sanduiche.jpg', 2, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Sanduíche Natural');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Chocolate ao Leite', 'Tablete de chocolate ao leite 90g', 5.00, 'https://img.exemplo.com/produtos/chocolate.jpg', 1, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Chocolate ao Leite');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Água Mineral', 'Água mineral sem gás 500ml', 2.00, 'https://img.exemplo.com/produtos/agua.jpg', 4, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Água Mineral');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Iogurte Natural', 'Iogurte sem açúcar com lactobacilos vivos', 3.80, 'https://img.exemplo.com/produtos/iogurte.jpg', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Iogurte Natural');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Biscoito Recheado', 'Biscoito crocante recheado com chocolate', 2.50, 'https://img.exemplo.com/produtos/biscoito.jpg', 1, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Biscoito Recheado');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Salada de Frutas', 'Frutas frescas cortadas em cubos, sem açúcar', 6.20, 'https://img.exemplo.com/produtos/salada.jpg', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Salada de Frutas');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Suco Detox', 'Suco natural de couve, limão e gengibre', 5.50, 'https://img.exemplo.com/produtos/detox.jpg', 4, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Suco Detox');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Hambúrguer Artesanal', 'Hambúrguer artesanal com pão brioche e cheddar', 14.90, 'https://img.exemplo.com/produtos/hamburguer.jpg', 2, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Hambúrguer Artesanal');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Brownie Vegano', 'Brownie de cacau com nozes, sem leite nem ovos', 6.90, 'https://img.exemplo.com/produtos/brownie-vegano.jpg', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Brownie Vegano');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Smoothie Proteico', 'Bebida cremosa com whey protein e frutas vermelhas', 9.50, 'https://img.exemplo.com/produtos/smoothie.jpg', 6, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Smoothie Proteico');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Wrap de Tofu', 'Wrap integral com tofu grelhado e legumes', 8.70, 'https://img.exemplo.com/produtos/wrap.jpg', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Wrap de Tofu');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Chips de Batata Doce', 'Chips crocantes assados, sem conservantes', 4.90, 'https://img.exemplo.com/produtos/chips.jpg', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Chips de Batata Doce');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Energy Drink', 'Bebida energética para quem precisa de energia extra', 7.00, 'https://img.exemplo.com/produtos/energy.jpg', 4, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Energy Drink');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Mousse de Maracujá', 'Mousse cremoso de maracujá com chantilly', 5.80, 'https://img.exemplo.com/produtos/mousse.jpg', 1, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Mousse de Maracujá');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Coxinha de Jaca', 'Coxinha vegana recheada com jaca desfiada temperada', 6.50, 'https://img.exemplo.com/produtos/coxinha-jaca.jpg', 5, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Coxinha de Jaca');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Panqueca Integral', 'Panqueca recheada com legumes e frango', 9.20, 'https://img.exemplo.com/produtos/panqueca.jpg', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Panqueca Integral');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Torta de Frango', 'Torta salgada com recheio cremoso de frango e milho', 10.00, 'https://img.exemplo.com/produtos/torta.jpg', 2, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Torta de Frango');

INSERT INTO tb_produtos (nome, descricao, preco, foto, categoria_id, usuario_id)
SELECT 'Salada Caesar', 'Alface, frango grelhado, parmesão e molho caesar', 8.90, 'https://img.exemplo.com/produtos/caesar.jpg', 6, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_produtos WHERE nome = 'Salada Caesar');