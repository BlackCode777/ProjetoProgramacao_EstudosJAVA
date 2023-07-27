package com.caso_de_estudo_contingencia.estudo_contingencia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.TipoContingencia;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.ContingenciaRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.repo.TipoContingenciaRepo;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.DateUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.ParseTipoContingenciaUtil;
import com.caso_de_estudo_contingencia.estudo_contingencia.util.QueryUtils;

@Service
public class TipoContingenciaService {

    @Autowired
    TipoContingenciaRepo tipoContiRepo;
    @Autowired
    ContingenciaRepo contingenciaRepo;
    @Autowired
    ContingenciaService contingenciaService;
    @Autowired
    QueriesService queryService;

    public TipoContingencia carregarData(String id) throws Exception {
        return tipoContiRepo.findById(id).get();
    }

    public void saveData(TipoContingencia tipoContingencia) throws Exception {
        if (hasTipoContingenciaSameTipoOcorrencia(tipoContingencia.getTipoOcorrencia(), tipoContingencia.getId())) {
            throw new CustomException(
                    "O tipo de contingencia existe. Não é possivel ter tipos de contingencia com mesmo descritivo de Tipo de Ocorrencia.",
                    HttpStatus.BAD_REQUEST);
        }
        tipoContiRepo.save(tipoContingencia);
    }

    public void deleteData(String id) throws Exception {
        if (contingenciaService.hasContingenciaByTipoAndData(DateUtil.now(), id)) {
            throw new CustomException(
                    "Não é possivel cancelar esse tipo de contingencia pois existe uma contingencia vigente relacionada a esse!",
                    HttpStatus.BAD_REQUEST);
        }
        tipoContiRepo.deleteById(id);
    }

    public List<TipoContingencia> listData() throws Exception {
        List<TipoContingencia> tipos = new ArrayList<TipoContingencia>();

        Iterable<TipoContingencia> tiposIt = tipoContiRepo.findAll();

        tiposIt.forEach(tipos::add);

        return tipos;
    }

    public boolean hasTipoContingenciaSameTipoOcorrencia(String tipoOcorrencia, String id) throws Exception {

        List<TipoContingencia> tipos = ParseTipoContingenciaUtil
                .parseDataToTipoContingencia(queryService.executeQueries(
                        QueryUtils.getQueryTipoContingenciaExiste(tipoOcorrencia, id), "sc_tipo_contingencia"));

        if (tipos != null && tipos.size() > 0) {
            return true;
        }

        return false;
    }

}
