package interfaces;

import ClienteServidor.Cliente;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utilitarios.ChecaTempo;

public class InterfaceJogo extends javax.swing.JFrame {

    private InterfacePrincipal principal;
    private static int ptsJ1, ptsJ2;
    private static String nomeJ1, nomeJ2, jogador, mensagem, imgJ1, imgJ2;
    private static Cliente cliente;
    private static int numJogador;

    public InterfaceJogo() {
        initComponents();

        lblMensagem.setText("Bem vindo!");
    }

    public InterfaceJogo(InterfacePrincipal principal, int ptsJ1, int ptsJ2, String nomeJ1, String nomeJ2, String jogador, String mensagem, String imgJ1, String imgJ2, int numJogador, Cliente cliente) throws HeadlessException {
        initComponents();
        this.ptsJ1 = ptsJ1;
        this.ptsJ2 = ptsJ2;
        this.nomeJ1 = nomeJ1;
        this.nomeJ2 = nomeJ2;
        this.jogador = jogador;
        this.mensagem = mensagem;
        this.imgJ1 = imgJ1;
        this.imgJ2 = imgJ2;
        this.cliente = cliente;
        this.numJogador = numJogador;
        this.principal = principal;
        atualizaCampos();

    }

    private static void travaBotoes() {
        btnPedra.setEnabled(false);
        btnPapel.setEnabled(false);
        btnTesoura.setEnabled(false);

        btnPedra.paintAll(btnPedra.getGraphics());
        btnPapel.paintAll(btnPapel.getGraphics());
        btnTesoura.paintAll(btnTesoura.getGraphics());
    }

    private static void destravaBotoes() {
        btnPedra.setEnabled(true);
        btnPapel.setEnabled(true);
        btnTesoura.setEnabled(true);

        btnPedra.paintAll(btnPedra.getGraphics());
        btnPapel.paintAll(btnPapel.getGraphics());
        btnTesoura.paintAll(btnTesoura.getGraphics());
    }

