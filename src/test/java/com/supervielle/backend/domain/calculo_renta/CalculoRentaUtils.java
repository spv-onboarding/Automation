package com.supervielle.backend.domain.calculo_renta;

import com.supervielle.backend.data.CalculoRentaDao;
import com.supervielle.backend.domain.sacnosis.SacnosisUtils;
import com.supervielle.backend.domain.veraz.VerazUtils;
import com.supervielle.framework.utils.NumberUtils;

public class CalculoRentaUtils {

    private static final int POLITICA_GENERAL_ID = 10;
    private static final int POLITICA_ESPEJO_ID  = 20;

    public static double getMaximumIncome(String SACNOSISresponse, String verazResponse) {
        double NSEValue = CalculoRentaDao.getNivelSocioEconomico(SacnosisUtils.getCategoryFromNSE(SACNOSISresponse))
                .getIngresoDeterminado();
        double incomePredictorValue = CalculoRentaDao
                .getIngresoIP(VerazUtils.getIncomePredictorCategoryFromVerazResponse(verazResponse))
                .getIngresoDeterminado();
        double maxBankGroups = VerazUtils.getMaxValueOfGruposBanco(verazResponse);
        return NumberUtils.getMaxOfNumbers(NSEValue, incomePredictorValue, maxBankGroups);
    }

    public static int getPoliticaIdFromResponse(String SACNOSISresponse, String verazResponse) {
        double maxBankGroups = VerazUtils.getMaxValueOfGruposBanco(verazResponse);
        double maxIncome = getMaximumIncome(SACNOSISresponse, verazResponse);
        if (maxBankGroups == maxIncome) {
            return POLITICA_ESPEJO_ID;
        }
        return POLITICA_GENERAL_ID;
    }

}
