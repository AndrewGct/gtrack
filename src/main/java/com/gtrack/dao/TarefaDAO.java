package com.gtrack.dao;

import com.gtrack.model.Tarefa;
import com.gtrack.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TarefaDAO {

   
    
    public boolean adicionarTarefa(Tarefa tarefa) {
    String sql = "INSERT INTO tarefas (descricao, inicio, termino, diaSemana, concluida, usuarioId) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, tarefa.getDescricao());
        stmt.setString(2, tarefa.getInicio());
        stmt.setString(3, tarefa.getTermino());
        stmt.setString(4, tarefa.getDiaSemana());
        stmt.setBoolean(5, tarefa.isConcluida());
        stmt.setInt(6, tarefa.getUsuarioId());

        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao adicionar tarefa: " + e.getMessage());
        return false;
    }
}
    
    public List<Tarefa> buscarTarefasPorUsuario(int usuarioId) {
    List<Tarefa> tarefas = new ArrayList<>();
    String sql = "SELECT * FROM tarefas WHERE usuarioId = ? ORDER BY inicio";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, usuarioId);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Tarefa tarefa = new Tarefa(
                rs.getInt("id"),
                rs.getString("descricao"),
                rs.getString("inicio"),
                rs.getString("termino"),
                rs.getString("diaSemana"),
                rs.getBoolean("concluida"),
                rs.getInt("usuarioId")
            );
            tarefas.add(tarefa);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar tarefas: " + e.getMessage());
    }
    return tarefas;
}
    public boolean atualizarTarefa(Tarefa tarefa) {
    String sql = "UPDATE tarefas SET descricao = ?, inicio = ?, termino = ?, diaSemana = ?, concluida = ? WHERE id = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, tarefa.getDescricao());
        stmt.setString(2, tarefa.getInicio());
        stmt.setString(3, tarefa.getTermino());
        stmt.setString(4, tarefa.getDiaSemana());
        stmt.setBoolean(5, tarefa.isConcluida());
        stmt.setInt(6, tarefa.getId());

        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar tarefa: " + e.getMessage());
        return false;
    }
}

public boolean excluirTarefa(int id) {
    String sql = "DELETE FROM tarefas WHERE id = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa: " + e.getMessage());
        return false;
    }
}
}