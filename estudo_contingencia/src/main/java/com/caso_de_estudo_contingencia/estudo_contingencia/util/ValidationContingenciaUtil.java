package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.Calendar;

import org.springframework.http.HttpStatus;

import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.Contingencia;

import io.micrometer.core.instrument.util.StringUtils;

public class ValidationContingenciaUtil {

    public static Contingencia validate(Contingencia contingencia) throws Exception {

        if (contingencia == null || contingencia.getTipo() == null
                || contingencia.getDataInicio() == null || contingencia.getDataFim() == null
                || !contingencia.hasAttMandatory()) {
            throw new CustomException(
                    "Campos obrigatorios não preenchidos! (Tipo, Locais/Postos, Órgãos, Serviços ou datas)",
                    HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isNotBlank(contingencia.getTodosPostos()) &&
                contingencia.getTodosPostos().equalsIgnoreCase("S") &&
                !DateUtil.sameDay(contingencia.getDataInicio(), contingencia.getDataFim())) {
            throw new CustomException("Uma contingência para todos os postos não pode ultrapassar 1 dia!",
                    HttpStatus.BAD_REQUEST);
        }

        if (DateUtil.after(contingencia.getDataInicio(), contingencia.getDataFim())) {
            throw new CustomException("Data início não pode ser superior a data fim", HttpStatus.BAD_REQUEST);
        }

        if (DateUtil.minimumTimeToPut(contingencia.getDataInicio(), contingencia.getDataFim())) {
            throw new CustomException("O período mínimo para cadastrar uma contingencia é de 1 minuto!",
                    HttpStatus.BAD_REQUEST);
        }

        Calendar agora = Calendar.getInstance();
        Calendar dataFim = Calendar.getInstance();

        dataFim.setTimeInMillis(contingencia.getDataFim());

        if (dataFim.before(agora)) {
            throw new CustomException("Não é possível cadastrar contingência retroativa!", HttpStatus.BAD_REQUEST);
        }

        return contingencia;
    }

}
