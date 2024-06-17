package tfg.v1.fotoorla.Service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.v1.fotoorla.AutoMapper.ConfiguracionOrlaMapper;
import tfg.v1.fotoorla.DTOModel.DTOConfiguracionOrla;
import tfg.v1.fotoorla.Model.ConfiguracionOrla;
import tfg.v1.fotoorla.Repository.IConfiguracionOrlaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConfiguracionOrlaServiceImpl {

    @Autowired
    private IConfiguracionOrlaRepository configuracionOrlaRepository;

    @Autowired
    private ConfiguracionOrlaMapper configuracionOrlaMapper;

    public DTOConfiguracionOrla saveConfiguracionOrla(DTOConfiguracionOrla configuracionOrlaDTO) {
        ConfiguracionOrla configuracionOrla = configuracionOrlaMapper.dtoToConfiguracionOrla(configuracionOrlaDTO);
        ConfiguracionOrla savedConfiguracionOrla = configuracionOrlaRepository.save(configuracionOrla);
        return configuracionOrlaMapper.configuracionOrlaToDTO(savedConfiguracionOrla);
    }

    public List<DTOConfiguracionOrla> getAllConfiguracionesOrla() {
        List<ConfiguracionOrla> configuracionesOrla = configuracionOrlaRepository.findAll();
        return configuracionesOrla.stream()
                .map(configuracionOrlaMapper::configuracionOrlaToDTO)
                .collect(Collectors.toList());
    }

    public DTOConfiguracionOrla getConfiguracionOrlaById(Long id) {
        ConfiguracionOrla configuracionOrla = configuracionOrlaRepository.findById(id).orElse(null);
        return configuracionOrla != null ? configuracionOrlaMapper.configuracionOrlaToDTO(configuracionOrla) : null;
    }

    public void deleteConfiguracionOrla(Long id) {
        configuracionOrlaRepository.deleteById(id);
    }
}