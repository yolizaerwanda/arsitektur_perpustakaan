package com.yoliza.buku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoliza.buku.model.BukuModel;
import com.yoliza.buku.repository.BukuRepository;

@Service
public class BukuService {
    @Autowired
    private BukuRepository bukuRepository;

    public List<BukuModel> getAllBuku() {
        return bukuRepository.findAll();
    }

    public BukuModel getBukuById(Long id) {
        return bukuRepository.findById(id).orElse(null);
    }

    public BukuModel createBuku(BukuModel buku) {
        return bukuRepository.save(buku);
    }

    public void deleteBuku(Long id) {
        bukuRepository.deleteById(id);
    }

    public BukuModel updateBuku(Long id, BukuModel bukuUpdate) {
        return bukuRepository.findById(id)
                .map(existingBuku -> {
                    if (bukuUpdate.getJudul() != null) {
                        existingBuku.setJudul(bukuUpdate.getJudul());
                    }
                    if (bukuUpdate.getPengarang() != null) {
                        existingBuku.setPengarang(bukuUpdate.getPengarang());
                    }
                    if (bukuUpdate.getPenerbit() != null) {
                        existingBuku.setPenerbit(bukuUpdate.getPenerbit());
                    }
                    if (bukuUpdate.getTahun_terbit() != null) {
                        existingBuku.setTahun_terbit(bukuUpdate.getTahun_terbit());
                    }
                    return bukuRepository.save(existingBuku);
                })
                .orElse(null);
    }
}
