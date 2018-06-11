package com.lxsoft.interceptor.pagination.dto;
/**
 * 产品字段
 * @author Administrator
 *
 */
public class ProductField {
	/**
	 * getField
	 * @param value
	 * @return String
	 */
	public static String getField(String value) {
		if (("recommendLevel").equals(value)) {
			return "RECOMMEND_LEVEL";
		}else if (("riskLevel").equals(value)) {
			return "RISK_LEVEL";
		} else if (("productType").equals(value)) {
			return "PRODUCT_TYPE";
		} else if (("productCode").equals(value)) {
			return "PRODUCT_CODE";
		} else if (("issuStart").equals(value)) {
			return "ISSU_START";
		} else if (("issuEnd").equals(value)) {
			return "ISSU_END";
		} else if (("valueDate").equals(value)) {
			return "VALUE_DATE";
		} else if (("matuDate").equals(value)) {
			return "MATU_DATE";
		} else if (("vluTerm").equals(value)) {
			return "VLU_TERM";
		} else if (("pftType").equals(value)) {
			return "PFT_TYPE";
		}
		return null;
	}
}
