package com.gtrack.view;

import com.gtrack.dao.TarefaDAO;
import com.gtrack.model.Tarefa;
import com.gtrack.model.Usuario;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormPrincipal extends JFrame {

    private DefaultTableModel modeloTabela;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private int usuarioId;

    public FormPrincipal(Usuario usuario) {
        initComponents();
        configurarTabela();
        configurarMenuPopup();
        this.usuarioId = usuario.getId();
        carregarTarefasDoUsuario();

        // Exemplo: adicionando uma tarefa para testar
        
        atualizarTabela();
    }
    
    private void carregarTarefasDoUsuario() {
    TarefaDAO dao = new TarefaDAO();
    listaTarefas = dao.buscarTarefasPorUsuario(usuarioId);
    atualizarTabela();
}
    private void configurarTabela() {
    String[] colunas = {"ID", "Descrição", "Início", "Término", "Dia", "Concluída"};
    modeloTabela = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 1 || column == 5; // permitir editar só Descrição e Concluída
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 5 ? Boolean.class : String.class;
        }
    };

    tblTarefas.setModel(modeloTabela);

    // Ocultar coluna ID
    tblTarefas.getColumnModel().getColumn(0).setMinWidth(0);
    tblTarefas.getColumnModel().getColumn(0).setMaxWidth(0);
    tblTarefas.getColumnModel().getColumn(0).setWidth(0);

    // Configurar checkbox na coluna Concluída
    tblTarefas.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));
    tblTarefas.getColumnModel().getColumn(5).setCellRenderer(tblTarefas.getDefaultRenderer(Boolean.class));
}

    private void configurarMenuPopup() {
        JPopupMenu menuPopup = new JPopupMenu();
        JMenuItem itemConcluir = new JMenuItem("Marcar como Concluída");
        JMenuItem itemEditar = new JMenuItem("Editar Tarefa");
        JMenuItem itemExcluir = new JMenuItem("Excluir Tarefa");

        menuPopup.add(itemConcluir);
        menuPopup.add(itemEditar);
        menuPopup.add(itemExcluir);

        tblTarefas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int row = tblTarefas.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < tblTarefas.getRowCount()) {
                        tblTarefas.setRowSelectionInterval(row, row);
                        menuPopup.show(tblTarefas, e.getX(), e.getY());
                    }
                }
            }
        });

        itemConcluir.addActionListener(e -> {
    int row = tblTarefas.getSelectedRow();
    if (row != -1) {
        int id = (int) modeloTabela.getValueAt(row, 0);
        listaTarefas.stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .ifPresent(t -> {
                t.setConcluida(true);
                new TarefaDAO().atualizarTarefa(t);  // Atualiza no banco
            });
        modeloTabela.setValueAt(true, row, 5);
    }
});

itemEditar.addActionListener(e -> {
    int row = tblTarefas.getSelectedRow();
    if (row != -1) {
        String atual = modeloTabela.getValueAt(row, 1).toString();
        String nova = JOptionPane.showInputDialog(null, "Editar descrição:", atual);
        if (nova != null && !nova.trim().isEmpty()) {
            int id = (int) modeloTabela.getValueAt(row, 0);
            listaTarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .ifPresent(t -> {
                    t.setDescricao(nova);
                    new TarefaDAO().atualizarTarefa(t);  // Atualiza no banco
                });
            modeloTabela.setValueAt(nova, row, 1);
        }
    }
});

itemExcluir.addActionListener(e -> {
    int row = tblTarefas.getSelectedRow();
    if (row != -1) {
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?");
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) modeloTabela.getValueAt(row, 0);
            new TarefaDAO().excluirTarefa(id);  // Exclui no banco
            listaTarefas.removeIf(t -> t.getId() == id);
            modeloTabela.removeRow(row);
        }
    }
});
    }

    private void atualizarTabela() {
    modeloTabela.setRowCount(0);
    listaTarefas.sort(Comparator.comparing(Tarefa::getInicio));
    for (Tarefa t : listaTarefas) {
        modeloTabela.addRow(new Object[]{
            t.getId(),
            t.getDescricao(),
            t.getInicio(),
            t.getTermino(),
            t.getDiaSemana(),
            t.isConcluida()
        });
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

        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarefas = new javax.swing.JTable();
        btnLogout = new javax.swing.JButton();
        btnOpcoes = new javax.swing.JButton();
        btnAdicionarTarefa = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        jButton6.setText("jButton6");

        jButton5.setBackground(new java.awt.Color(153, 0, 0));
        jButton5.setForeground(new java.awt.Color(255, 51, 51));
        jButton5.setText("🔒Logout");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new java.awt.BorderLayout());

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("GTrack -Gerenciador de Tarefas Semanais");
        jPanel3.add(lblTitulo, java.awt.BorderLayout.PAGE_START);

        tblTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
            }
        ));
        jScrollPane1.setViewportView(tblTarefas);

        btnLogout.setBackground(new java.awt.Color(153, 0, 0));
        btnLogout.setForeground(new java.awt.Color(255, 51, 51));
        btnLogout.setText("🔒Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnOpcoes.setBackground(new java.awt.Color(51, 51, 51));
        btnOpcoes.setForeground(new java.awt.Color(204, 204, 204));
        btnOpcoes.setText("⚙ Opções");
        btnOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcoesActionPerformed(evt);
            }
        });

        btnAdicionarTarefa.setBackground(new java.awt.Color(0, 153, 204));
        btnAdicionarTarefa.setText("+ Adicionar Tarefa");
        btnAdicionarTarefa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdicionarTarefa.setName(""); // NOI18N
        btnAdicionarTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTarefaActionPerformed(evt);
            }
        });

        btnAtualizar.setBackground(new java.awt.Color(0, 102, 0));
        btnAtualizar.setForeground(new java.awt.Color(153, 255, 153));
        btnAtualizar.setText("♻ Atualizar");
        btnAtualizar.setAlignmentX(4.0F);
        btnAtualizar.setAlignmentY(4.0F);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpcoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogout)
                        .addGap(0, 189, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAdicionarTarefa)
                                    .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdicionarTarefa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcoesActionPerformed
       JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.");
    }//GEN-LAST:event_btnOpcoesActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
    int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Confirmar Logout", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        this.dispose();
        new FormLogin().setVisible(true);
    }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnAdicionarTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTarefaActionPerformed
          FormAdicionarTarefa adicionarTarefa = new FormAdicionarTarefa(this.usuarioId);
    adicionarTarefa.setVisible(true);
    }//GEN-LAST:event_btnAdicionarTarefaActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
       carregarTarefasDoUsuario();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarTarefa;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOpcoes;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblTarefas;
    // End of variables declaration//GEN-END:variables
}
