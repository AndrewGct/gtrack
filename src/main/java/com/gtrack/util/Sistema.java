package com.gtrack.util;

import com.gtrack.model.Tarefa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sistema {

    // Lista de tarefas "temporária", simulando banco de dados
    public static List<Tarefa> tarefas = new ArrayList<>();
    private static int contadorId = 1;

    // Adicionar nova tarefa
    public static void adicionarTarefa(Tarefa tarefa) {
        tarefa.setId(contadorId++);
        tarefas.add(tarefa);
    }

    // Remover tarefa por referência
    public static void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

    // Editar diretamente (sem popup)
    public static void editarTarefa(int id, String novaDescricao, String novoInicio, String novoTermino) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) {
                t.setDescricao(novaDescricao);
                t.setInicio(novoInicio);
                t.setTermino(novoTermino);
                break;
            }
        }
    }

    // Marcar como concluída
    public static void concluirTarefa(int id) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) {
                t.setConcluida(true);
                break;
            }
        }
    }

    // Marcar como não feita
    public static void desfazerConclusao(int id) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) {
                t.setConcluida(false);
                break;
            }
        }
    }

    // Listar tarefas ordenadas por horário de início (HH:mm)
    public static List<Tarefa> listarTarefasOrdenadas() {
        List<Tarefa> ordenadas = new ArrayList<>(tarefas);
        Collections.sort(ordenadas, Comparator.comparing(Tarefa::getInicio));
        return ordenadas;
    }

    // Filtrar tarefas por dia da semana
    public static List<Tarefa> listarTarefasPorDia(String diaSemana) {
        List<Tarefa> filtradas = new ArrayList<>();
        for (Tarefa t : tarefas) {
            if (t.getDiaSemana().equalsIgnoreCase(diaSemana)) {
                filtradas.add(t);
            }
        }
        filtradas.sort(Comparator.comparing(Tarefa::getInicio));
        return filtradas;
    }
}