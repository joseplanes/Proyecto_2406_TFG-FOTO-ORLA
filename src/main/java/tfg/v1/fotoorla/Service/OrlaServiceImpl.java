package tfg.v1.fotoorla.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tfg.v1.fotoorla.AutoMapper.OrlaMapper;
import tfg.v1.fotoorla.DTOModel.DTOOrla;
import tfg.v1.fotoorla.Model.Orla;
import tfg.v1.fotoorla.Repository.IOrlaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrlaServiceImpl {


    @Autowired
    private IOrlaRepository orlaRepository;

    @Autowired
    private OrlaMapper orlaMapper;

    public DTOOrla saveOrla(DTOOrla orlaDTO) {
        Orla orla = orlaMapper.orlaDTOToOrla(orlaDTO);
        return orlaMapper.orlaToOrlaDTO(orlaRepository.save(orla));
    }

    public List<DTOOrla> getAllOrlas() {
        List<Orla> orlas = orlaRepository.findAll();
        return orlas.stream()
                .map(orlaMapper::orlaToOrlaDTO)
                .collect(Collectors.toList());
    }

    public DTOOrla getOrlaById(Long id) {
        Orla orla = orlaRepository.findById(id).orElse(null);
        if (orla != null) {
            return orlaMapper.orlaToOrlaDTO(orla);
        }
        return null;
    }

    public void deleteOrla(Long id) {
        orlaRepository.deleteById(id);
    }
}
