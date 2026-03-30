package com.agendecitas.repository;

import com.agendecitas.model.Cita;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CitaRepository {
    private static final List<Cita> CITAS = new ArrayList<>();
    private static final AtomicInteger SECUENCIA = new AtomicInteger(1);

    private CitaRepository() {
    }

    public static synchronized void guardar(Cita cita) {
        CITAS.add(cita);
    }

    public static synchronized List<Cita> listar() {
        return new ArrayList<>(CITAS);
    }

    public static synchronized boolean eliminar(int id) {
        return CITAS.removeIf(cita -> cita.getId() == id);
    }

    public static int siguienteId() {
        return SECUENCIA.getAndIncrement();
    }
}
