package visao;

import controle.ControlaUrna;
import modelo.*;

public class UrnaVotacao extends javax.swing.JFrame {

    private ControlaUrna cUrna = new ControlaUrna();
    private int voto[];
    private int zona;
    private Eleitor eleitor;
    private CargoPretendido votacaoAtual;
    private Candidato candidatoVoto;

    public UrnaVotacao(int zona, Eleitor eleitor) { //construtor
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//fecha apenas a janela

        this.zona = zona;
        jLabel4.setVisible(false);
        lblVotoEmBranco.setVisible(false);
        this.voto = new int[5];
        for (int i = 0; i < 5; i++) {
            this.voto[i] = -1;
        }
        this.eleitor = eleitor;
        this.votacaoAtual = CargoPretendido.VEREADOR;
        lblZonaEleitoral.setText(Integer.toString(zona));
        lblNomeEleitor.setText(eleitor.getNome());
        lblTitulo.setText(eleitor.getTitulo());
        lblCandidato.setVisible(false);
        lblNomePartido.setVisible(false);
        lblVotoNulo.setVisible(false);
        painelFIM.setVisible(false);
        corrige();
    }

    private void insereNumero(int num) { //quando clica em um numero
        int cont = 0;
        if (this.candidatoVoto != null) { //vê se não está votando branco
            if (this.candidatoVoto.getNumero() == 0) {
                return;
            }
        }

        if (votacaoAtual == CargoPretendido.PREFEITO) { //votando para prefeito
            lblnumVoto3.setVisible(false);
            lblnumVoto4.setVisible(false);
            lblnumVoto5.setVisible(false);
            for (int i = 0; i < 2; i++) { //ve se tem lugar vago para o numero e salva nela
                cont++;
                if (voto[i] == -1) {
                    voto[i] = num;
                    break;
                }
            }

            if (cont == 2) {
                int voto = this.voto[0] * 10 + this.voto[1]; //pega o voto
                this.candidatoVoto = cUrna.buscaCandidatoPrefeito(voto);
            }
            if (this.candidatoVoto != null) {
                System.out.println(candidatoVoto.getNome());
                lblCandidato.setText(candidatoVoto.getNome());
                lblNomePartido.setText(candidatoVoto.getPartido());
                lblNome.setVisible(true);
                lblpart.setVisible(true);
                lblCandidato.setVisible(true);
                lblNomePartido.setVisible(true);
                lblVotoNulo.setVisible(false);
            } else { //voto nulo
                System.out.println("NAO EXISTE");
                lblCandidato.setVisible(false);
                lblNome.setVisible(false);
                lblpart.setVisible(false);
                lblNomePartido.setVisible(false);
                lblVotoNulo.setVisible(true);

            }

        }

        if (votacaoAtual == CargoPretendido.VEREADOR) { //votando para vereador
            cont = 0;
            for (int i = 0; i < 5; i++) {
                cont++;
                if (voto[i] == -1) {
                    voto[i] = num;
                    break;
                }
            }
            int voto = this.voto[0] * 10000 + this.voto[1] * 1000 + this.voto[2] * 100 + this.voto[3] * 10 + this.voto[4];
            System.out.println("VOTO: " + voto);
            if (cont == 5) {
                System.out.println("atualiza candidatoVoto");
                this.candidatoVoto = cUrna.buscaCandidatoVereador(voto);
            }
            if (this.candidatoVoto != null) {
                System.out.println(candidatoVoto.getNome());
                lblCandidato.setText(candidatoVoto.getNome());
                lblNomePartido.setText(candidatoVoto.getPartido());
                lblCandidato.setVisible(true);
                lblNome.setVisible(true);
                lblpart.setVisible(true);
                lblNomePartido.setVisible(true);
                lblVotoNulo.setVisible(false);
            } else {
                System.out.println("NAO EXISTE");
                lblCandidato.setVisible(false);
                lblNome.setVisible(false);
                lblpart.setVisible(false);
                lblNomePartido.setVisible(false);
                lblVotoNulo.setVisible(true);
            }
        }
        atualizaNumerosVotacao();

        if (candidatoVoto != null) { //mostra os dados do candidato
            lblCandidato.setText(candidatoVoto.getNome());
            lblNomePartido.setText(candidatoVoto.getPartido());
        } else {

        }
    }