    private void encerra() {
        principal.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    private boolean enviaJogada(String jogada) {
        travaBotoes();
        String mensagem;

        mensagem = cliente.fazComunicacao(jogada, 20); //envia jogada
        atualizaDadosServ(mensagem);

        imgJogador1.setIcon(new ImageIcon("src/imagens/pedra.png"));
        imgJogador2.setIcon(new ImageIcon("src/imagens/interrogacao.png"));

        mensagem = cliente.fazComunicacao(jogada, 20); //recebe dados atualizados

        atualizaDadosServ(mensagem);

        mensagem = cliente.fazComunicacao(jogada, 20); //recebe mensagem
        System.out.println("Mensagem: " + mensagem);

        if (mensagem.equals("Não terminou")) { //se jogo nao terminou
            System.out.println("Jogo não terminou");

        } else {
            cliente.encerraCliente();
            System.err.println("Jogo terminou");
            lblMensagem.setText("Jogo Terminou!");
            lblMensagem.paintAll(lblMensagem.getGraphics());//atualiza label
            travaBotoes();
            JOptionPane.showMessageDialog(null, "O Jogo terminou!\n" + mensagem);
            encerra();
            return false;
        }

        destravaBotoes();
        return true;
    }

    private void atualizaDadosServ(String mensagemServidor) {
        String img1, img2;
        nomeJ1 = mensagemServidor.split(";")[0];
        nomeJ2 = mensagemServidor.split(";")[1];
        ptsJ1 = Integer.parseInt(mensagemServidor.split(";")[2]);
        ptsJ2 = Integer.parseInt(mensagemServidor.split(";")[3]);
        mensagem = mensagemServidor.split(";")[4];
        img1 = mensagemServidor.split(";")[5];
        img2 = mensagemServidor.split(";")[6];

        switch (img1) {
            case "-1":
                imgJ1 = "vazio";
                break;

            case "0":
                imgJ1 = "intorragacao";
                break;

            case "1":
                imgJ1 = "pedra";
                break;

            case "2":
                imgJ1 = "papel";
                break;

            case "3":
                imgJ1 = "tesoura";
                break;
        }

        switch (img2) {
            case "-1":
                imgJ2 = "vazio";
                break;

            case "0":
                imgJ2 = "intorragacao";
                break;

            case "1":
                imgJ2 = "pedra";
                break;

            case "2":
                imgJ2 = "papel";
                break;

            case "3":
                imgJ2 = "tesoura";
                break;
        }

        atualizaCampos();
    }

    private static void atualizaCampos() {
        lblNomeJ1.setText(nomeJ1);
        lblNomeJ2.setText(nomeJ2);
        lblPtsJ1.setText(Integer.toString(ptsJ1));
        lblPtsJ2.setText(Integer.toString(ptsJ2));
        lblJogador.setText("Você é o Jogador" + jogador);
        lblMensagem.setText(mensagem);

        if (!imgJ1.equals("")) {
            imgJogador1.setIcon(new ImageIcon("src/imagens/" + imgJ1 + ".png"));
        } else {
            imgJogador1.setIcon(new ImageIcon("src/imagens/vazio.png"));
        }

        if (!imgJ2.equals("")) {
            imgJogador2.setIcon(new ImageIcon("src/imagens/" + imgJ2 + ".png"));
        } else {
            imgJogador2.setIcon(new ImageIcon("src/imagens/vazio.png"));
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        imgJogador2 = new javax.swing.JLabel();
        imgJogador1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNomeJ1 = new javax.swing.JLabel();
        lblPtsJ1 = new javax.swing.JLabel();
        lblPtsJ2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNomeJ2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblJogador = new javax.swing.JLabel();
        lblMensagem = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnPedra = new javax.swing.JButton();
        btnPapel = new javax.swing.JButton();
        btnTesoura = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.SystemColor.textHighlight);

        jPanel1.setBackground(java.awt.SystemColor.inactiveCaption);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(java.awt.SystemColor.activeCaption);
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        imgJogador2.setBackground(new java.awt.Color(0, 0, 255));
        imgJogador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgJogador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vazio.png"))); // NOI18N
        imgJogador2.setBorder(javax.swing.BorderFactory.createTitledBorder("Jogador2"));

        imgJogador1.setBackground(new java.awt.Color(0, 0, 255));
        imgJogador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgJogador1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vazio.png"))); // NOI18N
        imgJogador1.setToolTipText("");
        imgJogador1.setBorder(javax.swing.BorderFactory.createTitledBorder("Jogador 1"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(imgJogador1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imgJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgJogador1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(java.awt.SystemColor.textHighlight);
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Placar:");

        lblNomeJ1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNomeJ1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNomeJ1.setText("Jogador 1");

        lblPtsJ1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPtsJ1.setForeground(new java.awt.Color(0, 0, 255));
        lblPtsJ1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPtsJ1.setText("0");

        lblPtsJ2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPtsJ2.setForeground(new java.awt.Color(0, 0, 255));
        lblPtsJ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPtsJ2.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("X");

        lblNomeJ2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNomeJ2.setText("Jogador 2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(lblNomeJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblPtsJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(lblPtsJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblNomeJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNomeJ1)
                    .addComponent(lblPtsJ1)
                    .addComponent(lblNomeJ2)
                    .addComponent(lblPtsJ2)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15))
        );

        jPanel4.setBackground(java.awt.SystemColor.activeCaption);
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblJogador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJogador.setText("Você é o Jogador 1");
        lblJogador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblMensagem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensagem.setText("Parabéns! Você ganhou esta rodada!");
        lblMensagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblJogador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblJogador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel5.setBackground(java.awt.SystemColor.activeCaption);
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Escolha sua jogada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btnPedra.setBackground(java.awt.SystemColor.activeCaption);
        btnPedra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pedra.png"))); // NOI18N
        btnPedra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedraActionPerformed(evt);
            }
        });

