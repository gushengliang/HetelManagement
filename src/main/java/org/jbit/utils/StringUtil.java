package org.jbit.utils;

public class StringUtil {
	/**
	 * 字符串转换成整数
	 * @param str
	 * @return
	 */
	public static Integer toInt(String str){
		Integer val=null;
		if(str!=null){
			val=Integer.parseInt(str);
		}
		return val;
	}
	/**
	 * 字符串转换成小数
	 * @param str
	 * @return
	 */
	public static Double toDouble(String str){
		Double val=null;
		if(str!=null){
			val=Double.parseDouble(str);
		}
		return val;
	}
	/**
	 * 字符串转换成null
	 * @param str
	 * @return
	 */
	public static String toNull(String str){
		if(str!=null && str.trim().length()==0){
			return null;
		}
		return str;
	}
	/**
	 * 字符串替换成*
	 * @param str
	 * @return
	 */
	public static String toStar(String str){
		String[] chr={"武汉","肺炎","他妈的","日你","我操","毛泽东","周恩来","邓小平","江泽民","胡锦涛","习近平","主席","总理","共产党"};
		if(str!=null){
			for(String s:chr){
				str=str.replaceAll(s, "*");
			}
		}
		return str;
	}

}
