package com.caso_de_estudo_contingencia.estudo_contingencia.api;

import org.springframework.http.HttpStatus;

import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;
import com.caso_de_estudo_contingencia.estudo_contingencia.model.TipoContingencia;

import io.micrometer.core.instrument.util.StringUtils;

public class ValidationTipoContingenciaUtil {

    public static TipoContingencia validate(TipoContingencia tipoContingencia) throws CustomException {

        if (tipoContingencia == null || tipoContingencia.getCodigoNotificacao() == null) {
            throw new CustomException("Campos obrigatorios não preenchidos(codigo notificacao e/ou tipo ocorrencia)!",
                    HttpStatus.BAD_REQUEST);
        }

        switch (tipoContingencia.getCodigoNotificacao()) {
            case 1:
            case 3:
                if (StringUtils.isBlank(tipoContingencia.getTipoOcorrencia())) {
                    throw new CustomException("Campos obrigatorios não preenchidos(tipo ocorrencia)!",
                            HttpStatus.BAD_REQUEST);
                }
                break;
            case 2:
            case 6:
                if (StringUtils.isBlank(tipoContingencia.getMensagemEmail())
                        || StringUtils.isBlank(tipoContingencia.getTipoOcorrencia())) {
                    throw new CustomException(
                            "Campos obrigatorios não preenchidos(tipo ocorrencia e/ou mensagem email)!",
                            HttpStatus.BAD_REQUEST);
                }
                break;
            case 4:
            case 7:
                if (StringUtils.isBlank(tipoContingencia.getMensagemPortal())
                        || StringUtils.isBlank(tipoContingencia.getTipoOcorrencia())) {
                    throw new CustomException(
                            "Campos obrigatorios não preenchidos(tipo ocorrencia e/ou mensagem portal)!",
                            HttpStatus.BAD_REQUEST);
                }
                break;
            case 5:
            case 8:
                if (StringUtils.isBlank(tipoContingencia.getMensagemEmail())
                        || StringUtils.isBlank(tipoContingencia.getMensagemPortal())
                        || StringUtils.isBlank(tipoContingencia.getTipoOcorrencia())) {
                    throw new CustomException(
                            "Campos obrigatorios não preenchidos(tipo ocorrencia, mensagem portal e/ou mensagem email)!",
                            HttpStatus.BAD_REQUEST);
                }
                break;
            default:
                break;
        }

        return tipoContingencia;
    }

}
