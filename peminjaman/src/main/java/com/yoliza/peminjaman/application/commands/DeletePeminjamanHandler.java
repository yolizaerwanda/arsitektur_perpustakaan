package com.yoliza.peminjaman.application.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yoliza.peminjaman.application.CommandHandler;
import com.yoliza.peminjaman.dto.CommandResult;
import com.yoliza.peminjaman.repository.PeminjamanRepository;

/**
 * Handler for DeletePeminjamanCommand
 * Returns CommandResult (CQRS compliant)
 */
@Component
public class DeletePeminjamanHandler implements CommandHandler<DeletePeminjamanCommand, CommandResult> {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Override
    public CommandResult handle(DeletePeminjamanCommand command) {
        if (peminjamanRepository.existsById(command.getId())) {
            peminjamanRepository.deleteById(command.getId());
            return CommandResult.success(command.getId(), "Peminjaman deleted successfully");
        }
        return CommandResult.failure("Peminjaman not found with ID: " + command.getId());
    }
}