        btnPapel.setBackground(java.awt.SystemColor.activeCaption);
        btnPapel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/papel.png"))); // NOI18N
        btnPapel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPapelActionPerformed(evt);
            }
        });

        btnTesoura.setBackground(java.awt.SystemColor.activeCaption);
        btnTesoura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/tesoura.png"))); // NOI18N
        btnTesoura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTesouraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPedra)
                .addGap(70, 70, 70)
                .addComponent(btnPapel)
                .addGap(70, 70, 70)
                .addComponent(btnTesoura)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTesoura, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPedra, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel6.setBackground(java.awt.SystemColor.activeCaption);
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2017");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPedraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedraActionPerformed
        // TODO add your handling code here:

        try {
            if (jogador.equals("1")) {
                imgJogador1.setIcon(new ImageIcon("src/imagens/pedra.png"));
                imgJogador2.setIcon(new ImageIcon("src/imagens/interrogacao.png"));
            } else {
                imgJogador2.setIcon(new ImageIcon("src/imagens/pedra.png"));
                imgJogador1.setIcon(new ImageIcon("src/imagens/interrogacao.png"));
            }

            imgJogador1.paintAll(imgJogador1.getGraphics());
            imgJogador2.paintAll(imgJogador2.getGraphics());
            enviaJogada("1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Isso é constrangedor! Aconteceu algum erro e o jogo será reiniciado.");
            encerra();
            return;
        }
    }//GEN-LAST:event_btnPedraActionPerformed

    private void btnPapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPapelActionPerformed
        // TODO add your handling code here:
        try {
            if (jogador.equals("1")) {
                imgJogador1.setIcon(new ImageIcon("src/imagens/papel.png"));
                imgJogador2.setIcon(new ImageIcon("src/imagens/interrogacao.png"));
            } else {
                imgJogador2.setIcon(new ImageIcon("src/imagens/papel.png"));
                imgJogador1.setIcon(new ImageIcon("src/imagens/interrogacao.png"));
            }

            imgJogador1.paintAll(imgJogador1.getGraphics());
            imgJogador2.paintAll(imgJogador2.getGraphics());
            enviaJogada("2");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Isso é constrangedor! Aconteceu algum erro e o jogo será reiniciado.");
            encerra();
            return;
        }
    }//GEN-LAST:event_btnPapelActionPerformed

    private void btnTesouraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTesouraActionPerformed
        // TODO add your handling code here:
        try {
            if (jogador.equals("1")) {
                imgJogador1.setIcon(new ImageIcon("src/imagens/tesoura.png"));
                imgJogador2.setIcon(new ImageIcon("src/imagens/interrogacao.png"));
            } else {
                imgJogador2.setIcon(new ImageIcon("src/imagens/tesoura.png"));
                imgJogador1.setIcon(new ImageIcon("src/imagens/interrogacao.png"));
            }
            imgJogador1.paintAll(imgJogador1.getGraphics());
            imgJogador2.paintAll(imgJogador2.getGraphics());
            enviaJogada("3");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Isso é constrangedor! Aconteceu algum erro e o jogo será reiniciado.");
            encerra();
            return;
        }
    }//GEN-LAST:event_btnTesouraActionPerformed

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
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceJogo().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btnPapel;
    private static javax.swing.JButton btnPedra;
    private static javax.swing.JButton btnTesoura;
    private static javax.swing.JLabel imgJogador1;
    private static javax.swing.JLabel imgJogador2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private static javax.swing.JLabel lblJogador;
    private static javax.swing.JLabel lblMensagem;
    private static javax.swing.JLabel lblNomeJ1;
    private static javax.swing.JLabel lblNomeJ2;
    private static javax.swing.JLabel lblPtsJ1;
    private static javax.swing.JLabel lblPtsJ2;
    // End of variables declaration//GEN-END:variables
}
