package visao;

/*
* Integrantes: Bernardo(1279), Gustavo(1773), Juliana(1787),  Marcos Vinícius(1804), Samuel(2282)
 */
import static java.awt.Color.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import modelo.*;
import controle.*;
import java.util.ArrayList;

public class JanelaProdutos extends javax.swing.JFrame {

    ControlaProdutos cProduto = new ControlaProdutos();

    public JanelaProdutos() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//fecha apenas a janela
        txtQuantidade.setEditable(false);
        txtArtista.setEditable(false);
        txtGenero.setEditable(false);
        txtCapacidade.setEditable(false);
        txtTitulo.setEditable(false);
        txtPlataforma.setEditable(false);
        lblFaltaArtista.setVisible(false);
        lblFaltaCapacidade.setVisible(false);
        lblFaltaGenero.setVisible(false);
        lblFaltaPlataforma.setVisible(false);
        lblFaltaTitulo.setVisible(false);
        lblAviso.setText("");
        BloqueiaEntrada();
        escondeCampos();
        btnCancelar.setVisible(false);
        btnSalvar.setVisible(false);
        btnInserir.setVisible(false);
        AddEstoque.setVisible(false);
        lblCod.setText("");
        lblCategoria.setText("");

        atualizaListaProdutos();

    }

    private boolean confereCampos(String artista, String capacidade, String genero, String titulo, String plataforma, String qtd) {
        boolean falta = false;
        if (artista.equals("")) {
            lblFaltaArtista.setText("*Campo obrigatório");
            falta = true;
        } else {
            lblFaltaArtista.setText("");
        }

        if (capacidade.equals("")) {
            lblFaltaCapacidade.setText("*Campo obrigatório");
            falta = true;
        } else {
            lblFaltaCapacidade.setText("");
        }

        if (genero.equals("")) {
            lblFaltaGenero.setText("*Campo obrigatório");
            falta = true;
        } else {
            lblFaltaGenero.setText("");
        }

        if (titulo.equals("")) {
            lblFaltaTitulo.setText("*Campo obrigatório");
            falta = true;
        } else {
            lblFaltaTitulo.setText("");
        }

        if (plataforma.equals("")) {
            lblFaltaPlataforma.setText("*Campo obrigatório");
            falta = true;
        } else {
            lblFaltaPlataforma.setText("");
        }

        if (qtd.equals("")) {
            txtQuantidade.setBackground(red);
            txtQuantidade.requestFocus();
            falta = true;
        }
        return !falta;
    }

    public void BloqueiaEntrada() {
        txtQuantidade.setEditable(false);
        txtArtista.setEditable(false);
        txtGenero.setEditable(false);
        txtCapacidade.setEditable(false);
        txtTitulo.setEditable(false);
        txtPlataforma.setEditable(false);

        EscolhaProduto.enable(false);
        txtQuantidade.enable(false);
        txtArtista.enable(false);
        txtGenero.enable(false);
        txtCapacidade.enable(false);
        txtTitulo.enable(false);
        txtPlataforma.enable(false);
    }

    void limpaCampos() {
        txtQuantidade.setText("");
        txtArtista.setText("");
        txtGenero.setText("");
        txtCapacidade.setText("");
        txtTitulo.setText("");
        txtPlataforma.setText("");
        lblCod.setText("");
        lblCategoria.setText("");
    }

    void mostraCamposCD() {
        EscolhaProduto.enable(true);

        lblArtista.setVisible(true);
        txtArtista.setVisible(true);
        txtArtista.setEditable(true);
        txtArtista.enable(true);

        lblGenero.setVisible(false);
        txtGenero.setVisible(false);

        lblCapacidade.setVisible(false);
        txtCapacidade.setVisible(false);

        lblTitulo.setVisible(true);
        txtTitulo.setVisible(true);
        txtTitulo.setEditable(true);
        txtTitulo.enable(true);

        lblPlataforma.setVisible(false);
        txtPlataforma.setVisible(false);

        lblCategoria.setText("Mídia");
    }

    void mostraCamposDVD() {
        EscolhaProduto.enable(true);

        lblArtista.setVisible(false);
        txtArtista.setVisible(false);

        lblGenero.setVisible(true);
        txtGenero.setVisible(true);
        txtGenero.setEditable(true);
        txtGenero.enable(true);

        lblCapacidade.setVisible(false);
        txtCapacidade.setVisible(false);

        lblTitulo.setVisible(true);
        txtTitulo.setVisible(true);
        txtTitulo.setEditable(true);
        txtTitulo.enable(true);

        lblPlataforma.setVisible(false);
        txtPlataforma.setVisible(false);

        lblCategoria.setText("Mídia");
    }

    void mostraCamposLivro() {
        EscolhaProduto.enable(true);

        lblArtista.setVisible(true);
        txtArtista.setVisible(true);
        txtArtista.setEditable(true);
        txtArtista.enable(true);

        lblGenero.setVisible(false);
        txtGenero.setVisible(false);

        lblCapacidade.setVisible(false);
        txtCapacidade.setVisible(false);

        lblTitulo.setVisible(true);
        txtTitulo.setVisible(true);
        txtTitulo.setEditable(true);
        txtTitulo.enable(true);

        lblPlataforma.setVisible(false);
        txtPlataforma.setVisible(false);

        lblCategoria.setText("Papelaria");
    }

    void mostraCamposJogos() {
        EscolhaProduto.enable(true);

        lblArtista.setVisible(false);
        txtArtista.setVisible(false);

        lblGenero.setVisible(false);
        txtGenero.setVisible(false);

        lblCapacidade.setVisible(false);
        txtCapacidade.setVisible(false);

        lblTitulo.setVisible(true);
        txtTitulo.setVisible(true);
        txtTitulo.setEditable(true);
        txtTitulo.enable(true);

        lblPlataforma.setVisible(true);
        txtPlataforma.setVisible(true);
        txtPlataforma.setEditable(true);
        txtPlataforma.enable(true);

        lblCategoria.setText("Games");
    }

    void mostraCamposPenDrive() {
        EscolhaProduto.enable(true);

        lblArtista.setVisible(false);
        txtArtista.setVisible(false);

        lblGenero.setVisible(false);
        txtGenero.setVisible(false);

        lblCapacidade.setVisible(true);
        txtCapacidade.setVisible(true);
        txtCapacidade.setEditable(true);
        txtCapacidade.enable(true);

        lblTitulo.setVisible(false);
        txtTitulo.setVisible(false);

        lblPlataforma.setVisible(false);
        txtPlataforma.setVisible(false);
        lblFaltaPlataforma.setVisible(false);

        lblCategoria.setText("Informática");
    }

    void escondeCampos() {
        EscolhaProduto.enable(false);

        lblArtista.setVisible(false);
        txtArtista.setVisible(false);
        lblFaltaArtista.setVisible(false);

        lblGenero.setVisible(false);
        txtGenero.setVisible(false);
        lblFaltaGenero.setVisible(false);

        lblCapacidade.setVisible(false);
        txtCapacidade.setVisible(false);
        lblFaltaCapacidade.setVisible(false);

        lblTitulo.setVisible(false);
        txtTitulo.setVisible(false);
        lblFaltaTitulo.setVisible(false);

        lblPlataforma.setVisible(false);
        txtPlataforma.setVisible(false);
        lblFaltaPlataforma.setVisible(false);

        lblCategoria.setText("");
        lblCod.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListProdutos = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEstoque = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        EscolhaProduto = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblArtista = new javax.swing.JLabel();
        txtArtista = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lblCapacidade = new javax.swing.JLabel();
        txtCapacidade = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblPlataforma = new javax.swing.JLabel();
        txtPlataforma = new javax.swing.JTextField();
        lblFaltaArtista = new javax.swing.JLabel();
        lblFaltaGenero = new javax.swing.JLabel();
        lblFaltaCapacidade = new javax.swing.JLabel();
        lblFaltaTitulo = new javax.swing.JLabel();
        lblFaltaPlataforma = new javax.swing.JLabel();
        pnBotoes = new javax.swing.JPanel();
        btnInserir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        AddEstoque = new javax.swing.JToggleButton();
        lblAviso = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel6.setText("Gerenciar Produtos");

        jListProdutos.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jListProdutos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings= new String[0];
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }

        });
        jListProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListProdutos);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEstoque.setText("Estoque");
        btnEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstoqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEstoque)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnNovo)
                    .addComponent(btnEstoque))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Geral"));

        jLabel1.setText("Quantidade Estoque");

        txtQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantidadeActionPerformed(evt);
            }
        });
        txtQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantidadeKeyTyped(evt);
            }
        });

        jLabel2.setText("Produto:");

        EscolhaProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CD", "DVD", "JOGOS", "PENDRIVE", "LIVRO" }));
        EscolhaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EscolhaProdutoActionPerformed(evt);
            }
        });

        jLabel10.setText("Cod.");

        lblCod.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCod.setText("0");

        jLabel12.setText("Categoria:");

        lblCategoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCategoria.setText(" ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtQuantidade)
                    .addComponent(EscolhaProduto, 0, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCod, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(118, 118, 118)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(EscolhaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(lblCod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(lblCategoria)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblArtista.setText("Artista/Autor");

        txtArtista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArtistaActionPerformed(evt);
            }
        });

        lblGenero.setText("Gênero");

        lblCapacidade.setText("Capacidade");

        txtCapacidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapacidadeKeyTyped(evt);
            }
        });

        lblTitulo.setText("Título");

        lblPlataforma.setText("Plataforma");

        lblFaltaArtista.setForeground(new java.awt.Color(255, 0, 0));
        lblFaltaArtista.setText("*Campo Obrigatório");

        lblFaltaGenero.setForeground(new java.awt.Color(255, 0, 0));
        lblFaltaGenero.setText("*Campo Obrigatório");

        lblFaltaCapacidade.setForeground(new java.awt.Color(255, 0, 0));
        lblFaltaCapacidade.setText("*Campo Obrigatório");

        lblFaltaTitulo.setForeground(new java.awt.Color(255, 0, 0));
        lblFaltaTitulo.setText("*Campo Obrigatório");

        lblFaltaPlataforma.setForeground(new java.awt.Color(255, 0, 0));
        lblFaltaPlataforma.setText("*Campo Obrigatório");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblPlataforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCapacidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGenero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblArtista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFaltaArtista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFaltaPlataforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFaltaGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFaltaCapacidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFaltaTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArtista)
                    .addComponent(txtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFaltaArtista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenero)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFaltaGenero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCapacidade)
                    .addComponent(txtCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFaltaCapacidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFaltaTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlataforma)
                    .addComponent(txtPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFaltaPlataforma))
                .addContainerGap())
        );

        btnInserir.setText("Inserir");
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        AddEstoque.setText("Adicionar");
        AddEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEstoqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBotoesLayout = new javax.swing.GroupLayout(pnBotoes);
        pnBotoes.setLayout(pnBotoesLayout);
        pnBotoesLayout.setHorizontalGroup(
            pnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBotoesLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnInserir)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddEstoque)
                .addGap(38, 38, 38))
        );
        pnBotoesLayout.setVerticalGroup(
            pnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnInserir)
                .addComponent(btnCancelar)
                .addComponent(btnSalvar)
                .addComponent(AddEstoque))
        );

        lblAviso.setText("info");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        Produto produto = null;
        Categoria categoria = null;
        ControlaProdutos cProdutos = new ControlaProdutos();
        boolean ok = false;

        if (EscolhaProduto.getSelectedItem().equals("CD")) {
            if (confereCampos(txtArtista.getText(), "-", "-", txtTitulo.getText(), "-", txtQuantidade.getText())) {
                produto = new CD("CD", Integer.parseInt(txtQuantidade.getText()), categoria.MIDIA, txtArtista.getText(), txtTitulo.getText());
                txtQuantidade.setText("");
                txtTitulo.setText("");
                txtArtista.setText("");
                BloqueiaEntrada();
                ok = true;
            }
        } else if (EscolhaProduto.getSelectedItem().equals("DVD")) {
            if (confereCampos("-", "-", txtGenero.getText(), txtTitulo.getText(), "-", txtQuantidade.getText())) {
                produto = new DVD("DVD", Integer.parseInt(txtQuantidade.getText()), categoria.MIDIA, txtTitulo.getText(), txtGenero.getText());
                txtQuantidade.setText(" ");
                txtTitulo.setText(" ");
                txtGenero.setText(" ");
                BloqueiaEntrada();
                ok = true;
            }
        } else if (EscolhaProduto.getSelectedItem().equals("JOGOS")) {
            if (confereCampos("-", "-", "-", txtTitulo.getText(), txtPlataforma.getText(), txtQuantidade.getText())) {
                produto = new Jogos("Jogos", Integer.parseInt(txtQuantidade.getText()), categoria.GAMES, txtTitulo.getText(), txtPlataforma.getText());
                txtQuantidade.setText(" ");
                txtTitulo.setText(" ");
                txtPlataforma.setText(" ");
                BloqueiaEntrada();
                ok = true;
            }
        } else if (EscolhaProduto.getSelectedItem().equals("LIVRO")) {
            if (confereCampos(txtArtista.getText(), "-", "-", txtTitulo.getText(), "-", txtQuantidade.getText())) {
                produto = new Livro("Livro", Integer.parseInt(txtQuantidade.getText()), categoria.PAPELARIA, txtTitulo.getText(), txtArtista.getText());
                txtQuantidade.setText(" ");
                txtTitulo.setText(" ");
                txtArtista.setText(" ");
                BloqueiaEntrada();
                ok = true;
            }
        } else if (confereCampos("-", txtCapacidade.getText(), "-", "-", "-", txtQuantidade.getText())) {
            produto = new PenDrive("PenDrive", Integer.parseInt(txtQuantidade.getText()), categoria.INFORMATICA, Integer.parseInt(txtCapacidade.getText()));
            txtQuantidade.setText(" ");
            txtCapacidade.setText(" ");
            BloqueiaEntrada();
            ok = true;
        }

        if (ok && cProdutos.cadastraProduto(produto)) {
            System.out.println("Produto cadastrado com sucesso!");
            System.out.println("ENCONTRADO: " + cProdutos.buscarProduto(produto).getDescricao());
            lblAviso.setText("Salvo com sucesso!");
            lblAviso.setForeground(blue);

            BloqueiaEntrada();
            escondeCampos();
            btnInserir.setVisible(false);
            btnSalvar.setVisible(false);
            btnCancelar.setVisible(false);
            lblAviso.setText("");
            limpaCampos();
        } else {
            System.out.println("Erro");
            lblAviso.setText("Não foi possível salvar");
            lblAviso.setForeground(red);
        }
        atualizaListaProdutos();
        btnEditar.setEnabled(false);
        btnEstoque.setEnabled(false);
        btnExcluir.setEnabled(false);
    }//GEN-LAST:event_btnInserirActionPerformed

    public void atualizaListaProdutos() {
        DefaultListModel lista = new DefaultListModel();
        String a[] = cProduto.retornaProdutos();

        for (int i = 0; i < a.length; i++) {
            lista.addElement(a[i]);
        }
        if (lista.size() == 0) {
            jListProdutos.setVisible(false);
            btnEditar.setEnabled(false);
            btnEstoque.setEnabled(false);
            btnExcluir.setEnabled(false);
        } else {
            jListProdutos.setModel(lista);
            jListProdutos.setVisible(true);
            btnEditar.setEnabled(true);
            btnEstoque.setEnabled(true);
            btnExcluir.setEnabled(true);
        }

    }


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        BloqueiaEntrada();
        escondeCampos();
        btnInserir.setVisible(false);
        btnSalvar.setVisible(false);
        btnCancelar.setVisible(false);
        lblAviso.setText("");
        AddEstoque.setVisible(false);
        limpaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jListProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListProdutosMouseClicked
        String valor = jListProdutos.getSelectedValue();
        String cod = "";
        cod = valor.split(" ")[0]; //pega o cpf dentro da string
        Produto produto = cProduto.buscaProdutoCod(Integer.parseInt(cod));

        btnCancelar.setVisible(false);
        btnInserir.setVisible(false);
        lblTitulo.setText("Pesquisando");
        lblAviso.setText("");
        if (produto != null) {
            EscolhaProduto.setSelectedItem(produto.getDescricao().toUpperCase());
            lblCod.setText(Integer.toString(produto.getCod()));
            lblCategoria.setText(produto.getCategoria().toString());
            switch (produto.getDescricao()) {
                case "CD":
                    CD cd = (CD) produto;
                    txtArtista.setText(cd.getArtista());
                    txtTitulo.setText(cd.getTitulo());
                    break;
                case "DVD":
                    DVD dvd = (DVD) produto;
                    txtGenero.setText(dvd.getGenero());
                    txtTitulo.setText(dvd.getTitulo());
                    break;
                case "Livro":
                    Livro livro = (Livro) produto;
                    txtArtista.setText(livro.getAutor());
                    txtTitulo.setText(livro.getTitulo());
                    break;
                case "PenDrive":
                    PenDrive pendrive = (PenDrive) produto;
                    txtCapacidade.setText(Integer.toString(pendrive.getCapacidade()));
                    break;
                case "Jogos":
                    Jogos jogo = (Jogos) produto;
                    txtPlataforma.setText(jogo.getPlataforma());
                    txtTitulo.setText(jogo.getTitulo());
                    break;
            }
            btnEditar.setEnabled(true);
            btnExcluir.setEnabled(true);
            btnEstoque.setEnabled(true);
            btnSalvar.setVisible(true);
            btnCancelar.setVisible(true);
        } else {
            btnEditar.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnEstoque.setEnabled(false);
            btnSalvar.setVisible(false);
            btnCancelar.setVisible(false);
        }
        BloqueiaEntrada();
    }//GEN-LAST:event_jListProdutosMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        btnCancelar.setVisible(true);
        btnSalvar.setVisible(true);
        txtQuantidade.requestFocus();
        if (EscolhaProduto.getSelectedItem().equals("CD")) {

            txtTitulo.setEditable(true);
            txtTitulo.enable(true);
            txtArtista.setEditable(true);
            txtArtista.enable(true);

        } else if (EscolhaProduto.getSelectedItem().equals("DVD")) {

            txtTitulo.setEditable(true);
            txtGenero.setEditable(true);
            txtTitulo.enable(true);
            txtGenero.enable(true);

        } else if (EscolhaProduto.getSelectedItem().equals("JOGOS")) {

            txtTitulo.setEditable(true);
            txtPlataforma.setEditable(true);
            txtTitulo.enable(true);
            txtPlataforma.enable(true);

        } else if (EscolhaProduto.getSelectedItem().equals("LIVRO")) {

            txtTitulo.setEditable(true);
            txtArtista.setEditable(true);
            txtTitulo.enable(true);
            txtArtista.enable(true);

        } else {

            txtCapacidade.setEditable(true);
            txtCapacidade.enable(true);

        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        txtQuantidade.requestFocus();
        btnCancelar.setVisible(true);
        btnSalvar.setVisible(false);
        btnInserir.setVisible(true);
        mostraCamposCD();
        txtQuantidade.enable(true);
        Produto produto = null;
        int cod = produto.getTotalProdutos() + 1;
        lblCod.setText(Integer.toString(cod));

        if (EscolhaProduto.getSelectedItem().equals("CD")) {
            txtQuantidade.setEditable(true);
            //txtQuantidade.setText("Informe quantidade de entrada");
            txtTitulo.setEditable(true);
            txtArtista.setEditable(true);
            //produto = new CD("CD",Integer.parseInt(txtQuantidade.getText()),categoria.MIDIA,txtArtista.getText(),txtTitulo.getText());   
        } else if (EscolhaProduto.getSelectedItem().equals("DVD")) {
            txtQuantidade.setEditable(true);
            txtTitulo.setEditable(true);
            txtGenero.setEditable(true);

        } else if (EscolhaProduto.getSelectedItem().equals("JOGOS")) {
            txtQuantidade.setEditable(true);
            txtTitulo.setEditable(true);
            txtPlataforma.setEditable(true);

        } else if (EscolhaProduto.getSelectedItem().equals("LIVRO")) {
            txtQuantidade.setEditable(true);
            txtTitulo.setEditable(true);
            txtArtista.setEditable(true);

        } else {
            txtQuantidade.setEditable(true);
            txtCapacidade.setEditable(true);

        }


    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        BloqueiaEntrada();
        if (!lblCod.getText().equals("")) {
            int cod = Integer.parseInt(lblCod.getText());
            if (cProduto.removeProdutos(cod)) {
                lblAviso.setText("Excluído com sucesso!");
                lblAviso.setForeground(blue);
            } else {
                lblAviso.setText("Não foi possível excluir");
                lblAviso.setForeground(red);
            }
        } else {
            lblAviso.setText("Não foi possível excluir");
            lblAviso.setForeground(red);
        }

        atualizaListaProdutos();


    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void EscolhaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EscolhaProdutoActionPerformed
        // TODO add your handling code here:
        String a = (String) EscolhaProduto.getSelectedItem();
        switch (a) {
            case "CD":
                mostraCamposCD();
                break;
            case "DVD":
                mostraCamposDVD();
                break;
            case "LIVRO":
                mostraCamposLivro();
                break;
            case "JOGOS":
                mostraCamposJogos();
                break;
            case "PENDRIVE":
                mostraCamposPenDrive();
                break;
        }

    }//GEN-LAST:event_EscolhaProdutoActionPerformed

    private void btnEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstoqueActionPerformed
        txtQuantidade.enable(true);
        txtQuantidade.setEditable(true);
        AddEstoque.setVisible(true);
        txtQuantidade.requestFocus();
        btnSalvar.setVisible(false);
    }//GEN-LAST:event_btnEstoqueActionPerformed

    private void txtArtistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArtistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArtistaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Produto produto = null;
        ControlaProdutos cProduto = new ControlaProdutos();
        Categoria categoria = null;

        if (EscolhaProduto.getSelectedItem().equals("CD")) {
            if (confereCampos(txtArtista.getText(), "-", "-", txtTitulo.getText(), "-", "-")) {
                cProduto.alteraCD((CD) cProduto.buscaProdutoCod(Integer.parseInt(jListProdutos.getSelectedValue().substring(0, 1))), txtArtista.getText(), txtTitulo.getText());
                txtArtista.enable(false);
                txtTitulo.enable(false);
                txtArtista.setEditable(false);
                txtTitulo.setEditable(false);
                txtArtista.setText("");
                txtTitulo.setText("");

            }

        } else if (EscolhaProduto.getSelectedItem().equals("DVD")) {
            if (confereCampos("-", "-", txtGenero.getText(), txtTitulo.getText(), "-", "-")) {
                cProduto.alteraDVD((DVD) cProduto.buscaProdutoCod(Integer.parseInt(jListProdutos.getSelectedValue().substring(0, 1))), txtTitulo.getText(), txtGenero.getText());
                txtGenero.enable(false);
                txtTitulo.enable(false);
                txtGenero.setEditable(false);
                txtTitulo.setEditable(false);
                txtGenero.setText("");
                txtTitulo.setText("");

            }
        } else if (EscolhaProduto.getSelectedItem().equals("JOGOS")) {
            if (confereCampos("-", "-", "-", txtTitulo.getText(), txtPlataforma.getText(), "-")) {
                cProduto.alteraJogo((Jogos) cProduto.buscaProdutoCod(Integer.parseInt(jListProdutos.getSelectedValue().substring(0, 1))), txtTitulo.getText(), txtPlataforma.getText());
                txtPlataforma.enable(false);
                txtTitulo.enable(false);
                txtPlataforma.setEditable(false);
                txtTitulo.setEditable(false);
                txtPlataforma.setText("");
                txtTitulo.setText("");

            }
        } else if (EscolhaProduto.getSelectedItem().equals("LIVRO")) {
            if (confereCampos(txtArtista.getText(), "-", "-", txtTitulo.getText(), "-", "-")) {
                cProduto.alteraLivro((Livro) cProduto.buscaProdutoCod(Integer.parseInt(jListProdutos.getSelectedValue().substring(0, 1))), txtTitulo.getText(), txtArtista.getText());
                txtArtista.enable(false);
                txtTitulo.enable(false);
                txtArtista.setEditable(false);
                txtTitulo.setEditable(false);
                txtArtista.setText("");
                txtTitulo.setText("");
            }
        } else if (confereCampos("-", txtCapacidade.getText(), "-", "-", "-", "-")) {
            cProduto.alteraPenDrive((PenDrive) cProduto.buscaProdutoCod(Integer.parseInt(jListProdutos.getSelectedValue().substring(0, 1))), Integer.parseInt(txtCapacidade.getText()));
            txtCapacidade.enable(false);;
            txtCapacidade.setEditable(false);
            txtCapacidade.setText("");

        }

        btnCancelar.setVisible(false);
        btnSalvar.setVisible(false);
        atualizaListaProdutos();


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyTyped

        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

    }//GEN-LAST:event_txtQuantidadeKeyTyped

    private void txtQuantidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyReleased
        // TODO add your handling code here:
        if (txtQuantidade.getText().equals("")) {
            txtQuantidade.setBackground(red);
        } else {
            txtQuantidade.setBackground(white);
        }
    }//GEN-LAST:event_txtQuantidadeKeyReleased

    private void AddEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEstoqueActionPerformed
        if (!txtQuantidade.getText().equals("")) {
            Produto prod;
            ControlaProdutos cProduto = new ControlaProdutos();

            prod = cProduto.buscaProdutoCod(Integer.parseInt(jListProdutos.getSelectedValue().substring(0, 1)));

            if (prod != null) {

                cProduto.alteraEstoque(prod, Integer.parseInt(txtQuantidade.getText()));
            }

            txtQuantidade.enable(false);
            txtQuantidade.setEditable(false);
            txtQuantidade.setText("");

            atualizaListaProdutos();
            AddEstoque.setVisible(false);
            btnEditar.setEnabled(false);
            btnEstoque.setEnabled(false);
            btnExcluir.setEnabled(false);
            btnCancelar.setVisible(false);
            limpaCampos();
        }
    }//GEN-LAST:event_AddEstoqueActionPerformed

    private void txtQuantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantidadeKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtQuantidadeKeyPressed

    private void txtCapacidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapacidadeKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCapacidadeKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton AddEstoque;
    private javax.swing.JComboBox<String> EscolhaProduto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEstoque;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private volatile javax.swing.JList<String> jListProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArtista;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblCapacidade;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblFaltaArtista;
    private javax.swing.JLabel lblFaltaCapacidade;
    private javax.swing.JLabel lblFaltaGenero;
    private javax.swing.JLabel lblFaltaPlataforma;
    private javax.swing.JLabel lblFaltaTitulo;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblPlataforma;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JTextField txtArtista;
    private javax.swing.JTextField txtCapacidade;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtPlataforma;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
