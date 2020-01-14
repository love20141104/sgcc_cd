package com.sgcc.util;

import com.google.common.base.Strings;

public class StreetUtil {
    public static String subStreet(String street){
        if (Strings.isNullOrEmpty(street)){
            street="";
        }
        if(street.contains("社区")){
            street=street.replaceAll("社区","");
        }
        if(street.contains("街道")){
            street=street.replaceAll("街道","");
        }
        if(street.contains("村")){
            street=street.replaceAll("村","");
        }
        if(street.contains("办事处")){
            street=street.replaceAll("办事处","");
        }
        if(street.contains("街办")){
            street=street.replaceAll("街办","");
        }
        if(street.contains("公寓")){
            street=street.replaceAll("公寓","");
        }
        if(street.contains("路")){
            street=street.replaceAll("路","");
        }
        if(street.contains("段")){
            street=street.replaceAll("段","");
        }
        if(street.contains("号")){
            street=street.replaceAll("号","");
        }
        if(street.contains("附")){
            street=street.replaceAll("附","");
        }
        if(street.contains("东")){
            street=street.replaceAll("东","");
        }
        if(street.contains("南")){
            street=street.replaceAll("南","");
        }
        if(street.contains("西")){
            street=street.replaceAll("西","");
        }
        if(street.contains("北")){
            street=street.replaceAll("北","");
        }
        if(street.contains("1")){
            street=street.replaceAll("1","");
        }
        if(street.contains("2")){
            street=street.replaceAll("2","");
        }
        if(street.contains("3")){
            street=street.replaceAll("3","");
        }
        if(street.contains("4")){
            street=street.replaceAll("4","");
        }
        if(street.contains("5")){
            street=street.replaceAll("5","");
        }
        if(street.contains("6")){
            street=street.replaceAll("6","");
        }
        if(street.contains("7")){
            street=street.replaceAll("7","");
        }
        if(street.contains("8")){
            street=street.replaceAll("8","");
        }
        if(street.contains("9")){
            street=street.replaceAll("9","");
        }
        if(street.contains("0")){
            street=street.replaceAll("0","");
        }
        if(street.contains("一")){
            street=street.replaceAll("一","");
        }
        if(street.contains("二")){
            street=street.replaceAll("二","");
        }
        if(street.contains("三")){
            street=street.replaceAll("三","");
        }
        if(street.contains("四")){
            street=street.replaceAll("四","");
        }
        if(street.contains("五")){
            street=street.replaceAll("五","");
        }
        if(street.contains("六")){
            street=street.replaceAll("六","");
        }
        if(street.contains("七")){
            street=street.replaceAll("七","");
        }
        if(street.contains("八")){
            street=street.replaceAll("八","");
        }
        if(street.contains("九")){
            street=street.replaceAll("九","");
        }
        if(street.contains("-")){
            street=street.replaceAll("-","");
        }
        if(street.contains("层")){
            street=street.replaceAll("层","");
        }
        if(street.contains("广场")){
            street=street.replaceAll("广场","");
        }






        return street;
    }
}