    /*public boolean validaVoto(){
        if (votacaoAtual == CargoPretendido.PREFEITO) {
            for (int i = 0; i < 2; i++) {
                if (voto[i] == -1) {
                    return false;
                }
            }
        }
        
        if (votacaoAtual == CargoPretendido.VEREADOR) {
            for (int i = 0; i < 5; i++) {
                if (voto[i] == -1) {
                    return false;
                }
            }
        }
        return true;
    }*/
    public void atualizaNumerosVotacao() {
        if (votacaoAtual == CargoPretendido.PREFEITO) {
            if (voto[0] != -1) {
                lblnumVoto1.setText(Integer.toString(voto[0]));
            } else {
                lblnumVoto1.setText("");
            }

            if (voto[1] != -1) {
                lblnumVoto2.setText(Integer.toString(voto[1]));
            } else {
                lblnumVoto2.setText("");
            }
        }

        if (votacaoAtual == CargoPretendido.VEREADOR) {
            if (voto[0] != -1) {
                lblnumVoto1.setText(Integer.toString(voto[0]));
            } else {
                lblnumVoto1.setText("");
            }

            if (voto[1] != -1) {
                lblnumVoto2.setText(Integer.toString(voto[1]));
            } else {
                lblnumVoto2.setText("");
            }

            if (voto[2] != -1) {
                lblnumVoto3.setText(Integer.toString(voto[2]));
            } else {
                lblnumVoto3.setText("");
            }

            if (voto[3] != -1) {
                lblnumVoto4.setText(Integer.toString(voto[3]));
            } else {
                lblnumVoto4.setText("");
            }

            if (voto[4] != -1) {
                lblnumVoto5.setText(Integer.toString(voto[4]));
            } else {
                lblnumVoto5.setText("");
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnNum2 = new javax.swing.JButton();
        btnNum1 = new javax.swing.JButton();
        btnNum3 = new javax.swing.JButton();
        btnNum4 = new javax.swing.JButton();
        btnNum5 = new javax.swing.JButton();
        btnNum6 = new javax.swing.JButton();
        btnNum0 = new javax.swing.JButton();
        btnNum8 = new javax.swing.JButton();
        btnNum9 = new javax.swing.JButton();
        btnNum7 = new javax.swing.JButton();
        btnConfirma = new javax.swing.JButton();
        btnBranco = new javax.swing.JButton();
        btnCorrige = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblZonaEleitoral = new javax.swing.JLabel();
        lblNomeEleitor = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        painelFIM = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        painelVoto = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTipoCandidato = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblnumVoto1 = new javax.swing.JLabel();
        lblnumVoto2 = new javax.swing.JLabel();
        lblnumVoto3 = new javax.swing.JLabel();
        lblnumVoto4 = new javax.swing.JLabel();
        lblnumVoto5 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblpart = new javax.swing.JLabel();
        lblCandidato = new javax.swing.JLabel();
        lblNomePartido = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblVotoEmBranco = new javax.swing.JLabel();
        lblVotoNulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("JUSTIÇA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ELEITORAL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)))
                .addGap(87, 87, 87))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        btnNum2.setBackground(new java.awt.Color(0, 0, 0));
        btnNum2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum2.setForeground(new java.awt.Color(204, 204, 204));
        btnNum2.setText("2");
        btnNum2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum2.setOpaque(false);
        btnNum2.setRequestFocusEnabled(false);
        btnNum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum2ActionPerformed(evt);
            }
        });

