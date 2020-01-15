package com.sgcc.util;

import com.google.common.base.Strings;

public class StreetUtil {
    public static String subStreet(String street){
        if (Strings.isNullOrEmpty(street)){
            street="";
        }
        if(street.contains("四川省")){
            street=street.replaceAll("四川省","");
        }
        if(street.contains("成都市")){
            street=street.replaceAll("成都市","");
        }
        if(street.contains("武侯区")){
            street=street.replaceAll("武侯区","");
        }
        if(street.contains("锦江区")){
            street=street.replaceAll("锦江区","");
        }
        if(street.contains("青羊区")){
            street=street.replaceAll("青羊区","");
        }
        if(street.contains("金牛区")){
            street=street.replaceAll("金牛区","");
        }
        if(street.contains("成华区")){
            street=street.replaceAll("成华区","");
        }
        if(street.contains("龙泉驿区")){
            street=street.replaceAll("龙泉驿区","");
        }
        if(street.contains("温江区")){
            street=street.replaceAll("温江区","");
        }
        if(street.contains("新都区")){
            street=street.replaceAll("新都区","");
        }
        if(street.contains("青白江区")){
            street=street.replaceAll("青白江区","");
        }
        if(street.contains("双流区")){
            street=street.replaceAll("双流区","");
        }
        if(street.contains("郫都区")){
            street=street.replaceAll("郫都区","");
        }
        if(street.contains("蒲江县")){
            street=street.replaceAll("蒲江县","");
        }
        if(street.contains("大邑县")){
            street=street.replaceAll("大邑县","");
        }
        if(street.contains("金堂县")){
            street=street.replaceAll("金堂县","");
        }
        if(street.contains("新津县")){
            street=street.replaceAll("新津县","");
        }
        if(street.contains("都江堰市")){
            street=street.replaceAll("都江堰市","");
        }
        if(street.contains("彭州市")){
            street=street.replaceAll("彭州市","");
        }
        if(street.contains("邛崃市")){
            street=street.replaceAll("邛崃市","");
        }
        if(street.contains("崇州市")){
            street=street.replaceAll("崇州市","");
        }
        if(street.contains("简阳市")){
            street=street.replaceAll("简阳市","");
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
        if(street.contains("小区")){
            street=street.replaceAll("小区","");
        }
        if(street.contains("院")){
            street=street.replaceAll("院","");
        }
        if(street.contains(",")){
            street=street.replaceAll(",","");
        }
        if(street.contains("，")){
            street=street.replaceAll("，","");
        }
        return street;
    }
    public static String city(String street){
        if (Strings.isNullOrEmpty(street)){
            street="";
        }
        if(street.contains("武侯")){
            return "武侯";
        }
        if(street.contains("锦江")){
            return "锦江";
        }
        if(street.contains("青羊")){
            return "锦江";
        }
        if(street.contains("金牛")){
            return "金牛";
        }
        if(street.contains("成华")){
            return "成华";
        }
        if(street.contains("龙泉驿")){
            return "龙泉驿";
        }
        if(street.contains("温江")){
            return "温江";
        }
        if(street.contains("新都")){
            return "新都";
        }
        if(street.contains("青白江")){
            return "青白江";
        }
        if(street.contains("双流")){
            return "双流";
        }
        if(street.contains("郫都")){
            return "郫都";
        }
        if(street.contains("蒲江")){
            return "蒲江";
        }
        if(street.contains("大邑")){
            return "大邑";
        }
        if(street.contains("金堂")){
            return "金堂";
        }
        if(street.contains("新津")){
            return "新津";
        }
        if(street.contains("都江堰")){
            return "都江堰";
        }
        if(street.contains("彭州")){
            return "彭州";
        }
        if(street.contains("邛崃")){
            return "邛崃";
        }
        if(street.contains("崇州")){
            return "崇州";
        }
        if(street.contains("简阳")){
            return "简阳";
        }
        else {
            return "";
        }
    }
}
