package com.supervielle.backend.domain.alta_producto;

import com.supervielle.backend.domain.alta_producto.alta_tarjeta.AltaTarjetaUtils;
import com.supervielle.backend.domain.alta_producto.alta_voucher.AltaVoucherUtils;

public class AltaProductoUtils {

    public static final String PRODUCTO_ONBOARDING_VOUCHER = "onboarding.voucher";
    public static final String ONBOARDING_VOUCHER_OFFER = "Voucher";
    
    public static String getResponseFromProductRegistrationService(String prospect, String product) {
        switch (product) {
        case PRODUCTO_ONBOARDING_VOUCHER:
            return AltaVoucherUtils.getResponseFromVoucherRegistrationService(prospect);
        default:
            return AltaTarjetaUtils.getResponseFromTarjetaRegistrationService(prospect);
        }
    }
    
    public static String getRequestFromProductRegistrationService(String prospect, String product) {
        switch (product) {
        case PRODUCTO_ONBOARDING_VOUCHER:
            return AltaVoucherUtils.getRequestFromVoucherRegistrationService(prospect);
        default:
            return AltaTarjetaUtils.getRequestFromTarjetaRegistrationService(prospect);
        }
    }
    
}