        btnNum1.setBackground(new java.awt.Color(0, 0, 0));
        btnNum1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum1.setForeground(new java.awt.Color(204, 204, 204));
        btnNum1.setText("1");
        btnNum1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum1.setOpaque(false);
        btnNum1.setRequestFocusEnabled(false);
        btnNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum1ActionPerformed(evt);
            }
        });

        btnNum3.setBackground(new java.awt.Color(0, 0, 0));
        btnNum3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum3.setForeground(new java.awt.Color(204, 204, 204));
        btnNum3.setText("3");
        btnNum3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum3.setOpaque(false);
        btnNum3.setRequestFocusEnabled(false);
        btnNum3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum3ActionPerformed(evt);
            }
        });

        btnNum4.setBackground(new java.awt.Color(0, 0, 0));
        btnNum4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum4.setForeground(new java.awt.Color(204, 204, 204));
        btnNum4.setText("4");
        btnNum4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum4.setOpaque(false);
        btnNum4.setRequestFocusEnabled(false);
        btnNum4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum4ActionPerformed(evt);
            }
        });

        btnNum5.setBackground(new java.awt.Color(0, 0, 0));
        btnNum5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum5.setForeground(new java.awt.Color(204, 204, 204));
        btnNum5.setText("5");
        btnNum5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum5.setOpaque(false);
        btnNum5.setRequestFocusEnabled(false);
        btnNum5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum5ActionPerformed(evt);
            }
        });

        btnNum6.setBackground(new java.awt.Color(0, 0, 0));
        btnNum6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum6.setForeground(new java.awt.Color(204, 204, 204));
        btnNum6.setText("6");
        btnNum6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum6.setOpaque(false);
        btnNum6.setRequestFocusEnabled(false);
        btnNum6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum6ActionPerformed(evt);
            }
        });

        btnNum0.setBackground(new java.awt.Color(0, 0, 0));
        btnNum0.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum0.setForeground(new java.awt.Color(204, 204, 204));
        btnNum0.setText("0");
        btnNum0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum0.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum0.setOpaque(false);
        btnNum0.setRequestFocusEnabled(false);
        btnNum0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum0ActionPerformed(evt);
            }
        });

        btnNum8.setBackground(new java.awt.Color(0, 0, 0));
        btnNum8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum8.setForeground(new java.awt.Color(204, 204, 204));
        btnNum8.setText("8");
        btnNum8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum8.setOpaque(false);
        btnNum8.setRequestFocusEnabled(false);
        btnNum8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum8ActionPerformed(evt);
            }
        });

        btnNum9.setBackground(new java.awt.Color(0, 0, 0));
        btnNum9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum9.setForeground(new java.awt.Color(204, 204, 204));
        btnNum9.setText("9");
        btnNum9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum9.setOpaque(false);
        btnNum9.setRequestFocusEnabled(false);
        btnNum9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum9ActionPerformed(evt);
            }
        });

        btnNum7.setBackground(new java.awt.Color(0, 0, 0));
        btnNum7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNum7.setForeground(new java.awt.Color(204, 204, 204));
        btnNum7.setText("7");
        btnNum7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNum7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNum7.setOpaque(false);
        btnNum7.setRequestFocusEnabled(false);
        btnNum7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNum7ActionPerformed(evt);
            }
        });

        btnConfirma.setBackground(java.awt.Color.green);
        btnConfirma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnConfirma.setText("CONFIRMA");
        btnConfirma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirma.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnConfirma.setOpaque(false);
        btnConfirma.setRequestFocusEnabled(false);
        btnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmaActionPerformed(evt);
            }
        });

        btnBranco.setBackground(new java.awt.Color(255, 255, 255));
        btnBranco.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnBranco.setText("BRANCO");
        btnBranco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBranco.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBranco.setOpaque(false);
        btnBranco.setRequestFocusEnabled(false);
        btnBranco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrancoActionPerformed(evt);
            }
        });

        btnCorrige.setBackground(new java.awt.Color(255, 85, 0));
        btnCorrige.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnCorrige.setText("CORRIGE");
        btnCorrige.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCorrige.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCorrige.setOpaque(false);
        btnCorrige.setRequestFocusEnabled(false);
        btnCorrige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorrigeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNum0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNum5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBranco, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCorrige)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirma)
                    .addComponent(btnNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNum5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNum0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBranco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCorrige, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNum4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNum6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(btnConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Zona Eleitoral:");

        lblZonaEleitoral.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblZonaEleitoral.setText("00");

        lblNomeEleitor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNomeEleitor.setText("eleitor");

        jLabel7.setText("Eleitor:");

        jLabel9.setText("Título:");

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTitulo.setText("titulo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblZonaEleitoral, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeEleitor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblZonaEleitoral))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblNomeEleitor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTitulo)))
        );

        painelFIM.setBackground(new java.awt.Color(204, 204, 204));
        painelFIM.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 90)); // NOI18N
        jLabel4.setText("F I M");

        javax.swing.GroupLayout painelFIMLayout = new javax.swing.GroupLayout(painelFIM);
        painelFIM.setLayout(painelFIMLayout);
        painelFIMLayout.setHorizontalGroup(
            painelFIMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFIMLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        painelFIMLayout.setVerticalGroup(
            painelFIMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelFIMLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        painelVoto.setBackground(new java.awt.Color(204, 204, 204));
        painelVoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("SEU VOTO PARA");

        lblTipoCandidato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTipoCandidato.setText("Vereador");

        lblNumero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNumero.setText("Número:");

        lblnumVoto1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnumVoto1.setAlignmentX(0.5F);
        lblnumVoto1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        lblnumVoto2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnumVoto2.setAlignmentX(0.5F);
        lblnumVoto2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        lblnumVoto3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnumVoto3.setAlignmentX(0.5F);
        lblnumVoto3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        lblnumVoto4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnumVoto4.setAlignmentX(0.5F);
        lblnumVoto4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        lblnumVoto5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnumVoto5.setAlignmentX(0.5F);
        lblnumVoto5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNome.setText("Nome:");

        lblpart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblpart.setText("Partido:");

        lblCandidato.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCandidato.setText("nome");

        lblNomePartido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNomePartido.setText("partido");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("___________________________________________________");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Aperte a tecla:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("VERDE para CONFIRMAR este voto");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("LARANJA para REINICIAR seu voto");

        lblVotoEmBranco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVotoEmBranco.setText("VOTO EM BRANCO");

        lblVotoNulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblVotoNulo.setText("VOTO NULO");

        javax.swing.GroupLayout painelVotoLayout = new javax.swing.GroupLayout(painelVoto);
        painelVoto.setLayout(painelVotoLayout);
        painelVotoLayout.setHorizontalGroup(
            painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVotoLayout.createSequentialGroup()
                .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelVotoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addGroup(painelVotoLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(painelVotoLayout.createSequentialGroup()
                        .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelVotoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3))
                            .addGroup(painelVotoLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(lblTipoCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelVotoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCandidato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(painelVotoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblpart, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNomePartido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(painelVotoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblnumVoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblnumVoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblnumVoto3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblnumVoto4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblnumVoto5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelVotoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVotoEmBranco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblVotoNulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(painelVotoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelVotoLayout.setVerticalGroup(
            painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelVotoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblTipoCandidato)
                .addGap(18, 18, 18)
                .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNumero)
                    .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblnumVoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblnumVoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblnumVoto3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblnumVoto4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblnumVoto5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVotoEmBranco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblCandidato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVotoNulo)
                .addGap(7, 7, 7)
                .addGroup(painelVotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblpart)
                    .addComponent(lblNomePartido))
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelFIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelVoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(painelVoto, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painelFIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum1ActionPerformed
        // TODO add your handling code here:
        insereNumero(1);
    }//GEN-LAST:event_btnNum1ActionPerformed

    private void btnConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmaActionPerformed
        // TODO add your handling code here:
        if (lblnumVoto1.getText().equals("")) {
            System.out.println("Nada selecionado");
            return;
        }
        if (votacaoAtual == CargoPretendido.PREFEITO) {
            int voto = this.voto[0] * 10 + this.voto[1];
            if (this.candidatoVoto != null) {
                System.out.println("Existe candidato");
                System.out.println(voto);
                cUrna.recebeVotoPrefeito(this.candidatoVoto.getNumero(), eleitor.getTitulo(), this.zona);

            } else {
                System.out.println("Voto nulo");
                cUrna.recebeVotoPrefeito(-1, eleitor.getTitulo(), this.zona);
            }

            System.out.println(voto);
            jLabel4.setVisible(true);
            painelVoto.setVisible(false);
            painelFIM.setVisible(true);
        }
        if (votacaoAtual == CargoPretendido.VEREADOR) {
            System.out.println("Voto para VEREADOR");
            int voto = this.voto[0] * 10000 + this.voto[1] * 1000 + this.voto[2] * 100 + this.voto[3] * 10 + this.voto[4];
            if (this.candidatoVoto != null) {
                System.out.println("Existe candidato");
                System.out.println(voto);
                cUrna.recebeVotoVereador(this.candidatoVoto.getNumero(), eleitor.getTitulo(), this.zona);

            } else {
                System.out.println("Voto nulo");
                cUrna.recebeVotoVereador(-1, eleitor.getTitulo(), this.zona);
            }
            this.votacaoAtual = CargoPretendido.PREFEITO;
            lblnumVoto3.setVisible(false);
            lblnumVoto4.setVisible(false);
            lblnumVoto5.setVisible(false);
            lblTipoCandidato.setText("Prefeito");
            for (int i = 0; i < 5; i++) {
                this.voto[i] = -1;
            }
            corrige();
        }
    }//GEN-LAST:event_btnConfirmaActionPerformed

    private void btnBrancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrancoActionPerformed
        // TODO add your handling code here:
        if (!lblnumVoto1.getText().equals("")) { //caso ja tenha algum numero selecionado
            return;
        }

        for (int i = 0; i < 5; i++) {
            voto[i] = 0;
        }
        lblnumVoto1.setText("0");
        if (this.votacaoAtual == CargoPretendido.PREFEITO) {
            candidatoVoto = new Candidato("Branco", 0, "", CargoPretendido.PREFEITO);
            System.out.println("PREFEITO BRANCO");
        } else {
            candidatoVoto = new Candidato("Branco", 0, "", CargoPretendido.VEREADOR);
            System.out.println("VEREADOR BRANCO");
        }

        lblVotoEmBranco.setVisible(true);
        lblNumero.setVisible(false);
        lblnumVoto1.setVisible(false);
        lblnumVoto2.setVisible(false);
        lblnumVoto3.setVisible(false);
        lblnumVoto4.setVisible(false);
        lblnumVoto5.setVisible(false);
        lblNome.setVisible(false);
        lblCandidato.setVisible(false);
        lblpart.setVisible(false);
        lblNomePartido.setVisible(false);

    }//GEN-LAST:event_btnBrancoActionPerformed

    private void btnCorrigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrigeActionPerformed
        // TODO add your handling code here:
        corrige();
    }//GEN-LAST:event_btnCorrigeActionPerformed

    private void btnNum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum2ActionPerformed
        // TODO add your handling code here:
        insereNumero(2);
    }//GEN-LAST:event_btnNum2ActionPerformed

    private void btnNum3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum3ActionPerformed
        // TODO add your handling code here:
        insereNumero(3);
    }//GEN-LAST:event_btnNum3ActionPerformed

    private void btnNum4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum4ActionPerformed
        // TODO add your handling code here:
        insereNumero(4);
    }//GEN-LAST:event_btnNum4ActionPerformed

    private void btnNum5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum5ActionPerformed
        // TODO add your handling code here:
        insereNumero(5);
    }//GEN-LAST:event_btnNum5ActionPerformed

    private void btnNum6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum6ActionPerformed
        // TODO add your handling code here:
        insereNumero(6);
    }//GEN-LAST:event_btnNum6ActionPerformed

    private void btnNum7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum7ActionPerformed
        // TODO add your handling code here:
        insereNumero(7);
    }//GEN-LAST:event_btnNum7ActionPerformed

    private void btnNum8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum8ActionPerformed
        // TODO add your handling code here:
        insereNumero(8);
    }//GEN-LAST:event_btnNum8ActionPerformed

    private void btnNum9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum9ActionPerformed
        // TODO add your handling code here:
        insereNumero(9);
    }//GEN-LAST:event_btnNum9ActionPerformed

    private void btnNum0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNum0ActionPerformed
        // TODO add your handling code here:
        insereNumero(0);
    }//GEN-LAST:event_btnNum0ActionPerformed

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
            java.util.logging.Logger.getLogger(UrnaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UrnaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UrnaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UrnaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                int zona = 1;
                //new UrnaVotacao(zona).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBranco;
    private javax.swing.JButton btnConfirma;
    private javax.swing.JButton btnCorrige;
    private javax.swing.JButton btnNum0;
    private javax.swing.JButton btnNum1;
    private javax.swing.JButton btnNum2;
    private javax.swing.JButton btnNum3;
    private javax.swing.JButton btnNum4;
    private javax.swing.JButton btnNum5;
    private javax.swing.JButton btnNum6;
    private javax.swing.JButton btnNum7;
    private javax.swing.JButton btnNum8;
    private javax.swing.JButton btnNum9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCandidato;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeEleitor;
    private javax.swing.JLabel lblNomePartido;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblTipoCandidato;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVotoEmBranco;
    private javax.swing.JLabel lblVotoNulo;
    private javax.swing.JLabel lblZonaEleitoral;
    private javax.swing.JLabel lblnumVoto1;
    private javax.swing.JLabel lblnumVoto2;
    private javax.swing.JLabel lblnumVoto3;
    private javax.swing.JLabel lblnumVoto4;
    private javax.swing.JLabel lblnumVoto5;
    private javax.swing.JLabel lblpart;
    private javax.swing.JPanel painelFIM;
    private javax.swing.JPanel painelVoto;
    // End of variables declaration//GEN-END:variables

    private void corrige() {
        for (int i = 0; i < 5; i++) {
            voto[i] = -1;
        }
        if (this.votacaoAtual == CargoPretendido.VEREADOR) {
            lblnumVoto3.setVisible(true);
            lblnumVoto4.setVisible(true);
            lblnumVoto5.setVisible(true);
        }
        this.candidatoVoto = null;
        atualizaNumerosVotacao();
        lblVotoEmBranco.setVisible(false);
        lblNumero.setVisible(true);
        lblnumVoto1.setVisible(true);
        lblnumVoto2.setVisible(true);

        lblNome.setVisible(true);
        lblCandidato.setVisible(false);
        lblpart.setVisible(true);
        lblNomePartido.setVisible(false);
        lblVotoNulo.setVisible(false);
    }
}
